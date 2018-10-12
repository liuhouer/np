package cn.northpark.zookeeper.web.lock;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author w_zhangyang
 *	zk客户端配置
 */
public class ZKCurator {
	
	final static Logger logger = LoggerFactory.getLogger(ZKCurator.class);

    private CuratorFramework client = null;

    public ZKCurator(CuratorFramework client) {
        this.client = client;
    }
    
    //可以扩展一些方法来方便使用
    /**
     * 初始化
     */
    public void init() {
    	//可以用构造函数来传参设值  亦可以直接填写
    	client.usingNamespace("np-zk-curator-client");
    }
    
    /**
     * @desc :判断zk的状态：是否运行
     */
    public boolean isZKAlive() {
    	return client.isStarted();
    }
    
}
