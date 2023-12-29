package cn.northpark.model;

import lombok.Data;

import java.util.Date;

public class NotifyRemind {
    private Integer id;

    private Integer remindid;

    private String senderid;

    private String sendername;

    private String senderaction;

    private String objectid;

    private String objecttype;

    private String objectlinks;

    private String recipientid;

    private Date createdat;

    private Date readat;




    /**
     * 通知提醒类型编号
     *  1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
     *  2类：最爱图册被点赞通知【通知-图册创建者】
     *  3类：树洞界面的留言被回复【通知-留言创建者】
     *  4类：xx关注了yy，【通知-被关注者yy】
     */
    private Integer remindID;

    /**
     * 操作者的ID，三个0代表是系统发送的
     */
    private String senderID;




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
    private String objectID;

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
    private String recipientID;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRemindid() {
        return remindid;
    }

    public void setRemindid(Integer remindid) {
        this.remindid = remindid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSenderaction() {
        return senderaction;
    }

    public void setSenderaction(String senderaction) {
        this.senderaction = senderaction;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getObjectlinks() {
        return objectlinks;
    }

    public void setObjectlinks(String objectlinks) {
        this.objectlinks = objectlinks;
    }

    public String getRecipientid() {
        return recipientid;
    }

    public void setRecipientid(String recipientid) {
        this.recipientid = recipientid;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getReadat() {
        return readat;
    }

    public void setReadat(Date readat) {
        this.readat = readat;
    }

    public Integer getRemindID() {
        return remindID;
    }

    public void setRemindID(Integer remindID) {
        this.remindID = remindID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAction() {
        return senderAction;
    }

    public void setSenderAction(String senderAction) {
        this.senderAction = senderAction;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectLinks() {
        return objectLinks;
    }

    public void setObjectLinks(String objectLinks) {
        this.objectLinks = objectLinks;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}