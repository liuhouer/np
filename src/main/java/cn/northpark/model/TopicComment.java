package cn.northpark.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,topic_id,topic_type,content,from_uid,to_uid,from_uname,to_uname,add_time,	

		#{id},	#{topic_id},	#{topic_type},	#{content},	#{from_uid},	#{to_uid},	#{from_uname},	#{to_uname},	#{add_time},	
	<update id="updateModel" parameterType="com.mai.app.entity.TopicComment">
        update TopicComment
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 topic_id = #{topic_id},
	   	 
	            
	   	 topic_type = #{topic_type},
	   	 
	            
	   	 content = #{content},
	   	 
	            
	   	 from_uid = #{from_uid},
	   	 
	            
	   	 to_uid = #{to_uid},
	   	 
	            
	   	 from_uname = #{from_uname},
	   	 
	            
	   	 to_uname = #{to_uname},
	   	 
	            
	   	 add_time = #{add_time},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.TopicComment">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.TopicComment">
        select * from TopicComment where TopicCommentID = #{id}
    </select>
	
	
	
	*/
}