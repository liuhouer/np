/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class MoviesQueryCondition implements Serializable {


	private String id;
  

	private String name;
  

	private String desc;
  

	private Integer price;
  

	private String path;
  

	private String time;
  



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
	public String getDesc() {
		return desc;	
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getPrice() {
		return price;	
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getTime() {
		return time;	
	}
	
	public void setTime(String time) {
		this.time = time;
	}

}