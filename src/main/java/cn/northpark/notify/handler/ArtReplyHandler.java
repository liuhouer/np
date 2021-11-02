package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.GeneralNotify;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月02日 14:08:21
 * 1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
 */
public class ArtReplyHandler extends GeneralNotify {


    @Override
    public void build(NotifyRemind param) {
        param.setRemindID(1);
        param.setSenderAction("1");
        param.setObjectType("2");
        param.setCreatedAt(new Date());
    }

}
