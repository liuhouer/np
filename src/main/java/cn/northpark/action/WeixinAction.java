package cn.northpark.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import cn.northpark.utils.wx.WXTokenUtil;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/wx")
public class WeixinAction  extends MultiActionController  {

	
//	@Autowired
//	private AstroManager astroManager;
//	/**
//	 * @author jeyy - 验证企业号的请求
//	 * @param request
//	 * @param response
//	 * @return sEchoStr
//	 */
//	@RequestMapping(method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
//	public void verfy(HttpServletRequest request,  HttpServletResponse response)
//			throws Exception {
//		response.setCharacterEncoding("UTF-8");
//		try {
//
//			String sToken = ParamesAPI.token;
//			String sCorpID = ParamesAPI.corpId;
//			String sEncodingAESKey = ParamesAPI.encodingAESKey;
//
//			/*
//			 * 假定公众平台上开发者设置的Token 1. 验证回调URL 点击验证时，企业收到类似请求： GET
//			 * /cgi-bin/wxpush?msg_signature
//			 * =5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3
//			 * &timestamp=1409659589&nonce=263014780
//			 * &echostr=P9nAzCzyDtyTWESHep1vC5X9xho
//			 * %2FqYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp
//			 * %2B4RPcs8TgAE7OaBO%2BFZXvnaqQ%3D%3D HTTP/1.1 Host:
//			 * qy.weixin.qq.com 接收到该请求时，企业应1.先验证签名的正确性 2. 解密出echostr原文。
//			 * 以上两步用VerifyURL完成
//			 */
//
//			// 微信加密签名
//			String sVerifyMsgSig = request.getParameter("msg_signature");
//			// 时间戳
//			String sVerifyTimeStamp = request.getParameter("timestamp");
//			// 随机数
//			String sVerifyNonce = request.getParameter("nonce");
//			// 随机字符串
//		 
//			String sVerifyEchoStr = request.getParameter("echostr") ;
//
//
//			String sEchoStr; // 需要返回的明文
//			PrintWriter out = response.getWriter();
//			WXBizMsgCrypt wxcpt;
//			try {
//				wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
//				sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
//						sVerifyNonce, sVerifyEchoStr);
//				System.out.println("fanhui---->"+sEchoStr);
//				// 验证URL成功，将sEchoStr返回
//				out.print(sEchoStr);
//			} catch (AesException e1) {
//				e1.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//	}
//	
//
//	
//	
//	/**
//	 * 处理微信发来的请求
//	 * 
//	 * @param request
//	 * @return xml
//	 */
//	@RequestMapping(method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
//	public  String processRequest(HttpServletRequest request,HttpServletResponse response) {
//		
//		
//		String encryptMsg = "";
//		try {
//			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
//			request.setCharacterEncoding("UTF-8");
//			response.setCharacterEncoding("UTF-8");
//
//			// 微信加密签名
//			String msg_signature = request.getParameter("msg_signature");
//			// 时间戳
//			String timestamp = request.getParameter("timestamp");
//			// 随机数
//			String nonce = request.getParameter("nonce");
//
//			// 从请求中读取整个post数据
//			InputStream inputStream = request.getInputStream();
//			// commons.io.jar 方法
//			String Post = IOUtils.toString(inputStream, "UTF-8");
//			// Post打印结果
//			System.out.println(Post);
//			System.out.println("msg_signature========="+msg_signature);
//			System.out.println("timestamp==========="+timestamp);
//			System.out.println("nonce========"+nonce);
//
//			String Msg = "";
//			WXBizMsgCrypt wxcpt = null;
//			try {
//				wxcpt = new WXBizMsgCrypt(ParamesAPI.token, ParamesAPI.encodingAESKey, ParamesAPI.corpId);
//				
//				System.out.println("WXBizMsgCrypt entity===="+wxcpt);
//				// 解密消息
//				Msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, Post);
//				
//				System.out.println("after decrypt msg: " + Msg);
//				// TODO: 解析出明文xml标签的内容进行处理
//				// For example:
//				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//				DocumentBuilder db = dbf.newDocumentBuilder();
//				StringReader sr = new StringReader(Msg);
//				InputSource is = new InputSource(sr);
//				Document document = db.parse(is);
//
//				Element root = document.getDocumentElement();
//				NodeList nodelist1 = root.getElementsByTagName("Content");
//				if(nodelist1!=null){
//					if(nodelist1.getLength()>0){
//						String Content = nodelist1.item(0).getTextContent();
//						System.out.println("Content：" + Content);
//					}
//				}
//				
//				
//			} catch (AesException e) {
//				e.printStackTrace();
//			}
//			// Msg打印结果
//			System.out.println("Msg打印结果：" + Msg);
//
//			// 调用核心业务类接收消息、处理消息
//			String respMessage = CoreService.processRequest(Msg,request);
//
//			// respMessage打印结果
//			System.out.println("respMessage打印结果：" + respMessage);
//			
//			try {
//				// 加密回复消息
//				encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
//			} catch (AesException e) {
//				e.printStackTrace();
//			}
//
//			// 响应消息
//			PrintWriter out = response.getWriter();
//			out.print(encryptMsg);
//			out.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		return encryptMsg;
//	}
//	
	
	
	
//	/**
//	 * oauth获取userid
//	 * @param request
//	 * @param map
//	 */
//	@RequestMapping(value="/qyh/getUserId")
//	public void getUserId(HttpServletRequest request,ModelMap map) {
//		try {
//			request.setCharacterEncoding("UTF-8");
//
//
//			String code = request.getParameter("code");
//			System.out.println("code----"+code);
//			if (!"authdeny".equals(code)) {
//				String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
//				// agentid 跳转链接时所在的企业应用ID
//				// 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
//				String UserID = OAuth2Core.getUserID(access_token, code, "5");
//				request.setAttribute("wx_cop_userid", UserID);
//			} else {
//				System.out.println("授权获取失败，至于为什么，自己找原因。。。"); 
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//
//	}

