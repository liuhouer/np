
package cn.northpark.action;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Movies;
import cn.northpark.model.User;
import cn.northpark.query.condition.MoviesQueryCondition;
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


		return "/movies";
	}
	
	
	//、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、
	

}
