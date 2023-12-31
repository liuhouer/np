package cn.northpark.model;

public class LyricsComment {
    private Integer id;

    private String comment;

    private String createTime;

    private Integer userid;

    private Integer lyricsid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getLyricsid() {
        return lyricsid;
    }

    public void setLyricsid(Integer lyricsid) {
        this.lyricsid = lyricsid;
    }
}