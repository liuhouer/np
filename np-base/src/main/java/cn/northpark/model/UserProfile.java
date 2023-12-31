package cn.northpark.model;

public class UserProfile {
    private Integer id;

    private Integer userId;

    private String name;

    private String nickname;

    private String language;

    private String location;

    private String meta;

    private String courseWare;

    private String gender;

    private String tel;

    private String mailingAddress;

    private String yearOfBirth;

    private String levelOfEducation;

    private String goals;

    private Boolean allowCertificate;

    private String country;

    private String city;

    private Boolean isDel;

    private String userSlug;

    private String courseware;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta == null ? null : meta.trim();
    }

    public String getCourseWare() {
        return courseWare;
    }

    public void setCourseWare(String courseWare) {
        this.courseWare = courseWare == null ? null : courseWare.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress == null ? null : mailingAddress.trim();
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth == null ? null : yearOfBirth.trim();
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation == null ? null : levelOfEducation.trim();
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals == null ? null : goals.trim();
    }

    public Boolean getAllowCertificate() {
        return allowCertificate;
    }

    public void setAllowCertificate(Boolean allowCertificate) {
        this.allowCertificate = allowCertificate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getUserSlug() {
        return userSlug;
    }

    public void setUserSlug(String userSlug) {
        this.userSlug = userSlug == null ? null : userSlug.trim();
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware == null ? null : courseware.trim();
    }
}