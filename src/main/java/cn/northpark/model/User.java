package cn.northpark.model;

public class User {
    private Integer id;

    private String username;

    private String email;

    private String emailFlag;

    private String tailSlug;

    private String password;

    private String headSpanClass;

    private String headSpan;

    private String headPath;

    private String meta;

    private String blogSite;

    private String dateJoined;

    private String lastLogin;

    private String qqOpenid;

    private String qqInfo;

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmailFlag() {
        return emailFlag;
    }

    public void setEmailFlag(String emailFlag) {
        this.emailFlag = emailFlag == null ? null : emailFlag.trim();
    }

    public String getTailSlug() {
        return tailSlug;
    }

    public void setTailSlug(String tailSlug) {
        this.tailSlug = tailSlug == null ? null : tailSlug.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHeadSpanClass() {
        return headSpanClass;
    }

    public void setHeadSpanClass(String headSpanClass) {
        this.headSpanClass = headSpanClass == null ? null : headSpanClass.trim();
    }

    public String getHeadSpan() {
        return headSpan;
    }

    public void setHeadSpan(String headSpan) {
        this.headSpan = headSpan == null ? null : headSpan.trim();
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath == null ? null : headPath.trim();
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta == null ? null : meta.trim();
    }

    public String getBlogSite() {
        return blogSite;
    }

    public void setBlogSite(String blogSite) {
        this.blogSite = blogSite == null ? null : blogSite.trim();
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined == null ? null : dateJoined.trim();
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin == null ? null : lastLogin.trim();
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid == null ? null : qqOpenid.trim();
    }

    public String getQqInfo() {
        return qqInfo;
    }

    public void setQqInfo(String qqInfo) {
        this.qqInfo = qqInfo == null ? null : qqInfo.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}