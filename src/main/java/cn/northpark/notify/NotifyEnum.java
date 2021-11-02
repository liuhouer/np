package cn.northpark.notify;


import cn.northpark.notify.handler.ArtReplyHandler;
import cn.northpark.notify.handler.FollowHandler;
import cn.northpark.notify.handler.LoveZanHandler;
import cn.northpark.notify.handler.NoteReplyHandler;
import lombok.Getter;

/**
 * @author bruce
 * @date 2021年11月2日
 * 通知提醒功能设计
 * 1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
 * 2类：最爱图册被点赞通知【通知-图册创建者】
 * 3类：树洞界面的留言被回复【通知-留言创建者】
 * 4类：xx关注了yy，【通知-被关注者yy】
 */
public enum NotifyEnum {

    /**
     * 1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
     */
    ART_REPLY("ART_REPLY",new ArtReplyHandler()),

    /**
     * 2类：最爱图册被点赞通知【通知-图册创建者】
     */
    LOVE_ZAN("LOVE_ZAN",new LoveZanHandler()),


    /**
     * 3类：树洞界面的留言被回复【通知-留言创建者】
     */
    NOTE_REPLY("NOTE_REPLY",new NoteReplyHandler()),

    /**
     * 4类：xx关注了yy，【通知-被关注者yy】
     */
    FOLLOW("FOLLOW",new FollowHandler()),


    ;


    @Getter
    public String name;

    @Getter
    public GeneralNotify notifyInstance;

    NotifyEnum(String name, GeneralNotify notifyInstance) {
        this.name = name;
        this.notifyInstance = notifyInstance;
    }

    //匹配
    public static NotifyEnum match(String name){
        NotifyEnum[] values = NotifyEnum.values();
        for (NotifyEnum value : values) {
            if(value.name.equals(name)){
                return value;
            }
        }
        return null;
    }

}
