
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.PoemManager;
import cn.northpark.model.Poem;
import cn.northpark.query.PoemQuery;
import cn.northpark.query.condition.PoemQueryCondition;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;
import cn.northpark.utils.safe.WAQ;


/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/poem")
@SessionAttributes({ "list", "poem" })
public class PoemAction {

 @Autowired	
 private PoemManager poemManager;
 @Autowired	
 private PoemQuery poemQuery;
 
 
 
 /**
  * 
  * 首页
 * @param map
 * @param request
 * @param response
 * @param session
 * @return
 */
@RequestMapping(value="/index.html")
	public String index(ModelMap map,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

 	String sql = "select * from bc_poem order by rand() limit 1";
 	List<Poem> list = poemManager.querySql(sql);
 	if(!CollectionUtils.isEmpty(list)){
 		request.setAttribute("poem", list.get(0));
 	}
 	
 	
 	
 	
 	//get the page  data
		String result="/poem"; 
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
		String page  = "0" ;
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("id", "asc");
		
		//获取pageview
		PageView<Poem> p = getPageView(currentpage, whereSql);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		List<Poem> resultlist = qr.getResultlist();
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
		map.addAttribute("actionUrl","/poem");
		
 	
		return "/poem";
	}

    /**
     * 诗词赏析页面
     * @param map
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value="/enjoy/{id}.html")
	public String detail(ModelMap map,HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response, HttpSession session) {
    	
    	try {
			Poem poem = poemManager.findPoem(Integer.parseInt(id));
			map.put("poem", poem);
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
    	
		return "/poem-enjoy";
	}

    
    @RequestMapping(value="/page{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
    	
    	String sql = "select * from bc_poem order by rand() limit 1";
    	List<Poem> list = poemManager.querySql(sql);
    	if(!CollectionUtils.isEmpty(list)){
    		request.setAttribute("poem", list.get(0));
    	}
    	
		
		String result="/poem";
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
		order.put("id", "asc");
		
		//获取pageview
		PageView<Poem> p = getPageView(currentpage, whereSql);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		List<Poem> resultlist = qr.getResultlist();
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
		map.addAttribute("actionUrl","/poem");
		
		
		//获取标签模块
		//getTags(map,request);
		

		return result;
	}
    
    
	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,PoemQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = poemQuery.getSql(condition);
		
		PageView<Poem> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Poem> qrs = this.poemManager.findByCondition(pageView,
				whereSql, order);
		List<Poem> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","poemAction/findAll" );

		return "admin/poem/poemList";
	}

	private PageView<Poem> getPageView(String page,
			String whereSql) {
		PageView<Poem> pageView = new PageView<Poem>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.poemManager.countHql(new Poem(), whereSql);;
		int maxresult = 12; /** 每页显示记录数**/
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
