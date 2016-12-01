/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public class AstroQueryCondition implements Serializable {


	private Integer id;
  

	private String wx_cop_userid;
  

	private String xzname;
  

	private String addtime;
  

	private String type;
  

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

}