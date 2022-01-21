package cn.northpark.notify;

import cn.northpark.model.NotifyRemind;

/**
 * @author bruce
 * @date 2021年11月04日 14:47:35
 */
public interface NotifyInterface {

    @Deprecated
    void addNotify(NotifyRemind param);

    /**
     * 异步通知
     * @param param
     */
    void startSync(final NotifyRemind param) ;
}
