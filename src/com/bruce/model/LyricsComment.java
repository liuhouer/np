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
@Table(name = "bc_lyrics_comment")
public class LyricsComment implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String lyricsid;

	@Column(length = 32)
	private String userid;

	@Column(length = 2000)
	private String comment;

	@Column(length = 200)
	private String create_time;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getLyricsid() {
		return lyricsid;	
	}
	
	public void setLyricsid(String lyricsid) {
		this.lyricsid = lyricsid;
	}
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;	
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreate_time() {
		return create_time;	
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}