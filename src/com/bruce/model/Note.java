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
@Table(name = "bc_note")
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8794166567111470371L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 255)	
	private String id;
	
	@Column()
	private String brief;

	@Column()
	private String note;

	@Column(length = 255)
	private String opened;

	@Column(length = 255)
	private String createtime;

	@Column(length = 32)
	private String userid;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
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
}