package cn.northpark.utils.wx.handler;


import cn.northpark.constant.BC_Constant;
import cn.northpark.utils.wx.WXTokenUtil;

import com.alibaba.fastjson.JSONObject;

public class TextHandler {

    private static WXTokenUtil ws = new WXTokenUtil();

    //private static String send_module_msg_url = "https://api.weixin.qq.com"
    //	+ "/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    public String sendTemplateMsgTofans(String openid, String content) {
        String res = "";

        return res;
    }

    private static TextHandler tx = null;

    public static TextHandler newstance() {
        if (tx == null)
            tx = new TextHandler();
        return tx;
    }
    /**
     public String sendModuleMsge(String openid,ModuleMessageEntity mme){
     String res = "";
     JSONObject jo = new JSONObject();
     jo.put("touser", openid);
     jo.put("template_id", mme.getTemplateid());
     jo.put("url", mme.getUrl());
     jo.put("topcolor", mme.getTemplateid());
     jo.put("data", mme.getDatas());
     String token = "";
     try {
     token = ws.getWeixinToken(BC_Constant.weixin_app_id, BC_Constant.weixin_app_secret_id);
     } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     if(!token.equals("")){
     String sendurl ="https://api.weixin.qq.com"
     + "/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
     sendurl = sendurl.replace("ACCESS_TOKEN", token);
     try {
     res = ws.sendMessage(sendurl,jo.toString());
     } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     }
     System.out.println("res is --------------->"+res);
     return res;
     }
     */
    /**
     * 主动发送客服消息
     *
     * @param openid
     * @param content
     * @return
     */
    public String sendTextMessage(String openid, String content) {
        String sendrst = "";
        String token = "";
        try {
            token = ws.getWeixinToken(BC_Constant.weixin_app_id, BC_Constant.weixin_app_secret_id);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String send_msg_url = "https://api.weixin.qq.com/"
                + "cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        if (!token.equals("")) {

            send_msg_url = send_msg_url.replace("ACCESS_TOKEN", token);
        }
        JSONObject jt = new JSONObject();
        jt.put("touser", openid);
        jt.put("msgtype", "text");
        JSONObject jc = new JSONObject();
        jc.put("content", content);
        jt.put("text", jc);

        try {
            sendrst = ws.sendMessage(send_msg_url, jt.toString());
            System.out.println("send msg res is " + sendrst);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sendrst;
    }
}
