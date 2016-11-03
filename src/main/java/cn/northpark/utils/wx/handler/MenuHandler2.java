package cn.northpark.utils.wx.handler;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import cn.northpark.constant.BC_Constant;
import cn.northpark.utils.wx.WXTokenUtil;


public class MenuHandler2 {
	//发送微信的menu菜单url
	static String menustr = "";
	
	
	static String appid="wxfb45034cee4c3e2f";
	static String appsecret="3777c7ca86152637f02b31dfc86a23ec";
	
	
	/**咨询url**/
	static String my_doctor = "/user/myDoctorList.shtml";           //我的医生
	static String other_doctor = "/patient/doctor/list.shtml";      //其他医生
	
	/****个人中心***/
	static String center_user = "/user/userInfo.shtml";        //个人中心
	static String my_ask = "/patient/onlineask/onlineasklist.shtml";//我的咨询
	
	/****免费核名****/
	static String checkName = "/weixin/mywf/index";
	/****约号****/
	static String yuehao = "/weixin/appointNo/index";
	/*****资讯站*******/
	static String zixun = "/zhishi/zixunzhan";
	
	public static String getUrl(String targetUrl){
		String tempUrl = "";
		try {
			tempUrl = BC_Constant.WX_url_base_shouquan_page.replace("APP_ID", appid).replace("REDIRECT_URI", URLEncoder.encode("http://wechat.feiyisheng.com/julu_wx/"+targetUrl, "utf-8"));
			//tempUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+MContents.weixin_app_id+"&redirect_uri="+URLEncoder.encode(MContents.weixin_hosts+targetUrl, "UTF-8")+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempUrl;
	}
	
	static{
		menustr = "{\"button\":["
				+"{\"name\":\"咨询\",\"sub_button\":["
				+ "{\"type\":\"view\",\"name\":\"我的医生\",\"url\":\""+getUrl(my_doctor)+"\"},"
				+ "{\"type\":\"view\",\"name\":\"其他医生\",\"url\":\""+getUrl(other_doctor)+"\"}"
				
				+ "]},"
				
//				+ "{\"name\":\"一问一答\",\"sub_button\":["
//				+ "{\"type\":\"view\",\"name\":\"test\",\"url\":\""+getUrl(center_user)+"\"}"
//				+ "]},"
				
				+"{\"name\":\"更多服务\",\"sub_button\":["
					+ "{\"type\":\"view\",\"name\":\"我的咨询\",\"url\":\""+getUrl(my_ask)+"\"},"
					+ "{\"type\":\"view\",\"name\":\"个人中心\",\"url\":\""+getUrl(center_user)+"\"}"
					+ "]}";
				/*
				+ "{\"type\":\"view\",\"name\":\"免费工具\",\"url\":\""+getUrl(checkName)+"\"}"
				+ "]}";*/
	}
	public String creatMenu(){
		String requrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		WXTokenUtil ws = new WXTokenUtil();
		String token = "";
		String ret = "";
		try {
			token = ws.getWeixinToken(appid, appsecret);
			if(StringUtils.isNotEmpty(token)){
				
				requrl = requrl.replace("ACCESS_TOKEN", token);
				ret = ws.sendMessage(requrl,menustr);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		System.out.println(ret);
	}
		return ret;
	}
	
	
	public static void main(String[] args){
		new MenuHandler2().creatMenu();		
		System.out.println("111111111");
//		System.out.println(getUrl(qy_reg));
//		try {
//			String couponurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76ec5c98f567d0e1&redirect_uri="+URLEncoder.encode(MContents.weixin_hosts+"/wx/myorder/nowPay/234?payid=123", "UTF-8")+"&response_type=code"
//					+ "&scope=snsapi_base&state=STATE#wechat_redirect";
//			System.out.println(couponurl);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
