package cn.northpark.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_knowledge")
public class Knowledge implements Serializable{

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

	@Column(length = 255)
	private String ret_code;

	@Column(length = 255)
	private String title;

	@Column(length = 255)
	private String brief_img;

	@Column(length = 1000)
	private String brief;

	@Column(length = 1000)
	private String content;

	@Column(length = 255)
	private String post_date;

	@Column(length = 11)
	private Integer price;

	@Column(length = 255)
	private String tags;

	@Column(length = 255)
	private String tags_code;

	@Column(length = 255)
	private String returl;

	@Column(length = 255)
	private String path;

	@Column(length = 255)
	private String link_url;

	@Column(length = 20)
	private long view_times;

	@Transient
	private List<Map<String, String>> taglist;


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
		return returl;	
	}
	
	public void setReturl(String returl) {
		this.returl = returl;
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

	public List<Map<String, String>> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<Map<String, String>> taglist) {
		this.taglist = taglist;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,ret_code,title,brief_img,brief,content,post_date,price,tags,tags_code,returl,path,link_url,view_times,	

		#{id},	#{ret_code},	#{title},	#{brief_img},	#{brief},	#{content},	#{post_date},	#{price},	#{tags},	#{tags_code},	#{returl},	#{path},	#{link_url},	#{view_times},	
	<update id="updateModel" parameterType="com.mai.app.entity.Knowledge">
        update Knowledge
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 ret_code = #{ret_code},
	   	 
	            
	   	 title = #{title},
	   	 
	            
	   	 brief_img = #{brief_img},
	   	 
	            
	   	 brief = #{brief},
	   	 
	            
	   	 content = #{content},
	   	 
	            
	   	 post_date = #{post_date},
	   	 
	            
	   	 price = #{price},
	   	 
	            
	   	 tags = #{tags},
	   	 
	            
	   	 tags_code = #{tags_code},
	   	 
	            
	   	 returl = #{returl},
	   	 
	            
	   	 path = #{path},
	   	 
	            
	   	 link_url = #{link_url},
	   	 
	            
	   	 view_times = #{view_times},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Knowledge">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Knowledge">
        select * from Knowledge where KnowledgeID = #{id}
    </select>
	
	
	
	*/
}