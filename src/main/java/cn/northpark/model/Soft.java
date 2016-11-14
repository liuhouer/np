package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_soft")
public class Soft implements Serializable{

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

	@Column(length = 20)
	private String retcode;
	
	@Column(length = 255)
	private String title;

	@Column()
	private String brief;

	@Column()
	private String content;
	

	@Column(length = 255)
	private String year;


	@Column(length = 255)
	private String month;


	@Column(length = 255)
	private String postdate;

	@Column(length = 255)
	private String os;

	@Column(length = 255)
	private String tags;
	
	@Column(length = 255)
	private String tagscode;

	@Column(length = 255)
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
	public String getOs() {
		return os;	
	}
	
	public void setOs(String os) {
		this.os = os;
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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTagscode() {
		return tagscode;
	}

	public void setTagscode(String tagscode) {
		this.tagscode = tagscode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	/*这里是mybatis部分代码
	
	id,retcode,brief,content,postdate,os,tags,returl,	

		#{id},	#{retcode},	#{brief},	#{content},	#{postdate},	#{os},	#{tags},	#{returl},	
	<update id="updateModel" parameterType="com.mai.app.entity.Soft">
        update Soft
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 retcode = #{retcode},
	   	 
	            
	   	 brief = #{brief},
	   	 
	            
	   	 content = #{content},
	   	 
	            
	   	 postdate = #{postdate},
	   	 
	            
	   	 os = #{os},
	   	 
	            
	   	 tags = #{tags},
	   	 
	            
	   	 returl = #{returl},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Soft">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Soft">
        select * from Soft where SoftID = #{id}
    </select>
	
	
	
	*/
}