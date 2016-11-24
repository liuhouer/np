
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.SoftManager;
import cn.northpark.model.Soft;
import cn.northpark.query.SoftQuery;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;
import cn.northpark.utils.safe.WAQ;


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
	public String list(HttpServletRequest request) {
		
		//搜索

				String rs = "redirect:/soft/mac/page0";

		return rs;
	}
	
	@RequestMapping(value="/mac/page{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","soft");
		String result="/soft";
		String whereSql = "";
		
		//搜索
		String keyword = request.getParameter("keyword");
		
		map.put("keyword", keyword);
		if(StringUtils.isNotEmpty(keyword)){
			keyword = WAQ.forSQL().escapeSql(keyword);
			if(keyword.contains(" ")){
				String keyword2 = keyword.replaceAll(" ", "");
				whereSql+= " where title like '%"+keyword+"%' or title like '%"+keyword2+"%' " ;
			}else{
				whereSql+= " where title like '%"+keyword+"%' " ;
			}
		
			
		}

		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("UNIX_TIMESTAMP(postdate)", "desc");
		
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
		
		
		//获取标签模块
		getTags(map,request);
		

		return result;
	}

	
	
	
	
	/**
	 * 查看全文
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping("/{retcode}.html")
	public String softdetail(ModelMap map, @PathVariable String retcode ,HttpServletRequest request) {
		try{
			//根据retcode获取文章内容
			List<Soft> list = softManager.querySql("select * from bc_soft where retcode=?", retcode);
			if(!CollectionUtils.isEmpty(list)){
				map.addAttribute("article", list.get(0));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "/softdetail";
	}

	
	/**
	 * 按照日期计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping("/date/{postdate}")
	public String datesearch(ModelMap map, @PathVariable String postdate ,HttpServletRequest request) {
		try{
			//根据retcode获取文章内容
			List<Soft> list = softManager.querySql("select * from bc_soft where postdate=?", postdate);
			if(!CollectionUtils.isEmpty(list)){
				map.addAttribute("list", list);
				map.addAttribute("pagein","no");
			}
			

		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "/soft";
	}
	
	
	/**
	 * 按照月份计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping("/month/{month}")
	public String monthsearch(ModelMap map, @PathVariable String month ,HttpServletRequest request) {
		return "redirect:/soft/month/"+month+"/page0";
	}
	
	/**
	 * 按照月份计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping("/month/{month}/page{page}")
	public String monthsearch(ModelMap map, @PathVariable String month , @PathVariable String page,HttpServletRequest request) {
		String result="/soft";
		try{
			month = WAQ.forSQL().escapeSql(month);
			String whereSql = " where month='"+month+"' ";
			
			map.put("selmonth", month);
			
			System.out.println("sql ---"+whereSql);
			String currentpage = page;
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("UNIX_TIMESTAMP(postdate)", "desc");
			
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
			map.addAttribute("actionUrl","/soft/month/"+month);
			

		}catch(Exception e){
			e.printStackTrace();
			
		}
		return result;
	}

	
	/**
	 * 按照标签计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/tag/{tagscode}")
	public String tagsearch(ModelMap map, @PathVariable String tagscode ,HttpServletRequest request) {
		String rs = "redirect:/soft/tag/"+tagscode+"/page0";
		return rs;
	}
	
	/**
	 * 按照标签分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tag/{tagscode}/page{page}")
	public String tagsearchpage(ModelMap map, @PathVariable String page,@PathVariable String tagscode,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/soft";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
 		String whereSql = " where tagscode = '"+tagscode+"' ";
 		
 		map.put("seltag", tagscode);
		
		
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("UNIX_TIMESTAMP(postdate)", "desc");
		
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
		map.addAttribute("actionUrl","/soft/tag/"+tagscode);
		

		return result;
	}
	
	
	
	///////common ----- method======================================================================================================================
	/**
	 * 获取标签模块
	 * @param map
	 */
	private void getTags(ModelMap map,HttpServletRequest request) {
		//获取标签
		List<Map<String, Object>> tags = softManager.querySqlMap("select count(tags) as num,tags,tagscode from bc_soft group by tags order by num desc");
		
		
		request.getSession().setAttribute("soft_tags", tags);
		
		//获取热门文章
		String hotsql = "select * from bc_soft order by postdate desc limit 0,10";
		List<Soft> hotlist = softManager.querySql(hotsql);
		
		request.getSession().setAttribute("hot_list", hotlist);
		
		//获取月份排序
		String datesql = "select * from bc_soft group by month order by id,month desc";
		List<Soft> datelist = softManager.querySql(datesql);
		
		request.getSession().setAttribute("date_list", datelist);
		
		
	}
	
	private PageView<Soft> getPageView(String page,
			String whereSql) {
		PageView<Soft> pageView = new PageView<Soft>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.softManager.countHql(new Soft(), whereSql);;
		int maxresult = 6; /** 每页显示记录数**/
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
		pageView.setMaxresult(maxresult);
		return pageView;
	}

}
