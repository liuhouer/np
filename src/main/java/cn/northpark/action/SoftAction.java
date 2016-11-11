
package cn.northpark.action;
import java.io.IOException;
import java.net.URLEncoder;
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
	public String list() {

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
		
		
		List<Map<String, Object>> tags = softManager.querySqlMap("select count(tags) as num,tags from bc_soft group by tags order by num desc");
		
		map.put("soft_tags", tags);
		

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
	 * 按照标签计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/tag/{tags}")
	public String tagsearch(ModelMap map, @PathVariable String tags ,HttpServletRequest request) {
		try{
			
			tags = URLEncoder.encode(tags,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		String rs = "redirect:/soft/pagetag/"+tags+"/page0";
		return rs;
	}
	
	/**
	 * 按照标签分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tag/{tags}/page{page}")
	public String tagsearchpage(ModelMap map, @PathVariable String page,@PathVariable String tags,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		String result="/soft";
		//防止sql注入
		tags = WAQ.forSQL().escapeSql(tags);
 		String whereSql = " where tags = '"+tags+"' ";
		
		
		
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
		map.addAttribute("actionUrl","/soft/pagetag/"+tags);
		
		

		return result;
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
