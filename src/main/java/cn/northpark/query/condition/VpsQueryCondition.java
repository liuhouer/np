/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class VpsQueryCondition implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer id;
  

	private String title;
  

	private String tags;
  

	private String brief;
  

	private String date;
  

	private String article;
  

	private String retcode;
  

	private String returl;
  

	private String color;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;	
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getBrief() {
		return brief;	
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getDate() {
		return date;	
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getArticle() {
		return article;	
	}
	
	public void setArticle(String article) {
		this.article = article;
	}
	public String getRetcode() {
		return retcode;	
	}
	
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getReturl() {
		return returl;	
	}
	
	public void setReturl(String returl) {
		this.returl = returl;
	}
	public String getColor() {
		return color;	
	}
	
	public void setColor(String color) {
		this.color = color;
	}

}