/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class OrdersQueryCondition implements Serializable {


	private String id;
  

	private String movie_id;
  

	private String addtime;
  

	private String status;
  

	private Integer fee;
  



	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getMovie_id() {
		return movie_id;	
	}
	
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getAddtime() {
		return addtime;	
	}
	
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getStatus() {
		return status;	
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getFee() {
		return fee;	
	}
	
	public void setFee(Integer fee) {
		this.fee = fee;
	}

}