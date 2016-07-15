package cn.northpark.utils.wx.model;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager; 

/**
 * 证书信任管理器（用于https请求）
*    
* 项目名称：WeiXin
* 类名称：MyX509TrustManager   
* 类描述：   
* 创建人：bruce
* 创建时间：2015-1-13 15:28:45
* 修改备注：   
* @version    
*
 */

public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
