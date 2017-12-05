
package cn.northpark.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.northpark.interceptor.CheckLogin;
import cn.northpark.manager.ResetManager;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.manager.UserManager;
import cn.northpark.manager.UserprofileManager;
import cn.northpark.model.Reset;
import cn.northpark.model.User;
import cn.northpark.model.UserFollow;
import cn.northpark.model.Userprofile;
import cn.northpark.query.condition.UserLyricsQueryCondition;
import cn.northpark.utils.Base64Util;
import cn.northpark.utils.EmailUtils;
import cn.northpark.utils.FileUtils;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.safe.WAQ;

@Controller
public class UserAction {

     /**
     * 域名
     */
     private final String DOMAIN = "northpark.cn";
     /**
      * 定向action'
      */
	 private final String LIST_ACTION = "redirect:/love";
	 private final String HOME_ACTION = "redirect:/";
	 private final String REG_ACTION = "redirect:/signup";
	 @Autowired	
	 private UserManager userManager;
	 @Autowired	
	 private UserprofileManager userprofileManager;
	 @Autowired	
	 private UserLyricsManager userlyricsManager;
	 @Autowired	
	 private UserFollowManager userfollowManager;
	 @Autowired	
	 private ResetManager resetManager;

	 
	     private static final Logger LOGGER = Logger.getLogger(UserAction.class);  
	 	
	 
	 	/**
	 	 * @ 页面对有关于用户的操作，先异步进行判断，假如有用户，返回1.没有用户直接跳转到登陆界面，并且传入需要return的url。
	 	 * @param request
	 	 * @param response
	 	 * @param map
	 	 * @param url
	 	 * @return 0、1 【0：没有 | 1：有】
	 	 */
	 	@RequestMapping("/cm/loginFlag")
	 	@ResponseBody
		public String loginFlag(HttpServletRequest request) {
	            String msg = "0";
	            User user = (User) request.getSession().getAttribute("user");
	 			if(user!=null){
	 				msg = "1";
	 			}
	 	     
	 			
	 	   return msg ; 
	         
		}
	 	
	 	/**
	 	 * @ 判断email的存在性
	 	 * @param request
	 	 * @param response
	 	 * @param map
	 	 * @param url
	 	 * @return 0、1 【0：没有 | 1：有】
	 	 */
	 	@RequestMapping("/cm/emailFlag")
	 	@ResponseBody
		public String emailFlag(HttpServletRequest request, HttpServletResponse response,ModelMap map,String email) {
	 		int num = userManager.countHql( " where email = '"+email+"' " );
	 		String msg = "exist";//存在；
			if(num<=0){
				msg = "notexist";//不存在	     
			}
	 	   return msg ; 
	         
		}
	 	
	 	/**
	 	 * @ 判断tail_slug的存在性
	 	 * @param request
	 	 * @param response
	 	 * @param map
	 	 * @param url
	 	 * @return 0、1 【0：没有 | 1：有】
	 	 */
	 	@RequestMapping("/cm/tailFlag")
	 	@ResponseBody
		public String tailFlag(HttpServletRequest request, HttpServletResponse response,ModelMap map,String tail) {
	 		int num = userManager.countHql(" where tail_slug = '"+tail+"' ");
	 		String msg = "exist";//存在；
			if(num<=0){
				msg = "notexist";//不存在	     
			}
	 	   return msg ; 
	         
		}
	 	
