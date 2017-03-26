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
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_poem_enjoy")
public class PoemEnjoy implements Serializable{

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
	private Integer poem_id;

	@Column(length = 255)
	private String title;

	@Column(length = 2000)
	private String comment;

	@Column(length = 2000)
	private String translation;

	@Column(length = 2000)
	private String enjoying;

	@Column(length = 2000)
	private String author_info;

	@Column(length = 255)
	private String tag;

	@Column(length = 255)
	private String tagcode;


	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPoem_id() {
		return poem_id;	
	}
	
	public void setPoem_id(Integer poem_id) {
		this.poem_id = poem_id;
	}
	public String getTitle() {
		return title;	
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;	
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTranslation() {
		return translation;	
	}
	
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public String getEnjoying() {
		return enjoying;	
	}
	
	public void setEnjoying(String enjoying) {
		this.enjoying = enjoying;
	}
	public String getAuthor_info() {
		return author_info;	
	}
	
	public void setAuthor_info(String author_info) {
		this.author_info = author_info;
	}
	public String getTag() {
		return tag;	
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTagcode() {
		return tagcode;	
	}
	
	public void setTagcode(String tagcode) {
		this.tagcode = tagcode;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,poem_id,title,comment,translation,enjoying,author_info,tag,tagcode,	

		#{id},	#{poem_id},	#{title},	#{comment},	#{translation},	#{enjoying},	#{author_info},	#{tag},	#{tagcode},	
	<update id="updateModel" parameterType="com.mai.app.entity.PoemEnjoy">
        update PoemEnjoy
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 poem_id = #{poem_id},
	   	 
	            
	   	 title = #{title},
	   	 
	            
	   	 comment = #{comment},
	   	 
	            
	   	 translation = #{translation},
	   	 
	            
	   	 enjoying = #{enjoying},
	   	 
	            
	   	 author_info = #{author_info},
	   	 
	            
	   	 tag = #{tag},
	   	 
	            
	   	 tagcode = #{tagcode},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.PoemEnjoy">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.PoemEnjoy">
        select * from PoemEnjoy where PoemEnjoyID = #{id}
    </select>
	
	
	
	*/
}