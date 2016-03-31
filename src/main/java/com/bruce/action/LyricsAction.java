
package com.bruce.action;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bruce.manager.LyricsManager;
import com.bruce.manager.LyricsZanManager;
import com.bruce.manager.UserFollowManager;
import com.bruce.manager.UserLyricsManager;
import com.bruce.manager.UserManager;
import com.bruce.model.Lyrics;
import com.bruce.model.User;
import com.bruce.model.UserLyrics;
import com.bruce.query.LyricsQuery;
import com.bruce.query.condition.LyricsQueryCondition;
import com.bruce.utils.FileUtils;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.ScriptTools;
import com.bruce.utils.TimeUtils;

@Controller
@RequestMapping("/lyrics")
@SessionAttributes({ "list", "lyrics" })
public class LyricsAction {

 private final String LIST_ACTION = "redirect:/lyrics/findAll";
 private final String LIST_ACTION2 = "redirect:/cm/pcentral";
 private final String LOGIN_ACTION = "redirect:/cm/toLogin";

 
 
 @Autowired	
 private LyricsManager lyricsManager;
 @Autowired	
 private LyricsQuery lyricsQuery;
 @Autowired	
 private UserManager userManager;
 @Autowired	
 private UserLyricsManager userlyricsManager;
 
 @Autowired	
 private LyricsZanManager lyricszanManager;
 @Autowired	
 private UserFollowManager userfollowManager;
	
	@RequestMapping("/add")
	public String toAdd(ModelMap map,String userid,HttpServletRequest request,HttpServletResponse response) {
		String result = "/page/user/lyricAdd";
		 User u = (User) request.getSession().getAttribute("user");
		 if(u!=null){
			 
		 }else{
			 result = LOGIN_ACTION;
		 }
		return result;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") Integer id,ModelMap map,String userid) {
		String result ="/page/user/lyricEdit";
		
		if(StringUtils.isEmpty(userid)){
            User u = (User) request.getSession().getAttribute("user");
            userid = String.valueOf(u.getId());
        } 
		if(StringUtils.isEmpty(userid)){
			result ="redirect:/lyrics/toView?id="+id;
		}
		map.put("userid", userid);
		if(null!=id && 0!=id){
			Lyrics model = lyricsManager.findLyrics(id);
			map.put("model", model);
			String imgpath = model.getAlbumImg(); 
			if(!StringUtils.isEmpty(imgpath)){
				map.put("imgp", imgpath);
			}
		}
		return result;
	}
	
