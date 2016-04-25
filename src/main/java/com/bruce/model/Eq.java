package com.bruce.model;

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
 * @date ${date}
 * @email zhangyang226@gmial.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_eq")
public class Eq implements Serializable{

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

	@Column(length = 2000)
	private String title;

	@Column(length = 2000)
	private String img;

	@Column(length = 2000)
	private String brief;

	@Column(length = 255)
	private String date;

	@Column(length = 255)
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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,title,img,brief,date,article,	

		#{id},	#{title},	#{img},	#{brief},	#{date},	#{article},	
	<update id="updateModel" parameterType="com.mai.app.entity.Eq">
        update Eq
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 title = #{title},
	   	 
	            
	   	 img = #{img},
	   	 
	            
	   	 brief = #{brief},
	   	 
	            
	   	 date = #{date},
	   	 
	            
	   	 article = #{article},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Eq">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Eq">
        select * from Eq where EqID = #{id}
    </select>
	
	
	
	*/
}