
package cn.northpark.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cn.northpark.manager.ResetManager;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.manager.UserManager;
import cn.northpark.manager.UserprofileManager;
import cn.northpark.model.QQinfo;
import cn.northpark.model.Reset;
import cn.northpark.model.User;
import cn.northpark.model.UserFollow;
import cn.northpark.model.Userprofile;
import cn.northpark.query.condition.UserLyricsQueryCondition;
import cn.northpark.utils.Base64Util;
import cn.northpark.utils.EmailUtils;
import cn.northpark.utils.FileUtils;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.JedisUtil;
import cn.northpark.utils.PageView;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.SerializationUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.URLUtil;
import cn.northpark.utils.json.JsonUtil;
import cn.northpark.utils.safe.WAQ;

@Controller
@RequestMapping("")
@SessionAttributes({ "list", "User" })
public class UserAction {

     /**
     * 域名
     */
     private final String DOMAIN = "northpark.cn";
     private final String DOMAIN_LOCAL = "localhost:8082";
     /**
      * 定向action'
      */
	 private final String LIST_ACTION = "redirect:/love";
	 private final String LOGIN_ACTION = "redirect:/login";
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
	 		int num = userManager.findByCondition(" where email = '"+email+"' ").getResultlist().size();
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
	 		int num = userManager.findByCondition(" where tail_slug = '"+tail+"' ").getResultlist().size();
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
			    if(list!=null){
			    	if(list.size()>0){
			    		userid = String.valueOf(list.get(0).getId());
			    	}
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
				if (gtList != null) {
				 if(gtList.size()>0){
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
				 }
				} else {// 已失效，重新获取验证码
					result = "invalid";
				}
			} catch (Exception e) {
				e.printStackTrace();
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
	 			int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
		 		if(nums==0){
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
		public String building(ModelMap map) {
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
		
		
		@RequestMapping("/cm/pcentral")
		public String pcentral(ModelMap map,HttpServletRequest request) {
			//获取域名
			 URLUtil.getDomain(request);

			 String rs = "/myself";
			User user   = (User) request.getSession().getAttribute("user");
			request.getSession().removeAttribute("tabs");
			request.getSession().setAttribute("tabs","pcenter");
			if(user!=null){	
					map.put("MyInfo", user);
					
					//查询个人歌词最爱历史
					String sql =  "select c.*,b.id as userlyricsid from bc_user a join  bc_user_lyrics b on a.id = b.userid join bc_lyrics c on b.lyricsid = c.id where a.id = ? order by c.updatedate desc" ;
					
					List<Map<String, Object>> list  = userManager.querySql(sql,String.valueOf(user.getId()));
					for (int i = 0; i < list.size(); i++) {
						//--批量处理时间
						list.get(i).put("updatedate", TimeUtils.formatToNear((String)list.get(i).get("updatedate")));
						
					}
					map.addAttribute("Lovelist", list);
			}else{
				rs =LOGIN_ACTION;
			}
			return rs;
		}
		
		@RequestMapping("/cm/myfans")
		public String myfans(ModelMap map, HttpServletRequest request) {

			 //取得当前用户
	         User user = (User) request.getSession().getAttribute("user");
	         if(user!=null){
	        	 
	 			map.put("MyInfo", user);
	 			
	 			//查询该用户的粉丝列表
	 			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
	 			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+user.getId()+"' ").getResultlist();
	 			if(fan_list!=null){
	 				if(fan_list.size()>0){
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
	 			}
	 			map.put("fanlist", fanlist);
	         }
			
			
			return "/fans";
		}
		
		@RequestMapping("/cm/fans/{userid}")
		public String fans(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
		try{
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
			
			//查询该用户的粉丝列表
			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+userid+"' ").getResultlist();
			if(fan_list!=null){
				if(fan_list.size()>0){
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
			}
			map.put("fanlist", fanlist);
			
			 //取得当前用户对作者的关注状态
	         User lo_user = (User) request.getSession().getAttribute("user");
	         if(lo_user!=null){
	        	 String follow_id = String.valueOf(lo_user.getId());
	        	 String author_id = userid;
	        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
	        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
	        		 if(nums>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
		}catch(Exception e){
			e.printStackTrace();
			
		}
			return "/spacefans";
		}
		
		@RequestMapping("/cm/detail/{userid}")
		public String detail(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
			try{
			//获取域名
			 URLUtil.getDomain(request);
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
			
			//查询个人歌词最爱历史
			String sql =  "select c.*,b.id as userlyricsid from bc_user a join  bc_user_lyrics b on a.id = b.userid join bc_lyrics c on b.lyricsid = c.id where a.id = ? order by c.updatedate desc" ;

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
	        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
	        		 if(nums>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
	         
			}catch(Exception e){
				e.printStackTrace();
			}
			return "/space";
		}
		
		@RequestMapping("/people/{tail_slug}")
		public String people(ModelMap map, @PathVariable String tail_slug ,HttpServletRequest request) {
			try{
			//获取域名
			 URLUtil.getDomain(request);
			User user = null;
			List<User> ul = userManager.findByCondition(" where tail_slug = '"+tail_slug+"' ").getResultlist();
			if(ul!=null){
				if(ul.size()>0){
					user = ul.get(0);
				}
			}
			map.put("MyInfo", user);
			//查询个人歌词最爱历史
			String sql =  "select c.*,b.id as userlyricsid from bc_user a join  bc_user_lyrics b on a.id = b.userid join bc_lyrics c on b.lyricsid = c.id where a.id = ? order by c.updatedate desc" ;
			

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
	        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
	        		 if(nums>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
			}catch(Exception e){
				e.printStackTrace();
			}
			return "/space";
		}
		
		@RequestMapping("/cm/toEditInfo")
		public String toEditInfo(ModelMap map,String userid,HttpServletRequest request) {
			if(StringUtils.isEmpty(userid)){
	              User u = (User) request.getSession().getAttribute("user");
	              userid = String.valueOf(u.getId());
	        }  
			
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
			Userprofile Duser = userprofileManager.getModelByUserid(userid);
			map.put("Dinfo", Duser);
			return "/EditInfo";
		}
		
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
			
			return "/login";
		}
		
		@RequestMapping("/signup")
		public String toReg(ModelMap map,HttpServletRequest request) {
			
			map.put("reged", request.getParameter("reged"));
			
			return "/reg";
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
		
		@RequestMapping("/cm/toEdit")
		public String toEdit(HttpServletRequest request, Integer id,ModelMap map) {
			if(null!=id && 0!=id){
                User u = (User) request.getSession().getAttribute("user");
                id =  u.getId();
            }  
			if(null!=id && 0!=id){
				User model = userManager.findUser(id);
				map.put("model", model);
			}
			return "/user/userEdit";
		}
		


		
		
		@RequestMapping("/cm/logout")
		public String logout(HttpServletRequest request, HttpSession session,HttpServletResponse response) {

			Enumeration e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String sessionName = (String) e.nextElement();
				System.out.println("存在的session有：" + sessionName);
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
			
			return LIST_ACTION+etc;
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
				e.printStackTrace();
			}

		}

		@RequestMapping("/cm/addUser")
		public String addUser(User user, ModelMap map,HttpSession session) {
			int num = userManager.findByCondition(" where email = '"+user.getEmail()+"' ").getResultlist().size();
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
					user.setHeadpath(headspan);
					user.setHeadspanclass(headspanclass);
				}
				user.setUsername(username);
				user.setPassword(Base64Util.JIAMI(user.getPassword()));
				this.userManager.addUser(user);
				session.setAttribute("user", user);
				map.put("user", user);
				return LIST_ACTION;
			}
		}

		
		@RequestMapping(value="/love/page{page}")
		public String listpage(ModelMap map,UserLyricsQueryCondition condition,@PathVariable String page,HttpServletRequest request,
				HttpServletResponse response, HttpSession session) {
			
			//获取域名+tab{selection}
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			String currentpage = page;
			
			User u = (User) session.getAttribute("user");
			
			String userid = (u==null?"":String.valueOf(u.getId()));
			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapPage(currentpage,userid);
			
			List<Map<String, Object>> list = pageView.getMapRecords();
			
			map.addAttribute("pageView", pageView);
			int pages = 0;
			try {
				 pages = Integer.parseInt(page)+1;
				
			} catch (Exception e) {
				// TODO: handle exception
				pages = 1;
			}
			map.put("page", pages);
			map.put("condition", condition);
//			map.addAttribute("list", pageView.getMapRecords()==null?"":list);
			map.addAttribute("actionUrl","love");
			
			String signoutflag = (String) request.getParameter("signout");
			if(StringUtils.isNotEmpty(signoutflag)){
				if(signoutflag.equals("true")){
					map.addAttribute("signout", "true");
				}
			}
			
			return "/welcome";
		}
		
		@RequestMapping(value="/love")
		public String list(ModelMap map,UserLyricsQueryCondition condition,HttpServletRequest request,
				HttpServletResponse response, HttpSession session,String userid) {
			//获取域名
			//URLUtil.getDomain(request);
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			String currentpage = request.getParameter("currentpage");
			
	        User u = (User) session.getAttribute("user");
				
			userid = (u==null?"":String.valueOf(u.getId()));

			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapPage(currentpage,userid);
			
			
			map.addAttribute("pageView", pageView);
			map.put("condition", condition);
			//map.addAttribute("list", pageView.getMapRecords()==null?"":pageView.getMapRecords());
			map.addAttribute("actionUrl","love");
			
			String signoutflag = (String) request.getParameter("signout");
			if(StringUtils.isNotEmpty(signoutflag)){
				if(signoutflag.equals("true")){
					map.addAttribute("signout", "true");
				}
			}
			
			return "/welcome";
		}
		
		
		//异步分页查询love'数据
		@RequestMapping(value="/lovequery")
		public String lovequery(ModelMap map,HttpServletRequest request, HttpSession session,String userid) {
			String currentpage = request.getParameter("currentpage");
			
	        User u = (User) session.getAttribute("user");
				
			userid = (u==null?"":String.valueOf(u.getId()));

			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapData(currentpage,userid);
			
			List<Map<String, Object>> lovelist = pageView.getMapRecords();
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
		return JsonUtil.jsonUtil.mapToJSONString(rsmap);
	}
	
	
	/**
	 * 绑定注册/登录用户信息
	 */
	@RequestMapping(value = "/cm/qq/add" )
	@ResponseBody
	public String qqAdd(HttpServletRequest request, HttpServletResponse response, HttpSession session, String openId,String qqinfo) {
		String result = "success";
		System.out.println(openId);
		if(StringUtils.isEmpty(openId)){
			openId = (String) session.getAttribute("openId");
		}
		List<User> list = userManager.findByCondition(" where qq_openid = '"+openId+"' ").getResultlist();
		if(list!=null){
			if(list.size()==0){

				System.out.println(qqinfo);
				if(StringUtils.isNotEmpty(qqinfo)){
					QQinfo qqinfom = JsonUtil.jsonToModel(qqinfo, QQinfo.class);
					//添加user
					User userm = new User();
					userm.setDate_joined(TimeUtils.nowTime());
					userm.setHeadpath(qqinfom.getFigureurl_qq_2());
					userm.setLast_login(TimeUtils.nowTime());
					userm.setQq_info(qqinfo);
					userm.setQq_openid(openId);
					userm.setUsername("qq_"+qqinfom.getNickname());
					userManager.addUser(userm);
					//添加详情
					Userprofile upm=new Userprofile();
					upm.setCity(qqinfom.getCity());
					upm.setGender(qqinfom.getGender());
					upm.setYear_of_birth(qqinfom.getYear());
					upm.setUser_id(userm.getId());
					userprofileManager.addUserprofile(upm);
				}
				
				
				
			}else{
				session.setAttribute("user", list.get(0));
			}
		}
		
		return result;
	}
	
	/**
	 * 判断登录用户信息【存在登陆成功，取出用户信息】【不存在，存放qqopenid，等待绑定信息】
	 */
	@RequestMapping(value = "/cm/qq/flag" )
	@ResponseBody
	public String qqFlag(HttpServletRequest request, HttpServletResponse response, HttpSession session, String openId) {
		String result = "0";
		System.out.println(openId);
		List<User> list = userManager.findByCondition(" where qq_openid = '"+openId+"' ").getResultlist();
		session.setAttribute("openId", openId);
		if(list!=null){
			if(list.size()>0){
				User user = list.get(0);
				session.setAttribute("user", user);
				result = "1";
			}
		}
		return result;
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
	
	/**
	 * 爬虫抓取的优惠券列表页
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/cp/index")
		public String quanlist(HttpSession session) throws IOException {
		 	  	
		        byte[] b = JedisUtil.getListByte("B_quan");
		        Set<String> tags = new HashSet<String>();
		        		
		        List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
		        if(list==null){
		        	HTMLParserUtil.retQuan();
		        	b = JedisUtil.getListByte("B_quan");
			        list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
			        
		        }
		        session.setAttribute("quan", list);
		        
		        for (int i = 0; i < list.size(); i++) {
		        	tags.add(list.get(i).get("from"));
				}
		        session.setAttribute("B_tags", new ArrayList<String>(tags));
		        
		 
		 	return "/quan";
		 	  	
		}
	
	
	 /**
		 * 从redis缓存筛选列表
		 * @param session
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		@RequestMapping("/cp/search")
			public String quanquery(HttpSession session,String keyword,ModelMap model) throws UnsupportedEncodingException {
			 	  	
			        byte[] b = JedisUtil.getListByte("B_quan");
			        List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
			        List<Map<String,String> > list_search  = new ArrayList<Map<String,String>>(); 
			        		
			        		if(StringUtils.isNotEmpty(keyword)){
			        			keyword = WAQ.forSQL().escapeSql(keyword);
			        			for (int i = 0; i < list.size(); i++) {
			        				Map<String,String> map = list.get(i);
			        				String from = map.get("from");
			        				if(StringUtils.isNotEmpty(from)){
			        					if(from.contains(keyword)){
			        						
			        						list_search.add(map);
			        					}
			        				}
			        			}
			        		}
			        		
			        		if(list_search.size()>0){
			        			model.put("quan", list_search);
			        		}else{
			        			model.put("quan", list);
			        		}
			        		
			        		model.put("keyword", keyword);
			        
			 
			 	return "/quan";
			 	  	
			}
	
	/**
	 * 爬虫抓取的优惠券列表页
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/cp/show/{id}")
		public String quanshow(HttpSession session, @PathVariable String id ,ModelMap map) throws UnsupportedEncodingException {
		 	  	
		        byte[] b = JedisUtil.getListByte("B_quan");
		        List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
		        String path_mt = "";
		        for (int i = 0; i < list.size(); i++) {
					if(list.get(i).get("id").equals(id)){
						 path_mt = list.get(i).get("path_mt");
					}
				}
		        
		        map.put("path_mt", path_mt);
		        
		 
		 	return "/quanshow";
		 	  	
		}
	
	
	


	
}