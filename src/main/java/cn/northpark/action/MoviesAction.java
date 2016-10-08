
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
/*
*@author bruce
*
**/
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.interceptor.CheckLogin;
import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Movies;
import cn.northpark.model.User;
import cn.northpark.query.condition.MoviesQueryCondition;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.safe.WAQ;

@Controller
@RequestMapping("")
@SessionAttributes({ "list", "movies" })
public class MoviesAction {

 @Autowired	
 private MoviesManager moviesManager;

 
 
	/**
	 * 跳转后台添加
	 * @param map
	 * @return
	 */
	@RequestMapping("/movies/add")
	@CheckLogin
	public String toAdd(ModelMap map,HttpServletRequest request) {
		String rs = "/page/admin/movies/moviesAdd";
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			rs = "/login2";
		}else{
			if(!user.getEmail().equals("654714226@qq.com") && !user.getEmail().equals("qhdsoft@126.com")){
				rs = "/login2";
			}
		}
		
		return rs;
	}
	
	
	
	
	/**
	 * 保存电影的方法
	 * @param map
	 * @return
	 */
	@RequestMapping("/movies/addItem")
	@ResponseBody
	public String addItem(ModelMap map,Movies model) {
		String rs = "success";
		try {
			model.setAddtime(TimeUtils.nowTime());
			moviesManager.addMovies(model);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "ex";
		}
		return rs;
	}
	
	/**
	 * 查询列表
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/movies")
	public String list() {
//		String  sql = "select * from bc_movies   order by addtime desc limit 0,200 ";
//		List<Movies> list =  moviesManager.querySql(sql);
//		map.addAttribute("list", list);

		return "redirect:/movies/page0";
	}
	
	@RequestMapping(value="/movies/page{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		String result="/movies";
		String whereSql = "";
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("addtime", "desc");
		
		//获取pageview
		PageView<Movies> p = getPageView(currentpage, whereSql);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(p, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
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
		map.addAttribute("actionUrl","/movies");
		
		

		return result;
	}
	
	
	/**
	 * 关键字匹配列表
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/movies/search")
	public String list(ModelMap map,String keyword,String id,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		String wheresql = " where 1=1 ";
		if(StringUtils.isNotEmpty(keyword)){
			//sql注入处理
			keyword = WAQ.forSQL().escapeSql(keyword);
			wheresql = " where moviename like '%"+keyword+"%' or description like '%"+keyword+"%' ";
		}
		
		if(StringUtils.isNotEmpty(id)){
			//sql注入处理
			id = WAQ.forSQL().escapeSql(id);
			wheresql = " where id = "+id;
		}
		
		
		List<Movies> list =  moviesManager.findByCondition(wheresql+" order by addtime desc ").getResultlist();
		map.addAttribute("list", list);
		map.put("keyword", keyword);
		map.put("search", "search");
		
		return "/movies";
	}
	
	
	//、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、
	

	public PageView<Movies> getPageView(String current,
			String whereSql) {
		PageView<Movies> pageView = new PageView<Movies>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		//总条数
		int n = moviesManager.countHql(new Movies(), whereSql);
		int maxresult = 20; /** 每页显示记录数**/
        if(n % maxresult==0)
       {
          pages = n / maxresult ;
       }else{
          pages = n / maxresult + 1;
       }
        if(StringUtils.isEmpty(current)){
           currentpage = 0;
        }else{
           currentpage = Integer.parseInt(current);
           
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
