package cn.northpark.utils.wx;  
  
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.northpark.utils.wx.model.AccessToken;
import cn.northpark.utils.wx.model.MyX509TrustManager;
import cn.northpark.utils.wx.model.SNSUserInfo;
import cn.northpark.utils.wx.model.WeixinOauth2Token;
  
/** 
 * 定时获取微信access_token的线程 
 *  
 * @author bruce
 * @date 2015-1-13 
 */  

/*
 * 正式的
 * 
AppID(应用ID)
wx823a64ccacea6d6a
AppSecret(应用密钥)
76de6e0778e2e1f3638c024c7fe1dc5c 

*/

/*
 * 测试的
 * 

开发者ID
AppID(应用ID)wxa94898ee89beb79e
AppSecret(应用密钥)b466f07d204a00d01869af8ac08355b9 隐藏 重置

*/


public class WXTokenUtil {  
	
	
	
	private static String token = "";
	private static long timestam = 0;
	
	private static long ticketstam = 0;
	private static String ticket = "";
	
	String end="\r\n";
	String twoHyphens = "--"; //用于拼接
	String boundary="*****"; //用于拼接 可
	

	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"; 
    
	// 获取code的接口地址（GET） 限200（次/天）   
    public final static String code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=http://yunlu.me:8080/wxAction/toBindUser&response_type=code&scope=snsapi_base&state=1#wechat_redirect"; 
	    
	
	private static Logger log = LoggerFactory.getLogger(WXTokenUtil.class);  
    // 第三方用户唯一凭证  
    public static String appid = "";  
    // 第三方用户唯一凭证密钥  
    public static String appsecret = "";  
    public static AccessToken accessToken = null;  
  
    
    /**
	 * 获取网页授权凭证
	 * 
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInteger("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInteger("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}
    
	
	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken 网页授权接口调用凭证
	 * @param openId 用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInteger("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
//				snsUserInfo.setPrivilegeList(JSONArray.toList());
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInteger("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}
    
    public static AccessToken getAccessToken(String appid,String appsecret){
		AccessToken accessToken=null;
		String requestUrl=access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject=httpsRequest(requestUrl, "GET", null);
		
		//如果请求成功
		if(jsonObject!=null){
			try{
				accessToken=new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getString("expires_in"));
			}catch (Exception e) {
				// TODO: handle exception
				// TODO: handle exception
				accessToken=null;
				 // 获取token失败   
	            log.error("获取token失败 errcode:{"+jsonObject.getInteger("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}"); 

			}
		}
		return accessToken;
	}
    
    public static JSONObject getCode(String appid,String appsecret){
    	
		String requestUrl=code_url.replace("APPID", appid);
		JSONObject jsonObject=httpsRequest(requestUrl, "GET", null);
		
		//如果请求成功
		/*if(jsonObject!=null){
			try{
				accessToken=new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getString("expires_in"));
			}catch (Exception e) {
				// TODO: handle exception
				// TODO: handle exception
				accessToken=null;
				 // 获取token失败   
	            log.error("获取token失败 errcode:{"+jsonObject.getInteger("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}"); 

			}
		}*/
		return jsonObject;
	}
    
    
    /**
	 * 发起https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（Get或者post）
	 * @param outputStr 提交数据
	 * @return
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject=null;
		StringBuffer buffer=new StringBuffer();
		try{
			//创建SSLcontext管理器对像，使用我们指定的信任管理器初始化
			TrustManager[] tm={new MyX509TrustManager()};
			SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf=sslContext.getSocketFactory();
			
			URL url= new URL(requestUrl);
			HttpsURLConnection httpsUrlConn=(HttpsURLConnection)url.openConnection();
		    httpsUrlConn.setSSLSocketFactory(ssf);
		    httpsUrlConn.setDoInput(true);
		    httpsUrlConn.setDoOutput(true);
		    httpsUrlConn.setUseCaches(false);
		    //设置请求方式（GET/POST）
		    httpsUrlConn.setRequestMethod(requestMethod);
		    if("GET".equalsIgnoreCase(requestMethod)){
		    	httpsUrlConn.connect();
		    }
		    
		    //当有数据需要提交时
		    if(outputStr!=null){
		    	OutputStream outputStream=httpsUrlConn.getOutputStream();
		    	//防止中文乱码
		    	outputStream.write(outputStr.getBytes("UTF-8"));
		    	outputStream.close();
		    	outputStream=null;
		    }
		    
		    //将返回的输入流转换成字符串
		    InputStream inputStream=httpsUrlConn.getInputStream();
		    InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
		    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
		    
		    String str=null;
		    while((str=bufferedReader.readLine())!=null){
		    	buffer.append(str);
		    }
		    
		    bufferedReader.close();
		    inputStreamReader.close();
		    
		    inputStream.close();
		    inputStream=null;
		    
		    httpsUrlConn.disconnect();
		    jsonObject=JSONObject.parseObject(buffer.toString());
		    System.out.println(jsonObject);
		    
		}   
		catch (ConnectException ce) {
			// TODO: handle exception
			log.error("Weixin server connection timed out.");
		} 	
		catch (Exception e) {
			// TODO: handle exception
			log.error("https request error:{}", e);
		}
		
		return jsonObject;
	}
	
	
	private static String  gettoken(String tokenurl){
		String ret = "";
		System.out.println("=================>");
		try {
			
			ret = sendMessaeg(tokenurl,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = JSONObject.parseObject(ret);
		if(ret.contains("access_token")){
			token = obj.getString("access_token");
			timestam = new Date().getTime();
		}
		return ret;
	}

	public String makeWeixinQuer(String appid, String secrect,int sceneid) throws Exception {
		// TODO Auto-generated method stub
		String pic = "";
		String date = "{\"expire_seconds\": 1800, \"action_name\": \"QR_SCENE\", \"action_info\":"
				+ " {\"scene\": {\"scene_id\": \""+sceneid+"\"}}}";
		
		System.out.println("======>"+date);
		String ticketjoson = "";
		try {
			String token = getWeixinToken(appid,secrect);
			ticketjoson = sendMessaeg("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN".replace("TOKEN", token), date);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONObject jo = JSONObject.parseObject(ticketjoson);
		System.out.println("this big date is "+ticketjoson);
		String ticket = "";
		try {
			ticket = URLEncoder.encode(jo.getString("ticket"),"utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pic = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET".replace("TICKET", ticket);
		return pic;
	}

	public String sendMessage(String request, String msg) throws Exception {
		// TODO Auto-generated method stub
		String ret = "";
		try {
			ret = sendMessaeg(request,msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	

	
	public String getWeixinToken(String appid, String secrect) throws Exception {
		// TODO Auto-generated method stub
		String tokenurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+appid+"&secret="+secrect;
		long ced = new Date().getTime();
		long cu = new Date().getTime();
		System.out.println("before token is "+token+" timestem is "+timestam);
		if(token == null || timestam == 0 || (cu - timestam > 7200*1000)){
			synchronized(token){
				gettoken(tokenurl);
			}
			
		}
//		res = token;
		System.out.println("after token is "+token+" timestem is "+timestam);
		return token;
	}
	
	
	public String getWeixinJSToken(String appid, String secrect)
			throws Exception {
		// TODO Auto-generated method stub
		String jstoken = "";
		String token = getWeixinToken(appid,secrect);
		
		long ced = new Date().getTime();
		if(ticket.equals("") || ticketstam == 0 || (ced - ticketstam) > 7000*1000){
			synchronized(ticket){
				jstoken = newticket(token);
			}
		}
		return jstoken;
	}
	public static String newticket(String token)throws Exception{
		String jstickeurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=WX_URL&type=jsapi";
		String requestUrl = jstickeurl.replaceAll("WX_URL", token);
		String astokenrs = sendMessaeg(requestUrl, "");
		JSONObject jo = JSONObject.parseObject(astokenrs);
		if(jo.get("ticket") != null ){//成功
			ticket = (String) jo.get("ticket");
		}else{
			
		}
		return ticket;
	}
	
	/**
	 * 发送https请求
	 * @param requesturl
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String sendMessaeg(String requesturl,String message)throws Exception{
		String retmsg;
		System.out.println("requesturl is "+requesturl+" and the message is "+message);
		StringBuffer buffer = new StringBuffer();
		//String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=OubBCWtLMktHejKc8fnYQJyA-COCuph91X_HLg6TdOBLkCMlaITXlFfSbnBHuCKFTiaJxwvVzVSuu9-yYCXfmg";
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		URL url = new URL(requesturl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
				.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);
		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false); // 设置请求方式（GET/POST）
		httpUrlConn.setRequestMethod("POST");
//		if ("GET".equalsIgnoreCase(requestMethod))
//			httpUrlConn.connect();
		// 当有数据需要提交时
	//	if (null != outputStr) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(message.getBytes("UTF-8"));
			outputStream.close();
			// 将返回的输入流转换成字符串
						InputStream inputStream = httpUrlConn.getInputStream();
						InputStreamReader inputStreamReader = new InputStreamReader(
								inputStream, "utf-8");
						BufferedReader bufferedReader = new BufferedReader(
								inputStreamReader);
						String str = null;
						while ((str = bufferedReader.readLine()) != null) {
							buffer.append(str);
						}
						retmsg = buffer.toString();
						bufferedReader.close();
						inputStreamReader.close();
						// 释放资源
						inputStream.close();
						inputStream = null;
						httpUrlConn.disconnect();
		//}
		System.out.println("send msg result is "+retmsg);
		
		return retmsg;   
	}

	
	
	////////////////////////////////////////////////////////////
	public static void main(String[] args){
		String ret = "";
		String msg = "{\"action\":\"long2short\",\"long_url\":\"http://www.lvzheng.com\"}";
		String request = "";
		try {
			request = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token="+new WXTokenUtil().getWeixinToken("wx00ea855aaf1152af", "07a6dd9789e28772f6de32a2ec057fc0");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ret = sendMessaeg(request,msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jo = JSONObject.parseObject(ret);
		System.out.println(jo.getString("short_url")); 
	}

}  