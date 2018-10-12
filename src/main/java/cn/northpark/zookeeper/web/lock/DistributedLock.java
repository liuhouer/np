package cn.northpark.zookeeper.web.lock;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author w_zhangyang
 * 分布式锁的初始化、公共方法
 */
public class DistributedLock {


	final static Logger log = LoggerFactory.getLogger(DistributedLock.class);
	
	//分布式锁的总节点
    private static final String ZK_LOCK_PROJECT = "imooc-locks";

    //分布式锁节点
    private static final String DISTRIBUTED_LOCK = "distributed-lock";

    //起始分隔符
    private static  final String Separator = "/";

    /**
     * 用于挂起当前请求，并且的等待上一个请求释放锁
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 构造参数：命名空间
     */
    private String nameSpace;

    /**
     * 构造参数：客户端
     */
    private CuratorFramework client;

    /**
     * 构造函数设置参数
     * @param nameSpace
     * @param client
     */
    public DistributedLock(String nameSpace, CuratorFramework client) {
        this.nameSpace = nameSpace;
        this.client = client;
    }


    /**
     * 初始化
     */
    private void init (){
    	
    	/**
         * 使用命名空间
         */
        client.usingNamespace(nameSpace);
        /**
         *  创建zk 总节点
         *    namespace : ZK-locks-nameSpace
         *                    |
         *                     --  imooc-locks
         *                            |
         *                             -- distributed-lock
         */
        try{
            if(client.checkExists().forPath( Separator + ZK_LOCK_PROJECT) == null){
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(Separator + ZK_LOCK_PROJECT);
            }
            addWatcherToLock(Separator + ZK_LOCK_PROJECT);
        }catch (Exception e){
            log.error("分布式锁初始化失败！");
        }
    }

    /**
     * 获取锁，如果成功获取锁，countDownLath -1， 否则一直等待
     * 锁的实现 -- 创建子节点 DISTRIBUTED_LOCK
     */
    public void getLock(){

        /**
         * 使用死循环，当且仅当上一个锁释放并且当前请求获得锁之后才可以跳出
         */
        while (true){

            try{
                /**
                 * 如果节点可以创建，说明锁没有被占用
                 */
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(Separator + ZK_LOCK_PROJECT + Separator + DISTRIBUTED_LOCK);

                log.info("获得分布式锁成功！");
                return;
            }catch (Exception e){
                log.error("获取分布式锁锁失败");
                try{
                    /**
                     * 如果没有获得分布式锁，则需要重新设置同步资源值
                     */
                    if(getCountDownLatch().getCount() <= 0){
                        log.info("*********************");
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                }catch (Exception e1){
                    log.error("设置countDown失败！", e);
                }
            }

        }
    }


    /**
     * 分布式锁子节点添加watch事件，监听父节点子节点的移除--锁的释放
     */
    public void addWatcherToLock(String path) throws Exception{
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if(event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED){
                    String path = event.getData().getPath();
                    log.info("上一个会话已结束或已经释放锁路径：{}" ,path);
                    if(path.contains(ZK_LOCK_PROJECT)){
                        log.info("释放计数器，当前线程可获得分布式锁");
                        countDownLatch.countDown();
                    }
                }
            }
        });
    }


    /**
     * 释放锁操作
     * @return 成功过 true 失败 false
     */
    public boolean releaseLock(){

        try{
            if(client.checkExists().forPath(Separator + ZK_LOCK_PROJECT + Separator + DISTRIBUTED_LOCK) != null){
                client.delete().forPath(Separator + ZK_LOCK_PROJECT + Separator + DISTRIBUTED_LOCK);
            }
        }catch (Exception e){
            log.error("释放锁失败！", e);
            return false;
        }
        log.info("分布式锁释放成功！");
        return true;
    }



    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    
    
}
