
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

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

import cn.northpark.manager.SoftManager;
import cn.northpark.model.Soft;
import cn.northpark.query.SoftQuery;
import cn.northpark.query.condition.SoftQueryCondition;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;


/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/soft")
@SessionAttributes({ "list", "soft" })
public class SoftAction {

 private final String LIST_ACTION = "redirect:/softAction/findAll";
 @Autowired	
 private SoftManager softManager;
 @Autowired	
 private SoftQuery softQuery;
 
 
 /**
	 * 查询列表
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/mac")
	public String list() {
//		String  sql = "select * from bc_movies   order by addtime desc limit 0,200 ";
//		List<Soft> list =  softManager.querySql(sql);
//		map.addAttribute("list", list);

		return "redirect:/soft/mac/page0";
	}
	
	@RequestMapping(value="/mac/page{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		String result="/soft";
		String whereSql = "";
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("postdate,id", "desc");
		
		//获取pageview
		PageView<Soft> p = getPageView(currentpage, whereSql);
		QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
		List<Soft> resultlist = qr.getResultlist();
		int pages = 0;
		try {
			 pages = Integer.parseInt(page)+1;
			
		} catch (Exception e) {
			// TODO: handle exception
			pages = 1;
		}
		map.put("page", pages);
		
		map.addAttribute("pageView", p);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/soft/mac");
		
		

		return result;
	}
	

	

	@RequestMapping("/addSoft")
	public String addSoft(Soft soft) {
		this.softManager.addSoft(soft);
		return LIST_ACTION;
	}


	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,SoftQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = softQuery.getSql(condition);
		
		PageView<Soft> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Soft> qrs = this.softManager.findByCondition(pageView,
				whereSql, order);
		List<Soft> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","softAction/findAll" );

		return "admin/soft/softList";
	}

	private PageView<Soft> getPageView(String page,
			String whereSql) {
		PageView<Soft> pageView = new PageView<Soft>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.softManager.countHql(new Soft(), whereSql);;
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
