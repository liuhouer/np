package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.GeneralNotify;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月02日 14:08:21
 *  2类：最爱图册被点赞通知【通知-图册创建者】
 */
public class LoveZanHandler extends GeneralNotify {


    @Override
    public void build(NotifyRemind param) {
        param.setRemindID(2);
        param.setSenderAction("2");
        param.setObjectType("2");
        param.setCreatedAt(new Date());
    }


}
