
package com.bruce.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bruce.manager.GetImgManager;
import com.bruce.manager.GetNoteManager;
import com.bruce.manager.LyricsCommentManager;
import com.bruce.manager.LyricsManager;
import com.bruce.manager.LyricsZanManager;
import com.bruce.manager.NoteManager;
import com.bruce.manager.ResetManager;
import com.bruce.manager.UserFollowManager;
import com.bruce.manager.UserLyricsManager;
import com.bruce.manager.UserManager;
import com.bruce.manager.UserprofileManager;
import com.bruce.model.GetImg;
import com.bruce.model.GetNote;
import com.bruce.model.Lyrics;
import com.bruce.model.LyricsZan;
import com.bruce.model.Note;
import com.bruce.model.QQinfo;
import com.bruce.model.Reset;
import com.bruce.model.User;
import com.bruce.model.UserFollow;
import com.bruce.model.UserLyrics;
import com.bruce.model.Userprofile;
import com.bruce.query.UserLyricsQuery;
import com.bruce.query.UserQuery;
import com.bruce.query.condition.UserLyricsQueryCondition;
import com.bruce.utils.Base64Util;
import com.bruce.utils.EmailUtils;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.PinyinUtil;
import com.bruce.utils.TimeUtils;
import com.bruce.utils.json.JsonUtil;

@Controller
@RequestMapping("/cm")
@SessionAttributes({ "list", "User" })
public class UserAction {

	 private final String LIST_ACTION = "redirect:/cm/list";
	 private final String COMMON_PIC = "redirect:/cm/pic";
	 private final String DOMAIN = "lonelyrobots.cn";
	 private final String LOGIN_ACTION = "redirect:/cm/toLogin";
	 @Autowired	
	 private UserManager userManager;
	 @Autowired	
	 private UserprofileManager userprofileManager;
	 @Autowired	
	 private UserQuery userQuery;
	 @Autowired	
	 private UserLyricsQuery userlyricsQuery;
	 @Autowired	
	 private UserLyricsManager userlyricsManager;
	 @Autowired	
	 private LyricsManager lyricsManager;
	 @Autowired	
	 private LyricsZanManager lyricsZanManager;
	 @Autowired	
	 private LyricsCommentManager lyricsCommentManager;
	 @Autowired	
	 private UserFollowManager userfollowManager;
	 @Autowired	
	 private ResetManager resetManager;
	 
