
package cn.northpark.action;
/*
*@author bruce
*
**/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
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
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.manager.UserManager;
import cn.northpark.model.Lyrics;
import cn.northpark.model.User;
import cn.northpark.model.UserLyrics;
import cn.northpark.query.LyricsQuery;
import cn.northpark.utils.FileUtils;
import cn.northpark.utils.ScriptTools;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.PageView;

@Controller
@RequestMapping("/lyrics")
public class LyricsAction {

 
 private final String LIST_ACTION2 = "redirect:/cm/pcentral";
 
 private static final Logger LOGGER = Logger
         .getLogger(LyricsAction.class);
 
 @Autowired	
 private LyricsManager lyricsManager;
 @Autowired	
 private LyricsQuery lyricsQuery;
 @Autowired	
 private UserManager userManager;
 @Autowired	
 private UserLyricsManager userlyricsManager;
 @Autowired	
 private  LyricsCommentManager  plManager;
 @Autowired	
 private LyricsZanManager lyricszanManager;
 @Autowired	
 private UserFollowManager userfollowManager;
	
 	/**
 	 * 跳转到添加最爱页面
 	 * @return
 	 */
 	@CheckLogin
	@RequestMapping("/add")
	public String toAdd() {
		String result = "/page/user/lyricAdd";
		return result;
	}
	
//

	@RequestMapping("/toView/{id}")
	public String toView(HttpServletRequest request, @PathVariable("id") Integer id,ModelMap map) {
		if(null!=id && 0!=id){
			Lyrics model = lyricsManager.findLyrics(id);
			List<UserLyrics> rs =userlyricsManager.findByCondition(" where lyricsid='"+id+"' ").getResultlist();
			if(rs.size()>0){
				Integer autherid = rs.get(0).getUserid();
				if(null!=autherid && autherid>0){
				String auther = userManager.findUser(autherid).getEmail();
				map.put("auther", auther);
				}
			}
			map.put("model", model);
			String imgpath = model.getAlbumImg(); //e:/yunlu/upload/1399976848969.jpg
			if(!StringUtils.isEmpty(imgpath)){
				map.put("imgp", imgpath);
			}
		}
		return "/page/user/lyricView";
	}
	
	@RequestMapping("/remove/{lyricsid}/{userlyricsid}")
	public String remove(@PathVariable("lyricsid") Integer lyricsid,String userid,@PathVariable("userlyricsid") Integer userlyricsid,HttpServletRequest request) {
		String result =LIST_ACTION2;
		User u = (User) request.getSession().getAttribute("user");
        userid = String.valueOf(u.getId());
		if(StringUtils.isNotEmpty(userid)){
			lyricsManager.delLyrics(lyricsid);
			userlyricsManager.delUserLyrics(userlyricsid);
		}
		return result;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids,String userid) {
		String result =LIST_ACTION2;
		if(StringUtils.isNotEmpty(userid)){
		String[] glbstr = ids.split(",");
		for(String s :glbstr){
			UserLyrics userlyrics = this.userlyricsManager.findUserLyrics(Integer.parseInt(s));
			if(userlyrics!=null){//批量删除删除歌词表
				Integer lyid = userlyrics.getLyricsid();
				if(null!=lyid && lyid >0){
					this.lyricsManager.delLyrics(lyid);
				}
			}
			this.userlyricsManager.delUserLyrics(Integer.parseInt(s));//批量删除关联表
		}
		result=LIST_ACTION2+"?userid="+userid;
		}
		return result;
	}
	
	@RequestMapping("/update")
	public String update(Lyrics model,HttpServletRequest request,String oldpath, @RequestParam(value = "file", required = false) MultipartFile[] file,ModelMap map,String userid) {
		// 执行删除
		FileUtils.removeOldFile(oldpath, file);


	    	//执行上传		
			List<String> filelist = FileUtils.commonUpload(file, FileUtils.suffix_album);
	        //执行上传end

			if(filelist.size()>0){
				model.setAlbumImg(filelist.get(0));
			}else{
				model.setAlbumImg(oldpath);
			}
	        model.setUpdatedate(TimeUtils.nowTime());
		    this.lyricsManager.updateLyrics(model);
		    
		return LIST_ACTION2;
	}

	
	
	

