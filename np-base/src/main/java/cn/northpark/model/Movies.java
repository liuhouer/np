package cn.northpark.model;

import java.util.List;
import java.util.Map;

public class Movies {
    private Integer id;

    private String retCode;

    private String movieName;

    private String movieDescMinio;

    private Integer price;

    private String addTime;

    private String tag;

    private String tagCode;

    private Integer viewNum;

    private String color;

    private Integer hotIndex;

    private String displayed;

    private String useMinio;

    private String brief;

    private String movieDesc;

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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    public String getMovieDescMinio() {
        return movieDescMinio;
    }

    public void setMovieDescMinio(String movieDescMinio) {
        this.movieDescMinio = movieDescMinio == null ? null : movieDescMinio.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode == null ? null : tagCode.trim();
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
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

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc == null ? null : movieDesc.trim();
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