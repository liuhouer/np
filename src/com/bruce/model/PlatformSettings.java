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
@Table(name = "bc_platform_settings")
public class PlatformSettings implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 255)
	private String schoolName;

	@Column(length = 255)
	private String platformName;

	@Column(length = 255)
	private String createtime;

	@Column(length = 255)
	private String fzr;

	@Column(length = 255)
	private String telephone;

	@Column(length = 11)
	private String mobilephone;

	@Column(length = 255)
	private String videoUrl;

	@Column(length = 255)
	private String schoolLogo;

	@Column(length = 4)
	private Integer scoreLimit;

	@Column(length = 4)
	private Integer courseLimit;

	@Column(length = 4)
	private Integer infoLimit;

	@Column(length = 1)
	private Integer isDel;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getSchool_name() {
		return schoolName;	
	}
	
	public void setSchool_name(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getPlatform_name() {
		return platformName;	
	}
	
	public void setPlatform_name(String platformName) {
		this.platformName = platformName;
	}
	public String getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getFzr() {
		return fzr;	
	}
	
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getTelephone() {
		return telephone;	
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilephone() {
		return mobilephone;	
	}
	
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getVideo_url() {
		return videoUrl;	
	}
	
	public void setVideo_url(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getSchool_logo() {
		return schoolLogo;	
	}
	
	public void setSchool_logo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}
	public Integer getScore_limit() {
		return scoreLimit;	
	}
	
	public void setScore_limit(Integer scoreLimit) {
		this.scoreLimit = scoreLimit;
	}
	public Integer getCourse_limit() {
		return courseLimit;	
	}
	
	public void setCourse_limit(Integer courseLimit) {
		this.courseLimit = courseLimit;
	}
	public Integer getInfo_limit() {
		return infoLimit;	
	}
	
	public void setInfo_limit(Integer infoLimit) {
		this.infoLimit = infoLimit;
	}
	public Integer getIs_del() {
		return isDel;	
	}
	
	public void setIs_del(Integer isDel) {
		this.isDel = isDel;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}