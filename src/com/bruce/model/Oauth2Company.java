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
@Table(name = "bc_oauth2_company")
public class Oauth2Company implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String clientId;

	@Column(length = 32)
	private String clientSecret;

	@Column(length = 255)
	private String companyName;

	@Column(length = 255)
	private String companyUrl;

	@Column(length = 2000)
	private String companyDetail;

	@Column(length = 32)
	private String companyLicenseNumber;

	@Column(length = 200)
	private String email;

	@Column(length = 32)
	private String contact;

	@Column(length = 32)
	private String phone;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
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
	public String getCompany_name() {
		return companyName;	
	}
	
	public void setCompany_name(String companyName) {
		this.companyName = companyName;
	}
	public String getCompany_url() {
		return companyUrl;	
	}
	
	public void setCompany_url(String companyUrl) {
		this.companyUrl = companyUrl;
	}
	public String getCompany_detail() {
		return companyDetail;	
	}
	
	public void setCompany_detail(String companyDetail) {
		this.companyDetail = companyDetail;
	}
	public String getCompany_license_number() {
		return companyLicenseNumber;	
	}
	
	public void setCompany_license_number(String companyLicenseNumber) {
		this.companyLicenseNumber = companyLicenseNumber;
	}
	public String getEmail() {
		return email;	
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;	
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;	
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}