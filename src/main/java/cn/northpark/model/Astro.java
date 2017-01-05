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
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_astro")
public class Astro implements Serializable{

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
	private String wx_cop_userid;

	@Column(length = 255)
	private String xzname;

	@Column(length = 255)
	private String addtime;

	@Column(length = 255)
	private String type;

	@Column(length = 255)
	private String status;


	public Integer getId() {
		return id;	
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWx_cop_userid() {
		return wx_cop_userid;	
	}
	
	public void setWx_cop_userid(String wx_cop_userid) {
		this.wx_cop_userid = wx_cop_userid;
	}
	public String getXzname() {
		return xzname;	
	}
	
	public void setXzname(String xzname) {
		this.xzname = xzname;
	}
	public String getAddtime() {
		return addtime;	
	}
	
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getType() {
		return type;	
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;	
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,wx_cop_userid,xzname,addtime,type,status,	

		#{id},	#{wx_cop_userid},	#{xzname},	#{addtime},	#{type},	#{status},	
	<update id="updateModel" parameterType="com.mai.app.entity.Astro">
        update Astro
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 wx_cop_userid = #{wx_cop_userid},
	   	 
	            
	   	 xzname = #{xzname},
	   	 
	            
	   	 addtime = #{addtime},
	   	 
	            
	   	 type = #{type},
	   	 
	            
	   	 status = #{status},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Astro">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Astro">
        select * from Astro where AstroID = #{id}
    </select>
	
	
	
	*/
}