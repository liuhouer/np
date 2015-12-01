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
@Table(name = "bc_rolepermissions")
public class Rolepermissions implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 255)
	private String roleid;

	@Column(length = 255)
	private String permissionid;

	@Column(length = 1)
	private Integer isDel;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getROLEID() {
		return roleid;	
	}
	
	public void setROLEID(String roleid) {
		this.roleid = roleid;
	}
	public String getPERMISSIONID() {
		return permissionid;	
	}
	
	public void setPERMISSIONID(String permissionid) {
		this.permissionid = permissionid;
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