package com.bruce.utils.wx.event;

import java.util.Date;
import java.util.List;

import com.bruce.constant.BC_Constant;
import com.bruce.utils.wx.handler.MessageHandler;
import com.bruce.utils.wx.model.MessageEntity;


public class TextAction {
	private static TextAction ts = null;

	public static TextAction getstance(){
		if(null == ts)
			ts = new TextAction();
		return ts;
	}
	public String dealMessage(MessageEntity me){
		String res = "";
		String rm = me.getContent();
		//String[] array = rm.split("_");
		System.out.println("come into dealmessage "+rm);
		if(rm.indexOf(BC_Constant.bd_prefix) == 0){
			String phone = rm.substring(4);
			System.out.println("ok yes i got phone number is "+phone);
			boolean f = false;
			try {
				f = bdphone(phone, me.getFromuser());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(f){
				res = "绑定成功!";
			}else{
				res = "绑定失败!";
			}
			if(!"".equals(res)){
				res = MessageHandler.newstance().callbackcontent(res, me.getFromuser());
			}
			
		}
		System.out.println("text action result is "+res);
		return res;
	}
	public boolean bdphone(String phone,String openid)throws Exception{
		System.out.println(this.getClass()+" come into phone bind action s ");
		boolean f = false;
//		String condition = "userphone='"+phone+"'";
//		LoginEntity bp = null;
//		List<LoginEntity> plist = null;
//		try {
////			plist = RSBLL.getstance().getLoginService().getLoginEntity(condition, 1, 1, "userid");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(plist != null && plist.size() > 0){
//			bp = plist.get(0);
//			bp.setOpenid(openid);
////			RSBLL.getstance().getLoginService().updateLoginEntity(bp);//.updateBuzPartner(bp);
//			f = true;
//		}else if(plist != null && plist.size() == 0){
//			bp = new LoginEntity();
//			bp.setUserphone(phone);
//			bp.setOpenid(openid);
//			bp.setAddtime(new Date().getTime());
////			long uid = RSBLL.getstance().getLoginService().addLoginEntity(bp);
////			if(uid > 0){
////				f = true;
////			}
//		}
		return f;
	}
	public String sendMessage(String openid,String content){
		String res = "";
		return res;
	}
	/**
	public void dealScan(String openid,String content){
		String[] cs = content.split("_");
		if(cs.length == 2){//
			int actiontype = Integer.parseInt(cs[0]);
			String phonenumber = cs[1];
			
			String condition = "orderphone='"+phonenumber+"'";
			try {
//				OrderProcessEntity op = new OrderProcessEntity();
//				RSBLL.getOrderProcessService().insertOrderProcess(arg0);
				List<OrderInfoEntity> list = 
						RSBLL.getOrderService().getOrderListBypage(condition, 1, 1, "orderid");
				if(list != null && list.size() > 0){
					MessageBuz.getstance().sendTextMsg(list.get(0), actiontype);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*/
	public static void main(String[] args){
		String a = "abc";
		String[] ar = a.split("_");
		System.out.println(ar.length);
	}
}
