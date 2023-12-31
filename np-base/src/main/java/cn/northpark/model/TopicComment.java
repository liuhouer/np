package cn.northpark.model;

public class TopicComment {
    private Integer id;

    private Integer topicId;

    private String topicType;

    private String content;

    private Integer fromUid;

    private String fromSpan;

    private Integer toUid;

    private String fromUname;

    private String toUname;

    private String addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromSpan() {
        return fromSpan;
    }

    public void setFromSpan(String fromSpan) {
        this.fromSpan = fromSpan == null ? null : fromSpan.trim();
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getFromUname() {
        return fromUname;
    }

    public void setFromUname(String fromUname) {
        this.fromUname = fromUname == null ? null : fromUname.trim();
    }

    public String getToUname() {
        return toUname;
    }

    public void setToUname(String toUname) {
        this.toUname = toUname == null ? null : toUname.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }
}