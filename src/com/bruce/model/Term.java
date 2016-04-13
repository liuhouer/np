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
@Table(name = "bc_term")
public class Term implements Serializable{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private Date starttime;

	@Column(length = 255)
	private Date endtime;

	@Column(length = 255)
	private String status;

	@Column(length = 255)
	private String createtime;

	@Column(length = 11)
	private Integer sortid;

	@Column(length = 1)
	private Integer isDel;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Date getStarttime() {
		return starttime;	
	}
	
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;	
	}
	
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getStatus() {
		return status;	
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getSortid() {
		return sortid;	
	}
	
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
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