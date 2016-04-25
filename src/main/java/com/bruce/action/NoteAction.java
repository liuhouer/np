
package com.bruce.action;
/*
*@author bruce
*
**/
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.LyricsManager;
import com.bruce.manager.NoteManager;
import com.bruce.manager.UserFollowManager;
import com.bruce.manager.UserManager;
import com.bruce.model.Note;
import com.bruce.model.User;
import com.bruce.query.NoteQuery;
import com.bruce.query.condition.NoteQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.TimeUtils;

@Controller
@RequestMapping("/note")
@SessionAttributes({ "list", "note" })
public class NoteAction {

 private final String LIST_ACTION = "redirect:/note/findAll";
 private final String LOGIN_ACTION = "redirect:/cm/toLogin";
 @Autowired	
 private NoteManager noteManager;
 @Autowired	
 private UserManager userManager;
 @Autowired	
 private NoteQuery noteQuery;
 @Autowired	
 private LyricsManager lyricsManager;
 @Autowired	
 private UserFollowManager userfollowManager;
	
	
	

	@RequestMapping("/addNote")
	public String addNote(Note note) {
		String rs= LIST_ACTION;
		if(StringUtils.isNotEmpty(String.valueOf(note.getUserid()))){
			if(StringUtils.isEmpty(note.getOpened())){
				note.setOpened("yes");
			}
			note.setCreatetime(TimeUtils.nowTime());
			
			//处理笔记和介绍
			String note_ = note.getNote();
			StringBuilder sb = handleNotes(note_);
			note.setBrief(sb.toString());
			//end-------------------
			this.noteManager.addNote(note);
		}else{
			rs = LOGIN_ACTION;
		}
		return rs;
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
	

	
	
	@RequestMapping(value="/viewNotes/{userid}")
	public String viewNotes(ModelMap map,NoteQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session, @PathVariable String userid) {
		//condition.setOpened("yes");
		String result="/spacenote";
		if(StringUtils.isNotEmpty(userid)){
			condition.setUserid(userid);
			map.addAttribute("userid",userid);
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
		}else{
			condition.setUserid("空");
			map.addAttribute("userid","");
		}
		String whereSql = noteQuery.getSql(condition);
		
		PageView<Note> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
				whereSql, order);
		List<Note> list = qrs.getResultlist();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCreatetime().contains("-")){
				String t = list.get(i).getCreatetime().substring(0, 10);
				list.get(i).setCreatetime(t);
			}
		}
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/viewNotes/"+userid );
		
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

		return result;
	}
	
	@RequestMapping(value="/viewNotes/{userid}/page{page}")
	public String viewNotesPages(ModelMap map,NoteQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session, @PathVariable String userid, @PathVariable String page) {
		//condition.setOpened("yes");
		String result="/spacenote";
		if(StringUtils.isNotEmpty(userid)){
			condition.setUserid(userid);
			map.addAttribute("userid",userid);
			User user = userManager.findUser(Integer.parseInt(userid));
			map.put("MyInfo", user);
		}else{
			condition.setUserid("空");
			map.addAttribute("userid","");
		}
		String whereSql = noteQuery.getSql(condition);
		
		PageView<Note> pageView = getPageView(page, whereSql);
		
		request.setAttribute("page", page);
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
				whereSql, order);
		List<Note> list = qrs.getResultlist();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCreatetime().contains("-")){
				String t = list.get(i).getCreatetime().substring(0, 10);
				list.get(i).setCreatetime(t);
			}
		}
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/viewNotes/"+userid );
		
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

		return result;
	}

	@RequestMapping(value="/findAll/page{page}")
	public String findAllpages(ModelMap map,NoteQueryCondition condition,@PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session,String userid) {
		//condition.setOpened("yes");
		
		 //取得当前用户
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
        	userid = String.valueOf(user.getId());
        }
		String result="/note";
		if(StringUtils.isNotEmpty(userid)){
			condition.setUserid(userid);
			map.addAttribute("userid",userid);
			map.put("MyInfo", user);
		}else{
			condition.setUserid("空");
			map.addAttribute("userid","");
		}
		String whereSql = noteQuery.getSql(condition);
		
		request.setAttribute("page", page);
		PageView<Note> pageView = getPageView(page, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
				whereSql, order);
		List<Note> list = qrs.getResultlist();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCreatetime().contains("-")){
				String t = list.get(i).getCreatetime().substring(0, 10);
				list.get(i).setCreatetime(t);
			}
		}
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/findAll" );

		return result;
	}
	
	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,NoteQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session,String userid) {
		//condition.setOpened("yes");
		
		 //取得当前用户
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
        	userid = String.valueOf(user.getId());
        }
		String result="/note";
		if(StringUtils.isNotEmpty(userid)){
			condition.setUserid(userid);
			map.addAttribute("userid",userid);
			map.put("MyInfo", user);
		}else{
			condition.setUserid("空");
			map.addAttribute("userid","");
		}
		String whereSql = noteQuery.getSql(condition);
		
		PageView<Note> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
				whereSql, order);
		List<Note> list = qrs.getResultlist();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCreatetime().contains("-")){
				String t = list.get(i).getCreatetime().substring(0, 10);
				list.get(i).setCreatetime(t);
			}
		}
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/findAll" );

		return result;
	}
	
	
	@RequestMapping(value="/list")
	public String list_(ModelMap map,NoteQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","note");
		condition.setOpened("yes");
		String result="/story";
		String whereSql = noteQuery.getMixSql(condition);
		
		System.out.println("sql ---"+whereSql);
		String currentpage = "0";
		
		PageView<List<Map<String, Object>>> pageView = this.noteManager.findmixByCondition(currentpage,whereSql);
		List<Map<String, Object>> list = pageView.getMapRecords();
		
		for (int i = 0; i < list.size(); i++) {
			//时间处理
			String createtime = (String) list.get(i).get("createtime"); //e:/yunlu/upload/1399976848969.jpg
			if(StringUtils.isNotEmpty(createtime)){
				createtime = TimeUtils.getHalfDate(createtime);
			}
			list.get(i).put("createtime", createtime);
			
		}
		
		map.put("page", "1");
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/list");
		
		

		return result;
	}
	@RequestMapping(value="/list/page{page}")
	public String list(ModelMap map,NoteQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","note");
		condition.setOpened("yes");
		String result="/story";
		String whereSql = noteQuery.getMixSql(condition);
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		
		PageView<List<Map<String, Object>>> pageView = this.noteManager.findmixByCondition(currentpage,whereSql);
		List<Map<String, Object>> list = pageView.getMapRecords();
		
		for (int i = 0; i < list.size(); i++) {
			//时间处理
			String createtime = (String) list.get(i).get("createtime"); //e:/yunlu/upload/1399976848969.jpg
			if(StringUtils.isNotEmpty(createtime)){
				createtime = TimeUtils.getHalfDate(createtime);
			}
			list.get(i).put("createtime", createtime);
			
		}
		int pages = 0;
		try {
			 pages = Integer.parseInt(page)+1;
			
		} catch (Exception e) {
			// TODO: handle exception
			pages = 1;
		}
		map.put("page", pages);
		
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","note/list");
		
		

		return result;
	}


	private PageView<Note> getPageView(String page,
			String whereSql) {
		PageView<Note> pageView = new PageView<Note>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.noteManager.countHql(new Note(), whereSql);
		int maxresult = MyConstant.MAXRESULT; /** 每页显示记录数**/
        if(n % maxresult==0)
       {
          pages = n / maxresult ;
       }else{
          pages = n / maxresult + 1;
       }
        if(StringUtils.isEmpty(page)){
           currentpage = 0;
        }else{
           currentpage = Integer.parseInt(page);
           
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
		pageView.setTotalrecord(n);
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}
	
}
