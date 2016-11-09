/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class SoftQueryCondition implements Serializable {


	private Integer id;
  

	private String retcode;
  

	private String brief;
  

	private String content;
  

	private String postdate;
  

	private String tags;
  

	private String returl;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRetcode() {
		return retcode;	
	}
	
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getBrief() {
		return brief;	
	}
	
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;	
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostdate() {
		return postdate;	
	}
	
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public String getTags() {
		return tags;	
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getReturl() {
		return returl;	
	}
	
	public void setReturl(String returl) {
		this.returl = returl;
	}

}