package cn.northpark.utils.wx.handler;

import java.net.URLEncoder;

import cn.northpark.constant.BC_Constant;
import cn.northpark.utils.wx.WXTokenUtil;


public class MenuHandler {
    //发送微信的menu菜单url
    static String menustr = "";


    /**
     * 企业服务url
     **/
    static String qy_reg = "/wxdetail/38229817543169.html";
    static String qy_address = "/wx/addresslist.html";
    static String qy_change = "/wxdetail/38230152382977.html";
    static String qy_brand = "/wxdetail/38230926529537.html";
    static String qy_index = "/wx/index";

    /****个人中心***/
    static String center_user = "/wx/usercenter/index.html";  //个人中心
    static String center_order = "/wx/myorder/index.html";
    static String center_Fservice = "/wx/myservice/agency.html";
    static String center_Mservice = "/wx/myservice/free.html";
//	static String center_yijian = "";

    /****免费核名****/
    static String checkName = "/weixin/mywf/index";
    /****约号****/
    static String yuehao = "/weixin/appointNo/index";

    public static String getUrl(String targetUrl) {
        String tempUrl = "";
        try {
            tempUrl = BC_Constant.WX_url_base_shouquan_page.replace("APP_ID", BC_Constant.weixin_app_id).replace("REDIRECT_URI", URLEncoder.encode(BC_Constant.weixin_hosts + targetUrl, "utf-8"));
            //tempUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+BC_Constant.weixin_app_id+"&redirect_uri="+URLEncoder.encode(BC_Constant.weixin_hosts+targetUrl, "UTF-8")+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempUrl;
    }

    static {
        menustr = "{\"button\":["
                + "{\"name\":\"企业服务\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"公司注册\",\"url\":\"" + getUrl(qy_reg) + "\"},"
                + "{\"type\":\"view\",\"name\":\"代理地址\",\"url\":\"" + getUrl(qy_address) + "\"},"
                + "{\"type\":\"view\",\"name\":\"公司变更\",\"url\":\"" + getUrl(qy_change) + "\"},"
                + "{\"type\":\"view\",\"name\":\"商标注册\",\"url\":\"" + getUrl(qy_brand) + "\"},"
                + "{\"type\":\"view\",\"name\":\"全部商品\",\"url\":\"" + getUrl(qy_index) + "\"}"
                + "]},"

                + "{\"name\":\"我的小微\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"个人中心\",\"url\":\"" + getUrl(center_user) + "\"},"
                + "{\"type\":\"view\",\"name\":\"我的订单\",\"url\":\"" + getUrl(center_order) + "\"},"
                + "{\"type\":\"view\",\"name\":\"代理服务\",\"url\":\"" + getUrl(center_Fservice) + "\"},"
                + "{\"type\":\"view\",\"name\":\"自助服务\",\"url\":\"" + getUrl(center_Mservice) + "\"},"
                + "{\"type\":\"click\",\"name\":\"意见反馈\",\"key\":\"YJ_fankui\"}"
                + "]},"

                + "{\"name\":\"免费工具\",\"sub_button\":["
                + "{\"type\":\"view\",\"name\":\"免费核名\",\"url\":\"" + getUrl(checkName) + "\"},"
                + "{\"type\":\"view\",\"name\":\"免费约号\",\"url\":\"" + getUrl(yuehao) + "\"},"
                + "]},";
				/*
				+ "{\"type\":\"view\",\"name\":\"免费工具\",\"url\":\""+getUrl(checkName)+"\"}"
				+ "]}";*/
    }

    public String creatMenu() {
        String requrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        WXTokenUtil ws = new WXTokenUtil();
        String token = "";
        String ret = "";
        try {
            token = ws.getWeixinToken(BC_Constant.weixin_app_id, BC_Constant.weixin_app_secret_id);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
//		}
            if (!"".equals(token)) {
                requrl = requrl.replace("ACCESS_TOKEN", token);
            }
            try {
                ret = ws.sendMessage(requrl, menustr);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(ret);
        }
        return ret;
    }

    public static void main(String[] args) {
//		new MenuHandler().creatMenu();		

//		System.out.println(getUrl(qy_reg));
        try {
            String couponurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx76ec5c98f567d0e1&redirect_uri=" + URLEncoder.encode(BC_Constant.weixin_hosts + "/wx/myorder/nowPay/234?payid=123", "UTF-8") + "&response_type=code"
                    + "&scope=snsapi_base&state=STATE#wechat_redirect";
            System.out.println(couponurl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
