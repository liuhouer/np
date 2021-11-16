/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class KnowledgeQueryCondition implements Serializable {


	private Integer id;
  

	private String ret_code;
  

	private String title;
  

	private String brief_img;
  

	private String brief;
  

	private String content;
  

	private String post_date;
  

	private Integer price;
  

	private String tags;
  

	private String tags_code;
  

	private String ret_url;
  

	private String path;
  

	private String link_url;
  

	private long view_times;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRet_code() {
		return ret_code;	
	}
	
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief_img() {
		return brief_img;	
	}
	
	public void setBrief_img(String brief_img) {
		this.brief_img = brief_img;
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
	public String getPost_date() {
		return post_date;	
	}
	
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public Integer getPrice() {
		return price;	
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTags() {
		return tags;	
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getTags_code() {
		return tags_code;	
	}
	
	public void setTags_code(String tags_code) {
		this.tags_code = tags_code;
	}
	public String getReturl() {
		return ret_url;
	}
	
	public void setReturl(String ret_url) {
		this.ret_url = ret_url;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	public String getLink_url() {
		return link_url;	
	}
	
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public long getView_times() {
		return view_times;	
	}
	
	public void setView_times(long view_times) {
		this.view_times = view_times;
	}

}