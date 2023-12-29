package cn.northpark.notify;

import cn.northpark.model.NotifyRemind;
import cn.northpark.service.NotifyRemindService;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author bruce
 * @date 2021年11月2日
 * 通知场景抽象处理类
 */
@Slf4j
public abstract class GeneralNotify implements NotifyInterface{


    private transient NotifyRemindService notifyRemindManager;

    public GeneralNotify() {
        //
        log.error("执行了通知场景抽象处理类 --构造函数");
        log.error("从上下文获取到notifyRemindManager");
        synchronized (GeneralNotify.class){
            if(notifyRemindManager ==null){
                synchronized (this){
                    notifyRemindManager = (NotifyRemindService) SpringContextUtils.getBean("NotifyRemindManager");
                }
            }
        }
    }


    //定义构建参数方法
    public abstract void build(NotifyRemind param);

    /**
     * 因为这个保存通知的方法是通用的，子类没必要再写一遍。直接实现在这里
     * @param param
     */
    @Override
    @Deprecated
    public void addNotify(NotifyRemind param) {
        notifyRemindManager.addNotifyRemind(param);
    }

    //定义模板--模板模式【规定方法的执行顺序】
    public final void execute(NotifyRemind param){
        build(param);
        addNotify(param);
    }


    /**
     * 异步通知
     * @param param
     */
    @Override
    public void startSync(final NotifyRemind param) {
        ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    notifyRemindManager.addNotifyRemind(param);
                }catch (Exception ig){
                    log.error("northpark异步通知异常---->",ig);
                }

            }

        });
    }
}
