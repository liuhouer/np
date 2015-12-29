package com.bruce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_userprofile")
public class Userprofile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1132876192910997061L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String user_id;

	@Column(length = 255)
	private String name;

	@Column(length = 32)
	private String nickname;

	@Column(length = 255)
	private String language;

	@Column(length = 255)
	private String location;

	@Column(length = 255)
	private String meta;

	@Column(length = 255)
	private String courseware;

	@Column(length = 6)
	private String gender;

	@Column(length = 16)
	private String tel;

	@Column(length = 16)
	private String mailing_address;

	@Column(length = 255)
	private String year_of_birth;

	@Column(length = 6)
	private String level_of_education;

	@Column(length = 6)
	private String goals;

	@Column(length = 1)
	private Integer allow_certificate;

	@Column(length = 2)
	private String country;

	@Column(length = 2)
	private String city;

	@Column(length = 1)
	private Integer is_del;

	@Column(length = 255)
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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}