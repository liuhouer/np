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
@Table(name = "bc_orders")
public class Orders implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6087407188859023529L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 32)
	private String movie_id;

	@Column(length = 255)
	private String addtime;

	@Column(length = 255)
	private String status;

	@Column(length = 11)
	private Integer fee;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getMovie_id() {
		return movie_id;	
	}
	
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getAddtime() {
		return addtime;	
	}
	
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getStatus() {
		return status;	
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getFee() {
		return fee;	
	}
	
	public void setFee(Integer fee) {
		this.fee = fee;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/*这里是mybatis部分代码
	
	id,movie_id,addtime,status,fee,	

		#{id},	#{movie_id},	#{addtime},	#{status},	#{fee},	
	<update id="updateModel" parameterType="com.mai.app.entity.Orders">
        update Orders
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 movie_id = #{movie_id},
	   	 
	            
	   	 addtime = #{addtime},
	   	 
	            
	   	 status = #{status},
	   	 
	            
	   	 fee = #{fee},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Orders">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Orders">
        select * from Orders where OrdersID = #{id}
    </select>
	
	
	
	*/
}