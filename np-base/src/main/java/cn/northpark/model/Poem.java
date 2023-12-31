package cn.northpark.model;

public class Poem {
    private Integer id;

    private String retCode;

    private String retUrl;

    private String title;

    private String years;

    private String yearsCode;

    private String types;

    private String typesCode;

    private String author;

    private String picPoem;

    private String createTime;

    private String content;

    private String content1;

    private String enjoys;

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

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl == null ? null : retUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getYearsCode() {
        return yearsCode;
    }

    public void setYearsCode(String yearsCode) {
        this.yearsCode = yearsCode == null ? null : yearsCode.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getTypesCode() {
        return typesCode;
    }

    public void setTypesCode(String typesCode) {
        this.typesCode = typesCode == null ? null : typesCode.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPicPoem() {
        return picPoem;
    }

    public void setPicPoem(String picPoem) {
        this.picPoem = picPoem == null ? null : picPoem.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1 == null ? null : content1.trim();
    }

    public String getEnjoys() {
        return enjoys;
    }

    public void setEnjoys(String enjoys) {
        this.enjoys = enjoys == null ? null : enjoys.trim();
    }
}