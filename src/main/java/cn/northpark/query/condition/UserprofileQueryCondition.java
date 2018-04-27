/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

public class UserprofileQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 4122965977872844272L;


    private String id;


    private String user_id;


    private String name;


    private String nickname;


    private String language;


    private String location;


    private String meta;


    private String courseware;


    private String gender;


    private String tel;


    private String mailing_address;


    private String year_of_birth;


    private String level_of_education;


    private String goals;


    private Integer allow_certificate;


    private String country;


    private String city;


    private Integer is_del;


    private String user_slug;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public String getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(String year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getLevel_of_education() {
        return level_of_education;
    }

    public void setLevel_of_education(String level_of_education) {
        this.level_of_education = level_of_education;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public Integer getAllow_certificate() {
        return allow_certificate;
    }

    public void setAllow_certificate(Integer allow_certificate) {
        this.allow_certificate = allow_certificate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public String getUser_slug() {
        return user_slug;
    }

    public void setUser_slug(String user_slug) {
        this.user_slug = user_slug;
    }

}