package cn.northpark.notify.handler;

import cn.northpark.model.NotifyRemind;
import cn.northpark.notify.GeneralNotify;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author bruce
 * @date 2021年11月10日 09:12:33
 * 站长通知
 * 5类1：xx用户注册了
 * 5类2：xx用户yy时间登录|自动登录了
 * 5类3：xx用户反馈： yy资源已失效
 */
public class WebmasterNotice  extends GeneralNotify {

    @Override
    public void build(NotifyRemind param) {
        param.setRemindID(5);
        param.setSenderID("000");//系统发送
        if(StringUtils.isEmpty(param.getSenderName())){
            param.setSenderName("站内通知");
        }
        param.setSenderAction("5");//站内通知
        param.setObjectType("3");//推送
        param.setCreatedAt(new Date());
        param.setRecipientID("507723");//站长ID
    }

}
