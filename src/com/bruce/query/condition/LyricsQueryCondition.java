/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class LyricsQueryCondition implements Serializable {


	private String id;
  

	private String type;
  

	private String artist;
  

	private String album;
  

	private String medialength;
  

	private String rating;
  

	private Integer downloads;
  

	private String updatedate;
  



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

}