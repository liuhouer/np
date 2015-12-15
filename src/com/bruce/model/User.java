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
@Table(name = "bc_user")
public class User implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 30)
	private String username;

	@Column(length = 30)
	private String first_name;

	@Column(length = 30)
	private String last_name;

	@Column(length = 75)
	private String email;

	@Column(length = 128)
	private String password;

	@Column(length = 1)
	private Integer is_staff;

	@Column(length = 1)
	private Integer is_active;

	@Column(length = 1)
	private Integer is_superuser;

	@Column(length = 32)
	private String last_login;

	@Column(length = 32)
	private String date_joined;

	@Column(length = 1)
	private Integer is_del;

	@Column(length = 255)
	private String headpath;
	
	@Column(length = 255)
	private String wx_openid;

	@Column(length = 2000)
	private String wx_info;

	@Column(length = 255)
	private String qq_openid;

	@Column(length = 2000)
	private String qq_info;

	@Column(length = 255)
	private String wb_openid;

	@Column(length = 2000)
	private String wb_info;
	
	@Column(length = 2000)
	private String tail_slug;//自己的域名空间【字母代号】


	public String getWx_openid() {
		return wx_openid;
	}

	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}

	public String getWx_info() {
		return wx_info;
	}

	public void setWx_info(String wx_info) {
		this.wx_info = wx_info;
	}

	public String getQq_openid() {
		return qq_openid;
	}

	public void setQq_openid(String qq_openid) {
		this.qq_openid = qq_openid;
	}

	public String getQq_info() {
		return qq_info;
	}

	public void setQq_info(String qq_info) {
		this.qq_info = qq_info;
	}

	public String getWb_openid() {
		return wb_openid;
	}

	public void setWb_openid(String wb_openid) {
		this.wb_openid = wb_openid;
	}

	public String getWb_info() {
		return wb_info;
	}

	public void setWb_info(String wb_info) {
		this.wb_info = wb_info;
	}

	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;	
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;	
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;	
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;	
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;	
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIs_staff() {
		return is_staff;	
	}
	
	public void setIs_staff(Integer is_staff) {
		this.is_staff = is_staff;
	}
	public Integer getIs_active() {
		return is_active;	
	}
	
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}
	public Integer getIs_superuser() {
		return is_superuser;	
	}
	
	public void setIs_superuser(Integer is_superuser) {
		this.is_superuser = is_superuser;
	}
	public String getLast_login() {
		return last_login;	
	}
	
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getDate_joined() {
		return date_joined;	
	}
	
	public void setDate_joined(String date_joined) {
		this.date_joined = date_joined;
	}
	public Integer getIs_del() {
		return is_del;	
	}
	
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	public String getHeadpath() {
		return headpath;	
	}
	
	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getTail_slug() {
		return tail_slug;
	}

	public void setTail_slug(String tail_slug) {
		this.tail_slug = tail_slug;
	}
}