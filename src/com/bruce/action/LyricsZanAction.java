
package com.bruce.action;
/*
*@author bruce
*
**/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.LyricsCommentManager;
import com.bruce.manager.LyricsZanManager;
import com.bruce.model.LyricsComment;
import com.bruce.model.LyricsZan;
import com.bruce.model.User;
import com.bruce.query.LyricsZanQuery;
import com.bruce.query.condition.LyricsZanQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.TimeUtils;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/zanAction")
@SessionAttributes({ "list", "lyricszan" })
public class LyricsZanAction {

 private final String LIST_ACTION = "redirect:/lyricszanAction/findAll";
 @Autowired	
 private LyricsZanManager lyricszanManager;
 @Autowired	
 private  LyricsCommentManager  plManager;
 @Autowired	
 private LyricsZanQuery lyricszanQuery;
 
 
 	@RequestMapping("/zan")
 	@ResponseBody
	public String zan(String lyricsid,String userid,HttpServletRequest request) {
 		
 		if(StringUtils.isEmpty(userid)){
            User u = (User) request.getSession().getAttribute("user");
            userid = u.getId();
        } 
 		String msg = "success";
		int num = this.lyricszanManager.findByCondition(" where lyricsid='"+lyricsid+"' and userid = '"+userid+"' ").getResultlist().size();
		if(num>0){
			msg = "zanguole";
		}else{
			try {
				
				LyricsZan model = new LyricsZan();
				model.setLyricsid(lyricsid);
				model.setUserid(userid);
				lyricszanManager.addLyricsZan(model);
				msg = "success";
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exception";
			}
		}
		return msg;
	}
 	
 	@RequestMapping("/addComment")
 	@ResponseBody
	public String addComment(String comment,String userid,String lyricsid,HttpServletRequest request) {
 		if(StringUtils.isEmpty(userid)){
            User u = (User) request.getSession().getAttribute("user");
            userid = u.getId();
        } 
 		
 		String msg = "success";
			try {
				
				LyricsComment model = new LyricsComment();
				model.setComment(comment);
				model.setUserid(userid);
				model.setLyricsid(lyricsid);
				model.setCreate_time(TimeUtils.nowTime());

				plManager.addLyricsComment(model);
				msg = "success";
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exception";
			}
		return msg;
	}


	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<LyricsZan> Pidlist = lyricszanManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/lyricszan/lyricszanAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<LyricsZan> Pidlist = lyricszanManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			LyricsZan model = lyricszanManager.findLyricsZan(id);
			map.put("model", model);
		}
		return "admin/lyricszan/lyricszanEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.lyricszanManager.delLyricsZan(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.lyricszanManager.delLyricsZan(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(LyricsZan model) {
		this.lyricszanManager.updateLyricsZan(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addLyricsZan")
	public String addLyricsZan(LyricsZan lyricszan) {
		this.lyricszanManager.addLyricsZan(lyricszan);
		return LIST_ACTION;
	}

	@RequestMapping("/findLyricsZan")
	private String findLyricsZan(@RequestParam("id") String id, ModelMap map) {
		LyricsZan lyricszan = this.lyricszanManager.findLyricsZan(id);
		map.addAttribute("lyricszan", lyricszan);
		return "findresult";
	}

	@RequestMapping("/delLyricsZan")
	public String delLyricsZan(@RequestParam("id") String id) {
		this.lyricszanManager.delLyricsZan(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateLyricsZan")
	public String updateLyricsZan(@RequestParam("id") String id) {
		LyricsZan lyricszan = this.lyricszanManager.findLyricsZan(id);
		this.lyricszanManager.updateLyricsZan(lyricszan);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,LyricsZanQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = lyricszanQuery.getSql(condition);
		
		PageView<LyricsZan> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<LyricsZan> qrs = this.lyricszanManager.findByCondition(pageView,
				whereSql, order);
		List<LyricsZan> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","lyricszanAction/findAll" );

		return "admin/lyricszan/lyricszanList";
	}

	private PageView<LyricsZan> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<LyricsZan> pageView = new PageView<LyricsZan>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.lyricszanManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.lyricszanManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
