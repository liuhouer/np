package cn.northpark.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_topic_comment")
public class TopicComment implements Serializable{

/**
 *
 * serialVersionUID
 */
private static final long serialVersionUID = 1L;

	 /* 注释掉的uuid模板
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")  */
	
	/* 下面是自增的native ID方式 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native") 
	@Column(length = 6)	
	private Integer id;

	@Column(length = 11)
	private Integer topic_id;

	@Column(length = 1)
	private String topic_type;

	@Column(length = 2000)
	private String content;

	@Column(length = 11)
	private Integer from_uid;

	@Column(length = 5)
	private String from_span;

	@Column(length = 11)
	private Integer to_uid;

	@Column(length = 255)
	private String from_uname;

	@Column(length = 255)
	private String to_uname;

	@Column(length = 32)
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

	public String getFrom_span() {
		return from_span;
	}

	public void setFrom_span(String from_span) {
		this.from_span = from_span;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}