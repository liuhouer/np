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
@Table(name = "bc_lyrics_zan")
public class LyricsZan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6905370184898544851L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private Integer lyricsid;

	@Column(length = 32)
	private Integer userid;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getLyricsid() {
		return lyricsid;
	}

	public void setLyricsid(Integer lyricsid) {
		this.lyricsid = lyricsid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}