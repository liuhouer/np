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
@Table(name = "bc_lyrics")
public class Lyrics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352450415620898579L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(length = 6)	
	private Integer id;

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
	
	@Column(length = 11)
	private Integer zan;

	@Column(length = 11)
	private Integer pl;
	


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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public Integer getPl() {
		return pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

}