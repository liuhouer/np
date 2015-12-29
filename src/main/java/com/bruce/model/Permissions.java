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
@Table(name = "bc_permissions")
public class Permissions implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String tag;

	@Column(length = 500)
	private String description;

	@Column(length = 256)
	private String url;

	@Column(length = 4)
	private Integer enabled;

	@Column(length = 11)
	private Integer type;

	@Column(length = 256)
	private String imgPath;

	@Column(length = 11)
	private Integer sortId;

	@Column(length = 50)
	private String code;

	@Column(length = 11)
	private Integer status;

	@Column(length = 11)
	private Integer sort;

	@Column(length = 20)
	private Integer parentId;

	@Column(length = 1)
	private Integer isDel;


	public String getID() {
		return id;	
	}
	
	public void setID(String id) {
		this.id = id;
	}
	public String getNAME() {
		return name;	
	}
	
	public void setNAME(String name) {
		this.name = name;
	}
	public String getTAG() {
		return tag;	
	}
	
	public void setTAG(String tag) {
		this.tag = tag;
	}
	public String getDESCRIPTION() {
		return description;	
	}
	
	public void setDESCRIPTION(String description) {
		this.description = description;
	}
	public String getURL() {
		return url;	
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	public Integer getENABLED() {
		return enabled;	
	}
	
	public void setENABLED(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getTYPE() {
		return type;	
	}
	
	public void setTYPE(Integer type) {
		this.type = type;
	}
	public String getIMG_PATH() {
		return imgPath;	
	}
	
	public void setIMG_PATH(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getSORT_ID() {
		return sortId;	
	}
	
	public void setSORT_ID(Integer sortId) {
		this.sortId = sortId;
	}
	public String getCODE() {
		return code;	
	}
	
	public void setCODE(String code) {
		this.code = code;
	}
	public Integer getSTATUS() {
		return status;	
	}
	
	public void setSTATUS(Integer status) {
		this.status = status;
	}
	public Integer getSORT() {
		return sort;	
	}
	
	public void setSORT(Integer sort) {
		this.sort = sort;
	}
	public Integer getPARENT_ID() {
		return parentId;	
	}
	
	public void setPARENT_ID(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getIs_del() {
		return isDel;	
	}
	
	public void setIs_del(Integer isDel) {
		this.isDel = isDel;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}