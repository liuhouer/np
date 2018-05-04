package cn.northpark.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.northpark.manager.AstroManager;
import cn.northpark.model.Astro;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.wx.WXTokenUtil;
import cn.northpark.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.utils.wx.qyh.oauth2.OAuth2Core;

@Controller
@RequestMapping("")
public class WeixinAction   {

	
	@Autowired
	private AstroManager astroManager;

	//跳转星座页面
	@RequestMapping(value="/astro")
	public String astro(HttpServletRequest request,ModelMap map) {
	    
		String xzname = request.getParameter("xzname");
		String type = request.getParameter("type");
		
		
		String wx_cop_userid = request.getParameter("wx_cop_userid");
		if(StringUtils.isNotEmpty(wx_cop_userid)){
			map.put("wx_cop_userid", wx_cop_userid);
			List<Astro> list = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
			
			if(!CollectionUtils.isEmpty(list)){
				xzname = list.get(0).getXzname();
			}
		}
		
		if(StringUtils.isEmpty(xzname)){
			xzname = "摩羯座";
		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		
		
		map.put("xzname", xzname);
		map.put("type", type);
		
		
		return "astro";
	}
	
	   //wx跳转星座页面
		@RequestMapping(value="/wxastro")
		public String  wxastro(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		    
			
			String wx_cop_userid = (String) request.getSession().getAttribute("wx_cop_userid");
			
			
			if(StringUtils.isEmpty(wx_cop_userid)){
				
				try {
					request.setCharacterEncoding("UTF-8");


					String code = request.getParameter("code");
					System.out.println("code----"+code);
					if (!"authdeny".equals(code)) {
						String access_token = WeixinQyhUtil.getAccessToken();
						// agentid 跳转链接时所在的企业应用ID
						// 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
						String UserID = OAuth2Core.getUserID(access_token, code, "5");
						wx_cop_userid = UserID;
						request.getSession().setAttribute("wx_cop_userid", UserID);
						map.put("wx_cop_userid", UserID);
					} else {
						System.out.println("授权获取失败，至于为什么，自己找原因。。。"); 
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
			}

			
			
//			 String url = "redirect:http://localhost:8089/astro?wx_cop_userid="+wx_cop_userid;   
//		     return new ModelAndView(url);  
			return "/astro";
			
			
		}
	
	//获取星座数据
	@RequestMapping(value="/getAstro")
	@ResponseBody
	public String getAstro(HttpServletRequest request,ModelMap map) {
		String xzname = request.getParameter("xzname");
		System.out.println("xzname --from --js--ajax=="+xzname);
		String type = request.getParameter("type");
		if(StringUtils.isEmpty(xzname)){
			xzname = "摩羯座";
		}
//		else{
//			try {
//				xzname = URLDecoder.decode(xzname,"UTF-8");
//				System.out.println("xzname --from --URLDecoder=="+xzname);
//				xzname = new String(xzname.getBytes("UTF-8"));
//				System.out.println("xzname --from --getBytes=="+xzname);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		map.put("xzname", xzname);
		map.put("type", type);
		
	    Map<String, Object> data = WXTokenUtil.getXZYS(xzname, type);
	    map.put("data", data);
	    
		
		return JsonUtil.object2json(map);
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
