/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class PoemQueryCondition implements Serializable {


	private Integer id;
  

	private String title;
  

	private String years;
  

	private String author;
  

	private String content;
  

	private String createtime;
  



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
	public String getYears() {
		return years;	
	}
	
	public void setYears(String years) {
		this.years = years;
	}
	public String getAuthor() {
		return author;	
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;	
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}