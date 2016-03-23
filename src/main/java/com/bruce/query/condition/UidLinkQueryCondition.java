/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class UidLinkQueryCondition implements Serializable {


	private String id;
  

	private String userid;
  

	private Integer uid_new;
  



	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getUid_new() {
		return uid_new;	
	}
	
	public void setUid_new(Integer uid_new) {
		this.uid_new = uid_new;
	}

}