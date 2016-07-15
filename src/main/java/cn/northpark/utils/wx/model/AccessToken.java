package cn.northpark.utils.wx.model;

/**
 * 微信通用接口凭证
*    
* 项目名称：WeiXin
* 类名称：AccessToken   
* 类描述：   
* 创建人：bruce
* 创建时间：2015-1-13 15:29:22 
* 修改备注：   
* @version    
*
 */
public class AccessToken {
	
	//获取到的凭证
	private String token;
	//凭证有效时间
	private String expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	

}
