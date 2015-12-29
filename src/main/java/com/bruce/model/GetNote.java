package com.bruce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "getnotes")
public class GetNote implements Serializable{

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
	private String brief;

	@Column()
	private String notes;

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

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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



	
}