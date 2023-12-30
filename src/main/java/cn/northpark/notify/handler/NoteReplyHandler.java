package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemindB;
import cn.northpark.notify.GeneralNotify;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月02日 14:08:21
 * 3类：树洞界面的留言被回复【通知-留言创建者】
 */
public class NoteReplyHandler extends GeneralNotify {

    @Override
    public void build(NotifyRemindB param) {
        param.setRemindId(3);
        param.setSenderAction("1");
        param.setObjectType("1");
        param.setCreatedAt(new Date());
    }

}
