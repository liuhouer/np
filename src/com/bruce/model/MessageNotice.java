package com.bruce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_message_notice")
public class MessageNotice implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 255)
	private String title;

	@Column(length = 255)
	private String userid;

	@Column(length = 255)
	private Date sendtime;

	@Column(length = 4000)
	private String message;

	@Column(length = 200)
	private String url;

	@Column(length = 1)
	private Integer isViewed;

	@Column(length = 1)
	private Integer isEmailed;

	@Column(length = 1)
	private Integer type;

	@Column(length = 1)
	private Integer isDel;


	public String getID() {
		return id;	
	}
	
	public void setID(String id) {
		this.id = id;
	}
	public String getTITLE() {
		return title;	
	}
	
	public void setTITLE(String title) {
		this.title = title;
	}
	public String getUSERID() {
		return userid;	
	}
	
	public void setUSERID(String userid) {
		this.userid = userid;
	}
	public Date getSENDTIME() {
		return sendtime;	
	}
	
	public void setSENDTIME(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getMESSAGE() {
		return message;	
	}
	
	public void setMESSAGE(String message) {
		this.message = message;
	}
	public String getURL() {
		return url;	
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	public Integer getIS_VIEWED() {
		return isViewed;	
	}
	
	public void setIS_VIEWED(Integer isViewed) {
		this.isViewed = isViewed;
	}
	public Integer getIS_EMAILED() {
		return isEmailed;	
	}
	
	public void setIS_EMAILED(Integer isEmailed) {
		this.isEmailed = isEmailed;
	}
	public Integer getTYPE() {
		return type;	
	}
	
	public void setTYPE(Integer type) {
		this.type = type;
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