package cn.northpark.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.weixin.service.HealthServiceI;
import cn.northpark.weixin.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.weixin.utils.wx.qyh.oauth2.OAuth2Core;

@Controller
@RequestMapping("/health")
public class HealthAction   {


	@Autowired
	private HealthServiceI healthService;

	//跳转下单页面
	@RequestMapping(value="/order")
	public String order(HttpServletRequest request,ModelMap map) {
		
		//获取openid，传递到页面去
		
		return "thymeleaf/order";
	}
	
	//跳转说明页面
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request,ModelMap map) {

		return "detail";
	}

	//保存下单
	@RequestMapping(value="/saveOrder")
	public String  saveOrder(HttpServletRequest request,ModelMap map,HttpServletResponse response) {

		//获取openid
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


}
