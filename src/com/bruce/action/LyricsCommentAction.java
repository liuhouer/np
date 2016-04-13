
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.LyricsCommentManager;
import com.bruce.model.LyricsComment;
import com.bruce.query.LyricsCommentQuery;
import com.bruce.query.condition.LyricsCommentQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lyricscommentAction")
@SessionAttributes({ "list", "lyricscomment" })
public class LyricsCommentAction {

 private final String LIST_ACTION = "redirect:/lyricscommentAction/findAll";
 @Autowired	
 private LyricsCommentManager lyricscommentManager;
 @Autowired	
 private LyricsCommentQuery lyricscommentQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<LyricsComment> Pidlist = lyricscommentManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/lyricscomment/lyricscommentAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<LyricsComment> Pidlist = lyricscommentManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			LyricsComment model = lyricscommentManager.findLyricsComment(id);
			map.put("model", model);
		}
		return "admin/lyricscomment/lyricscommentEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.lyricscommentManager.delLyricsComment(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.lyricscommentManager.delLyricsComment(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(LyricsComment model) {
		this.lyricscommentManager.updateLyricsComment(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addLyricsComment")
	public String addLyricsComment(LyricsComment lyricscomment) {
		this.lyricscommentManager.addLyricsComment(lyricscomment);
		return LIST_ACTION;
	}

	@RequestMapping("/findLyricsComment")
	private String findLyricsComment(@RequestParam("id") String id, ModelMap map) {
		LyricsComment lyricscomment = this.lyricscommentManager.findLyricsComment(id);
		map.addAttribute("lyricscomment", lyricscomment);
		return "findresult";
	}

	@RequestMapping("/delLyricsComment")
	public String delLyricsComment(@RequestParam("id") String id) {
		this.lyricscommentManager.delLyricsComment(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateLyricsComment")
	public String updateLyricsComment(@RequestParam("id") String id) {
		LyricsComment lyricscomment = this.lyricscommentManager.findLyricsComment(id);
		this.lyricscommentManager.updateLyricsComment(lyricscomment);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,LyricsCommentQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = lyricscommentQuery.getSql(condition);
		
		PageView<LyricsComment> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<LyricsComment> qrs = this.lyricscommentManager.findByCondition(pageView,
				whereSql, order);
		List<LyricsComment> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","lyricscommentAction/findAll" );

		return "admin/lyricscomment/lyricscommentList";
	}

	private PageView<LyricsComment> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<LyricsComment> pageView = new PageView<LyricsComment>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.lyricscommentManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.lyricscommentManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