	//跳转星座页面
	@RequestMapping(value="/astro")
	public String astro(HttpServletRequest request,ModelMap map) {
	    
		String xzname = request.getParameter("xzname");
		String type = request.getParameter("type");
		
		
		
//		if(StringUtils.isNotEmpty(wx_cop_userid)){
//			map.put("wx_cop_userid", wx_cop_userid);
//			List<Astro> list = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
//			
//			if(!CollectionUtils.isEmpty(list)){
//				xzname = list.get(0).getXzname();
//			}
//		}
		
		if(StringUtils.isEmpty(xzname)){
			xzname = "摩羯座";
		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		
		
		map.put("xzname", xzname);
		map.put("type", type);
		
		
		return "/astro";
	}
	
	   //wx跳转星座页面
//		@RequestMapping(value="/wxastro")
//		public String wxastro(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
//		    
//			String xzname = request.getParameter("xzname");
//			String type = request.getParameter("type");
//			System.out.println("点击的菜单类型=====》"+type);
//			
//			String wx_cop_userid = (String) request.getSession().getAttribute("wx_cop_userid");
//			
//			
//			if(StringUtils.isEmpty(wx_cop_userid)){
//				
//				try {
//					request.setCharacterEncoding("UTF-8");
//
//
//					String code = request.getParameter("code");
//					System.out.println("code----"+code);
//					if (!"authdeny".equals(code)) {
//						String access_token = WeixinQyhUtil.getAccessToken();
//						// agentid 跳转链接时所在的企业应用ID
//						// 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
//						String UserID = OAuth2Core.getUserID(access_token, code, "5");
//						request.getSession().setAttribute("wx_cop_userid", UserID);
//						map.put("wx_cop_userid", UserID);
//					} else {
//						System.out.println("授权获取失败，至于为什么，自己找原因。。。"); 
//					}
//
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				
//				
//			}else{
//				
//				map.put("wx_cop_userid", wx_cop_userid);
//				List<Astro> list = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
//				
//				if(!CollectionUtils.isEmpty(list)){
//					xzname = list.get(0).getXzname();
//				}
//			}
//			
//			
//			
//			if(StringUtils.isEmpty(xzname)){
//				xzname = "摩羯座";
//			}
//			if(StringUtils.isEmpty(type)){
//				type = "today";
//			}
//			
//			
//			
//			map.put("xzname", xzname);
//			map.put("type", type);
//			
//			
//			return "/astro";
//		}
	
	//获取星座数据
	@RequestMapping(value="/getAstro")
	public String getAstro(HttpServletRequest request,ModelMap map) {
		String xzname = request.getParameter("xzname");
		String type = request.getParameter("type");
		if(StringUtils.isEmpty(xzname)){
			xzname = "摩羯座";
		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		map.put("xzname", xzname);
		map.put("type", type);
		
	    JSONObject data = WXTokenUtil.getXZYS(xzname, type);
	    map.put("data", data);
	    
		
		return "/page/astro/"+type;
	}
	
		//定制星座推送
//		@RequestMapping(value="/bindAstro")
//		@ResponseBody
//		public String bindAstro(HttpServletRequest request,ModelMap map) {
//			String xzname = request.getParameter("xzname");
//			String wx_cop_userid = request.getParameter("wx_cop_userid");
//			String type = request.getParameter("type");
//			
//			Astro model = null;
//			List<Astro> list = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
//			if(!CollectionUtils.isEmpty(list)){
//				model = list.get(0);
//				if("cancel".equals(type)){
//					//取消绑定
//					model.setStatus("0");
//					astroManager.updateAstro(model);
//				}else{
//					//更新绑定
//					model.setXzname(xzname);
//					astroManager.updateAstro(model);
//				}
//				
//			}else{
//				//新用户绑定
//				model = new Astro();
//				model.setAddtime(TimeUtils.nowTime());
//				model.setStatus("1");
//				model.setWx_cop_userid(wx_cop_userid);
//				model.setXzname(xzname);
//				astroManager.addAstro(model);
//			}
//			
//			return "/page/astro/"+type;
//		}
//	
	
	
	

}
