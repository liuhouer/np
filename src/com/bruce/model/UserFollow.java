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
@Table(name = "bc_user_follow")
public class UserFollow implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String author_id;

	@Column(length = 32)
	private String follow_id;

	@Column(length = 32)
	private String create_time;

	@Column(length = 11)
	private Integer status;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor_id() {
		return author_id;	
	}
	
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getFollow_id() {
		return follow_id;	
	}
	
	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}
	public String getCreate_time() {
		return create_time;	
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;	
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}