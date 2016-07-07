package com.bruce.utils.wx.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jdom2.Element;

import com.alibaba.fastjson.JSONObject;
import com.bruce.constant.BC_Constant;
import com.bruce.utils.XmlParser;
import com.bruce.utils.wx.model.Message;
import com.bruce.utils.wx.model.PayMessage;


public class MessageHandler{

	private static MessageHandler message = null;
	public static MessageHandler newstance(){
		if(message == null)
			message = new MessageHandler();
		return message;
	}
	public String callbackcontent(String content,String openid){
		String ret = "<xml>"
		+"<ToUserName><![CDATA["+openid+"]]></ToUserName>"
		+"<FromUserName><![CDATA["+BC_Constant.weixin_app_name+"]]></FromUserName>"
		+"<CreateTime>"+new Date().getTime()+"</CreateTime>"
		+"<MsgType><![CDATA[text]]></MsgType>"
		+"<Content><![CDATA["+content+"]]></Content>"
		+"</xml>";
		System.out.println(this.getClass()+":res is "+ret);
		return ret;
	}
	public Message getMessageEntity(HttpServletRequest re){
		String c = tranceUserMessageTostring(re);
		Message me = tranceStrToentity(c);
		return me;
	}
	public String tranceUserMessageTostring(HttpServletRequest re){
		String res = null;
		try {
			InputStream is = re.getInputStream();
			int size = re.getContentLength();
			
			byte[] buffer = new byte[size];
			byte[] xmldataByte = new byte[size];
			
			int count = 0;
			int rbyte = 0;
			
			while(count < size){
				rbyte = is.read(buffer);
				for(int i=0;i<rbyte;i++){
					 xmldataByte[count + i] = buffer[i];
				}
				count += rbyte;
			}
			is.close();
			res = new String(xmldataByte,"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public PayMessage trancePayResultToentity(String xmlstr){
		PayMessage pme = new PayMessage();
		Element el = null;
		try {
			el = XmlParser.newInstance().parseXmlToElement(xmlstr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(el != null){
			String returncode = el.getChild("return_code").getValue();
			pme.setReturn_code(returncode);
			if(returncode.equalsIgnoreCase("SUCCESS")){
				pme.setAppid(el.getChild("appid").getValue());
				pme.setMch_id(el.getChild("mch_id").getValue());
				pme.setNonce_str(el.getChild("nonce_str").getValue());
				pme.setSign(el.getChild("sign").getValue());
				
				String resultcode = el.getChild("result_code").getValue();
				pme.setResult_code(resultcode);
				if(resultcode.equalsIgnoreCase(resultcode)){
					if(null != el.getChild("prepay_id"))
						pme.setPrepay_id(el.getChild("prepay_id").getValue());
					if(null != el.getChild("trade_type"))
						pme.setTrade_type(el.getChild("trade_type").getValue());
					
				}
				
			}else if(returncode.equalsIgnoreCase("FAIL")){
				pme.setReturn_msg(el.getChild("return_msg").getValue());
			}
		}
		return pme;
	}
	public Message tranceStrToentity(String xmlstr){
		System.out.println("revice msg from weixin is "+xmlstr);
		Message me = new Message();
		Element el = null;
		try {
			el = XmlParser.newInstance().parseXmlToElement(xmlstr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(el != null){
			me.setTouser(el.getChild("ToUserName").getValue());
			if(el.getChild("Content") != null){
				me.setContent(el.getChild("Content").getValue());
			}
			String mstype = el.getChild("MsgType").getValue();
			me.setMessagetype(mstype);
			me.setFromuser(el.getChild("FromUserName").getValue());
			me.setMessageid(el.getChild("MsgId") == null?"":el.getChild("MsgId").getValue());
			
			JSONObject jo = new JSONObject();
			
			if(mstype.equals("image")){//图片
				jo.put("PicUrl", el.getChild("PicUrl").getValue());
			}else if(mstype.equals("voice")){//声音
				jo.put("MediaId", el.getChild("MediaId").getValue());
				jo.put("Format", el.getChild("Format").getValue());
				jo.put("Recognition", el.getChild("Recognition").getValue());
			}else if(mstype.equals("location")){//位置
				jo.put("Location_X", el.getChild("Location_X").getValue());
				jo.put("Location_Y", el.getChild("Location_Y").getValue());
				jo.put("Scale", el.getChild("Scale").getValue());
				jo.put("Label", el.getChild("Label").getValue());
			}else if(mstype.equals("video")){//视频
				jo.put("MediaId", el.getChild("MediaId").getValue());
				jo.put("ThumbMediaId", el.getChild("ThumbMediaId").getValue());
			}else if(mstype.equals("event")){//事件类型消息
				String et = el.getChild("Event").getValue();
				jo.put("eventtype", et);
				
				String event = el.getChild("EventKey") == null?"":el.getChild("EventKey").getValue();//事件类型
				jo.put("Event", event);
				
				if(el.getChild("Ticket") != null){//二维码扫描事件
					jo.put("Ticket", el.getChild("Ticket").getValue());
					
					String eventkey = el.getChild("EventKey").getValue();
					
					if(eventkey.indexOf("qrscene_") == 0){//未关注
						String sc = eventkey.substring(eventkey.indexOf("_")+1, eventkey.length());
					}
				}
				
				if(el.getChild("Latitude") != null){
					jo.put("Latitude", el.getChild("Latitude").getValue());
				}
				if(el.getChild("Longitude") != null){
					jo.put("Longitude", el.getChild("Longitude").getValue());
				}
				if(el.getChild("Longitude") != null){
					jo.put("Longitude", el.getChild("Longitude").getValue());
				}
				
			}else if(mstype.equals("text")){
				
			}
			String uniquecontent = jo.toString();
			me.setUniquecontent(uniquecontent);
		}
		return me;
	}
}
