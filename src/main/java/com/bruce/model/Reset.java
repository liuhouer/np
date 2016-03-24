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
@Table(name = "bc_reset")
public class Reset implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2550628003086306974L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private Integer user_id;

	@Column(length = 255)
	private String auth_code;

	@Column(length = 50)
	private String invalid_time;

	@Column(length = 1)
	private Integer is_email_authed;

	@Column(length = 50)
	private String created_time;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}


	public String getAuth_code() {
		return auth_code;	
	}
	
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public String getInvalid_time() {
		return invalid_time;	
	}
	
	public void setInvalid_time(String invalid_time) {
		this.invalid_time = invalid_time;
	}
	public Integer getIs_email_authed() {
		return is_email_authed;	
	}
	
	public void setIs_email_authed(Integer is_email_authed) {
		this.is_email_authed = is_email_authed;
	}
	public String getCreated_time() {
		return created_time;	
	}
	
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	/*这里是mybatis部分代码
	
	id,user_id,auth_code,invalid_time,is_email_authed,created_time,	

		#{id},	#{user_id},	#{auth_code},	#{invalid_time},	#{is_email_authed},	#{created_time},	
	<update id="updateModel" parameterType="com.mai.app.entity.Reset">
        update Reset
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 user_id = #{user_id},
	   	 
	            
	   	 auth_code = #{auth_code},
	   	 
	            
	   	 invalid_time = #{invalid_time},
	   	 
	            
	   	 is_email_authed = #{is_email_authed},
	   	 
	            
	   	 created_time = #{created_time},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
	
	
	
	*/
}