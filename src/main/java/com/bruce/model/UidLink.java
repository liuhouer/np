package com.bruce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_uid_link")
public class UidLink implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5961819110386870921L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 255)	
	private String id;

	@Column(length = 32)
	private String userid;

	@Column(length = 11)
	private Integer uid_new;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getUid_new() {
		return uid_new;	
	}
	
	public void setUid_new(Integer uid_new) {
		this.uid_new = uid_new;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,userid,uid_new,	

		#{id},	#{userid},	#{uid_new},	
	<update id="updateModel" parameterType="com.mai.app.entity.UidLink">
        update UidLink
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 userid = #{userid},
	   	 
	            
	   	 uid_new = #{uid_new},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.UidLink">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.UidLink">
        select * from UidLink where UidLinkID = #{id}
    </select>
	
	
	
	*/
}