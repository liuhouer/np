/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class QuanQueryCondition implements Serializable {


	private Integer id;
  

	private String path;
  

	private String from;
  

	private String publistime;
  

	private String authorIP;
  

	private String title;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getFrom() {
		return from;	
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPublistime() {
		return publistime;	
	}
	
	public void setPublistime(String publistime) {
		this.publistime = publistime;
	}
	public String getAuthorIP() {
		return authorIP;	
	}
	
	public void setAuthorIP(String authorIP) {
		this.authorIP = authorIP;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}