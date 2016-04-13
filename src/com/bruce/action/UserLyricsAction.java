
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

import com.bruce.manager.UserLyricsManager;
import com.bruce.model.UserLyrics;
import com.bruce.query.UserLyricsQuery;
import com.bruce.query.condition.UserLyricsQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userlyricsAction")
@SessionAttributes({ "list", "userlyrics" })
public class UserLyricsAction {

 private final String LIST_ACTION = "redirect:/userlyrics/findAll";
 @Autowired	
 private UserLyricsManager userlyricsManager;
 @Autowired	
 private UserLyricsQuery userlyricsQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<UserLyrics> Pidlist = userlyricsManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/userlyrics/userlyricsAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<UserLyrics> Pidlist = userlyricsManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			UserLyrics model = userlyricsManager.findUserLyrics(id);
			map.put("model", model);
		}
		return "admin/userlyrics/userlyricsEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.userlyricsManager.delUserLyrics(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.userlyricsManager.delUserLyrics(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(UserLyrics model) {
		this.userlyricsManager.updateUserLyrics(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addUserLyrics")
	public String addUserLyrics(UserLyrics userlyrics) {
		this.userlyricsManager.addUserLyrics(userlyrics);
		return LIST_ACTION;
	}

	@RequestMapping("/findUserLyrics")
	private String findUserLyrics(@RequestParam("id") String id, ModelMap map) {
		UserLyrics userlyrics = this.userlyricsManager.findUserLyrics(id);
		map.addAttribute("userlyrics", userlyrics);
		return "findresult";
	}

	@RequestMapping("/delUserLyrics")
	public String delUserLyrics(@RequestParam("id") String id) {
		this.userlyricsManager.delUserLyrics(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateUserLyrics")
	public String updateUserLyrics(@RequestParam("id") String id) {
		UserLyrics userlyrics = this.userlyricsManager.findUserLyrics(id);
		this.userlyricsManager.updateUserLyrics(userlyrics);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,UserLyricsQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = userlyricsQuery.getSql(condition);
		
		PageView<UserLyrics> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<UserLyrics> qrs = this.userlyricsManager.findByCondition(pageView,
				whereSql, order);
		List<UserLyrics> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","userlyrics/findAll" );

		return "admin/userlyrics/userlyricsList";
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

}
