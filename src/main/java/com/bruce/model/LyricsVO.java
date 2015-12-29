package com.bruce.model;

import java.io.Serializable;

import javax.transaction.Transactional;

@Transactional
public class LyricsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339303245779220594L;


	private String id;

	private String type;

	private String title;

	private String artist;

	private String album;

	private String medialength;

	private String rating;

	private Integer downloads;

	private String updatedate;

	private String path;

	private String albumImg;
	
	
	private String userlyricsid;


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


	public String getUserlyricsid() {
		return userlyricsid;
	}

	public void setUserlyricsid(String userlyricsid) {
		this.userlyricsid = userlyricsid;
	}
}