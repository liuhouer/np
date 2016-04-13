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
@Table(name = "getimgs")
public class GetImg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3360889057953723246L;

	/**
	 * 
	 */

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 255)	
	private String id;
	
	@Column()
	private String path;

	@Column()
	private String type;

	@Column(length = 255)
	private String isGened;

	@Column(length = 255)
	private String createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getIsGened() {
		return isGened;
	}

	public void setIsGened(String isGened) {
		this.isGened = isGened;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	
}