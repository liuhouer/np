
package cn.northpark.action;
/*
* @author bruce
* 本类用于网络爬虫获取各种网站资料【图片|笔记|文章|】
**/
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.manager.GetnotesManager;
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.NoteManager;
import cn.northpark.manager.QuanManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.manager.UserManager;
import cn.northpark.model.Getnotes;
import cn.northpark.model.Lyrics;
import cn.northpark.model.LyricsComment;
import cn.northpark.model.LyricsZan;
import cn.northpark.model.Quan;
import cn.northpark.model.User;
import cn.northpark.utils.JedisUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.SerializationUtil;
import cn.northpark.utils.TimeUtils;


@Controller
@RequestMapping("/web")
//@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-hibernate.xml" })
public class SpiderAction {
 @Autowired	
 private NoteManager noteManager;
 @Autowired	
 private UserManager userManager;
 @Autowired	
 private LyricsManager lyricsManager;
 @Autowired	
 private UserLyricsManager userlyricsManager;
 @Autowired	
 private LyricsZanManager zanManager;
 @Autowired	
 private LyricsCommentManager commentManager;
 
 @Autowired	
 private GetnotesManager getnotesManager;
 
 @Autowired
 public QuanManager quanManager;

 
 public static Set<Integer> m = new HashSet<Integer>();
	/**
	 * 生成6位int  ID
	 * @return
	 */
	public static int getInt6() {
		int a = 0;
			do {
				a = (int) (Math.random() * 10000+700000);
			} while (m.contains(a));
			m.add(a);
			System.out.println(a);
		return a;
	}
	
