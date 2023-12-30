package cn.northpark.model;

import lombok.Data;

import java.util.Date;

@Data
public class NotifyRemindB {

    private Integer id;

    /**
     * 通知提醒类型编号
     *  1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
     *  2类：最爱图册被点赞通知【通知-图册创建者】
     *  3类：树洞界面的留言被回复【通知-留言创建者】
     *  4类：xx关注了yy，【通知-被关注者yy】
     */
    private Integer remindId;

    /**
     * 操作者的ID，三个0代表是系统发送的
     */
    private String senderId;



    /**
     * 操作者用户名
     */
    private String senderName;

    /**
     * 操作者的动作，如：捐款、更新、评论、收藏
     * 【1：评论,2：收藏（爱上），3：关注，5：站内通知】
     */
    private String senderAction;

    /**
     * 目标对象ID
     */
    private String objectId;

    /**
     * 目标对象内容或简介，比如：文章标题
     */
    private String object;

    /**
     * 被操作对象类型，如：人、文章、活动、视频等[1：人，2：文章，3：推送]
     */
    private String objectType;

    /**
     * 关联资源链接
     */
    private String objectLinks;

    /**
     * 消息接收者-可能是对象的所有者或订阅者【目前仅设置为人的通知，订阅先不做，因为此字段为消息接收者的userid】
     */
    private String recipientId;

    /**
     * 消息内容，由提醒模版生成，需要提前定义
     */
    private String message;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 是否阅读，默认未读，【0：未读，1：已读】
     */
    private String status;

    /**
     * 阅读时间
     */
    private Date readAt;

    /**
     * 格式化-创建时间
     */
    private String createTime;




}