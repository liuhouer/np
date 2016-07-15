/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class EqQueryCondition implements Serializable {


	private Integer id;
  

	private String title;
  

	private String img;
  

	private String brief;
  

	private String date;
  

	private String article;
  



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
	public String getImg() {
		return img;	
	}
	
	public void setImg(String img) {
		this.img = img;
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

}