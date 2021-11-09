package cn.northpark.notify;

import cn.northpark.dao.NotifyRemindDao;
import cn.northpark.model.NotifyRemind;
import cn.northpark.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bruce
 * @date 2021年11月2日
 * 通知场景抽象处理类
 */
@Slf4j
public abstract class GeneralNotify implements NotifyInterface{


    private transient NotifyRemindDao notifyRemindDao;

    public GeneralNotify() {
        //
        log.error("执行了通知场景抽象处理类 --构造函数");
        log.error("从上下文获取到NotifyRemindDao");
        synchronized (GeneralNotify.class){
            if(notifyRemindDao==null){
                synchronized (this){
                    notifyRemindDao = (NotifyRemindDao) SpringContextUtils.getBean("NotifyRemindDao");
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
    public void addNotify(NotifyRemind param) {
        notifyRemindDao.save(param);
    }

    //定义模板--模板模式【规定方法的执行顺序】
    public final void execute(NotifyRemind param){
        build(param);
        addNotify(param);
    }





}
