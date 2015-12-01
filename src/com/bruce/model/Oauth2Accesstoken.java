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
@Table(name = "bc_oauth2_accesstoken")
public class Oauth2Accesstoken implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String accessToken;

	@Column(length = 32)
	private String clientId;

	@Column(length = 32)
	private String clientSecret;

	@Column(length = 32)
	private String code;

	@Column(length = 32)
	private String grantType;

	@Column(length = 255)
	private String redirectUrl;

	@Column(length = 32)
	private String userId;

	@Column(length = 32)
	private String expiresIn;

	@Column(length = 32)
	private Date createtime;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getAccess_token() {
		return accessToken;	
	}
	
	public void setAccess_token(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getClient_id() {
		return clientId;	
	}
	
	public void setClient_id(String clientId) {
		this.clientId = clientId;
	}
	public String getClient_secret() {
		return clientSecret;	
	}
	
	public void setClient_secret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getCode() {
		return code;	
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getGrant_type() {
		return grantType;	
	}
	
	public void setGrant_type(String grantType) {
		this.grantType = grantType;
	}
	public String getRedirect_url() {
		return redirectUrl;	
	}
	
	public void setRedirect_url(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getUser_id() {
		return userId;	
	}
	
	public void setUser_id(String userId) {
		this.userId = userId;
	}
	public String getExpires_in() {
		return expiresIn;	
	}
	
	public void setExpires_in(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}