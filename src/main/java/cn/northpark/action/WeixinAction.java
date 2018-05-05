package cn.northpark.action;

import java.net.URLEncoder;
import java.util.HashMap;
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

import cn.northpark.constant.BC_Constant.XINGZUO;
import cn.northpark.manager.AstroManager;
import cn.northpark.model.Astro;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.wx.WXTokenUtil;
import cn.northpark.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.utils.wx.qyh.oauth2.OAuth2Core;

@Controller
@RequestMapping("/weixin")
public class WeixinAction   {

	public static Map<String,String> codemap = new HashMap<>();
	
	@Autowired
	private AstroManager astroManager;

	//跳转星座页面
	@RequestMapping(value="/astro")
	public String astro(HttpServletRequest request,ModelMap map) {
	    
		String xzname = request.getParameter("xzname");
		String type = request.getParameter("type");
		
		
		if(StringUtils.isEmpty(xzname)){
			
			xzname = String.valueOf(XINGZUO.MOJIE.getIndex());
		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		
		
		map.put("xzname", xzname);
		map.put("type", type);
		
		
		return "/astro";
	}
	
	   //wx跳转星座页面
		@RequestMapping(value="/wxastro")
		public String  wxastro(HttpServletRequest request,ModelMap map,HttpServletResponse response) {
		    
			
			String wx_cop_userid = "";
			
			String type = request.getParameter("type");
			
			String xzname = "";
			
			if(StringUtils.isEmpty(wx_cop_userid)){
				
				try {
					request.setCharacterEncoding("UTF-8");


					String code = request.getParameter("code");
					System.out.println("code----"+code);
					if (!"authdeny".equals(code)) {
						
						String UserID = codemap.get(code);
						
						if(StringUtils.isEmpty(UserID)) {
						
							String access_token = WeixinQyhUtil.getAccessToken();
							// agentid 跳转链接时所在的企业应用ID
							// 管理员须拥有agent的使用权限；agentid必须和跳转链接时所在的企业应用ID相同
						    UserID = OAuth2Core.getUserID(access_token, code, "5");
							wx_cop_userid = UserID;
							request.getSession().setAttribute("wx_cop_userid", UserID);
							System.out.println("wx_cop_userid-------"+wx_cop_userid);
							if(StringUtils.isNotEmpty(wx_cop_userid)&&StringUtils.isNotEmpty(code)) {
								codemap.put(code, wx_cop_userid);
							}
						}
						
					} else {
						System.out.println("授权获取失败，至于为什么，自己找原因。。。"); 
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
			}

			if(StringUtils.isNotEmpty(wx_cop_userid)) {
				List<Astro> resultlist = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
				if(!CollectionUtils.isEmpty(resultlist)) {
					 xzname = resultlist.get(0).getXzname();
					 System.out.println(xzname);
				}
			}
			
			map.put("xzname", xzname);
			map.put("type", type);
			map.put("wx_cop_userid", wx_cop_userid);
			
			return "/astro";
			
			
		}
	
	//获取星座数据
	@RequestMapping(value="/getAstro")
	@ResponseBody
	public Map<String, Object> getAstro(HttpServletRequest request) {
		String xzname = request.getParameter("xzname");
		System.out.println("xzname --from --js--ajax=="+xzname);
		String type = request.getParameter("type");
		if(StringUtils.isEmpty(xzname)){
			xzname = String.valueOf(XINGZUO.MOJIE.getIndex());
		}else {
			for (XINGZUO simpleEnum : XINGZUO.values()) { 
				
				System.out.println(simpleEnum + "  ordinal  " + simpleEnum.ordinal()+"     name   "+ simpleEnum.getName());  
				if(xzname.equals(simpleEnum.toString())) {
					
					xzname = String.valueOf(simpleEnum.getIndex());
					break;
				}
	        }  
		}
		if(StringUtils.isEmpty(type)){
			type = "today";
		}
		
		
	    String data = WXTokenUtil.getXZYS(xzname, type);
	    
		
		return JsonUtil.json2map(data);
	}
	
		//定制星座推送
		@RequestMapping(value="/bindAstro")
		@ResponseBody
		public String bindAstro(HttpServletRequest request,ModelMap map) {
			String xzname = request.getParameter("xzname");
			String wx_cop_userid = request.getParameter("wx_cop_userid");
			String type = request.getParameter("type");
			
			Astro model = null;
			List<Astro> list = astroManager.findByCondition(" where wx_cop_userid = '"+wx_cop_userid+"' ").getResultlist();
			if(!CollectionUtils.isEmpty(list)){
				model = list.get(0);
				if("cancel".equals(type)){
					//取消绑定
					model.setStatus("0");
					astroManager.updateAstro(model);
				}else{
					//更新绑定
					model.setXzname(xzname);
					astroManager.updateAstro(model);
				}
				
			}else{
				//新用户绑定
				model = new Astro();
				model.setAddtime(TimeUtils.nowTime());
				model.setStatus("1");
				model.setType("1");
				model.setWx_cop_userid(wx_cop_userid);
				model.setXzname(xzname);
				astroManager.addAstro(model);
			}
			
			return "/page/astro/"+type;
		}
	
	
	
	

}
