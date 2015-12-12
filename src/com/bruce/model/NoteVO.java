package com.bruce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.bruce.utils.TimeUtils;

public class NoteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2589638127282480059L;


	private String noteid;
	
	private String brief;

	private String note;

	private String opened;

	private String createtime;

	private String userid;
	
	private String username;
	
	private String email;
	
	private String headpath;
	
	private String showtime;


	public String getNote() {
		return note;	
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	public String getOpened() {
		return opened;	
	}
	
	public void setOpened(String opened) {
		this.opened = opened;
	}
	public String getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getNoteid() {
		return noteid;
	}

	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadpath() {
		
		String imgpath = headpath; //e:/yunlu/upload/1399976848969.jpg
		String imgp = "";
		if(!StringUtils.isEmpty(imgpath)){
			String[] str = imgpath.split("/heads/");
			if(str.length>1){
			  imgp = "heads/"+str[1];
			}
		}
		return imgp;
	}

	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}

	public String getShowtime() {
		return TimeUtils.getHalfDate(this.createtime);
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
}