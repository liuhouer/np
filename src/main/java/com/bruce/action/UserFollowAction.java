
package com.bruce.action;
/*
*@author bruce
*
**/
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.UserFollowManager;
import com.bruce.model.UserFollow;
import com.bruce.query.UserFollowQuery;
import com.bruce.query.condition.UserFollowQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Controller
@RequestMapping("/userfollowAction")
@SessionAttributes({ "list", "userfollow" })
public class UserFollowAction {

 private final String LIST_ACTION = "redirect:/userfollowAction/findAll";
 @Autowired	
 private UserFollowManager userfollowManager;
 @Autowired	
 private UserFollowQuery userfollowQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<UserFollow> Pidlist = userfollowManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "/admin/userfollow/userfollowAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<UserFollow> Pidlist = userfollowManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			UserFollow model = userfollowManager.findUserFollow(id);
			map.put("model", model);
		}
		return "/admin/userfollow/userfollowEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.userfollowManager.delUserFollow(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.userfollowManager.delUserFollow(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(UserFollow model) {
		this.userfollowManager.updateUserFollow(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addUserFollow")
	public String addUserFollow(UserFollow userfollow) {
		this.userfollowManager.addUserFollow(userfollow);
		return LIST_ACTION;
	}

	@RequestMapping("/findUserFollow")
	private String findUserFollow(@RequestParam("id") String id, ModelMap map) {
		UserFollow userfollow = this.userfollowManager.findUserFollow(id);
		map.addAttribute("userfollow", userfollow);
		return "/findresult";
	}

	@RequestMapping("/delUserFollow")
	public String delUserFollow(@RequestParam("id") String id) {
		this.userfollowManager.delUserFollow(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateUserFollow")
	public String updateUserFollow(@RequestParam("id") String id) {
		UserFollow userfollow = this.userfollowManager.findUserFollow(id);
		this.userfollowManager.updateUserFollow(userfollow);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,UserFollowQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = userfollowQuery.getSql(condition);
		
		PageView<UserFollow> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<UserFollow> qrs = this.userfollowManager.findByCondition(pageView,
				whereSql, order);
		List<UserFollow> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","userfollowAction/findAll" );

		return "/admin/userfollow/userfollowList";
	}

	private PageView<UserFollow> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<UserFollow> pageView = new PageView<UserFollow>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.userfollowManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.userfollowManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
