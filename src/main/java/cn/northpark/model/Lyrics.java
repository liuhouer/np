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
@Table(name = "bc_lyrics")
public class Lyrics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352450415620898579L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(length = 6)	
	private Integer id;


	@Column(length = 255)
	private String title;
	
	@Column(length = 255)
	private String titlecode;


	@Column(length = 255)
	private String updatedate;

	@Column(length = 255)
	private String albumImg;
	
	@Column(length = 11)
	private Integer zan;

	@Column(length = 11)
	private Integer pl;
	


	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUpdatedate() {
		return updatedate;	
	}
	
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	
	public String getAlbumImg() {
		return albumImg;	
	}
	
	public void setAlbumImg(String albumImg) {
		this.albumImg = albumImg;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public Integer getPl() {
		return pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

	public String getTitlecode() {
		return titlecode;
	}

	public void setTitlecode(String titlecode) {
		this.titlecode = titlecode;
	}

}