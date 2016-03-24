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
@Table(name = "bc_user_lyrics")
public class UserLyrics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3554243167217151099L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private Integer userid;

	@Column(length = 32)
	private Integer lyricsid;


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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLyricsid() {
		return lyricsid;
	}

	public void setLyricsid(Integer lyricsid) {
		this.lyricsid = lyricsid;
	}
}