
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

import com.bruce.manager.UserprofileManager;
import com.bruce.model.Userprofile;
import com.bruce.query.UserprofileQuery;
import com.bruce.query.condition.UserprofileQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Controller
@RequestMapping("/userprofileAction")
@SessionAttributes({ "list", "userprofile" })
public class UserprofileAction {

 private final String LIST_ACTION = "redirect:/userprofileAction/findAll";
 @Autowired	
 private UserprofileManager userprofileManager;
 @Autowired	
 private UserprofileQuery userprofileQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<Userprofile> Pidlist = userprofileManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "/admin/userprofile/userprofileAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<Userprofile> Pidlist = userprofileManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			Userprofile model = userprofileManager.findUserprofile(id);
			map.put("model", model);
		}
		return "/admin/userprofile/userprofileEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.userprofileManager.delUserprofile(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.userprofileManager.delUserprofile(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(Userprofile model) {
		this.userprofileManager.updateUserprofile(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addUserprofile")
	public String addUserprofile(Userprofile userprofile) {
		this.userprofileManager.addUserprofile(userprofile);
		return LIST_ACTION;
	}

	@RequestMapping("/findUserprofile")
	private String findUserprofile(@RequestParam("id") String id, ModelMap map) {
		Userprofile userprofile = this.userprofileManager.findUserprofile(id);
		map.addAttribute("userprofile", userprofile);
		return "findresult";
	}

	@RequestMapping("/delUserprofile")
	public String delUserprofile(@RequestParam("id") String id) {
		this.userprofileManager.delUserprofile(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateUserprofile")
	public String updateUserprofile(@RequestParam("id") String id) {
		Userprofile userprofile = this.userprofileManager.findUserprofile(id);
		this.userprofileManager.updateUserprofile(userprofile);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,UserprofileQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = userprofileQuery.getSql(condition);
		
		PageView<Userprofile> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Userprofile> qrs = this.userprofileManager.findByCondition(pageView,
				whereSql, order);
		List<Userprofile> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","userprofileAction/findAll" );

		return "admin/userprofile/userprofileList";
	}

	private PageView<Userprofile> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<Userprofile> pageView = new PageView<Userprofile>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.userprofileManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.userprofileManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
