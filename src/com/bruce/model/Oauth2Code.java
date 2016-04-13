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
@Table(name = "bc_oauth2_code")
public class Oauth2Code implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String userId;

	@Column(length = 32)
	private String code;

	@Column(length = 255)
	private String redirectUrl;

	@Column(length = 32)
	private String clientId;

	@Column(length = 32)
	private String state;

	@Column(length = 32)
	private String responseType;

	@Column(length = 32)
	private String scope;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return userId;	
	}
	
	public void setUser_id(String userId) {
		this.userId = userId;
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
	public String getState() {
		return state;	
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getResponse_type() {
		return responseType;	
	}
	
	public void setResponse_type(String responseType) {
		this.responseType = responseType;
	}
	public String getScope() {
		return scope;	
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}