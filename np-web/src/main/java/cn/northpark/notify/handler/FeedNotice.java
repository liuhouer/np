package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.GeneralNotify;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月10日 09:12:33
 * 站内通知
 * 6类：站内通知：xx用户反馈，yy资源，已失效
 * 6类：站内通知： yy资源已更新，请知悉
 */
public class FeedNotice extends GeneralNotify {

    @Override
    public void build(NotifyRemind param) {
        param.setRemindID(6);
        param.setSenderID("000");//系统发送
        param.setSenderName("站内通知");
        param.setSenderAction("5");//站内通知
        param.setObjectType("2");//文章
        param.setCreatedAt(new Date());
    }

}
