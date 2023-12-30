package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemindB;
import cn.northpark.notify.GeneralNotify;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月02日 14:08:21
 *  4类：xx关注了yy，【通知-被关注者yy】
 */
public class FollowHandler extends GeneralNotify {

    @Override
    public void build(NotifyRemindB param) {
        param.setRemindId(4);
        param.setSenderAction("3");
        param.setObjectType("1");
        param.setCreatedAt(new Date());
    }


}
