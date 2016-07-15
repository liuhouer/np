package cn.northpark.utils.wx.event;

import cn.northpark.constant.BC_Constant;


public class ScanAction {

	
	private static ScanAction sa = null;
	public static ScanAction getstance(){
		if(sa == null)
			sa = new ScanAction();
		return sa;
	}
	public void dealScan(String openid,int scenid){
		System.out.println("come into dealscan");
		if(BC_Constant.scend_id_map.containsKey(scenid)){//服务商绑定处理
			long uid = BC_Constant.scend_id_map.get(scenid);
			System.out.println("get uid "+uid);
			if(uid > 0){
				try {//先判断uid是否是服务商
//					LvzProviderServiceEntity vendor = RSBLL.getstance().getLvzProviderService().getEntityById(uid);
//					if(vendor!=null){
//						vendor.setOpenid(openid);
//						RSBLL.getstance().getLvzProviderService().updateProviderServiceEntity(vendor);
//						new TextHandler().sendTextMessage(openid, "微信绑定成功！");
//					}else{//判断是否是员工id
//						BFEmployersEntity ee = RSBLL.getstance().getEmployerService().getEmployersEntityById(uid);
//						if(ee != null){
//							ee.setOpenid(openid);
//							RSBLL.getstance().getEmployerService().updateEmployersEntity(ee);
//							new TextHandler().sendTextMessage(openid, "微信绑定成功！");
//						}
//					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	//根据一个id生成专用二维码
//	@Path("/emp/bindpic/{empid:\\d+}")
//	public ActionResult bindWeixin(long empid){
//		long uid = empid;
//		int scenid = com.jx.blackmen.utils.UtilsHelper.getUniqueSceneid();
//		com.jx.blackmen.utils.MContents.scend_id_map.put(scenid,uid);
//		System.out.println("cmps con scendid "+scenid+" and emplid "+uid);
//		
//		String imgurl = "";
//		try {
//			imgurl = RSBLL.getstance().getWeixinService().makeWeixinQuer(MContents.weixin_app_id, MContents.weixin_app_secret_id,
//					scenid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("make pic imgurl is "+imgurl);
//		MContents.scend_id_map.put(scenid, empid);
//		beat().getModel().add("imgurl", imgurl);
//		
//		String bindType=beat().getRequest().getParameter("bindType");
//		if("vendorBind".equals(bindType)){//是服务商绑定操作
//			JSONObject result=new JSONObject();
//			result.put("imgurl", imgurl);
//			String callback=beat().getRequest().getParameter("jsonpCallback");
//			return ActionResultUtils.renderJson(callback+"("+result.toString()+")");
//		}
//		return view("houtai/bindpic");
//	}	
}
