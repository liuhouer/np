package cn.northpark.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	

	@Column(length = 255)
	private String tag;
	

	@Column(length = 255)
	private String tagcode;
	
	@Column(length = 11)
	private Integer viewnum;
	
	
	@Column(length = 255)
	private String color;
	
	@Column(length = 11)
	private Integer hotindex;
	
	
	@Column(length = 255)
	private String displayed;
	
	@Transient
	private List<Map<String,String>> taglist;
	


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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTagcode() {
		return tagcode;
	}

	public void setTagcode(String tagcode) {
		this.tagcode = tagcode;
	}

	public List<Map<String, String>> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<Map<String, String>> taglist) {
		this.taglist = taglist;
	}

	public Integer getViewnum() {
		return viewnum;
	}

	public void setViewnum(Integer viewnum) {
		this.viewnum = viewnum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getHotindex() {
		return hotindex;
	}

	public void setHotindex(Integer hotindex) {
		this.hotindex = hotindex;
	}


}