	/**
	  * 添加lrcid映射
	 * @param user
	 * @param map
	 * @param session
	 */
	@RequestMapping("/addQuan")
	public void addQuan(){
		//去重
		  List<Quan> list_q =  quanManager.findAll();
		  Set<String> set = new HashSet<String>();
		  for(Quan q:list_q){
			  set.add(q.getPath());
		  }
		  
		  
		  byte[] b = JedisUtil.getListByte("B_quan");
	      List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
	      
	    for (int i = 0; i <list.size(); i++) {
	    	Map<String,String> map = list.get(i);
	    	if(set.add(map.get("path"))){//判断去重
			    	Quan model = new Quan();
			    	model.setFromwhere(map.get("from"));     
					model.setPublistime( map.get("publishtime"));   
					model.setAuthorIP(map.get("authorIP"));     
					model.setPath(map.get("path"));    
					model.setTitle(map.get("title"));    
					model.setPath_mt(map.get("path_mt"));    
					model.setAddtime(map.get("addtime"));
					quanManager.addQuan(model);
	    	}
		}
	}

	
	/**
	  * 添加lrcid映射
	 * @param user
	 * @param map
	 * @param session
	 */
	@RequestMapping("/lrccm")
		public void lrccm( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<LyricsComment> ul = commentManager.findAll();
	        Set<Integer> set1 = new HashSet<Integer>(); 
	        Set<Integer> set2 = new HashSet<Integer>(); 
			for (LyricsComment u:ul) {
				if(set1.add(u.getId())){
					
				}else{
					set2.add(u.getId());
				}
			}
			
			
			for(LyricsComment u:ul){
				for(int i:set2){
					if(u.getId() == i){
						u.setId(getInt6());
					}
				}
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			   
		}
	
	/**
	  * 更新note表信息
	 * @param user
	 * @param map
	 * @param session
	 */
//	@RequestMapping("/noteid")
//		public void noteid( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//		try {
//			
//					
//			List<Note> ul = noteManager.findAll();
//	        
//			for (Note u:ul) {
//				int id6 = getInt6();
//				u.setId_(id6);
//				try{
//					int a = u.getUserid_();
//					if(String.valueOf(a).length()<6){
//						u.setUserid_(507723);
//					}
//				}catch(Exception e){
//					u.setUserid_(507723);
//				}
//				noteManager.updateNote(u);
//			}
//				
//				
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//			   
//		}
	
	/**
	  * 添加lrcid映射
	 * @param user
	 * @param map
	 * @param session
	 */
//	@RequestMapping("/lrcidlink")
//		public void lrcidlink( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//		try {
//			
//					
//			List<Lyrics> ul = lyricsManager.findAll();
//	        
//			for (Lyrics u:ul) {
//				LrcidLink  lrcidlink = new LrcidLink();
//				String lrcid = u.getId();
//				int id6 = getInt6();
//				lrcidlink.setLyricsid(lrcid);
//				lrcidlink.setLyricsid_(id6);
//				lrcidLinkManager.addLrcidLink(lrcidlink);
//			}
//				
//				
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//			   
//		}

 
 /**
  * 添加uid映射
 * @param user
 * @param map
 * @param session
 */
//@RequestMapping("/uidlink")
//	public void uidlink( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//	try {
//		
//				
//		List<User> ul = userManager.findAll();
//        
//		for (User u:ul) {
//			UidLink uidlink = new UidLink();
//			String userid = u.getId();
//			int uid = getInt6();
//			uidlink.setUserid(userid);
//			uidlink.setUid_new(uid);
//			uidLinkManager.addUidLink(uidlink);
//		}
//			
//			
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//		   
//	}
//
// 
 
 
 // http://music.163.com/#/discover/toplist?id=19723756
 
 /**
  * 头像
 * 
 */
@RequestMapping("/head")
	public void head( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
	try {
		
				
//		List<Lyrics> list = lyricsManager.findAll();
		List<User> ul = userManager.findAll();
		for(User u:ul){
			String name  = u .getUsername();
			
			String abc = PinyinUtil.paraseStringToPinyin(name);
			if(StringUtils.isNotEmpty(abc)){
				String headspan = abc.substring(0, 1).toUpperCase();
				String headspanclass = "text-"+headspan.toLowerCase();
				u.setHeadpath(headspan);
				u.setHeadspanclass(headspanclass);
				userManager.updateUser(u);
			}
		}
			
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		   
	}
 
 /**
  * 生成赞和评论
 * @param user
 * @param map
 * @param session
 */
@RequestMapping("/zan")
	public void zan( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
	try {
		
				
		List<Lyrics> list = lyricsManager.findAll();
		List<User> ul = userManager.findAll();
		List<Getnotes> nol = getnotesManager.findAll();
		System.out.println("生成开始..."+TimeUtils.nowTime());
		for (int i = 0; i < list.size(); i++) {
			try{
			   for(int j =0;j<getRandomInt();j++){
			   	//添加评论
			   	String commet = nol.get(getRandomOne(nol)).getBrief();
			   	LyricsComment cm =  new LyricsComment();
			   	cm.setComment(commet);
			   	cm.setCreate_time(TimeUtils.getNowTime());
			   	cm.setLyricsid(list.get(i).getId());
			   	cm.setUserid(ul.get(getRandomOne(ul)).getId());
			   	commentManager.addLyricsComment(cm);
			   	System.out.println("生成中...");
			   }
               
//			   for(int k =0;k<getRandomInt();k++){
//			   		//添加赞
//			   		LyricsZan zan  =new LyricsZan();
//			   		zan.setLyricsid(list.get(i).getId());
//			   		zan.setUserid(ul.get(getRandomOne(ul)).getId());
//			   		zanManager.addLyricsZan(zan);
//			   
//			   }
			}catch(Exception e){
				continue;
			}
		}

		System.out.println("生成结束..."+TimeUtils.nowTime());
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		   
	}

/**
 * 生成评论数目
* @param user
* @param map
* @param session
*/
@RequestMapping("/plnum")
	public void plnum( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
	try {
		
				
		List<Lyrics> list = lyricsManager.findAll();
		System.out.println("生成开始..."+TimeUtils.nowTime());
		for (int i = 0; i < list.size(); i++) {
			Lyrics lyrics = list.get(i);
			Integer lrcid = lyrics.getId();
			String sql= "select * from bc_lyrics_comment where lyricsid="+lrcid;
			int nums = commentManager.countSql(sql);
			System.out.println("nums-----"+nums);
			lyrics.setPl(nums);
			lyricsManager.updateLyrics(lyrics);
		}

		System.out.println("生成结束..."+TimeUtils.nowTime());
			
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		   
	}
//
//
///**
// * @param list
// * @param ul
// * @param nol
// * @param i
// */
//public void resetVal(List<Lyrics> list ,List<GetNote> nol ,List<User> ul , int i) {
//	String userid = ul.get(getRandomOne(ul)).getId();
//	String lyricsid = list.get(i).getId();
//	String commet = nol.get(getRandomOne(nol)).getBrief();
//	//添加赞和评论的方法
//	addZanPl(userid, lyricsid,commet);
//}


///**
// * 添加赞和评论的方法
// * @param userid
// * @param lyricsid
// * @param commet
// */
//public void addZanPl(String userid, String lyricsid,String commet) {
//	//赞
////	LyricsZan zan = new LyricsZan();
////	zan.setLyricsid(lyricsid);
////	zan.setUserid(userid);
////	zanManager.addLyricsZan(zan);
////	//评论
//	LyricsComment cm =  new LyricsComment();
//	cm.setComment(commet);
//	cm.setCreate_time(TimeUtils.getNowTime());
//	cm.setLyricsid(lyricsid);
//	cm.setUserid(userid);
//	commentManager.addLyricsComment(cm);
//}
//  
// 
// /**
//  * 生成随机时间
// * @param user
// * @param map
// * @param session
// */
//@RequestMapping("/timelrc")
//	public void timelrc( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//	try {
//		
//				
//		    	//茶找用户
//		    	List<Note> ulist = noteManager.findAll();
//		    	for (int i = 0; i < ulist.size(); i++) {
//					Note u = ulist.get(i);
//					String update = u.getCreatetime();
//					if(StringUtils.isNotEmpty(update)){
//
//						if(TimeUtils.stringToMillis(update) > new Date().getTime()){
//							String time =  TimeUtils.getRandomDate();
//							u.setCreatetime(time);
//							noteManager.updateNote(u);
//						}
//						
//					}
//						
//				}
//
//			
//			
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//		   
//	}
// 
//
// /**
//  * 生成唯一自主域名
// * @param user
// * @param map
// * @param session
// */
//@RequestMapping("/upslug")
//	public void upslug( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//	try {
//		
//				
//		    	//茶找用户
//		    	List<User> ulist = userManager.findAll();
//		    	for (int i = 0; i < ulist.size(); i++) {
//					User u = ulist.get(i);
//					String email = u.getEmail();
//					if(StringUtils.isNotEmpty(email)){
//						if(email.contains("@")){
//						String slug = email.substring(0,email.lastIndexOf("@"));
//						System.out.println(slug);
//						u.setTail_slug(slug);
//						userManager.updateUser(u);
//						
//						}
//						
//					}
//				}
//
//			
//			
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//		   
//	}
// 
//
// 
// /**
//  * 生成图片lrc
// * @param user
// * @param map
// * @param session
// */
//@RequestMapping("/geneLrc")
//	public void geneLrc( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//	try {
//		
//		 //获取lrc图像抓取路径
//		List<GetImg> lrclist = getimgManager.findByCondition(" where type = 'lrc' and isGened = '0'").getResultlist();
//		    for (int i = 0; i < lrclist.size(); i++) {
//				
//		    	//茶找用户
//		    	List<User> ulist = userManager.findByCondition(" where date_joined >'2015-12-12 22:54:35'  ").getResultlist();
//
//		    	//随机分配给一个用户
//		    	User u = ulist.get(getRandomOne(ulist));
//		    	
//		    	//添加用户的歌词
//		    	Lyrics lyrics = new Lyrics();
//		    	//主
//			     lyrics.setType("lrc");
//			     lyrics.setAlbumImg("/mnt/apk/album/"+lrclist.get(i).getPath());
//			     lyrics.setPath("/mnt/apk/album/1449720831653.lrc");
//			     lyrics.setAlbum("Fantasty");
//			     lyrics.setArtist("Jay");
//			     lyrics.setMedialength("00:05:20");
//			     lyrics.setRating("5星");
//			     lyrics.setTitle("Sunny Day");
//			     lyrics.setType("lrc");
//			     lyrics.setUpdatedate(TimeUtils.N_YearDate(-1));
//			     
//			     this.lyricsManager.addLyrics(lyrics);
//			     //副
//			     UserLyrics ul = new UserLyrics();
//			     ul.setLyricsid(lyrics.getId());
//			     ul.setUserid(u.getId());
//			     this.userlyricsManager.addUserLyrics(ul);
//
//
//		    	//更新生成状态位
//		    	lrclist.get(i).setIsGened("1");
//		    	getimgManager.updateGetImg(lrclist.get(i));
//		    	
//			}
//			
//			
//			
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//		   
//	}
// 
//
// 
// /**
//  * 生成一切东西
// * @param user
// * @param map
// * @param session
// */
//@RequestMapping("/everything")
//	public void everything( ModelMap map,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
//	try {
//		
//		    //获取用户头像抓取路径
//			List<GetImg> headlist = getimgManager.findAll();
//			List<GetNote> notelist = getnoteManager.findAll();
//		    for (int i = 0; i < 1000; i++) {
//				
//		    	//添加用户
//		    	User user = new User();
//		    	//假设为一年以后的注册用户
//		    	user.setDate_joined(TimeUtils.N_YearTime(1));
//		    	String username =PinyinUtil.get4Hanzi();
//		    	String email = PinyinUtil.getFanyiEmail();
//		    	//唯一性递归-----------
//		    	int num = userManager.findByCondition(" where email = '"+email+"' ").getResultlist().size();
//				if(num>0){
//					email = PinyinUtil.getFanyiEmail();
//				}
//				int num2 = userManager.findByCondition(" where username = '"+username+"' ").getResultlist().size();
//				if(num2>0){
//					username = PinyinUtil.get4Hanzi();
//				}
//				//唯一性递归-----------end
//		    	user.setEmail(email);
//		    	String head = headlist.get(i)==null?"":headlist.get(i).getPath();
//		    	head = "/mnt/apkheads/"+head;
//		    	user.setUsername(username);
//		    	user.setHeadpath(head);
//		    	user.setPassword(Base64Util.JIAMI("123456"));
//		    	this.userManager.addUser(user);
//		    	
//		    	//添加用户的日记
//		    	Note note = new Note();
//		    	String note_ = notelist.get(i)==null?"":notelist.get(i).getNotes();
//		    	String brief_ = notelist.get(i)==null?"":notelist.get(i).getBrief();
//		    	note.setBrief(brief_);
//		    	note.setCreatetime(TimeUtils.N_YearTime(1));
//		    	note.setNote(note_);
//		    	note.setOpened("yes");
//		    	note.setUserid(user.getId());
//		    	noteManager.addNote(note);
//		    	//更新生成状态位
//		    	notelist.get(i).setIsGened("1");
//		    	getnoteManager.updateGetNote(notelist.get(i));
//		    	headlist.get(i).setIsGened("1");
//		    	getimgManager.updateGetImg(headlist.get(i));
//		    	
//			}
//			
//			//添加用户
//			
//			
//	} catch (Exception e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//		   
//	}
// 
// /**
//  * 爬虫生成歌单
//  * @param map
//  */
// @RequestMapping("/geneSong")
//	public void geneSong(ModelMap map) {
// 		
//		try {
//			List<String> list = HTMLParserUtil.readsong();
//			List<Lyrics> list2 = lyricsManager.findByCondition(" where album = 'Fantasty'").getResultlist();
//			for (Lyrics l:list2) {
//				l.setAlbum(list.get(getRandomOne(list)));
//				l.setArtist(list.get(getRandomOne(list)));
//				l.setTitle(list.get(getRandomOne(list)));
//				lyricsManager.updateLyrics(l);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 		
//	}
//	 
// /**
//  * 爬虫生成100页的图片【头像】内容
//  * @param map
//  */
// @RequestMapping("/geneImg")
//	public void geneImg(ModelMap map) {
// 	List<String> list = new ArrayList<String>();
// 	for (int i = 0; i < 100; i++) {
//			String url = "http://www.caimai.cc/love/page"+i;
// 		List<String> list_ = HTMLParserUtil.getImgUrl(url);
// 		
//
// 			list.addAll(list_);
// 		
//		}
//	 	for (int i = 0; i < list.size(); i++) {
//	
//	
//	 		//保存  
//	 		try {  
//	
//	 			String  headpath =  uploadHead(list, i);
//	 		    GetImg gg = new GetImg();
//	 		    gg.setCreatetime(TimeUtils.N_YearTime(1));
//	 		    gg.setIsGened("0");
//	 		    gg.setPath(headpath);
//	 		    gg.setType("lrc");
//	 		    
//	 		   getimgManager.addGetImg(gg);
//	 		    
//	 			
//	 		} catch (Exception e) {  
//	 			e.printStackTrace();  
//	 		}  
//	
//	 	}
//	}
// 
// 
// /**
//  * 爬虫我的的博客
//  * @param map
//  */
// @RequestMapping("/getMe")
//	public void getMe(ModelMap map) {
//	 try {
//		 for (int i = 0; i < 6000; i++) {
////				String url = "http://northpark.cn/";
////	 		    List<String> list_ = HTMLParserUtil.getImgUrl(url);
////	 		
//		 }
//	 } catch (Exception e) {
//	// TODO: handle exception
//		 e.printStackTrace();
//	 } 	
// 	
// 	
//	}
// /**
//  * 爬虫xukaiqiang的博客
//  * @param map
//  */
// @RequestMapping("/getArticle")
//	public void getArticle(ModelMap map) {
//	 try {
//       LinkedList<String> article = new LinkedList<String>();
//	   List<String> urilist = new ArrayList<String>();
//	for (int i = 1; i <= 6; i++) {
//		File in = new File("/Users/zhangyang/Downloads/a"+i+".html");
//
//		Document doc = Jsoup.parse(in, "UTF-8", ""); 
////		String title = doc.title();
////		System.out.println(title);
//		Elements notes   = doc.select("a");
//		for(Element p : notes){
//
//				   String links = p.attr("href");
//				   //System.out.println(links);
//				   if(links.startsWith("http://www.xukaiqiang.com/article/")){
//					   urilist.add(links);
//				   }
//
//		}
//	}
//	
//		HashSet<String> v = new HashSet<String>();
//		v.addAll(urilist);
//		List<String> list = new ArrayList<String>();
//		list.addAll(v);
//		for (String s:list) {
//			System.out.println(s);
//			//开始第二步，读取url里面的内容
//			List<String> artlist = HTMLParserUtil.getClassCont(s, "div[class=span12]");
//			article.addAll(artlist);
//		}
//		
//		
////		for (int i = 0; i < article.size(); i++) {
//
//
////			FileWriter fw = null;
////			File f = new File("//Users//zhangyang//Documents//art//a"+i+".txt");
////
////			if(!f.exists()){
////			    f.createNewFile();
////			}
////			fw = new FileWriter(f);
////			BufferedWriter out = new BufferedWriter(fw);
////			out.write(article.get(i), 0, article.get(i).length()-1);
////			out.close();
//			
//			
////		}
//		
//		
//	 } catch (Exception e) {
//	// TODO: handle exception
//		 e.printStackTrace();
//	 } 	
// 	
// 	
//	}
//
///**
// * 上传头像并且返回最后的路径
// * @param list
// * @param i
// * @throws MalformedURLException
// * @throws IOException
// * @throws FileNotFoundException
// */
//private String uploadHead(List<String> list, int i) throws MalformedURLException,
//		IOException, FileNotFoundException {
//	URL   url   =   new   URL(list.get(i)); 
//	URLConnection   uc   =   url.openConnection(); 
//	InputStream   is   =   uc.getInputStream(); 
//
//	//执行上传头像	
//	System.out.println("-------------------------------------->开始");  
//	Properties prop = System.getProperties();
//
//	String os = prop.getProperty("os.name");
//	System.out.println(os);
//	String path = "e:/bruce/album";
//	String fileName=list.get(i);
//	String newName="";
//	if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
//		path = "e:/bruce/album";
//	}else{
//		path = "/mnt/apk/album";
//	}
//	String headpath = "";
//	System.out.println(path); 
//	if(!StringUtils.isEmpty(fileName)){
//		String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
//		newName = String.valueOf(System.currentTimeMillis())+"."+ext;
//		File targetFile = new File(path, newName);  
//
//		FileOutputStream   out   =   new   FileOutputStream(targetFile); 
//		int   i1=0; 
//		while   ((i1=is.read())!=-1)   { 
//			out.write(i1); 
//		} 
//		is.close();
//	}
//	headpath = newName;
//	return headpath;
//}
//    
//    /**
//     * 爬虫生成100页的笔记内容
//     * @param map
//     */
//    @RequestMapping("/geneNote")
//	public void geneNote(ModelMap map) {
//    	List<String> listnote = new ArrayList<String>();
////    	List<String> listbrief = new ArrayList<String>();
//    	for (int i = 100; i < 200; i++) {
//			String url = "http://www.caimai.cc/story/page"+i;
//    		List<String> list_note = HTMLParserUtil.getClassCont(url, "div[class=clearfix hidden]");
//    		listnote.addAll(list_note);
//    		
//    		//[attr^=value], [attr$=value], [attr*=value]	这三个语法分别代表，属性以 value 开头、结尾以及包含
////    		List<String> list_brief = HTMLParserUtil.getClassCont(url, "p[id$=brief_]");
////    		listbrief.addAll(list_brief);
//		}
//    	for (int i = 0; i < listnote.size(); i++) {
//					
//					String note = listnote.get(i);
//					GetNote gg = new GetNote();
//					gg.setIsGened("0");
//					gg.setNotes(note);
//					gg.setCreatetime(TimeUtils.N_YearTime(1));
//					
//					getnoteManager.addGetNote(gg);
//				}
//	}
//	
//
//	/**
//	 * 处理笔记
//	 * @param note_
//	 * @return
//	 */
//	private static StringBuilder handleNotes(String note_) {
//		StringBuilder sb = new StringBuilder();
//		String str[] = note_.split("</p>");
//		if(str.length>=3){
//			sb.append(str[0]).append("</p>");
//			sb.append(str[1]).append("</p>");
//			sb.append(str[2]).append("</p>");
//		}else{
//			String rp_ = note_.replaceAll("<p>", "").replaceAll("</p>", "");
//			String br[] = rp_.split("<br>");
//			if(br.length>=3){
//				sb.append(br[0]).append("<br>");
//				sb.append(br[1]).append("<br>");
//				sb.append(br[2]).append("<br>");
//			}else{
//				String space[] = rp_.split(" ");
//				for (int i = 0; i < space.length; i++) {
//					if(i!=space.length-1){
//					    space[i] += "<br>";
//					}
//					sb.append(space[i]);
//				}
//			}
//		}
//		return sb;
//	}
//	
//	@RequestMapping("/Bief")
//	public String updateBief() {
//		
//		List<GetNote> list = this.getnoteManager.findAll();
//		
//		for(GetNote n:list){
//			//处理笔记和介绍
//			String note_ = n.getNotes();
//			StringBuilder sb = handleNotes(note_);
//			System.out.println(sb.toString());
//			n.setBrief(sb.toString());
//			
//			getnoteManager.updateGetNote(n);
//			//end-------------------
//			
//			
//		}
//			
//		return "111";
//	}
//
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
	 * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
	 * @return
	 */
	public static Integer getRandomInt(){
		
		
		Random ramdom =  new Random();
		int number = -1;
		int max = 100;
		
		//size 为  10 ，取得类似0-9的区间数
		number = Math.abs(ramdom.nextInt() % max  );
		
		if(number==0){
			number+=1;
		}
		
		return number;
    
	}
//	
//	
//	
//	
//	
//	
//	@RequestMapping("/upup")
//	public void updateeE() {
//		
//		List<User> ulist = userManager.findByCondition(" where date_joined >'"+"2015-12-14 18:49:19"+"' ").getResultlist();
//			
//			for (int i = 0; i < ulist.size(); i++) {
//				User u  = ulist.get(i);
//				String email = u.getEmail();
//				email.replaceAll("", "_");
//                u.setEmail(email.toLowerCase());
//				userManager.updateUser(u);
//			}
//	}
//	
//	
//
//	
//	
//	
//	/**
//	 * list去重
//	 * @return
//	 */
//	public static  List<String> listRM(List<String> list) {  
//	    List<String> tempList= new ArrayList<String>();  
//	    for(String i:list){  
//	        if(!tempList.contains(i)&&!"采麦故事".equals(i)){  
//	            tempList.add(i);  
//	        }  
//	    }  
//	    
//	    return tempList;
//	}     
//	
//	/**
//	 * list去重
//	 * @return
//	 */
//	public static  List<String> listRM1(List<String> list) {  
//	    List<String> listTemp= new ArrayList<String>();  
//		 Iterator<String> it=list.iterator();  
//		 while(it.hasNext()){  
//		  String a=it.next();  
//		  if(listTemp.contains(a)){  
//		   it.remove();  
//		  }  
//		  else{  
//		   listTemp.add(a);  
//		  }  
//		 }  
//	    
//	    return listTemp;
//	}  
//	
//	/**
//	 * list去重
//	 * @return
//	 */
//	public static  List<String> listRM3(List<String> list) {  
//		List<String> listTemp= null;  
//		HashSet<String> set = new HashSet<String>();
//		set.addAll(list);
//		listTemp = new ArrayList<String>(set);
//	    return listTemp;
//	}
//	
//	
//	
//	public static HashSet<String> set = new HashSet<String>();	
//	class Run implements Runnable{
//        String userid;
//        String lyricsid;
//        String commet;
//        public Run(String userid,String lyricsid,String commet){
//           this.userid=userid;
//           this.commet=commet;
//           this.lyricsid=lyricsid;
//        }
//     
//            @Override 
//        public synchronized void run() {
//            while(set.add(commet+userid+lyricsid)){
//                 //  addZanPl(userid, lyricsid, commet);           
//            }
//        }
//	}
//	
//	public  void addNote(String note_){
//		//添加用户的日记
//    	Note note = new Note();
//    	String brief_  = handleNotes(note_).toString();
//    	note.setBrief(brief_);
//    	note.setCreatetime(TimeUtils.N_YearTime(1));
//    	note.setNote(note_);
//    	note.setOpened("yes");
//    	note.setUserid("111");
//
//
//    	noteManager.addNote(note); 
//	}
//	
	public static void main(String[] args) {
//	List<String> list   =  new ArrayList<String>();
//	for (int i = 0; i < 165; i++) {
//		
//		List<String> list2 = HTMLParserUtil.getClassCont("http://www.caimai.cc/story/page"+i, "small[class=gray-text]");
//		list.addAll(list2);
//	}
//	list = listRM(list);
//	for (int i = 0; i < list.size(); i++) {
//		System.out.println(list.get(i));
//	}
//	
//	System.out.println(list.size());
		
		getInt6();
}
	
}
