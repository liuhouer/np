
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
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.XMemcachedUtil;
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
		String rs = "redirect:/movies/date/"+tagscode+"/page0";
		return rs;
	}
	
	/**
	 * 按照日期分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/movies/date/{tagscode}/page{page}")
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
		order.put("hotindex,UNIX_TIMESTAMP(addtime)", "desc");
		
		//获取pageview
		PageView<Movies> p = getPageView(currentpage, whereSql);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(p, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
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
		
		map.addAttribute("pageView", p);
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
		String rs = "redirect:/movies/tag/"+tagscode+"/page0";
		return rs;
	}
	
	/**
	 * 按照标签分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/movies/tag/{tagscode}/page{page}")
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
		order.put("hotindex,UNIX_TIMESTAMP(addtime)", "desc");
		
		//获取pageview
		PageView<Movies> p = getPageView(currentpage, whereSql);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(p, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
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
		
		map.addAttribute("pageView", p);
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
//		String  sql = "select * from bc_movies   order by addtime desc limit 0,200 ";
//		List<Movies> list =  moviesManager.querySql(sql);
//		map.addAttribute("list", list);

		return "redirect:/movies/page0";
	}
	
	@RequestMapping(value="/movies/page{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","movies");
		
		String result="/movies2";
		String whereSql = "";
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("hotindex", "desc");
		order.put("UNIX_TIMESTAMP(addtime)", "desc");
		
		//获取pageview
		PageView<Movies> p = getPageView(currentpage, whereSql);
		QueryResult<Movies> qr = this.moviesManager.findByCondition(p, whereSql, order);
		List<Movies> resultlist = qr.getResultlist();
		
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
		
		map.addAttribute("pageView", p);
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
		}
		
		
		List<Movies> list =  moviesManager.findByCondition(wheresql+" order by addtime desc ").getResultlist();
		map.addAttribute("list", list);
		
		map.put("search", "search");
		
		return "/moviesdetail";
	}
	
	
	//、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、
	
	/**
	 * 获取标签模块
	 * @param map
	 */
	private void getTags(ModelMap map,HttpServletRequest request) {
		List<Tags> tags = null;
		List<Movies> movies_hot_list  =null;
		
		tags = (List<Tags>) XMemcachedUtil.get("movies_tags");
		
		movies_hot_list = (List<Movies>) XMemcachedUtil.get("movies_hot_list");
		
		
		
		if(tags==null || movies_hot_list==null){
			//获取标签
			
			tags = tagsManager.findByCondition(" where tagtype = '1' ").getResultlist();
			XMemcachedUtil.put("movies_tags", tags, 1000 * 60 *24 *7);
			
			//获取热门电影
			String hotsql = "select * from bc_movies order by hotindex,viewnum desc limit 0,50";
			movies_hot_list = moviesManager.querySql(hotsql);
			
			XMemcachedUtil.put("movies_hot_list", movies_hot_list, 1000 * 60 *24 *7);
			
		}
		request.getSession().setAttribute("movies_tags", tags);
		request.getSession().setAttribute("movies_hot_list", movies_hot_list);
		
		
	}
	
	
public static void main(String[] args) {
	XMemcachedUtil.remove("movies_tags");
	XMemcachedUtil.remove("movies_hot_list");
}
	public PageView<Movies> getPageView(String current,
			String whereSql) {
		PageView<Movies> pageView = new PageView<Movies>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		//总条数
		int n = moviesManager.countHql(new Movies(), whereSql);
		int maxresult = 6; /** 每页显示记录数**/
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
		pageView.setMaxresult(maxresult);
		return pageView;
	}

}
