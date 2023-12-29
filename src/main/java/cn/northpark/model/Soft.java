package cn.northpark.model;

import org.apache.ibatis.type.Alias;

@Alias("cm_link")
public class Soft {
    private Integer id;

    private String retCode;

    private String title;

    private String contentMinio;

    private String year;

    private String month;

    private String postDate;

    private String os;

    private String tags;

    private String tagsCode;

    private String retUrl;

    private String color;

    private Integer hotIndex;

    private String displayed;

    private String useMinio;

    private String brief;

    private String content;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode == null ? null : retCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentMinio() {
        return contentMinio;
    }

    public void setContentMinio(String contentMinio) {
        this.contentMinio = contentMinio == null ? null : contentMinio.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate == null ? null : postDate.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getTagsCode() {
        return tagsCode;
    }

    public void setTagsCode(String tagsCode) {
        this.tagsCode = tagsCode == null ? null : tagsCode.trim();
    }

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl == null ? null : retUrl.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
    }

    public String getDisplayed() {
        return displayed;
    }

    public void setDisplayed(String displayed) {
        this.displayed = displayed == null ? null : displayed.trim();
    }

    public String getUseMinio() {
        return useMinio;
    }

    public void setUseMinio(String useMinio) {
        this.useMinio = useMinio == null ? null : useMinio.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}