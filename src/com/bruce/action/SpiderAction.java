
package com.bruce.action;
/*
* @author bruce
* 本类用于网络爬虫获取各种网站资料【图片|笔记|文章|】
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.manager.GetImgManager;
import com.bruce.manager.GetNoteManager;
import com.bruce.manager.LyricsManager;
import com.bruce.manager.NoteManager;
import com.bruce.manager.UserLyricsManager;
import com.bruce.manager.UserManager;
import com.bruce.model.GetImg;
import com.bruce.model.GetNote;
import com.bruce.model.Lyrics;
import com.bruce.model.Note;
import com.bruce.model.User;
import com.bruce.model.UserLyrics;
import com.bruce.utils.Base64Util;
import com.bruce.utils.HTMLParserUtil;
import com.bruce.utils.PinyinUtil;
import com.bruce.utils.TimeUtils;


@Controller
@RequestMapping("/web")
public class SpiderAction {
 @Autowired	
 private NoteManager noteManager;
 @Autowired	
 private UserManager userManager;
 @Autowired	
 private LyricsManager lyricsManager;
 @Autowired	
 private GetNoteManager getnoteManager;
 @Autowired	
 private GetImgManager getimgManager;
 @Autowired	
 private UserLyricsManager userlyricsManager;
 
 
 
 // http://music.163.com/#/discover/toplist?id=19723756
 
 
 /**
  * 生成唯一自主域名
 * @param user
 * @param map
 * @param session
 */
