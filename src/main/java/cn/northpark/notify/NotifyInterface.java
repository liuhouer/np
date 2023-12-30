package cn.northpark.notify;

import cn.northpark.model.NotifyRemindB;

/**
 * @author bruce
 * @date 2021年11月04日 14:47:35
 */
public interface NotifyInterface {

    @Deprecated
    void addNotify(NotifyRemindB param);

    /**
     * 异步通知
     * @param param
     */
    void startSync(final NotifyRemindB param) ;
}
