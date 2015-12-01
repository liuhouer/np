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
@Table(name = "bc_teacher_introduce")
public class TeacherIntroduce implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String teacherId;

	@Column(length = 255)
	private String organization;

	@Column(length = 255)
	private String degree;

	@Column(length = 2000)
	private String introduce;

	@Column(length = 1)
	private Integer isDel;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getTeacher_id() {
		return teacherId;	
	}
	
	public void setTeacher_id(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getOrganization() {
		return organization;	
	}
	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDegree() {
		return degree;	
	}
	
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getIntroduce() {
		return introduce;	
	}
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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