/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class getinfoQueryCondition implements Serializable {


	private Integer id;
  

	private String path;
  

	private String isGened;
  

	private String createtime;
  

	private String type;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
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
	public String getType() {
		return type;	
	}
	
	public void setType(String type) {
		this.type = type;
	}

}