	/**
	 * 
	 * 保存最爱
	 * @param lyrics
	 * @param userid
	 * @param request
	 * @param file
	 * @param map
	 * @return
	 */
	@RequestMapping("/addLyrics")
	public String addLyrics(Lyrics lyrics,String userid,HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile[] file,ModelMap map) {
		
		 User u = (User) request.getSession().getAttribute("user");
		 if(u!=null){
			 
			 userid = String.valueOf(u.getId());
		 }
		if(StringUtils.isNotEmpty(userid)){
			//上传
				 String albumpath = "";
				 String lrcpath   ="";
			     List<String> filelist = FileUtils.commonUpload(file, FileUtils.suffix_album);
			     if(filelist!=null&&filelist.size()>0){
			    	 albumpath = filelist.get(0);
			    	 lrcpath = filelist.size()>1?filelist.get(1):"";
			     }
		        lyrics.setUpdatedate(TimeUtils.nowTime());
		        lyrics.setType("lrc");
		        lyrics.setAlbumImg(albumpath);
		        lyrics.setPath(lrcpath);
		        this.lyricsManager.addLyrics(lyrics);
		        
		       UserLyrics userlyrics = new UserLyrics();
		       userlyrics.setLyricsid(lyrics.getId());
		       userlyrics.setUserid(Integer.parseInt(userid));
		       this.userlyricsManager.addUserLyrics(userlyrics);
			}else{//添加失败了
				map.addAttribute("albumpath", "Failure...");  
			}
		return LIST_ACTION2;
	}
	
	
	/**
	 * 根据图片lrc主题异步获取所有喜爱者
	 * @param request
	 * @param lyricsid
	 * @param map
	 * @param userid
	 * @return
	 */
	@RequestMapping("/getMoreZan")
	@ResponseBody
	public String getMoreZan(HttpServletRequest request,HttpServletResponse response, String lyricsid,ModelMap map) {
		response.setContentType("text/html; charset=UTF-8");  
		//取得zan的人的列表
		String sql  = "select b.id,b.tail_slug,b.username from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' ";
		List<Map<String, Object>> zanList = lyricszanManager.mixSqlQuery(sql);

		StringBuilder sb = new StringBuilder();   
		for (int i = 0; i < zanList.size(); i++) {
			String href = "";
			String ym = (String) zanList.get(i).get("tail_slug");
			if(StringUtils.isNotEmpty(ym)){
				href = "/people/"+ym;
			}else{
				href = "/cm/detail/"+zanList.get(i).get("id");
			}
			String str = "<span><a href = '"+href+"' title= '"+zanList.get(i).get("username")+"' >"+zanList.get(i).get("username")+"</a> &nbsp;</span>";
			sb.append(str);
		}
		
		LOGGER.debug(sb.toString());
		return sb.toString();
	}
	
