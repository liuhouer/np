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
@Table(name = "bc_movies")
public class Movies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716389884123787304L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(length = 6)	
	private Integer id;

	@Column(length = 2000)
	private String moviename;
	
	@Column(length = 255)
	private String retcode;

	@Column()
	private String description;

	@Column(length = 11)
	private Integer price;

	@Column(length = 2000)
	private String path;

	@Column(length = 255)
	private String addtime;


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

	
	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}