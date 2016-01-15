
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

import com.bruce.manager.MoviesManager;
import com.bruce.model.Movies;
import com.bruce.model.User;
import com.bruce.query.MoviesQuery;
import com.bruce.query.condition.MoviesQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.TimeUtils;

@Controller
@RequestMapping("/movies")
@SessionAttributes({ "list", "movies" })
public class MoviesAction {

 @Autowired	
 private MoviesManager moviesManager;
 @Autowired	
 private MoviesQuery moviesQuery;

 private final String LIST_ACTION = "redirect:/moviesAction/findAll";
 public static final String pattern = "([-+*/^()\\]\\[])" ;
// test = test.replaceAll(pattern, "");
	
 
 
	/**
	 * 跳转后台添加
	 * @param map
	 * @return
	 */
	@RequestMapping("/add")
	public String toAdd(ModelMap map,HttpServletRequest request) {
		String rs = "/page/admin/movies/moviesAdd";
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			rs = "/login";
		}else{
			if(!user.getEmail().equals("654714226@qq.com") && !user.getEmail().equals("qhdsoft@126.com")){
				rs = "/login";
			}
		}
		
		return rs;
	}
	
	
	
	
	/**
	 * 保存电影的方法
	 * @param map
	 * @return
	 */
	@RequestMapping("/addItem")
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
	@RequestMapping(value="/list")
	public String list(ModelMap map,MoviesQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String  sql = "select * from bc_movies   order by addtime desc limit 0,200 ";
		List<Movies> list =  moviesManager.querySql(sql);
		map.addAttribute("list", list);

		return "/movies";
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
	@RequestMapping(value="/search")
	public String list(ModelMap map,String keyword,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		String wheresql = " where 1=1 ";
		if(StringUtils.isNotEmpty(keyword)){
			keyword = keyword.replaceAll(pattern, "");
			 wheresql = " where moviename like '%"+keyword+"%' or description like '%"+keyword+"%' ";
		}
		List<Movies> list =  moviesManager.findByCondition(wheresql+" order by addtime desc ").getResultlist();
		map.addAttribute("list", list);
		map.put("keyword", keyword);


		return "/movies";
	}
	
	
	//、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") String id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<Movies> Pidlist = moviesManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(!StringUtils.isEmpty(id)){
			Movies model = moviesManager.findMovies(id);
			map.put("model", model);
		}
		return "/page/admin/movies/moviesEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") String id) {
		this.moviesManager.delMovies(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.moviesManager.delMovies(s);
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(Movies model) {
		this.moviesManager.updateMovies(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addMovies")
	public String addMovies(Movies movies) {
		this.moviesManager.addMovies(movies);
		return LIST_ACTION;
	}

	@RequestMapping("/findMovies")
	private String findMovies(@RequestParam("id") String id, ModelMap map) {
		Movies movies = this.moviesManager.findMovies(id);
		map.addAttribute("movies", movies);
		return "findresult";
	}

	@RequestMapping("/delMovies")
	public String delMovies(@RequestParam("id") String id) {
		this.moviesManager.delMovies(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updateMovies")
	public String updateMovies(@RequestParam("id") String id) {
		Movies movies = this.moviesManager.findMovies(id);
		this.moviesManager.updateMovies(movies);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,MoviesQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = moviesQuery.getSql(condition);
		
		PageView<Movies> pageView = getPageView(request, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Movies> qrs = this.moviesManager.findByCondition(pageView,
				whereSql, order);
		List<Movies> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","moviesAction/findAll" );

		return "/page/admin/movies/moviesList";
	}
	

	private PageView<Movies> getPageView(HttpServletRequest request,
			String whereSql) {
		PageView<Movies> pageView = new PageView<Movies>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.moviesManager.findByCondition(whereSql).getResultlist().size();
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
		pageView.setTotalrecord(this.moviesManager.findAll().size());
		pageView.setCurrentpage(currentpage);
		pageView.setTotalpage(pages);
		return pageView;
	}

}
