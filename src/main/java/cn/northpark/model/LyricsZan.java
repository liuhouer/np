package cn.northpark.model;

public class LyricsZan {
    private Integer id;

    private Integer userid;

    private Integer lyricsid;

    private String loveYear;

    private String loveDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLoveYear() {
        return loveYear;
    }

    public void setLoveYear(String loveYear) {
        this.loveYear = loveYear == null ? null : loveYear.trim();
    }

    public String getLoveDate() {
        return loveDate;
    }

    public void setLoveDate(String loveDate) {
        this.loveDate = loveDate == null ? null : loveDate.trim();
    }
}