	//异步分页查询#comment'数据
	@RequestMapping(value="/commentQuery")
	public String commentQuery(ModelMap map,HttpServletRequest request, HttpSession session,String userid) {
		String page = request.getParameter("currentpage");
		String lyricsid =    request.getParameter("lrcid");
		

		//取得 评论 的列表 
        String sql_  = "select b.username,b.tail_slug,b.email,b.headpath,b.headspan,b.headspanclass,a.* from bc_lyrics_comment a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' order by a.create_time desc";
        
		//每页展示6条  获取分页信息
		PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(Integer.parseInt(page), 6);
		
		pageview = this.plManager.getMixMapPage(pageview, sql_);
		
		
		//  获取分页数据
		List<Map<String, Object>> plList = this.plManager.findmixByCondition(pageview, sql_);
		
        for (int i = 0; i < plList.size(); i++) {
			
			//批量处理时间
			plList.get(i).put("create_time",TimeUtils.formatToNear((String) plList.get(i).get("create_time")));
		 }
        
       
        map.put("plList", plList);
        
        map.put("pageview", pageview);
        
        
        //分页到尽头结束了
        if(pageview.getTotalpage()<=Integer.parseInt(page)){
        	map.put("tail", "tail");
        }
        
		
		
		return "/page/zancmt/loadcomment";
	}
	
	
	/**
	 * 最爱主题页面-评论、爱上列表、点赞列表
	 * @param request
	 * @param lyricsid
	 * @param map
	 * @param userid
	 * @return
	 */
	@RequestMapping("/comment/{lyricsid}.html")
	public String comment(HttpServletRequest request, @PathVariable Integer lyricsid,ModelMap map,String userid) {
		 String result ="/zancmmet";
		 
		 //取得歌词/图片的信息 +取得上传者的信息+取得zan的人数+取得评论的条数
		 String sqlmap = "SELECT u.tail_slug as by_tail_slug, u.id as by_id, u.username as by_username,"
		 		+ " lrc.id as lrc_id, lrc.albumImg as lrc_albumImg, lrc.title as lrc_title,"
		 		+ " lrc.updatedate as lrc_updatedate, lrc.zan as zanNum, lrc.pl as plNum "
		 		+ "FROM bc_user_lyrics ul JOIN bc_user u ON ul.userid = u.id "
		 		+ "join bc_lyrics lrc on lrc.id = ul.lyricsid where ul.lyricsid = ?";
		 
		 List<Map<String, Object>> querySql = userlyricsManager.querySql(sqlmap, lyricsid);
		
		 if(!CollectionUtils.isEmpty(querySql)){
			 map.put("datamap", querySql.get(0));
		 }
         
         //取得zan的人的列表
         String sql  = "select b.id,b.tail_slug,b.username  from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' limit 0 , 10 ";
         List<Map<String, Object>> zanList = lyricszanManager.mixSqlQuery(sql);
         map.put("zanList", zanList);
         
         
         
         
         //取得谁爱上谁的一个列表
         String sql_2  = "select b.id as userid,b.tail_slug,b.username,b.email,b.headpath,b.headspan,b.headspanclass,c.id as lyricsid,c.title from bc_lyrics_zan a join bc_user b on a.userid = b.id join bc_lyrics c on a.lyricsid = c.id order by c.updatedate desc limit 0 , 50 ";
         List<Map<String, Object>> loveList = lyricszanManager.mixSqlQuery(sql_2);

         Collections.shuffle(loveList);    
         map.put("loveList", loveList);
         
         
         //取得当前用户对这个主题的赞的状态
         User user = (User) request.getSession().getAttribute("user");
         if(user!=null){
        	 Integer uid =  user.getId();

        	 Integer num = lyricszanManager.countHql( " where userid="+uid+" and lyricsid="+lyricsid);
        	 
        	 if(num>0){
        		 map.put("yizan", "yizan");
        	 }
        	 
         }
         
   		 return result;
	}
	


	
	@RequestMapping(value="/downloadLrc")
	public String downloadLrc(ModelMap map,String id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		String lrcpath ="";
		String rs ="";
		if (StringUtils.isNotEmpty(id)) {
			Lyrics  lyrics =lyricsManager.findLyrics(Integer.parseInt(id));
			if(lyrics!=null){
				lrcpath = lyrics.getPath();
				String[] str = lrcpath.split("/album/");
				if(str.length>1){
				String filename = str[1];
				//此时判断操作系统进行查找
				Properties prop = System.getProperties();

				String os = prop.getProperty("os.name");
				LOGGER.debug(os);
				String qianzhui = "e:/bruce/album";
		         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		        	 qianzhui = "e:/bruce/album";
		         }else{
		        	 qianzhui = "/mnt/apk/album";
		         }
		         
		         lrcpath=qianzhui+"/"+filename;
				}
				//此时判断操作系统进行查找---end
		      String filedisplay = lyrics.getTitle() +".lrc";//下载文件时显示的文件保存名称
		      File lrc = new File(lrcpath);
		      if(lrc.exists()){
		    	  response.setContentType("application/x-download");//设置为下载application/x-download
		    	  response.setHeader("Content-Disposition","attachment;filename=" + java.net.URLEncoder.encode(filedisplay, "UTF-8"));
		    	  FileInputStream fis = null;
		    	  ServletOutputStream fos = null;
		    	  try {
		    		  fis=new FileInputStream(lrc);
		    		  fos= response.getOutputStream();
		    	      byte a[] = new byte[1024];
		    		  fis.read(a);
			    	  fos.write(a);
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					fos.flush();
					fos.close();
					fis.close();
				}
		    	  
		      }else{
		    	  ScriptTools.alert(request, response, "the lrc file has lost");
				   rs = "/page/user/lyricView";
		      }
			}
		}
		return rs;
		
	}
	


}
