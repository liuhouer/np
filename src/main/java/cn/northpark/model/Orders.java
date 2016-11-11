package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_orders")
public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6087407188859023529L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(length = 6)	
	private Integer id;


	@Column(length = 32)
	private String movie_id;

	@Column(length = 255)
	private String addtime;

	@Column(length = 255)
	private String status;

	@Column(length = 11)
	private Integer fee;


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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
}