	 	/**
		 * 发送”修改密码“邮件
		 */
		@RequestMapping("/cm/resetEmail")
		@ResponseBody
		public String resetEmail(HttpServletRequest request,
				HttpServletResponse response, ModelMap map,String email)
				throws ParseException {
			    String userid = "";
			    List<User> list = userManager.findByCondition(" where email = '"+email+"' ").getResultlist();
			    if(!CollectionUtils.isEmpty(list)){
			    		userid = String.valueOf(list.get(0).getId());
			    }
				Reset reset = new Reset();
				reset.setCreated_time(TimeUtils.nowTime());
				String code = cn.northpark.utils.UUIDUtils.geneInt();
				reset.setAuth_code(code);
				reset.setInvalid_time(TimeUtils.getDayAfter(TimeUtils.nowTime()));
				reset.setIs_email_authed(0);
				
				reset.setUser_id(Integer.parseInt(userid));
				resetManager.addReset(reset);
				String result = "";
				try {
					EmailUtils.emailUtil.changePwd(email, userid, code, request);
				} catch (Exception e) {
					result = "exception";
				}
			    result = "success";
			return result;
		}
		/**
		 * 重置密码
		 */
		@RequestMapping("/cm/reset")
		public String reset(HttpServletRequest request,
				HttpServletResponse response, ModelMap map, String userid,
				String auth_code) throws ParseException {

			String result = "false";
			List<cn.northpark.model.Reset> gtList = resetManager.findByCondition(" where user_id='"+userid+"' and auth_code='"+auth_code+"' order by created_time desc").getResultlist();
			try {
				if (!CollectionUtils.isEmpty(gtList)) {
					 Reset gtmodel = gtList.get(0);
					if (gtmodel.getAuth_code().equals(auth_code)) {// Email认证通过
						if (StringUtils.isNotEmpty(gtmodel.getInvalid_time())) {// 设置了失效时间
							if (TimeUtils.isInvalid(TimeUtils.nowTime(),
									gtmodel.getInvalid_time())&& (0==gtmodel.getIs_email_authed() )) {// 时间未过期
								User user = userManager.findUser(Integer.parseInt(userid));
								if (user != null) {
									user.setPassword(Base64Util.JIAMI(auth_code));
									userManager.updateUser(user);
									
									request.getSession().setAttribute("user", user);
									//更新数据
									gtmodel.setIs_email_authed(1);
									resetManager.updateReset(gtmodel);
									result = "success";
								}
							} else {
								result = "isolded";
							}
						} else {// 没有设置失效时间
							if(0==gtmodel.getIs_email_authed()){
								User user = userManager.findUser(Integer.parseInt(userid));
								if (user != null) {
									user.setPassword(Base64Util.JIAMI(auth_code));
									userManager.updateUser(user);
									request.getSession().setAttribute("user", user);
									//更新数据
									gtmodel.setIs_email_authed(1);
									resetManager.updateReset(gtmodel);
									result = "success";
								}
							}else{
								result = "isolded";
							}
						}
					}
				} else {// 已失效，重新获取验证码
					result = "invalid";
				}
			} catch (Exception e) {
				LOGGER.error("commonAction------>",e);
				result = "exception";
			} finally {
				map.addAttribute("msg", result);
			}
			return "/forget";
		}

	 	
	 
	 
	   /* //一键修改密码
	    @RequestMapping("/cm/pwddd")
	 	@ResponseBody
		public String pwddd() {
	 		String msg = "success";
	 		List<User> list = userManager.findByCondition(" where 1=1").getResultlist();
	 		for (int i = 0; i < list.size(); i++) {
				String pwd=  list.get(i).getPassword();
				String encodepwd= Base64Util.JIAMI(pwd);
				String id  = list.get(i).getId();
				String sql = "update bc_user a  set a.password = '"+encodepwd+"' where a.id = '"+id+"' ";
				userManager.pwddd(sql);
			}
	 		
			return msg;
		}*/
	 
	 //成为粉丝
	 	@RequestMapping("/cm/follow")
	 	@ResponseBody
		public String follow(ModelMap map,String author_id,String follow_id) {
	 		String msg = "success";
	 		
	 		try {
	 			
	 			String ygznums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
	 			List<Map<String, Object>> ygznums_list = userfollowManager.querySql(ygznums_sql, author_id,follow_id);
		 		String nums = (String) ygznums_list.get(0).get("nums");
	 			if(Integer.parseInt(nums)==0){
		 			UserFollow uf = new UserFollow();
			 		uf.setAuthor_id(Integer.parseInt(author_id));
			 		uf.setFollow_id(Integer.parseInt(follow_id));
			 		uf.setStatus(1);
			 		uf.setCreate_time(TimeUtils.nowTime());
			 		if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
			 			userfollowManager.addUserFollow(uf);
			 		}
		 		}
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exp";
			}
	 		
	 		
	 		
