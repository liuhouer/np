
package cn.northpark.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
/*
*@author bruce
*
**/
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.interceptor.CheckLogin;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Movies;
import cn.northpark.model.Tags;
import cn.northpark.model.User;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;

@Controller
@RequestMapping("")
@SessionAttributes({ "list", "movies" })
public class MoviesAction {

 @Autowired	
 private MoviesManager moviesManager;

 @Autowired	
 private TagsManager  tagsManager;
 
 /**
 * 每页展示多少条电影数
 */
private static int MoviesCount = 6;
 
     /**
     * @author Bruce
	 * 置顶的方法
	 * @param map
	 * @return
	 */
	@RequestMapping("/movies/handup")
	@ResponseBody
	public String handup(HttpServletRequest request) {
		String rs = "success";
		try {
			String id = request.getParameter("id");
			
			String max_hot_sql_id =  "select max(hotindex) as hotindex from bc_movies ";
			List<Map<String, Object>> list = moviesManager.querySqlMap(max_hot_sql_id);
			Integer hotindex = 0 ;
			if(!CollectionUtils.isEmpty(list)){
				hotindex = (Integer) list.get(0).get("hotindex");
				hotindex++;
			}
			if(hotindex>0){
				Movies m = moviesManager.findMovies(Integer.parseInt(id));
				if(m!=null){
					m.setHotindex(hotindex);
					moviesManager.updateMovies(m);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "ex";
		}
		return rs;
	}
	
	
	
	/**
	 * 隐藏电影的方法
	 * @param map
	 * @return
	 */
	@RequestMapping("/movies/hideup")
	@ResponseBody
	public String hideup(HttpServletRequest request) {
		String rs = "success";
		try {
			String id = request.getParameter("id");
			
				Movies m = moviesManager.findMovies(Integer.parseInt(id));
				if(m!=null){
					m.setDisplayed("N");
					moviesManager.updateMovies(m);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "ex";
		}
		return rs;
	}
 
 
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
			if(!user.getEmail().equals("654714226@qq.com") && !user.getEmail().equals("qhdsoft@126.com")){
				rs = "/login2";
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
			model.setAddtime(TimeUtils.nowdate());
			moviesManager.addMovies(model);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = "ex";
		}
		return rs;
	}
	
	
	
	/**
	 * 按照日期计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/movies/date/{tagscode}")
	public String datesearch(ModelMap map, @PathVariable String tagscode ,HttpServletRequest request) {
		String rs = "redirect:/movies/date/"+tagscode+"/page/1";
		return rs;
	}
	
	/**
	 * 按照日期分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/movies/date/{tagscode}/page/{page}")
	public String datelistpage(ModelMap map, @PathVariable String page,HttpServletRequest request,@PathVariable String tagscode,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/movies2";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
		String whereSql = " where addtime = '"+tagscode+"' ";
		 		
		map.put("seldate", tagscode);
				
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("hotindex,id", "desc");
		
		//获取pageview
		PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
		//生成分页信息
		pageview.setQueryResult(qr);
		
		//处理标签列表
		handleTag(resultlist);
		
		
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/movies/date/"+tagscode);
		
		

		return result;
	}
	
	
	/**
	 * 按照标签计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/movies/tag/{tagscode}")
	public String tagsearch(ModelMap map, @PathVariable String tagscode ,HttpServletRequest request) {
		String rs = "redirect:/movies/tag/"+tagscode+"/page/1";
		return rs;
	}
	
	/**
	 * 按照标签分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/movies/tag/{tagscode}/page/{page}")
	public String taglistpage(ModelMap map, @PathVariable String page,HttpServletRequest request,@PathVariable String tagscode,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/movies2";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
		String whereSql = " where tagcode like '%"+tagscode+"%' ";
		 		
		map.put("seltag", tagscode);
				
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("hotindex,id", "desc");
		
		//获取pageview
		PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
		//生成分页信息
		pageview.setQueryResult(qr);
		
		//处理标签列表
		handleTag(resultlist);
		
		int pages = 0;
		try {
			 pages = Integer.parseInt(page)+1;
			
		} catch (Exception e) {
			// TODO: handle exception
			pages = 1;
		}
		map.put("page", pages);
		
		map.addAttribute("pageView",pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/movies/tag/"+tagscode);
		
		

		return result;
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

		return "redirect:/movies/page/1";
	}
	
	/**
	 * 电影分页列表
	 * @param map
	 * @param page
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/movies/page/{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			 HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","movies");
		
		String result="/movies2";
		String whereSql = " where displayed is  null ";
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		String orderby = request.getParameter("orderby");
		if(StringUtils.isNotEmpty(orderby)){
			if("hot".equals(orderby)){
				order.put("hotindex", "desc");
			}else if("latest".equals(orderby)){
				order.put("id", "desc");
			}
			map.put("orderby", orderby);
		}else{
			order.put("hotindex", "desc");
			order.put("id", "desc");
		}
		
		
		//获取pageview
		PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
		//生成分页信息
		pageview.setQueryResult(qr);
		//处理标签列表
		handleTag(resultlist);
		
		int pages = 0;
		try {
			 pages = Integer.parseInt(page)+1;
			
		} catch (Exception e) {
			// TODO: handle exception
			pages = 1;
		}
		map.put("page", pages);
		
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/movies");
		
		//获取标签模块
		getTags(map,request);

		return result;
	}



	/**
	 * 处理标签列表
	 * @param resultlist
	 */
	private void handleTag(List<Movies> resultlist) {
		if(!CollectionUtils.isEmpty(resultlist)){
			
			for(Movies m:resultlist){
				String tag = m.getTag();
				String tagcode = m.getTagcode();
				if(StringUtils.isNotEmpty(tag)&&StringUtils.isNotEmpty(tagcode)){
					String[] tags = tag.split(",");
					String[] tagcodes = tagcode.split(",");
					List<Map<String,String>> taglist = new ArrayList<Map<String,String>>();
					for (int i = 0; i < tags.length; i++) {
						Map<String,String> map = new HashMap<String, String>();
						map.put("tag", tags[i]);
						map.put("tagcode", tagcodes[i]);
						taglist.add(map);
						map = null;
					}
					m.setTaglist(taglist);
				}
				
			}
		}
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
	public String search(ModelMap map,String keyword,String id,HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		String wheresql = " where 1=1 ";
		if(StringUtils.isNotEmpty(keyword)){
			//sql注入处理
			keyword = WAQ.forSQL().escapeSql(keyword);
			
			if(keyword.contains(" ")){
				String keyword2 = keyword.replaceAll(" ", "");
				wheresql= " where moviename like '%"+keyword+"%' or moviename like '%"+keyword2+"%' "+" or description like '%"+keyword+"%' " ;
			}else{
				wheresql= " where moviename like '%"+keyword+"%' "+" or description like '%"+keyword+"%' " ;
			}
			
			map.put("keyword", keyword);
		}
		
		if(StringUtils.isNotEmpty(id)){
			//sql注入处理
			id = WAQ.forSQL().escapeSql(id);
			wheresql = " where id = "+id;
			
			Movies findMovies = moviesManager.findMovies(Integer.parseInt(id));
			if(findMovies!=null){
				
				map.put("keyword", findMovies.getMoviename());
			}
			
			map.put("searchbyid", "searchbyid");
		}
		
		
		List<Movies> list =  moviesManager.findByCondition(wheresql+" order by id desc ").getResultlist();
		map.addAttribute("list", list);
		
		map.put("search", "search");
		
		return "/moviesdetail";
	}
	
	
	//、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、
	
	/**
	 * 获取标签模块
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param map
	 */
	private void getTags(ModelMap map,HttpServletRequest request) {
		List<Tags> tags = null;
		List<Map<String,Object>> movies_hot_list  =null;
		
		tags = (List<Tags>) request.getSession().getAttribute("movies_tags");
		
		movies_hot_list = (List<Map<String, Object>>) request.getSession().getAttribute("movies_hot_list");
		
		
		
		if(tags==null || movies_hot_list==null){
			//获取标签
			
			tags = tagsManager.findByCondition(" where tagtype = '1' ").getResultlist();
			
			//获取热门电影
			String hotsql = "select id,moviename,color from bc_movies order by rand() desc limit 0,70";
			movies_hot_list = moviesManager.querySql(hotsql);
			
			
		}
		request.getSession().setAttribute("movies_tags", tags);
		request.getSession().setAttribute("movies_hot_list", movies_hot_list);
		
		
	}
	
	

}
