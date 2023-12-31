package cn.northpark.model;

import java.util.List;
import java.util.Map;

public class Knowledge {
    private Integer id;

    private String retCode;

    private String title;

    private String briefImg;

    private String brief;

    private String postDate;

    private Integer price;

    private String tags;

    private String tagsCode;

    private String retUrl;

    private String linkUrl;

    private Long viewTimes;

    private String color;

    private Integer hotIndex;

    private String displayed;

    private String content;

    private String path;

    private List<Map<String, String>> tag_list;

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

    public String getBriefImg() {
        return briefImg;
    }

    public void setBriefImg(String briefImg) {
        this.briefImg = briefImg == null ? null : briefImg.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate == null ? null : postDate.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public Long getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Long viewTimes) {
        this.viewTimes = viewTimes;
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

    public List<Map<String, String>> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<Map<String, String>> tag_list) {
        this.tag_list = tag_list;
    }
}