			return msg;
		}
	 
	 	//移除某个粉丝
	 	@RequestMapping("/cm/rmfollow")
	 	@ResponseBody
		public String rmfollow(ModelMap map,String id) {
	 		String msg = "success";
	 		
	 		try {
	 			if(StringUtils.isNotEmpty(id)){
	 				userfollowManager.delUserFollow(Integer.parseInt(id));
	 			}else{
	 				msg= "nullid";
	 			}
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exp";
			}
	 		
			return msg;
		}
	 
	 
	 
	 
	 
	 	@RequestMapping("/cm/xbjt")
		public String xbjt(ModelMap map) {
			return "/bruce-quiet-listen";
		}
	 	
	 	@RequestMapping("/cm/vlog")
		public String vlog(ModelMap map) {
			return "/vlog";
		}
	 	
	 	
	 	/**
	 	 * 404错误
	 	 * @param map
	 	 * @return
	 	 */
	 	@RequestMapping("/building")
		public String building(ModelMap map,HttpServletRequest request) {
	 		
	 		//记录访问者的IP、记录访问者的url请求路径，打印到日志里去
//	 		String IP = AddressUtils.getIpAddr(request);
//	 		String queryString = request.getQueryString();
//	 		String url = request.getRequestURI();
//	 		if(StringUtils.isNotBlank(queryString)){
//	 			url = url+"?"+queryString;
//	 		}
//	 		LOGGER.error("IP:"+IP+"|||URL:"+url+"|||TIME:"+TimeUtils.nowTime());
			return "/building";
		}
	 	
	 	
	 	
	 	
	 	/**
	 	 * 500错误
	 	 * @param map
	 	 * @return
	 	 */
	 	@RequestMapping("/error")
		public String error(ModelMap map) {
			return "/error";
		}
		
		
		/**
		 * 跳转到登陆后的个人中心-最爱【需要判断登录】
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/pcentral")
		@CheckLogin
		public String pcentral(ModelMap map,HttpServletRequest request) {

			 String rs = "/myself";
			User user   = (User) request.getSession().getAttribute("user");
			request.getSession().removeAttribute("tabs");
			request.getSession().setAttribute("tabs","pcenter");
					map.put("MyInfo", user);
					
					//查询个人歌词最爱历史
					String sql =  "select c.updatedate,c.id,c.title,c.albumImg,b.id as userlyricsid from    bc_user_lyrics b  join bc_lyrics c on b.lyricsid = c.id where b.userid = ? order by c.updatedate desc" ;
					
					List<Map<String, Object>> list  = userManager.querySql(sql,String.valueOf(user.getId()));
					for (int i = 0; i < list.size(); i++) {
						//--批量处理时间
						list.get(i).put("updatedate", TimeUtils.formatToNear((String)list.get(i).get("updatedate")));
						
					}
					map.addAttribute("Lovelist", list);
			return rs;
		}
		
		/**
		 * 跳转到登陆后的个人中心--我的粉丝【需要判断登录】
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/myfans")
		@CheckLogin
		public String myfans(ModelMap map, HttpServletRequest request) {

			 //取得当前用户
	         User user = (User) request.getSession().getAttribute("user");
	        	 
	 			map.put("MyInfo", user);
	 			
	 			//查询该用户的粉丝列表
	 			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
	 			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+user.getId()+"' ").getResultlist();
	 			if(!CollectionUtils.isEmpty(fan_list)){
	 					for (int i = 0; i < fan_list.size(); i++) {
	 						Map<String,Object> map_ = new HashMap<String,Object>();
	 						String u_id = String.valueOf(fan_list.get(i).getFollow_id());
	 						User uu = userManager.findUser(Integer.parseInt(u_id));
	 						
	 						UserFollow ff = fan_list.get(i);
	 						//处理时间格式
	 						ff.setCreate_time(TimeUtils.formatToNear(ff.getCreate_time()));
	 						map_.put("user", uu);
	 						map_.put("follow", ff);
	 						fanlist.add(map_);
	 					}
	 			}
	 			map.put("fanlist", fanlist);
			
			
			return "/fans";
		}
		
		/**
		 * 跳转到某人个人中心--ta的粉丝
		 * @param map
		 * @param userid
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/fans/{userid}")
		public String fans(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
		try{
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
			
			//查询该用户的粉丝列表
			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+userid+"' ").getResultlist();
			if(!CollectionUtils.isEmpty(fan_list)){
					for (int i = 0; i < fan_list.size(); i++) {
						Map<String,Object> map_ = new HashMap<String,Object>();
						String u_id = String.valueOf(fan_list.get(i).getFollow_id());
						User uu = userManager.findUser(Integer.parseInt(u_id));
						
						UserFollow ff = fan_list.get(i);
						//处理时间格式
						ff.setCreate_time(TimeUtils.formatToNear(ff.getCreate_time()));
						map_.put("user", uu);
						map_.put("follow", ff);
						fanlist.add(map_);
					}
			}
			map.put("fanlist", fanlist);
			
			 //取得当前用户对作者的关注状态
	         User lo_user = (User) request.getSession().getAttribute("user");
	         if(lo_user!=null){
	        	 String follow_id = String.valueOf(lo_user.getId());
	        	 String author_id = userid;
	        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
	        		 
	        		 String ygznums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
	 	 			List<Map<String, Object>> ygznums_list = userfollowManager.querySql(ygznums_sql, author_id,follow_id);
	 	 			String nums =  ygznums_list.get(0).get("nums").toString();
	        		 if(Integer.parseInt(nums)>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
		}catch(Exception e){
			LOGGER.error("commonAction------>",e);
			
		}
			return "/spacefans";
		}
		
		/**
		 * 跳转到某人的个人中心【参数是用户id】
		 * @param map
		 * @param userid
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/detail/{userid}")
		public String detail(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
			try{
				
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
			
			//查询个人歌词最爱历史
			String sql =  "select c.updatedate,c.id,c.title,c.albumImg,b.id as userlyricsid from    bc_user_lyrics b  join bc_lyrics c on b.lyricsid = c.id where b.userid = ? order by c.updatedate desc" ;

			if(user!=null){
				List<Map<String, Object>> list  = userManager.querySql(sql,String.valueOf(user.getId()));
				for (int i = 0; i < list.size(); i++) {
					//--批量处理时间
					list.get(i).put("updatedate", TimeUtils.formatToNear((String)list.get(i).get("updatedate")));;
				
				}
				map.addAttribute("Lovelist", list);
				 //取得当前用户对作者的关注状态
		         User lo_user = (User) request.getSession().getAttribute("user");
		         if(lo_user!=null){
		        	 String follow_id = String.valueOf(lo_user.getId());
		        	 String author_id = userid;
		        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
		        		
		        		 String ygznums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
		 	 			List<Map<String, Object>> ygznums_list = userfollowManager.querySql(ygznums_sql, author_id,follow_id);
		 	 			String nums =  ygznums_list.get(0).get("nums").toString();
		        		 if(Integer.parseInt(nums)>0){
		        			 map.put("gz", "ygz");
		        		 }
		        	 }
		        	 
		         }
			}
			
	         
			}catch(Exception e){
				LOGGER.error("commonAction------>",e);
			}
			return "/space";
		}
		
		/**
		 * 跳转到某人的个人中心【参数是用户名】
		 * @param map
		 * @param tail_slug
		 * @param request
		 * @return
		 */
		@RequestMapping("/people/{tail_slug}")
		public String people(ModelMap map, @PathVariable String tail_slug ,HttpServletRequest request) {
			try{
			
				tail_slug = WAQ.forSQL().escapeSql(tail_slug);	
			User user = null;
			List<User> ul = userManager.findByCondition(" where tail_slug = '"+tail_slug+"' ").getResultlist();
			if(!CollectionUtils.isEmpty(ul)){
					user = ul.get(0);
			}
			map.put("MyInfo", user);
			//查询个人歌词最爱历史
			String sql =  "select c.updatedate,c.id,c.title,c.albumImg,b.id as userlyricsid from    bc_user_lyrics b  join bc_lyrics c on b.lyricsid = c.id where b.userid = ? order by c.updatedate desc" ;
			
			if(user!=null){
				List<Map<String, Object>> list  = userManager.querySql(sql,String.valueOf(user.getId()));
				for (int i = 0; i < list.size(); i++) {
					
					//--批量处理时间
					list.get(i).put("updatedate", TimeUtils.formatToNear((String)list.get(i).get("updatedate")));
				}
				map.addAttribute("Lovelist", list);
				
				
				 //取得当前用户对作者的关注状态
		         User lo_user = (User) request.getSession().getAttribute("user");
		         if(lo_user!=null){
		        	 String follow_id = String.valueOf(lo_user.getId());
		        	 String author_id = String.valueOf(user.getId());
		        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
		        		 
		        		 String ygznums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
		 	 			List<Map<String, Object>> ygznums_list = userfollowManager.querySql(ygznums_sql, author_id,follow_id);
		 		 		String nums =  ygznums_list.get(0).get("nums").toString();
		        		 if(Integer.parseInt(nums)>0){
		        			 map.put("gz", "ygz");
		        		 }
		        	 }
		        	 
		         }
			}
			
			
			}catch(Exception e){
				LOGGER.error("commonAction------>",e);
			}
			return "/space";
		}
		
		/**
		 * 跳转编辑个人资料【需要验证登录】
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/toEditInfo")
		@CheckLogin
		public String toEditInfo(ModelMap map,HttpServletRequest request) {
	              User u = (User) request.getSession().getAttribute("user");
			
			User user = userManager.findUser(u.getId());
			map.put("MyInfo", user);
			Userprofile Duser = userprofileManager.getModelByUserid(String.valueOf(u.getId()));
			map.put("Dinfo", Duser);
			return "/EditInfo";
		}
		
		/**
		 * 
		 * 
		 * 保存个人资料
		 * @param session
		 * @param model
		 * @param map
		 * @param oldpath
		 * @param file
		 * @param new_password
		 * @param new_password_confirmation
		 * @param courseware
		 * @param year_of_birth
		 * @param user_slug
		 * @return
		 */
		@RequestMapping("/cm/saveEditInfo")
		public String saveEditInfo(HttpSession session,User model,ModelMap map,String oldpath, @RequestParam(value = "file", required = false) MultipartFile[] file,String new_password,String new_password_confirmation,String courseware,String year_of_birth,String user_slug) {
			// 执行删除图片缓存
			FileUtils.removeOldFile(oldpath, file);
			
			//执行上传		
			List<String> filelist = FileUtils.commonUpload(file, FileUtils.suffix_head);
	        //执行上传end

			if(filelist.size()>0){
				model.setHeadpath(filelist.get(0));
			}else{
				model.setHeadpath(oldpath);
			}
		        
		        //处理密码信息
		        if(!StringUtils.isEmpty(new_password)&&!StringUtils.isEmpty(new_password_confirmation)&&new_password.equals(new_password_confirmation)){
		        	model.setPassword(Base64Util.JIAMI(new_password));
		        }
		        //处理密码信息
			   this.userManager.updateUser(model);
			   session.setAttribute("user",model);
			  //保存User表信息-------结束
			   
			 //保存User明细表信息-------start
            String userid = String.valueOf(model.getId());
            Userprofile up = userprofileManager.getModelByUserid(userid);
            if(StringUtils.isEmpty(String.valueOf(up.getUser_id()))){
            	up.setUser_id(Integer.parseInt(userid));
            }
            if (!StringUtils.isEmpty(courseware)) {
            	up.setCourseware(courseware);
			}
            if (!StringUtils.isEmpty(year_of_birth)) {
            	up.setYear_of_birth(year_of_birth);
			}
            if (!StringUtils.isEmpty(user_slug)) {
            	up.setUser_slug(user_slug);
			}
            userprofileManager.updateUserprofile(up);
          //保存User明细表信息-------end
			return "redirect:pcentral";
		}
		
		/**
		 * 跳转到登录页面
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/login")
		public String toLogin(ModelMap map,HttpServletRequest request) {
			//redirect URI的设置
			String redirectURI = request.getParameter("redirectURI");
			if(StringUtils.isNotEmpty(redirectURI)){
				if(redirectURI.equals("/login")){
					redirectURI = "/love";
				}
				map.put("redirectURI", redirectURI);
			}
			
			return "/login2";
		}
		
		/**
		 * 跳转到注册页面
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/signup")
		public String toReg(ModelMap map,HttpServletRequest request) {
			
			map.put("reged", request.getParameter("reged"));
			
			return "/reg2";
		}
		
		/**
		 * @function 忘记密码
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/cm/forget")
		public String forget(ModelMap map,HttpServletRequest request) {
			
			return "/forget";
		}
		
		
		
		@RequestMapping("/cm/logout")
		public String logout(HttpServletRequest request, HttpSession session,HttpServletResponse response) {

			Enumeration<?> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String sessionName = (String) e.nextElement();
				LOGGER.info("存在的session有：" + sessionName);
					session.removeAttribute(sessionName);

			}
			clearCookie(request, response);
			String a = request.getParameter("flag");
			String etc="?signout=true";
			if(StringUtils.isNotEmpty(a)){
				if(a.equals("qq")){
					etc = "";
				}
			}
			
			return HOME_ACTION+etc;
		}
		
		

		/**
		 * 清空cookie,保留username
		 */
		private static void clearCookie(HttpServletRequest request,
				HttpServletResponse response) {
			Cookie[] cookies = request.getCookies();
			try {
				for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = new Cookie(cookies[i].getName(), null);
						cookie.setMaxAge(0);
						response.addCookie(cookie); 
				}
			} catch (Exception e) {
				LOGGER.error("commonAction------>",e);
			}

		}

		/**
		 * 注册用户方法
		 * @param user
		 * @param map
		 * @param session
		 * @return
		 */
		@RequestMapping("/cm/addUser")
		public String addUser(User user, ModelMap map,HttpSession session) {
			String email = user.getEmail();
			String pwd = user.getPassword();
			email = WAQ.forSQL().escapeSql(email);
			pwd = WAQ.forSQL().escapeSql(pwd);
			int num = userManager.countHql( " where email= '"+email+"' ");
			if(num>0){
				map.put("reged", "reged");
				return REG_ACTION;
			}else{
				
				user.setDate_joined(TimeUtils.nowTime());
				String username ="";
				String str = user.getEmail();
				if(str.contains("@")){
					String[] strs = str.split("@");
					username=strs[0];
				}else{
					username=str;
				}
				//默认字符头像
				String abc = PinyinUtil.paraseStringToPinyin(username);
				if(StringUtils.isNotEmpty(abc)){
					String headspan = abc.substring(0, 1).toUpperCase();
					String headspanclass = "text-"+headspan.toLowerCase();
					user.setHeadspan(headspan);
					user.setHeadspanclass(headspanclass);
				}
				user.setUsername(username);
				user.setPassword(Base64Util.JIAMI(user.getPassword()));
				this.userManager.addUser(user);
				session.setAttribute("user", user);
				map.put("user", user);
				//发送邮件
//				try {
//					
//					EmailUtils.ThanksReg(email);
//				} catch (Exception e) {
//					// TODO: handle exception
//					LOGGER.error("commonAction------>",e);
//				}
				return LIST_ACTION;
			}
		}

		
		/**
		 * 最爱分页查询 【跳转】
		 * @param map
		 * @param condition
		 * @param page
		 * @param request
		 * @param response
		 * @param session
		 * @return
		 */
		@RequestMapping(value="/love/page/{page}")
		public String listpage(ModelMap map,UserLyricsQueryCondition condition,@PathVariable String page,HttpServletRequest request,
				HttpServletResponse response, HttpSession session) {
			
			//获取域名+tab{selection}
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			
			User u = (User) session.getAttribute("user");
			
			String userid = (u==null?"":String.valueOf(u.getId()));
			
			//定义pageview
			PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(Integer.parseInt(page), MyConstant.MAXRESULT);
			
			//获取分页结构不获取数据
			
			pageview = this.userlyricsManager.getMixMapPage(pageview, userid);
			
			map.addAttribute("pageView", pageview);
			map.put("condition", condition);
			map.addAttribute("actionUrl","/love");
			map.addAttribute("page", page);
			
			return "/welcome";
		}
		
		/**
		 * 最爱查询 【跳转】
		 * @param map
		 * @param condition
		 * @param request
		 * @param response
		 * @param session
		 * @param userid
		 * @return
		 */
		@RequestMapping(value="/love")
		public String list(ModelMap map,UserLyricsQueryCondition condition,HttpServletRequest request,
				 HttpSession session,String userid) {
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			
	        User u = (User) session.getAttribute("user");
				
			userid = (u==null?"":String.valueOf(u.getId()));

			
			//定义pageview
			PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(1, MyConstant.MAXRESULT);
			
			//获取分页结构不获取数据
			
			pageview = this.userlyricsManager.getMixMapPage(pageview, userid);
			
			
			map.addAttribute("pageView", pageview);
			map.put("condition", condition);
			map.addAttribute("actionUrl","/love");
			
			
			return "/welcome";
		}
		
		
		/**
		 * 异步分页查询love'数据
		 * @param map
		 * @param request
		 * @param session
		 * @param userid
		 * @return
		 */
		@RequestMapping(value="/lovequery")
		public String lovequery(ModelMap map,HttpServletRequest request, HttpSession session,String userid) {
			String currentpage = request.getParameter("currentpage");
			
	        User u = (User) session.getAttribute("user");
				
			userid = (u==null?"":String.valueOf(u.getId()));

			
			//定义pageview
			PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);
			
			
			//获取分页数据
			List<Map<String, Object>>  lovelist = this.userlyricsManager.getMixMapData(pageview,userid);
			
			
			
			if(!CollectionUtils.isEmpty(lovelist)){
				for (int i = 0; i < lovelist.size(); i++) {
					Map<String, Object> map2 = lovelist.get(i);
					
					//处理标题截断
					String title = (String) map2.get("title");
					String cutString = HTMLParserUtil.CutString(title, 12);
					map2.put("cuttitle", cutString);
					
					//处理日期显示格式
					String updatedate = (String) map2.get("updatedate");
					String engDate = TimeUtils.parse2EnglishDate(updatedate);
					map2.put("engDate", engDate);
				}
			}
			
			
			map.addAttribute("lovelist", lovelist);
			
			
			return "/page/love/lovedata";
		}
		

		
		
	/**
	 * 登录方法
	 * @param request
	 * @param response
	 * @param session
	 * @param email
	 * @param password
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cm/login")
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String email,
			String password, ModelMap map) throws IOException {
		String result = "success";
		String info   = "";
		Cookie[] cookies = request.getCookies();
		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
			//防止sql注入--email
			email = WAQ.forSQL().escapeSql(email);
			password = Base64Util.JIAMI(password);
			User user = userManager.login(email, password);
			if (user != null) {

				session.setAttribute("user", user);
				map.put("user", user);
				Cookie cname = new Cookie("email", email);
				Cookie cpasswd = new Cookie("password", password);

				/* cookie的有效期为30秒 */
				cname.setPath("/");
				cname.setDomain(DOMAIN);
				cname.setMaxAge(60 * 60 * 24 * 7); /* 设置cookie的有效期为 7 天 */
				cpasswd.setPath("/");
				cpasswd.setDomain(DOMAIN);
				cpasswd.setMaxAge(60 * 60 * 24 * 7); /* 设置cookie的有效期为 7 天 */
				response.addCookie(cname);
				response.addCookie(cpasswd);
				session.setAttribute("user", user);
				result = "success";
				info = "登陆成功";
			} else {
				result = "error";
				info = "用户名密码错误";
			}
		} else if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("email")) {
					String name = cookies[i].getValue();
					if (!StringUtils.isEmpty(name)) {
						email = name;
					}
					continue;
				}

				if (cookies[i].getName().equals("password")) {
					String password1 = cookies[i].getValue();
					if (!StringUtils.isEmpty(password1)) {
						password = password1;
					}
					continue;
				}
			}
			//防止sql注入--email
			email = WAQ.forSQL().escapeSql(email);
			
			User user = userManager.login(email, password);
			if (user != null) {

				session.setAttribute("user", user);
				map.put("user", user);

				result = "success";
				info = "登陆成功";
			}else {
				result = "error";
				info = "用户名密码错误";
			}
		}
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap.put("result", result);
		rsmap.put("info", info);
		return JsonUtil.map2json(rsmap);
	}
	
	
	/////////////////////////////////////////失效的方法和工具方法、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、

	/**
	 * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
	 * @return
	 */
	public static Integer getRandomOne(List<?> list){
		
		
		Random ramdom =  new Random();
		int number = -1;
		int max = list.size();
		
		//size 为  10 ，取得类似0-9的区间数
		number = Math.abs(ramdom.nextInt() % max  );
		
		return number;
    
	}

	
}
