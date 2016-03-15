
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.GetinfoManager;
import com.bruce.model.Getinfo;
import com.bruce.query.GetinfoQuery;
import com.bruce.query.condition.getinfoQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.TimeUtils;

@Controller
@RequestMapping("/catch/info")
@SessionAttributes({ "list", "getinfo" })
public class GetinfoAction {

 private final String LIST_ACTION = "redirect:/getinfoAction/findAll";
 @Autowired	
 private GetinfoManager getinfoManager;
 @Autowired	
 private GetinfoQuery getinfoQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<Getinfo> Pidlist = getinfoManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/getinfo/getinfoAdd";
	}
	
	@RequestMapping("/batch")
	@ResponseBody
	public String batch(ModelMap map) {
		Getinfo model = new Getinfo();
		
		model.setCreatetime(TimeUtils.nowTime());
		model.setIsGened("1");
		model.setPath("010");
		model.setType("1");
		getinfoManager.addGetinfo(model);
		return "success";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<Getinfo> Pidlist = getinfoManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			Getinfo model = getinfoManager.findGetinfo(id);
			map.put("model", model);
		}
		return "admin/getinfo/getinfoEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.getinfoManager.delGetinfo(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.getinfoManager.delGetinfo(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(Getinfo model) {
		this.getinfoManager.updateGetinfo(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addGetinfo")
	public String addGetinfo(Getinfo getinfo) {
		this.getinfoManager.addGetinfo(getinfo);
		return LIST_ACTION;
	}

	@RequestMapping("/findGetinfo")
	private String findGetinfo(@RequestParam("id") String id, ModelMap map) {
		Getinfo getinfo = this.getinfoManager.findGetinfo(id);
		map.addAttribute("getinfo", getinfo);
		return "findresult";
	}

	@RequestMapping("/delGetinfo")
	public String delGetinfo(@RequestParam("id") String id) {
		this.getinfoManager.delGetinfo(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateGetinfo")
	public String updateGetinfo(@RequestParam("id") String id) {
		Getinfo getinfo = this.getinfoManager.findGetinfo(id);
		this.getinfoManager.updateGetinfo(getinfo);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,getinfoQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = getinfoQuery.getSql(condition);
		
		PageView<Getinfo> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Getinfo> qrs = this.getinfoManager.findByCondition(pageView,
				whereSql, order);
		List<Getinfo> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","getinfoAction/findAll" );

		return "admin/getinfo/getinfoList";
	}

	private PageView<Getinfo> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<Getinfo> pageView = new PageView<Getinfo>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.getinfoManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.getinfoManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
