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
@Table(name = "bc_lyrics")
public class Lyrics implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String type;

	@Column(length = 255)
	private String title;

	@Column(length = 255)
	private String artist;

	@Column(length = 255)
	private String album;

	@Column(length = 32)
	private String medialength;

	@Column(length = 32)
	private String rating;

	@Column(length = 11)
	private Integer downloads;

	@Column(length = 255)
	private String updatedate;

	@Column(length = 255)
	private String path;

	@Column(length = 255)
	private String albumImg;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;	
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;	
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;	
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getMedialength() {
		return medialength;	
	}
	
	public void setMedialength(String medialength) {
		this.medialength = medialength;
	}
	public String getRating() {
		return rating;	
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Integer getDownloads() {
		return downloads;	
	}
	
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	public String getUpdatedate() {
		return updatedate;	
	}
	
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getAlbumImg() {
		return albumImg;	
	}
	
	public void setAlbumImg(String albumImg) {
		this.albumImg = albumImg;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}