	@RequestMapping("/toView")
	public String toView(HttpServletRequest request, @RequestParam("id") Integer id,ModelMap map) {
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
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("lyricsid") Integer lyricsid,String userid,Integer userlyricsid) {
		String result =LIST_ACTION2;
		if(StringUtils.isNotEmpty(userid)){
		this.lyricsManager.delLyrics(lyricsid);
		userlyricsManager.delUserLyrics(userlyricsid);
		result=LIST_ACTION2+"?userid="+userid;
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
		String sql  = "select b.* from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' ";
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
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	@RequestMapping("/comment/{lyricsid}.html")
	public String comment(HttpServletRequest request, @PathVariable Integer lyricsid,ModelMap map,String userid) {
		 String result ="/zancmmet";
		 String by_id = "";
		 List<UserLyrics> list = userlyricsManager.findByCondition(" where lyricsid = '"+lyricsid+"'").getResultlist();
		 if(list!=null){
			 if(list.size()>0){
				 by_id =  String.valueOf(list.get(0).getUserid());
			 }
		 }
		 //取得歌词/图片的信息 
		 Lyrics lyrics = lyricsManager.findLyrics(lyricsid);
		 
		 map.put("lrc", lyrics);
		 
		 //取得上传者的信息
         if(StringUtils.isNotEmpty(by_id)){
        	 User  by =  userManager.findUser(Integer.parseInt(by_id));
        	 map.put("by", by);
         }
         
         //取得zan的人的列表
         String sql  = "select b.* from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' limit 0 , 10 ";
         List<Map<String, Object>> zanList = lyricszanManager.mixSqlQuery(sql);
         map.put("zanList", zanList);
         
         
         //取得 评论 的列表
         String sql_  = "select b.username,b.tail_slug,b.email,b.headpath,a.* from bc_lyrics_comment a join bc_user b on a.userid = b.id where a.lyricsid = '"+lyricsid+"' order by a.create_time desc";
         List<Map<String, Object>> plList = lyricszanManager.mixSqlQuery(sql_);
         for (int i = 0; i < plList.size(); i++) {
 			
 			//批量处理时间
 			plList.get(i).put("create_time",TimeUtils.formatToNear((String) plList.get(i).get("create_time")));
		 }
         
        
         map.put("plList", plList);
         
         //取得zan的人数
         int zanNum = lyricszanManager.getZanNumByLRC(String.valueOf(lyricsid));
         map.put("zanNum", zanNum);
         
         //取得评论的条数
         int plNum = lyricszanManager.getCommentNumByLRC(String.valueOf(lyricsid));
         map.put("plNum", plNum);
         
         //取得谁爱上谁的一个列表
         String sql_2  = "select b.id as userid,b.tail_slug,b.username,b.email,b.headpath,c.id as lyricsid,c.title from bc_lyrics_zan a join bc_user b on a.userid = b.id join bc_lyrics c on a.lyricsid = c.id  limit 0 , 100 ";
         List<Map<String, Object>> loveList = lyricszanManager.mixSqlQuery(sql_2);

         Collections.shuffle(loveList);    
         map.put("loveList", loveList);
         
         
         //取得当前用户对作者的关注状态
         User user = (User) request.getSession().getAttribute("user");
         if(user!=null){
        	 String follow_id =  String.valueOf(user.getId());
        	 String author_id = by_id;
        	 if(StringUtils.isNotEmpty(follow_id)&&StringUtils.isNotEmpty(author_id)){
        		 int nums = userfollowManager.findByCondition(" where author_id = '"+author_id+"' and follow_id = '"+follow_id+"' ").getResultlist().size();
        		 if(nums>0){
        			 map.put("gz", "ygz");
        		 }
        	 }
        	 
         }
         
   		 return result;
	}
	
	

	@RequestMapping("/findLyrics")
	private String findLyrics(@RequestParam("id") Integer id, ModelMap map) {
		Lyrics lyrics = this.lyricsManager.findLyrics(id);
		map.addAttribute("lyrics", lyrics);
		return "findresult";
	}

	@RequestMapping("/delLyrics")
	public String delLyrics(@RequestParam("id") Integer id) {
		this.lyricsManager.delLyrics(id);
		return LIST_ACTION;
	}

	
	@RequestMapping(value="/downloadLrc")
	@ResponseBody
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
				System.out.println(os);
				String qianzhui = "e:/bruce/album";
				String fileName="";
				String newName="";
		         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		        	 qianzhui = "e:/bruce/album";
		         }else{
		        	 qianzhui = "/mnt/apk/album";
		         }
		         
		         lrcpath=qianzhui+"/"+filename;
				}
				//此时判断操作系统进行查找---end
		      String filedownload = "/要下载的文件名";//即将下载的文件的相对路径
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
		    	  ScriptTools.alert(request, response, "歌词已丢失");
				   rs = "/page/user/lyricView";
		      }
			}
		}
		return rs;
		
	}
	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,LyricsQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = lyricsQuery.getSql(condition);
		
		PageView<Lyrics> pageView = getPageView(request, whereSql);
		

//		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
//		order.put("createtime", "desc");

		QueryResult<Lyrics> qrs = this.lyricsManager.findByCondition(pageView,
				whereSql, null);
		List<Lyrics> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","lyrics/findAll" );

		return "/page/user/lyricList";
	}

	private PageView<Lyrics> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<Lyrics> pageView = new PageView<Lyrics>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.lyricsManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.lyricsManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
