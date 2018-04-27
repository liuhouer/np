package cn.northpark.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_vps")
public class Vps implements Serializable {

    /**
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
    private String tags;

    @Column(length = 2000)
    private String brief;

    @Column(length = 255)
    private String date;

    @Column(length = 255)
    private String article;

    @Column(length = 255)
    private String retcode;

    @Column(length = 255)
    private String returl;

    @Column(length = 11)
    private String color;


    @Transient
    private List<Map<String, String>> taglist;


    public List<Map<String, String>> getTaglist() {
        return taglist;
    }

    public void setTaglist(List<Map<String, String>> taglist) {
        this.taglist = taglist;
    }


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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;

        //设置taglist
        List<Map<String, String>> taglist2 = new ArrayList<Map<String, String>>();
        if (StringUtils.isNotEmpty(tags)) {
            String[] tagarr = tags.split(",");
            for (int i = 0; i < tagarr.length; i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("tag", tagarr[i]);
                taglist2.add(map);
            }
        }

        setTaglist(taglist2);
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

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getReturl() {
        return returl;
    }

    public void setReturl(String returl) {
        this.returl = returl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
	/*这里是mybatis部分代码
	
	id,title,tags,brief,date,article,retcode,returl,color,	

		#{id},	#{title},	#{tags},	#{brief},	#{date},	#{article},	#{retcode},	#{returl},	#{color},	
	<update id="updateModel" parameterType="com.mai.app.entity.Vps">
        update Vps
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 title = #{title},
	   	 
	            
	   	 tags = #{tags},
	   	 
	            
	   	 brief = #{brief},
	   	 
	            
	   	 date = #{date},
	   	 
	            
	   	 article = #{article},
	   	 
	            
	   	 retcode = #{retcode},
	   	 
	            
	   	 returl = #{returl},
	   	 
	            
	   	 color = #{color},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Vps">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Vps">
        select * from Vps where VpsID = #{id}
    </select>
	
	
	
	*/
}