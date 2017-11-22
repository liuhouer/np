/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

public class ResetQueryCondition implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9009427486691408121L;


	private String id;
  

	private String user_id;
  

	private String auth_code;
  

	private String invalid_time;
  

	private Integer is_email_authed;
  

	private String created_time;
  



	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;	
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

}