	 @Autowired	
	 private GetNoteManager getnoteManager;
	 @Autowired	
	 private GetImgManager getimgManager;
	 @Autowired	
	 private NoteManager noteManager;
	 
	 
	 
	 
	 /**
	  * 生成一切东西
	 * @param user
	 * @param map
	 * @param session
	 */
	@RequestMapping("/everything")
		public void everything( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		try {
			
			    //获取用户头像抓取路径
				List<GetImg> headlist = getimgManager.findAll();
				List<GetNote> notelist = getnoteManager.findAll();
			    for (int i = 0; i < 1000; i++) {
					
			    	//添加用户
			    	User user = new User();
			    	//假设为一年以后的注册用户
			    	user.setDate_joined(TimeUtils.N_YearTime(1));
			    	String username =PinyinUtil.get4Hanzi();
			    	String email = PinyinUtil.getFanyiEmail();
			    	//唯一性递归-----------
			    	int num = userManager.findByCondition(" where email = '"+email+"' ").getResultlist().size();
					if(num>0){
						email = PinyinUtil.getFanyiEmail();
					}
					int num2 = userManager.findByCondition(" where username = '"+username+"' ").getResultlist().size();
					if(num2>0){
						username = PinyinUtil.get4Hanzi();
					}
					//唯一性递归-----------end
			    	user.setEmail(email);
			    	String head = headlist.get(i)==null?"":headlist.get(i).getPath();
			    	head = "/mnt/apk/heads/"+head;
			    	user.setUsername(username);
			    	user.setHeadpath(head);
			    	user.setPassword(Base64Util.JIAMI("123456"));
			    	this.userManager.addUser(user);
			    	
			    	//添加用户的日记
			    	Note note = new Note();
			    	String note_ = notelist.get(i)==null?"":notelist.get(i).getNotes();
			    	String brief_ = notelist.get(i)==null?"":notelist.get(i).getBrief();
			    	note.setBrief(brief_);
			    	note.setCreatetime(TimeUtils.N_YearTime(1));
			    	note.setNote(note_);
			    	note.setOpened("yes");
			    	note.setUserid(user.getId());
			    	noteManager.addNote(note);
			    	//更新生成状态位
			    	notelist.get(i).setIsGened("1");
			    	getnoteManager.updateGetNote(notelist.get(i));
			    	headlist.get(i).setIsGened("1");
			    	getimgManager.updateGetImg(headlist.get(i));
			    	
				}
				
				//添加用户
				
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			   
		}

	 	/**
	 	 * @ 页面对有关于用户的操作，先异步进行判断，假如有用户，返回1.没有用户直接跳转到登陆界面，并且传入需要return的url。
	 	 * @param request
	 	 * @param response
	 	 * @param map
	 	 * @param url
	 	 * @return 0、1 【0：没有 | 1：有】
	 	 */
	 	@RequestMapping("/loginFlag")
	 	@ResponseBody
		public String loginFlag(HttpServletRequest request, HttpServletResponse response,ModelMap map,String url) {
	         System.out.println(">>>: " + url);
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
	 	@RequestMapping("/emailFlag")
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
		 * 发送”修改密码“邮件
		 */
		@RequestMapping("/resetEmail")
		@ResponseBody
		public String resetEmail(HttpServletRequest request,
				HttpServletResponse response, ModelMap map, String email)
				throws ParseException {
			    String userid = "";
			    List<User> list = userManager.findByCondition(" where email = '"+email+"' ").getResultlist();
			    if(list!=null){
			    	if(list.size()>0){
			    		userid = list.get(0).getId();
			    	}
			    }
				Reset reset = new Reset();
				reset.setCreated_time(TimeUtils.nowTime());
				String code = com.bruce.utils.UUIDUtils.geneInt();
				reset.setAuth_code(code);
				reset.setInvalid_time(TimeUtils.getDayAfter(TimeUtils.nowTime()));
				reset.setIs_email_authed(0);
				
				reset.setUser_id(userid);
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
		@RequestMapping("/reset")
		public String reset(HttpServletRequest request,
				HttpServletResponse response, ModelMap map, String userid,
				String auth_code) throws ParseException {

			String result = "false";
			List<com.bruce.model.Reset> gtList = resetManager.findByCondition(" where user_id='"+userid+"' and auth_code='"+auth_code+"' order by created_time desc").getResultlist();
			try {
				if (gtList != null) {
				 if(gtList.size()>0){
					 Reset gtmodel = gtList.get(0);
					if (gtmodel.getAuth_code().equals(auth_code)) {// Email认证通过
						if (StringUtils.isNotEmpty(gtmodel.getInvalid_time())) {// 设置了失效时间
							if (TimeUtils.isInvalid(TimeUtils.nowTime(),
									gtmodel.getInvalid_time())&& (0==gtmodel.getIs_email_authed() )) {// 时间未过期
								User user = userManager.findUser(userid);
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
								User user = userManager.findUser(userid);
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
			return "forget";
		}

	 	
	 
	 
	   /* //一键修改密码
	    @RequestMapping("/pwddd")
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
	 	@RequestMapping("/follow")
	 	@ResponseBody
		public String follow(ModelMap map,String author_id,String follow_id) {
	 		String msg = "success";
	 		
	 		try {
	 			int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
		 		if(nums==0){
		 			UserFollow uf = new UserFollow();
			 		uf.setAuthor_id(author_id);
			 		uf.setFollow_id(follow_id);
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
	 	@RequestMapping("/rmfollow")
	 	@ResponseBody
		public String rmfollow(ModelMap map,String id) {
	 		String msg = "success";
	 		
	 		try {
	 			if(StringUtils.isNotEmpty(id)){
	 				userfollowManager.delUserFollow(id);
	 			}else{
	 				msg= "nullid";
	 			}
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exp";
			}
	 		
			return msg;
		}
	 
	 
	 
	 
	 
	 	@RequestMapping("/xbjt")
		public String xbjt(ModelMap map) {
			return "bruce-quiet-listen";
		}
		
		@RequestMapping("/toAdd")
		public String toAdd(ModelMap map) {
			//List<User> Pidlist = userManager.findAll();
			//map.addAttribute("Pidlist",Pidlist);
			return "user/userAdd";
		}
		
		
		@RequestMapping("/pcentral")
		public String pcentral(ModelMap map,HttpServletRequest request) {
			String rs = "myself";
			User user   = null;
			String userid = "";
			request.getSession().removeAttribute("tabs");
			request.getSession().setAttribute("tabs","pcenter");
				User u = (User) request.getSession().getAttribute("user");
				if(u!=null){
					userid = u.getId();
					user = u;
				}
			if(user!=null){	
			//处理图片路径
			String imgpath = user.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
			if(!StringUtils.isEmpty(imgpath)){
			String[] str = imgpath.split("/heads/");
			if(str.length>1){
			String imgp = "heads/"+str[1];
			user.setHeadpath(imgp);
			}
			}
			//处理图片路径
			map.put("MyInfo", user);
			
			//查询个人歌词最爱历史
			List<UserLyrics> Lovelist = userlyricsManager.findByCondition(" where 1=1 ").getResultlist();
			
			//查询歌词列表
			List<Lyrics> LyricsList = lyricsManager.findAll();
			for (int i = 0; i < LyricsList.size(); i++) {
				//批量处理图片的路径
				String imgpath2 = LyricsList.get(i).getAlbumImg(); //e:/yunlu/upload/1399976848969.jpg
				if(!StringUtils.isEmpty(imgpath2)){
				String[] str = imgpath2.split("/album/");
				if(str.length>1){
				String imgp = "album/"+str[1];
				LyricsList.get(i).setAlbumImg(imgp);
				}
				}
				
				//--批量处理时间
				LyricsList.get(i).setUpdatedate(TimeUtils.formatToNear(LyricsList.get(i).getUpdatedate()));
				
			}
			map.addAttribute("LyricsList", LyricsList);
			map.addAttribute("Lovelist", Lovelist);
			}else{
				rs =LOGIN_ACTION;
			}
			return rs;
		}
		
		@RequestMapping("/myfans")
		public String myfans(ModelMap map, HttpServletRequest request) {

			 //取得当前用户
	         User user = (User) request.getSession().getAttribute("user");
	         if(user!=null){
	        	//处理图片路径
	 			String imgpath = user.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
	 			if(!StringUtils.isEmpty(imgpath)){
	 			String[] str = imgpath.split("/heads/");
	 			if(str.length>1){
	 			String imgp = "heads/"+str[1];
	 			user.setHeadpath(imgp);
	 			}
	 			}
	 			//处理图片路径
	 			map.put("MyInfo", user);
	 			
	 			//查询该用户的粉丝列表
	 			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
	 			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+user.getId()+"' ").getResultlist();
	 			if(fan_list!=null){
	 				if(fan_list.size()>0){
	 					for (int i = 0; i < fan_list.size(); i++) {
	 						Map<String,Object> map_ = new HashMap<String,Object>();
	 						String u_id = fan_list.get(i).getFollow_id();
	 						User uu = userManager.findUser(u_id);
	 						
	 						//处理图片路径
	 						String imgpath1 = uu.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
	 						if(!StringUtils.isEmpty(imgpath1)){
	 						String[] str = imgpath1.split("/heads/");
	 						if(str.length>1){
	 						String imgp = "heads/"+str[1];
	 						uu.setHeadpath(imgp);
	 						}
	 						}
	 						
	 						
	 						
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
			
			
			return "fans";
		}
		
		@RequestMapping("/fans/{userid}")
		public String fans(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
			User user = userManager.findUser(userid);
			//处理图片路径
			String imgpath = user.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
			if(!StringUtils.isEmpty(imgpath)){
			String[] str = imgpath.split("/heads/");
			if(str.length>1){
			String imgp = "heads/"+str[1];
			user.setHeadpath(imgp);
			}
			}
			//处理图片路径
			map.put("MyInfo", user);
			
			//查询该用户的粉丝列表
			List<Map<String,Object>> fanlist = new ArrayList<Map<String,Object>>();
			List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '"+userid+"' ").getResultlist();
			if(fan_list!=null){
				if(fan_list.size()>0){
					for (int i = 0; i < fan_list.size(); i++) {
						Map<String,Object> map_ = new HashMap<String,Object>();
						String u_id = fan_list.get(i).getFollow_id();
						User uu = userManager.findUser(u_id);
						
						//处理图片路径
						String imgpath1 = uu.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
						if(!StringUtils.isEmpty(imgpath1)){
						String[] str = imgpath1.split("/heads/");
						if(str.length>1){
						String imgp = "heads/"+str[1];
						uu.setHeadpath(imgp);
						}
						}
						
						
						
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
	        	 String follow_id = lo_user.getId();
	        	 String author_id = userid;
	        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
	        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
	        		 if(nums>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
			return "spacefans";
		}
		
		@RequestMapping("/detail/{userid}")
		public String detail(ModelMap map, @PathVariable String userid ,HttpServletRequest request) {
			User user = userManager.findUser(userid);
			//处理图片路径
			String imgpath = user.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
			if(!StringUtils.isEmpty(imgpath)){
			String[] str = imgpath.split("/heads/");
			if(str.length>1){
			String imgp = "heads/"+str[1];
			user.setHeadpath(imgp);
			}
			}
			//处理图片路径
			map.put("MyInfo", user);
			
			//查询个人歌词最爱历史
			List<UserLyrics> Lovelist = userlyricsManager.findByCondition(" where 1=1 ").getResultlist();
			
			//查询歌词列表
			List<Lyrics> LyricsList = lyricsManager.findAll();
			for (int i = 0; i < LyricsList.size(); i++) {
				//批量处理图片的路径
				String imgpath2 = LyricsList.get(i).getAlbumImg(); //e:/yunlu/upload/1399976848969.jpg
				if(!StringUtils.isEmpty(imgpath2)){
				String[] str = imgpath2.split("/album/");
				if(str.length>1){
				String imgp = "album/"+str[1];
				LyricsList.get(i).setAlbumImg(imgp);
				}
				}
				
				//--批量处理时间
			    LyricsList.get(i).setUpdatedate(TimeUtils.formatToNear(LyricsList.get(i).getUpdatedate()));
				
			}
			map.addAttribute("LyricsList", LyricsList);
			map.addAttribute("Lovelist", Lovelist);
			
			
			 //取得当前用户对作者的关注状态
	         User lo_user = (User) request.getSession().getAttribute("user");
	         if(lo_user!=null){
	        	 String follow_id = lo_user.getId();
	        	 String author_id = userid;
	        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
	        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
	        		 if(nums>0){
	        			 map.put("gz", "ygz");
	        		 }
	        	 }
	        	 
	         }
			return "space";
		}
		
		@RequestMapping("/toEditInfo")
		public String toEditInfo(ModelMap map,String userid,HttpServletRequest request) {
			if(StringUtils.isEmpty(userid)){
	              User u = (User) request.getSession().getAttribute("user");
	              userid = u.getId();
	        }  
			
			User user = userManager.findUser(userid);
			//处理图片路径
				String imgpath = user.getHeadpath(); //e:/yunlu/upload/1399976848969.jpg
				if(!StringUtils.isEmpty(imgpath)){
				String[] str = imgpath.split("/heads/");
				if(str.length>1){
				String imgp = "heads/"+str[1];
				user.setHeadpath(imgp);
				}
				}
				//处理图片路径
			map.put("MyInfo", user);
			Userprofile Duser = userprofileManager.getModelByUserid(userid);
			map.put("Dinfo", Duser);
			return "EditInfo";
		}
		
		@RequestMapping("/saveEditInfo")
		public String saveEditInfo(HttpSession session,User model,ModelMap map,String oldpath, @RequestParam(value = "file", required = false) MultipartFile[] file,String new_password,String new_password_confirmation,String courseware,String year_of_birth,String user_slug) {
			//保存User表信息
			// 执行删除图片缓存
			if(file.length>=1){
				System.out.println(file[0].getOriginalFilename()+"------------------------------------------------》》"); 
				if(StringUtils.isNotEmpty(file[0].getOriginalFilename())){//新上传了图片才把以前的删除
			if(StringUtils.isNotEmpty(oldpath)){
			  File f = new File(oldpath);
			  System.out.println("要删除文件的绝对路径是："+f.getAbsolutePath());
				if (f.exists()){
					f.delete();
				}else{
					System.out.println("文件已经丢失!");
				}
			  }
			}
			}
			// 执行删除图片缓存
		    //执行上传头像	
			 System.out.println("-------------------------------------->开始");  
			    Properties prop = System.getProperties();

				String os = prop.getProperty("os.name");
				System.out.println(os);
				String path = "e:/bruce/album";
				String fileName="";
				String newName="";
		         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		        	 path = "e:/bruce/heads";
		         }else{
		        	 path = "/mnt/apk/heads";
		         }
		       //  String path = "e:/bruce/upload";
		         String headpath = "";
		        for (int i = 0; i < file.length; i++) {
		        	fileName = file[i].getOriginalFilename();  
			        System.out.println(path); 
			        if(!StringUtils.isEmpty(fileName)){//新上传了才执行保存
			        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
			        newName = String.valueOf(System.currentTimeMillis())+"."+ext;
			        File targetFile = new File(path, newName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
			  
			        //保存  
			        try {  
			            file[i].transferTo(targetFile);  
			        } catch (Exception e) {  
			            e.printStackTrace();  
			        }  
			        
			        if(i==0){
			        	headpath = newName;
			        }
			        }
				}
//		        String imgpath = path+"/"+newName;
//		        System.out.println(imgpath);
		      //执行上传头像end
		      //上传图片非空时，执行保存路径
		        headpath = path+"/"+headpath;
		        //lrcpath = path +"/"+lrcpath;
		        if(headpath.contains(".")){
		        	model.setHeadpath(headpath);
		        }else{
		        	model.setHeadpath(oldpath);//以前的头像
		        }
		        System.out.println("-------------------------------------->结束");  
		        
		        //处理密码信息
		        if(!StringUtils.isEmpty(new_password)&&!StringUtils.isEmpty(new_password_confirmation)&&new_password.equals(new_password_confirmation)){
		        	model.setPassword(Base64Util.JIAMI(new_password));
		        }
		        //处理密码信息
			   this.userManager.updateUser(model);
			   session.setAttribute("user",model);
			  //保存User表信息-------结束
			   
			 //保存User明细表信息-------start
            String userid = model.getId();
            Userprofile up = userprofileManager.getModelByUserid(userid);
            if(StringUtils.isEmpty(up.getUser_id())){
            	up.setUser_id(userid);
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
			return "redirect:pcentral?userid="+userid;
		}
		
		@RequestMapping("/toLogin")
		public String toLogin(ModelMap map,HttpServletRequest request) {
			//redirect URI的设置
			String redirectURI = request.getParameter("redirectURI");
			if(StringUtils.isNotEmpty(redirectURI)){
				if(redirectURI.equals("/cm/toLogin")){
					redirectURI = "/cm/pic";
				}
				map.put("redirectURI", redirectURI);
			}
			
			return "login";
		}
		
		/**
		 * @function 忘记密码
		 * @param map
		 * @param request
		 * @return
		 */
		@RequestMapping("/forget")
		public String forget(ModelMap map,HttpServletRequest request) {
			
			return "forget";
		}
		
		@RequestMapping("/toEdit")
		public String toEdit(HttpServletRequest request, String id,ModelMap map) {
			if(StringUtils.isEmpty(id)){
                User u = (User) request.getSession().getAttribute("user");
                id = u.getId();
            }  
			if(!StringUtils.isEmpty(id)){
				User model = userManager.findUser(id);
				map.put("model", model);
			}
			return "user/userEdit";
		}
		
		@RequestMapping("/remove")
		public String remove(@RequestParam("id") String id) {
			this.userManager.delUser(id);
			return LIST_ACTION;
		}
		
		@RequestMapping("/removes")
		public String removes(@RequestParam("ids") String ids) {
			String[] str = ids.split(",");
			for(String s :str){
				this.userManager.delUser(s);
			}
			return LIST_ACTION;
		}
		
		@RequestMapping("/update")
		public String update(User model) {
			this.userManager.updateUser(model);
			return LIST_ACTION;
		}
		
		@RequestMapping("/logout")
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

		@RequestMapping("/addUser")
		public String addUser(User user, ModelMap map,HttpSession session) {
			int num = userManager.findByCondition(" where email = '"+user.getEmail()+"' ").getResultlist().size();
			if(num>0){
				map.put("reged", "reged");
				return "reg";
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
				user.setUsername(username);
				user.setPassword(Base64Util.JIAMI(user.getPassword()));
				this.userManager.addUser(user);
				session.setAttribute("user", user);
				map.put("user", user);
				return "redirect:pic";
			}
		}

		@RequestMapping("/findUser")
		private String findUser(@RequestParam("id") String id, ModelMap map) {
			User user = this.userManager.findUser(id);
			map.addAttribute("user", user);
			return "findresult";
		}

		@RequestMapping("/delUser")
		public String delUser(@RequestParam("id") String id) {
			this.userManager.delUser(id);
			return LIST_ACTION;
		}

		@RequestMapping("/updateUser")
		public String updateUser(@RequestParam("id") String id) {
			User user = this.userManager.findUser(id);
			this.userManager.updateUser(user);
			return LIST_ACTION;
		}

		
		@RequestMapping(value="/pic/page{page}")
		public String pic(ModelMap map,UserLyricsQueryCondition condition,@PathVariable String page,HttpServletRequest request,
				HttpServletResponse response, HttpSession session) {
			
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			String currentpage = page;
			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapData(currentpage);
			
			List<Map<String, Object>> list = pageView.getMapRecords();
			if(list!=null){
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						
						//批量处理图片的路径
						String imgpath = (String) list.get(i).get("albumImg"); //e:/yunlu/upload/1399976848969.jpg
						if(!StringUtils.isEmpty(imgpath)){
						String[] str = imgpath.split("/album/");
						if(str.length>1){
						String imgp = "album/"+str[1];
						list.get(i).put("albumImg",imgp);
						}
						}
						
						//批量处理赞和评论的个数
						String lyricsid = (String) list.get(i).get("id");
						int zan =  lyricsZanManager.getZanNumByLRC(lyricsid);
						int pl  =  lyricsZanManager.getCommentNumByLRC(lyricsid);
						list.get(i).put("zan",zan);
						list.get(i).put("pl",pl);
						
						//批量处理当前用户对这个pic/歌词的赞状态
						User c_user = (User) session.getAttribute("user");
						boolean yizan =  false;
						if(c_user!=null){
							List<LyricsZan> lz = lyricsZanManager.findByCondition(" where lyricsid= '"+lyricsid+"' ").getResultlist();
							if(lz!=null){
								if(lz.size()>0){
									for (int j = 0; j < lz.size(); j++) {
										String u_id = lz.get(j).getUserid();
										if(u_id.equals(c_user.getId())){
											yizan = true ; 
										}
									}
								}
							}
						}
						if(yizan == true){
							list.get(i).put("yizan","yizan");
						}
						
						
						
					}
				}
			}
			
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
			map.addAttribute("list", pageView.getMapRecords()==null?"":list);
			map.addAttribute("actionUrl","cm/pic");
			
			String signoutflag = (String) request.getParameter("signout");
			if(StringUtils.isNotEmpty(signoutflag)){
				if(signoutflag.equals("true")){
					map.addAttribute("signout", "true");
				}
			}
			
			return "welcome";
		}
		@RequestMapping(value="/list")
		public String findAll(ModelMap map,UserLyricsQueryCondition condition,HttpServletRequest request,
				HttpServletResponse response, HttpSession session,String userid) {
			if(!StringUtils.isEmpty(userid)){
			    condition.setUserid(userid);
			    map.addAttribute("userid",userid);
			}else{
				map.addAttribute("userid","");
			}

			String currentpage = request.getParameter("currentpage");
			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapData(currentpage);
			
			List<Map<String, Object>> list = pageView.getMapRecords();
			if(list!=null){
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						
						//批量处理图片的路径
						String imgpath = (String) list.get(i).get("albumImg"); //e:/yunlu/upload/1399976848969.jpg
						if(!StringUtils.isEmpty(imgpath)){
						String[] str = imgpath.split("/album/");
						if(str.length>1){
						String imgp = "album/"+str[1];
						list.get(i).put("albumImg",imgp);
						}
						}
						
						//批量处理赞和评论的个数
						String lyricsid = (String) list.get(i).get("id");
						int zan =  lyricsZanManager.getZanNumByLRC(lyricsid);
						int pl  =  lyricsZanManager.getCommentNumByLRC(lyricsid);
						list.get(i).put("zan",zan);
						list.get(i).put("pl",pl);
					}
				}
			}
			
			map.addAttribute("pageView", pageView);
			map.put("condition", condition);
			map.addAttribute("list", pageView.getMapRecords()==null?"":pageView.getMapRecords());
			map.addAttribute("actionUrl","cm/pic");
			
			String signoutflag = (String) request.getParameter("signout");
			if(StringUtils.isNotEmpty(signoutflag)){
				if(signoutflag.equals("true")){
					map.addAttribute("signout", "true");
				}
			}
			
			return "welcome";
		}
		
		@RequestMapping(value="/pic")
		public String commonPic(ModelMap map,UserLyricsQueryCondition condition,HttpServletRequest request,
				HttpServletResponse response, HttpSession session) {
			
			session.removeAttribute("tabs");
			session.setAttribute("tabs","pic");
			String currentpage = request.getParameter("currentpage");
			
			PageView<List<Map<String, Object>>> pageView = this.userlyricsManager.getMixMapData(currentpage);
			
			List<Map<String, Object>> list = pageView.getMapRecords();
			if(list!=null){
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						
						//批量处理图片的路径
						String imgpath = (String) list.get(i).get("albumImg"); //e:/yunlu/upload/1399976848969.jpg
						if(!StringUtils.isEmpty(imgpath)){
						String[] str = imgpath.split("/album/");
						if(str.length>1){
						String imgp = "album/"+str[1];
						list.get(i).put("albumImg",imgp);
						}
						}
						
						//批量处理赞和评论的个数
						String lyricsid = (String) list.get(i).get("id");
						int zan =  lyricsZanManager.getZanNumByLRC(lyricsid);
						int pl  =  lyricsZanManager.getCommentNumByLRC(lyricsid);
						list.get(i).put("zan",zan);
						list.get(i).put("pl",pl);
						
						//批量处理当前用户对这个pic/歌词的赞状态
						User c_user = (User) session.getAttribute("user");
						boolean yizan =  false;
						if(c_user!=null){
							List<LyricsZan> lz = lyricsZanManager.findByCondition(" where lyricsid= '"+lyricsid+"' ").getResultlist();
							if(lz!=null){
								if(lz.size()>0){
									for (int j = 0; j < lz.size(); j++) {
										String u_id = lz.get(j).getUserid();
										if(u_id.equals(c_user.getId())){
											yizan = true ; 
										}
									}
								}
							}
						}
						if(yizan == true){
							list.get(i).put("yizan","yizan");
						}
						
						
						
					}
				}
			}
			
			map.addAttribute("pageView", pageView);
			map.put("condition", condition);
			map.addAttribute("list", pageView.getMapRecords()==null?"":pageView.getMapRecords());
			map.addAttribute("actionUrl","cm/pic");
			
			String signoutflag = (String) request.getParameter("signout");
			if(StringUtils.isNotEmpty(signoutflag)){
				if(signoutflag.equals("true")){
					map.addAttribute("signout", "true");
				}
			}
			
			return "welcome";
		}

		private PageView<UserLyrics> getPageView(HttpServletRequest request,
				String whereSql) {
			PageView<UserLyrics> pageView = new PageView<UserLyrics>();
			int currentpage = 0; //当前页码
			int pages = 0; //总页数
			int n = this.userlyricsManager.findByCondition(whereSql).getResultlist().size();
			int maxresult = MyConstant.MAXRESULT; /** 每页显示记录数**/
	        if(n % maxresult==0)
	       {
	          pages = n / maxresult ;
	       }else{
	          pages = n / maxresult + 1;
	       }
	        if(StringUtils.isEmpty(request.getParameter("currentpage"))){
	           currentpage = 0;
	        }else{
	           currentpage = Integer.parseInt(request.getParameter("currentpage"));
	           
	           if(currentpage<0)
	           {
	              currentpage = 0;
	           }
	           if(currentpage>=pages)
	           {
	              currentpage = pages - 1;
	           }
	        }
			int startindex = currentpage*maxresult;
			int endindex = startindex+maxresult-1;
			pageView.setStartindex(startindex);
			pageView.setEndindex(endindex);
			pageView.setTotalrecord(this.userlyricsManager.findAll().size());
			pageView.setCurrentpage(currentpage);
			pageView.setTotalpage(pages);
			return pageView;
		}
		
		
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String email,
			String password, ModelMap map) throws IOException {
		String result = "success";
		String info   = "";
		Cookie[] cookies = request.getCookies();
		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {
			password = Base64Util.JIAMI(password);
			User user = userManager.login(email, password);
			if (user != null) {
				//处理头像

			    String imgpath = user.getHeadpath();
                if(!StringUtils.isEmpty(imgpath)){
                   String[] str = imgpath.split("/heads/");
                   if(str.length>1){
                   String imgp = "heads/"+str[1];
                   user.setHeadpath(imgp);
                   }
                }

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
			User user = userManager.login(email, password);
			if (user != null) {
				//处理头像

			    String imgpath = user.getHeadpath();
                if(!StringUtils.isEmpty(imgpath)){
                   String[] str = imgpath.split("/heads/");
                   if(str.length>1){
                   String imgp = "heads/"+str[1];
                   user.setHeadpath(imgp);
                   }
                }

				session.setAttribute("user", user);
				map.put("user", user);

				result = "success";
				info = "登陆成功";
			}else {
				result = "error";
				info = "用户名密码错误";
			}
		}
		return result;
	}
	
	
	/**
	 * 绑定注册/登录用户信息
	 */
	@RequestMapping(value = "/qq/add" )
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
					userm.setIs_active(1);
					userm.setIs_del(0);
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
	@RequestMapping(value = "/qq/flag" )
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
	

    

}
