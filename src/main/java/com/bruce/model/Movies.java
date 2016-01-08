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
@Table(name = "bc_movies")
public class Movies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5716389884123787304L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)	
	private String id;

	@Column(length = 2000)
	private String moviename;

	@Column()
	private String description;

	@Column(length = 11)
	private Integer price;

	@Column(length = 2000)
	private String path;

	@Column(length = 255)
	private String addtime;


	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;	
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPath() {
		return path;	
	}
	
	public void setPath(String path) {
		this.path = path;
	}

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	/*这里是mybatis部分代码
	
	id,name,desc,price,path,time,	

		#{id},	#{name},	#{desc},	#{price},	#{path},	#{time},	
	<update id="updateModel" parameterType="com.mai.app.entity.Movies">
        update Movies
        <set>
                
	   	 id = #{id},
	   	 
	            
	   	 name = #{name},
	   	 
	            
	   	 desc = #{desc},
	   	 
	            
	   	 price = #{price},
	   	 
	            
	   	 path = #{path},
	   	 
	            
	   	 time = #{time},
	   	 
	            </set>
        <where>
         id = #{id}
        </where>
    </update>
    
    
    
    <select id="findAllByPage"  
            resultType="com.mai.X.entity.Movies">
        select * from modelName
    </select>
    
    <select id="findByID"  parameterType="string"
            resultType="com.mai.X.entity.Movies">
        select * from Movies where MoviesID = #{id}
    </select>
	
	
	
	*/
}