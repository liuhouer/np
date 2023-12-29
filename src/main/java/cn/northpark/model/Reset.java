package cn.northpark.model;

public class Reset {
    private Integer id;

    private Integer userId;

    private String authCode;

    private String invalidTime;

    private Integer isEmailAuthed;

    private String createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime == null ? null : invalidTime.trim();
    }

    public Integer getIsEmailAuthed() {
        return isEmailAuthed;
    }

    public void setIsEmailAuthed(Integer isEmailAuthed) {
        this.isEmailAuthed = isEmailAuthed;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime == null ? null : createdTime.trim();
    }
}