@RequestMapping("/upslug")
	public void upslug( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
	try {
		
				
		    	//茶找用户
		    	List<User> ulist = userManager.findAll();
		    	for (int i = 0; i < ulist.size(); i++) {
					User u = ulist.get(i);
					String email = u.getEmail();
					if(StringUtils.isNotEmpty(email)){
						if(email.contains("@")){
						String slug = email.substring(0,email.lastIndexOf("@"));
						System.out.println(slug);
						u.setTail_slug(slug);
						userManager.updateUser(u);
						
						}
						
					}
				}

			
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		   
	}
 

 
 /**
  * 生成图片lrc
 * @param user
 * @param map
 * @param session
 */
@RequestMapping("/geneLrc")
	public void geneLrc( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
	try {
		
		 //获取lrc图像抓取路径
		List<GetImg> lrclist = getimgManager.findByCondition(" where type = 'lrc' and isGened = '0'").getResultlist();
		    for (int i = 0; i < lrclist.size(); i++) {
				
		    	//茶找用户
		    	List<User> ulist = userManager.findByCondition(" where date_joined >'2015-12-12 22:54:35'  ").getResultlist();

		    	//随机分配给一个用户
		    	User u = ulist.get(getRandomOne(ulist));
		    	
		    	//添加用户的歌词
		    	Lyrics lyrics = new Lyrics();
		    	//主
			     lyrics.setType("lrc");
			     lyrics.setAlbumImg("/mnt/apk/album/"+lrclist.get(i).getPath());
			     lyrics.setPath("/mnt/apk/album/1449720831653.lrc");
			     lyrics.setAlbum("Fantasty");
			     lyrics.setArtist("Jay");
			     lyrics.setMedialength("00:05:20");
			     lyrics.setRating("5星");
			     lyrics.setTitle("Sunny Day");
			     lyrics.setType("lrc");
			     lyrics.setUpdatedate(TimeUtils.N_YearDate(-1));
			     
			     this.lyricsManager.addLyrics(lyrics);
			     //副
			     UserLyrics ul = new UserLyrics();
			     ul.setLyricsid(lyrics.getId());
			     ul.setUserid(u.getId());
			     this.userlyricsManager.addUserLyrics(ul);


		    	//更新生成状态位
		    	lrclist.get(i).setIsGened("1");
		    	getimgManager.updateGetImg(lrclist.get(i));
		    	
			}
			
			
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		   
	}
 

 
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
  * 爬虫生成歌单
  * @param map
  */
 @RequestMapping("/geneSong")
	public void geneSong(ModelMap map) {
 		
		try {
			List<String> list = HTMLParserUtil.readsong();
			List<Lyrics> list2 = lyricsManager.findByCondition(" where album = 'Fantasty'").getResultlist();
			for (Lyrics l:list2) {
				l.setAlbum(list.get(getRandomOne(list)));
				l.setArtist(list.get(getRandomOne(list)));
				l.setTitle(list.get(getRandomOne(list)));
				lyricsManager.updateLyrics(l);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
	 
 /**
  * 爬虫生成100页的图片【头像】内容
  * @param map
  */
 @RequestMapping("/geneImg")
	public void geneImg(ModelMap map) {
 	List<String> list = new ArrayList<String>();
 	for (int i = 0; i < 100; i++) {
			String url = "http://www.caimai.cc/love/page"+i;
 		List<String> list_ = HTMLParserUtil.getImgUrl(url);
 		

 			list.addAll(list_);
 		
		}
	 	for (int i = 0; i < list.size(); i++) {
	
	
	 		//保存  
	 		try {  
	
	 			String  headpath =  uploadHead(list, i);
	 		    GetImg gg = new GetImg();
	 		    gg.setCreatetime(TimeUtils.N_YearTime(1));
	 		    gg.setIsGened("0");
	 		    gg.setPath(headpath);
	 		    gg.setType("lrc");
	 		    
	 		   getimgManager.addGetImg(gg);
	 		    
	 			
	 		} catch (Exception e) {  
	 			e.printStackTrace();  
	 		}  
	
	 	}
	}

/**
 * 上传头像并且返回最后的路径
 * @param list
 * @param i
 * @throws MalformedURLException
 * @throws IOException
 * @throws FileNotFoundException
 */
private String uploadHead(List<String> list, int i) throws MalformedURLException,
		IOException, FileNotFoundException {
	URL   url   =   new   URL(list.get(i)); 
	URLConnection   uc   =   url.openConnection(); 
	InputStream   is   =   uc.getInputStream(); 

	//执行上传头像	
	System.out.println("-------------------------------------->开始");  
	Properties prop = System.getProperties();

	String os = prop.getProperty("os.name");
	System.out.println(os);
	String path = "e:/bruce/album";
	String fileName=list.get(i);
	String newName="";
	if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		path = "e:/bruce/album";
	}else{
		path = "/mnt/apk/album";
	}
	String headpath = "";
	System.out.println(path); 
	if(!StringUtils.isEmpty(fileName)){
		String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
		newName = String.valueOf(System.currentTimeMillis())+"."+ext;
		File targetFile = new File(path, newName);  

		FileOutputStream   out   =   new   FileOutputStream(targetFile); 
		int   i1=0; 
		while   ((i1=is.read())!=-1)   { 
			out.write(i1); 
		} 
		is.close();
	}
	headpath = newName;
	return headpath;
}
    
    /**
     * 爬虫生成100页的笔记内容
     * @param map
     */
    @RequestMapping("/geneNote")
	public void geneNote(ModelMap map) {
    	List<String> listnote = new ArrayList<String>();
//    	List<String> listbrief = new ArrayList<String>();
    	for (int i = 100; i < 200; i++) {
			String url = "http://www.caimai.cc/story/page"+i;
    		List<String> list_note = HTMLParserUtil.getClassCont(url, "div[class=clearfix hidden]");
    		listnote.addAll(list_note);
    		
    		//[attr^=value], [attr$=value], [attr*=value]	这三个语法分别代表，属性以 value 开头、结尾以及包含
//    		List<String> list_brief = HTMLParserUtil.getClassCont(url, "p[id$=brief_]");
//    		listbrief.addAll(list_brief);
		}
    	for (int i = 0; i < listnote.size(); i++) {
					
					String note = listnote.get(i);
					GetNote gg = new GetNote();
					gg.setIsGened("0");
					gg.setNotes(note);
					gg.setCreatetime(TimeUtils.N_YearTime(1));
					
					getnoteManager.addGetNote(gg);
				}
	}
	

	/**
	 * 处理笔记
	 * @param note_
	 * @return
	 */
	private StringBuilder handleNotes(String note_) {
		StringBuilder sb = new StringBuilder();
		String str[] = note_.split("</p>");
		if(str.length>=3){
			sb.append(str[0]).append("</p>");
			sb.append(str[1]).append("</p>");
			sb.append(str[2]).append("</p>");
		}else{
			String rp_ = note_.replaceAll("<p>", "").replaceAll("</p>", "");
			String br[] = rp_.split("<br>");
			if(br.length>=3){
				sb.append(br[0]).append("<br>");
				sb.append(br[1]).append("<br>");
				sb.append(br[2]).append("<br>");
			}else{
				String space[] = rp_.split(" ");
				for (int i = 0; i < space.length; i++) {
					if(i!=space.length-1){
					    space[i] += "<br>";
					}
					sb.append(space[i]);
				}
			}
		}
		return sb;
	}
	
	@RequestMapping("/Bief")
	public String updateBief() {
		
		List<GetNote> list = this.getnoteManager.findAll();
		
		for(GetNote n:list){
			//处理笔记和介绍
			String note_ = n.getNotes();
			StringBuilder sb = handleNotes(note_);
			System.out.println(sb.toString());
			n.setBrief(sb.toString());
			
			getnoteManager.updateGetNote(n);
			//end-------------------
			
			
		}
			
		return "111";
	}

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
	
	
	@RequestMapping("/upup")
	public void updateeE() {
		
		List<User> ulist = userManager.findByCondition(" where date_joined >'"+"2015-12-14 18:49:19"+"' ").getResultlist();
			
			for (int i = 0; i < ulist.size(); i++) {
				User u  = ulist.get(i);
				String email = u.getEmail();
				email.replaceAll("", "_");
                u.setEmail(email.toLowerCase());
				userManager.updateUser(u);
			}
	}
	
	
	public static void main(String[] args) {
		List<String> list   =  new ArrayList<String>();
		for (int i = 0; i < 165; i++) {
			
			List<String> list2 = HTMLParserUtil.getClassCont("http://www.caimai.cc/story/page"+i, "small[class=gray-text]");
			list.addAll(list2);
		}
		list = listRM(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println(list.size());
	}
	
	
	
	/**
	 * list去重
	 * @return
	 */
	public static  List<String> listRM(List<String> list) {  
	    List<String> tempList= new ArrayList<String>();  
	    for(String i:list){  
	        if(!tempList.contains(i)&&!"采麦故事".equals(i)){  
	            tempList.add(i);  
	        }  
	    }  
	    
	    return tempList;
	}     
	
	/**
	 * list去重
	 * @return
	 */
	public static  List<String> listRM1(List<String> list) {  
	    List<String> listTemp= new ArrayList<String>();  
		 Iterator<String> it=list.iterator();  
		 while(it.hasNext()){  
		  String a=it.next();  
		  if(listTemp.contains(a)){  
		   it.remove();  
		  }  
		  else{  
		   listTemp.add(a);  
		  }  
		 }  
	    
	    return listTemp;
	}  
	
	/**
	 * list去重
	 * @return
	 */
	public static  List<String> listRM3(List<String> list) {  
		List<String> listTemp= null;  
		HashSet<String> set = new HashSet<String>();
		set.addAll(list);
		listTemp = new ArrayList<String>(set);
	    return listTemp;
	}
	
	
	
	
	
		 
}
