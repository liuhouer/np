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
@Table(name = "bc_oauth2_accesskoken")
public class Oauth2Accesskoken implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String code;

	@Column(length = 255)
	private String redirectUrl;

	@Column(length = 32)
	private String clientId;

	@Column(length = 32)
	private String grantType;

	@Column(length = 32)
	private String clientSecret;

	@Column(length = 2000)
	private String accessToken;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;	
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getRedirect_url() {
		return redirectUrl;	
	}
	
	public void setRedirect_url(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getClient_id() {
		return clientId;	
	}
	
	public void setClient_id(String clientId) {
		this.clientId = clientId;
	}
	public String getGrant_type() {
		return grantType;	
	}
	
	public void setGrant_type(String grantType) {
		this.grantType = grantType;
	}
	public String getClient_secret() {
		return clientSecret;	
	}
	
	public void setClient_secret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getAccess_token() {
		return accessToken;	
	}
	
	public void setAccess_token(String accessToken) {
		this.accessToken = accessToken;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}