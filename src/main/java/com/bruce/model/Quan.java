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
@Table(name = "in_quan")
public class Quan implements Serializable{

/**
 *
 * serialVersionUID
 */
private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native") 
	@Column(length = 6)	
	private Integer id;

	@Column(length = 1000)
	private String path;

	@Column(length = 255)
	private String fromwhere;

	@Column(length = 255)
	private String publistime;

	@Column(length = 255)
	private String authorIP;

	@Column(length = 1000)
	private String title;

	@Column(length = 1000)
	private String path_mt;

	@Column(length = 255)
	private String addtime;


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
	public String getPath_mt() {
		return path_mt;	
	}
	
	public void setPath_mt(String path_mt) {
		this.path_mt = path_mt;
	}
	public String getAddtime() {
		return addtime;	
	}
	
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getFromwhere() {
		return fromwhere;
	}

	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
	}
	
}