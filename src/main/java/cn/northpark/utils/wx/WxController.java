package cn.northpark.utils.wx;
//package cn.northpark.utils.wx;
//
///*
// *@author bruce
// *
// **/
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yunlu.hr.model.wx.SNSUserInfo;
//import com.yunlu.hr.model.wx.WeixinOauth2Token;
//import com.yunlu.hr.service.CorporationService;
//import com.yunlu.hr.service.EducationService;
//import com.yunlu.hr.service.HonorService;
//import com.yunlu.hr.service.JobService;
//import com.yunlu.hr.service.KnowledgeService;
//import com.yunlu.hr.service.ResumeCompanyService;
//import com.yunlu.hr.service.ResumeService;
//import com.yunlu.model.CorJob;
//import com.yunlu.model.Corporation;
//import com.yunlu.model.Knowledge;
//import com.yunlu.model.User;
//import com.yunlu.model.UsrResCompany;
//import com.yunlu.model.UsrResEduExperience;
//import com.yunlu.model.UsrResEduHonor;
//import com.yunlu.model.UsrResume;
//import com.yunlu.utils.AccessToken;
//import com.yunlu.utils.Constants;
//import com.yunlu.utils.EmailUtils;
//import com.yunlu.utils.JsonUtils;
//import com.yunlu.utils.MD5Utils;
//import com.yunlu.utils.PageView;
//import com.yunlu.utils.TimeUtils;
//import com.yunlu.utils.WXTokenUtil;
//
//@Controller
//@RequestMapping("wxAction")
//@SuppressWarnings("unchecked")
//public class WxController {
//	/**
//	 * log:日志
//	 */
//	private  static Logger log = LoggerFactory.getLogger(WxController.class);
//	
//	@Autowired
//	private JobService jobManager;
//	@Autowired
//	private  CorporationService corporationManager;
//	@Autowired
//	private KnowledgeService knowledgeManager;
//	@Autowired
//	private com.yunlu.commons.service.UserService userService;
//	@Autowired	
//	private EducationService educationManager;
//	@Autowired	
//	private HonorService honorManager;
//	@Autowired	
//	private ResumeCompanyService resumecompanyManager;
//	@Autowired	
//	private ResumeService resumeManager;
//	
//	///////////////////////////////hr首页///////start////////////////////////////////
//	
//	//本次取得的token时间的存放
//    public static Map<String, String> TokenTimeMap  = new HashMap<String, String>();
//    
//    //本次取得的token时间的存放
//    public static Map<String, AccessToken> TokenMap  = new HashMap<String, AccessToken>();
//	
//	/**
//	 * 跳转绑定注册/登录用户信息
//	 */
//	@RequestMapping(value = "toBindUser" )
//	public String toBindUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		
//		String result = "hr/wx/login";
//		
//		String sessioncode = (String) session.getAttribute("authcode");
//		if(StringUtils.isNotEmpty(sessioncode)){//非首次请求
//			
//			//判断有没有用户信息
//			String openid = (String) request.getSession().getAttribute("openid");
//        	if(StringUtils.isNotEmpty(openid)){
//        		User u = userService.getUserByOpenId(openid);
//        		 if(u !=null){
// 				    try {
// 				    	request.getSession().setAttribute("wxuser",u);
// 						response.sendRedirect("/wxAction/viewResume");
// 						return null;
// 					} catch (IOException e) {
// 						e.printStackTrace();
// 					}
//        		 }
//        	}
//			
//		}else{//首次进入取得code
//			String code = request.getParameter("code");
//			if (!"authdeny".equals(code)&&StringUtils.isNotEmpty(code)) {
//				
//				WeixinOauth2Token weixinOauth2Token = WXTokenUtil.getOauth2AccessToken(Constants.APPID, Constants.AppSecret, code);
//				//自定义获取token
//				AccessToken accesstonken = ownAccesstoken(request, response, session);
//				String token = accesstonken.getToken();
//				// 用户标识
//				String openId = weixinOauth2Token.getOpenId();
//				// 获取用户信息
//				SNSUserInfo snsUserInfo = WXTokenUtil.getSNSUserInfo(token, openId);
//
//				// 设置session信息
//				session.setAttribute("authcode", code);
//				session.setAttribute("snsuserinfo", snsUserInfo);
//				session.setAttribute("openid", openId);
//				session.setAttribute("token", token);
//				
//				if(StringUtils.isNotEmpty(openId)){
//	        		User u = userService.getUserByOpenId(openId);
//	        		if(u!=null){//根据此openid取得用户不为空，跳转！简历页面
//	        			 try {
//	        				request.getSession().setAttribute("wxuser",u);
//	 						response.sendRedirect("/wxAction/viewResume");
//	 						return null;
//	 					} catch (IOException e) {
//	 						e.printStackTrace();
//	 					}
//	        		}
//	        	}
//				
//			}
//		}
//		
//		
//		return result;
//	}
//	
//	
//	/**
//	 * 绑定注册/登录用户信息
//	 */
//	@RequestMapping(value = "bindUser" )
//	@ResponseBody
//	public String bindUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String email, String password ,String openId) {
//		String result = "";
//		String status = "n";
//		String info = "用户名或密码错误";
//
//		String password_ = MD5Utils.encoding(password);
//
//		User user = null;
//
//		if (EmailUtils.isEmail(email)) {// email登陆
//			user = userService.login(email, password_);
//		} 
//		
//		    if(userService.existEmail(email)){//邮箱存在
//		    	if (user != null) {// 登陆成功
//					 user.setLastLoginTime(TimeUtils.nowTime());
//					 user.setWxOpenid(openId);
//					 userService.updateUser(user);
//					 info = "登录并绑定成功";
//					 status = "y";
//					 session.setAttribute("wxuser", user);
//		    	}else{
//		    		 info = "密码错误";
//		    	}
//		    }else{    //邮箱不存在
//		    	      //插入一条
//		    	user = new User();
//		    	user.setEmail(email);
//		    	user.setPassword(password_);
//		    	user.setJoinTime(TimeUtils.nowTime());
//		    	user.setIsInuse(1);
//		    	user.setStatus(0);
//		    	user.setWxOpenid(openId);
//		    	userService.registUser(user, Constants.Role.STUDENT.key, null,request);
//		    	session.setAttribute("wxuser", user);
//		    	info = "注册并绑定成功";
//				status = "y";
//		    }
//			
//
//		result = "{\"info\":\"" + info + "\",\"status\":\"" + status + "\"}";
//		return result;
//	}
//	
//	
//	/**
//	 * 解除绑定，重新绑定账号！
//	 */
//	@RequestMapping(value = "newBindUser" )
//	@ResponseBody
//	public String newBindUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String email, String password ,String openId) {
//		String result = "";
//		String status = "n";
//		String info = "用户名或密码错误";
//
//		
//		
//		String password_ = MD5Utils.encoding(password);
//
//		User user = null;
//
//		
//		
//		if (EmailUtils.isEmail(email)) {// email登陆
//			user = userService.login(email, password_);
//		} 
//		
//		    if(userService.existEmail(email)){//邮箱存在
//		    	if (user != null) {// 登陆成功
//					//删除旧信息，更新旧信息
//						if(StringUtils.isNotEmpty(openId)){
//							User u = userService.getUserByOpenId(openId);
//							if(u!=null){
//								u.setWxOpenid(null);
//								userService.updateUser(u);
//							}
//						}
//						 user.setLastLoginTime(TimeUtils.nowTime());
//						 user.setWxOpenid(openId);
//						 userService.updateUser(user);
//						 info = "更换绑定成功";
//						 status = "y";
//						 session.setAttribute("wxuser", user);
//		    	}else{
//		    		 info = "密码错误";
//		    	}
//		    }else{
//		    	info = "邮箱错误";
//		    }
//			
//
//		result = "{\"info\":\"" + info + "\",\"status\":\"" + status + "\"}";
//		return result;
//	}
//	
//
//	/**
//	 * 获取wx-accesstoken
//	 */
//	@RequestMapping(value = "getAccesstoken")
//	@ResponseBody
//	public String getAccesstoken(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		String lasttime =  TokenTimeMap.get("gettime");
//		AccessToken accessTonken = new AccessToken();
//		if(StringUtils.isNotEmpty(lasttime)){//非首次请求
//			String invaldtime = TimeUtils.getMinuteAfterOrBeforeN(lasttime, 110);
//			try {
//				if(TimeUtils.isInvalid(TimeUtils.nowTime(), invaldtime)){//未过期，从内存获取
//					accessTonken = TokenMap.get("token");
//				}else{//已过期重新请求
//					accessTonken = WXTokenUtil.getAccessToken(Constants.APPID, Constants.AppSecret);
//					TokenTimeMap.put("gettime", TimeUtils.nowTime());
//					TokenMap.put("token", accessTonken);
//				}
//			} catch (ParseException e) {
//				    accessTonken = new AccessToken();
//				e.printStackTrace();
//			}
//		}else{//进系统首次请求
//			         accessTonken = WXTokenUtil.getAccessToken(Constants.APPID, Constants.AppSecret);
//			         TokenTimeMap.put("gettime", TimeUtils.nowTime());
//			         TokenMap.put("token", accessTonken);
//		}
//		
//		return JsonUtils.toJSONString(accessTonken);
//	}
//	
//	
//	/**
//	 * 根据openid 获取User的数据，有的话返回true，没有返回false
//	 */
//	@RequestMapping(value = "bindFlag" )
//	@ResponseBody
//	public String bindFlag(HttpServletRequest request, HttpServletResponse response, HttpSession session,String openId) {
//		String result = "false";
//        if(null!=userService.getUserByOpenId(openId)){
//        	result = "true";
//        }
//		return result;
//	}
//	
//	
//	
//	/**
//	 * 
//	 * @desc  微信 岗位列表加载
//	 * @param request
//	 * @param map
//	 * @param condition
//	 * @param response
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping("/jobList")
//	@ResponseBody
//	public String jobList(HttpServletRequest request, ModelMap map,CorJob condition,
//			HttpServletResponse response, HttpSession session,String currentpage) {
//
//		Map map_ = new HashMap<>();
//		//获取岗位
//		PageView<List<Map<String, Object>>> pageView = jobManager.getJobListBySomeCondition(condition, currentpage);
//		List<Map<String, Object>> list =  (List<Map<String, Object>>) (pageView.getMapRecords()==null?(new ArrayList<Map<String,Object>>()):pageView.getMapRecords());
//		map_.put("pageView", pageView);
//		map_.put("condition", condition);
//		map_.put("list", list);
//        String aaa = JsonUtils.toJSONString(map_);
//		return JsonUtils.toJSONString(map_);
//	}
//	@RequestMapping("/workList")
//	public String workList(HttpServletRequest request) {
//			return "hr/wx/workList";
//
//	}
//	
//	/**
//	 * 
//	 * 名称：toView <br/>
//	 * 描述：查看某岗位具体信息 <br/>
//	 * @param request
//	 * @param id
//	 * @param jobId
//	 * @param knoId
//	 * @param map
//	 * @return String
//	 */
//	@RequestMapping("jobDetail")
//	public String jobDetail(HttpServletRequest request,Integer corId, Integer jobId,ModelMap map) {
//		if (corId!=null) {
//			// 取得公司信息
//			Corporation model = corporationManager.findCorporation(corId);
//			map.put("cor", model);
//			
//			List<Knowledge> knoList =  knowledgeManager.getKnoModelList();
//			map.put("knoList", knoList);
//			// 取得公司发布的职位
//			if (jobId!=null) {
//				CorJob job = jobManager.findJob(jobId);
//				String cityname = jobManager.getCityNameById(job.getRegionId());
//				job.setCityName(cityname);
//				map.put("job", job);
//			}
//
//		}
//		return "hr/wx/workDetails";
//	}
//	
//    //查询简历
//	@RequestMapping("/viewResume")
//	public String viewResume(ModelMap map,Integer usrId,HttpServletRequest request) {
//		UsrResume model = null;
//		
//        User m = (User) request.getSession().getAttribute("wxuser");
//        usrId  = (m==null)?null:m.getId();
//        if(usrId==null){
//        	String openid = (String) request.getSession().getAttribute("openid");
//        	if(StringUtils.isNotEmpty(openid)){
//        		User u = userService.getUserByOpenId(openid);
//        		usrId  = (u==null)?null:u.getId();
//        	}
//        }
//        //查询简历信息
//		if(usrId!=null){
//			 List<UsrResume> rs = resumeManager.findByUsrId(usrId);
//			 if(rs!=null){
//				 if(rs.size()>0){
//					 model = rs.get(0);
//					 if(model.getUsrId()!=null){
//						 model.setUsrId(usrId);
//					 }
//					 map.put("resume", model);
//				 }else{
//					 map.put("jl_empty", "yes");
//				 }
//				 
//			 }
//			 //查询简历信息--end
//			 
//			 
//			 //查询教育信息
//			 List<UsrResEduExperience> eduList = educationManager.findByUsrId(usrId);
//			 map.put("eduList", eduList);
//			 //查询教育信息--end
//			 
//			 //查询获奖信息
//			 List<UsrResEduHonor> honorList = honorManager.findByUsrId(usrId);
//			 map.put("honorList", honorList);
//			 //查询获奖信息--end
//			 
//			 //查询呆过的公司信息
//			 List<UsrResCompany> comList = resumecompanyManager.findByUsrId(usrId);
//			 map.put("comList", comList);
//			 //查询呆过的公司信息--end
//			 
//				
//		}else{//没有简历信息，去完善
//			 map.put("jl_empty", "yes");
//		}
//		
//		return "hr/wx/myinfo";
//	}
//	
//	
//	
//	
//	/**
//	 * 获取wx-accesstoken
//	 */
//	@RequestMapping(value = "ownAccesstoken")
//	public AccessToken ownAccesstoken(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		String lasttime =  TokenTimeMap.get("gettime");
//		AccessToken accessTonken = new AccessToken();
//		if(StringUtils.isNotEmpty(lasttime)){//非首次请求
//			String invaldtime = TimeUtils.getMinuteAfterOrBeforeN(lasttime, 110);
//			try {
//				if(TimeUtils.isInvalid(TimeUtils.nowTime(), invaldtime)){//未过期，从内存获取
//					accessTonken = TokenMap.get("token");
//				}else{//已过期重新请求
//					accessTonken = WXTokenUtil.getAccessToken(Constants.APPID, Constants.AppSecret);
//					TokenTimeMap.put("gettime", TimeUtils.nowTime());
//					TokenMap.put("token", accessTonken);
//				}
//			} catch (ParseException e) {
//				    accessTonken = new AccessToken();
//				e.printStackTrace();
//			}
//		}else{//进系统首次请求
//			         accessTonken = WXTokenUtil.getAccessToken(Constants.APPID, Constants.AppSecret);
//			         TokenTimeMap.put("gettime", TimeUtils.nowTime());
//			         TokenMap.put("token", accessTonken);
//		}
//		
//		return accessTonken;
//	}
//	
//}
