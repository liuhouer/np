/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class TopicCommentQueryCondition implements Serializable {


	private Integer id;
  

	private Integer topic_id;
  

	private String topic_type;
  

	private String content;
  

	private Integer from_uid;
  

	private Integer to_uid;
  

	private String from_uname;
  

	private String to_uname;
  

	private String add_time;
  



	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTopic_id() {
		return topic_id;	
	}
	
	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_type() {
		return topic_type;	
	}
	
	public void setTopic_type(String topic_type) {
		this.topic_type = topic_type;
	}
	public String getContent() {
		return content;	
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFrom_uid() {
		return from_uid;	
	}
	
	public void setFrom_uid(Integer from_uid) {
		this.from_uid = from_uid;
	}
	public Integer getTo_uid() {
		return to_uid;	
	}
	
	public void setTo_uid(Integer to_uid) {
		this.to_uid = to_uid;
	}
	public String getFrom_uname() {
		return from_uname;	
	}
	
	public void setFrom_uname(String from_uname) {
		this.from_uname = from_uname;
	}
	public String getTo_uname() {
		return to_uname;	
	}
	
	public void setTo_uname(String to_uname) {
		this.to_uname = to_uname;
	}
	public String getAdd_time() {
		return add_time;	
	}
	
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

}