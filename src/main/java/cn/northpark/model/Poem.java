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
 */

@Entity
@Table(name = "bc_poem")
public class Poem implements Serializable {

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

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String years;

    @Column(length = 255)
    private String years_code;

    @Column(length = 255)
    private String author;

    //诗词内容摘要...
    @Column(length = 0)
    private String content;

    @Column(length = 255)
    private String createtime;

    @Column(length = 0)
    private String enjoys;

    @Column(length = 255)
    private String pic_poem;

    @Column(length = 255)
    private String retcode;


    @Column(length = 255)
    private String types;

    @Column(length = 255)
    private String types_code;


    //诗词内容
    @Column(length = 0)
    private String content1;

    //爬取单页面的url
    @Column(length = 255)
    private String returl;


    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getEnjoys() {
        return enjoys;
    }

    public void setEnjoys(String enjoys) {
        this.enjoys = enjoys;
    }

    public String getPic_poem() {
        return pic_poem;
    }

    public void setPic_poem(String pic_poem) {
        this.pic_poem = pic_poem;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getReturl() {
        return returl;
    }

    public void setReturl(String returl) {
        this.returl = returl;
    }

    public String getYears_code() {
        return years_code;
    }

    public void setYears_code(String years_code) {
        this.years_code = years_code;
    }

    public String getTypes_code() {
        return types_code;
    }

    public void setTypes_code(String types_code) {
        this.types_code = types_code;
    }
	
	/*这里是mybatis部分代码
	
	id,title,years,author,content,createtime,	

		#{id},	#{title},	#{years},	#{author},	#{content},	#{createtime},	
	<update id="updateModel" parameterType="com.mai.app.entity.Poem">
        update Poem
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 title = #{title},
	   	 
	            
	   	 years = #{years},
	   	 
	            
	   	 author = #{author},
	   	 
	            
	   	 content = #{content},
	   	 
	            
	   	 createtime = #{createtime},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Poem">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Poem">
        select * from Poem where PoemID = #{id}
    </select>
	
	
	
	*/
}