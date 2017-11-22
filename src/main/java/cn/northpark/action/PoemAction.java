
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.manager.PoemManager;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Poem;
import cn.northpark.model.Tags;
import cn.northpark.query.PoemQuery;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
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
public class PoemAction {

 @Autowired	
 private PoemManager poemManager;
 @Autowired	
 private PoemQuery poemQuery;
 
 @Autowired	
 private TagsManager tagManager;
 
 
 private static final Logger LOGGER = Logger
         .getLogger(PoemAction.class);
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

	
	//查询诗词
	request.getSession().removeAttribute("poem");
 	String sql = "select * from bc_poem order by rand() limit 1";
 	List<Poem> list = poemManager.querySql(sql);
 	if(!CollectionUtils.isEmpty(list)){
 		request.getSession().setAttribute("poem", list.get(0));
 	}
 	
 	//查询标签
 	List<Tags> years_tag = tagManager.findByCondition(" where tagtype = 2 ").getResultlist();
 	
	List<Tags> types_tag = tagManager.findByCondition(" where tagtype = 3 ").getResultlist();
 	
	request.getSession().setAttribute("years_tag", years_tag);
	request.getSession().setAttribute("types_tag", types_tag);
 	
 	//get the page  data
		String whereSql = "";
		


		String currentpage = "1";
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("rand()", "asc");
		
		//获取pageview
		PageView<Poem> p =  new PageView<Poem>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		
		//触发分页
		p.setQueryResult(qr);
		
		List<Poem> resultlist = qr.getResultlist();
		
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
			map.put("poem_enjoy", poem);
    		
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("poemAction------>",e);
		}

		
    	
		return "/poem-enjoy";
	}

    
    /**
     * 
     * 列表页面
     * @param map
     * @param page
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/page/{page}")
	public String listpage(ModelMap map, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
    	
		
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
				whereSql+= " or author like '%"+keyword+"%' or author like '%"+keyword2+"%' " ;
				whereSql+= " or content1 like '%"+keyword+"%' or content1 like '%"+keyword2+"%' " ;
			}else{
				whereSql+= " where title like '%"+keyword+"%' " ;
				whereSql+= " or author like '%"+keyword+"%' " ;
				whereSql+= " or content1 like '%"+keyword+"%' " ;
			}
		
			
		}

		LOGGER.debug("sql ---"+whereSql);
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("rand()", "asc");
		

		//获取pageview
		PageView<Poem> p =  new PageView<Poem>(Integer.parseInt(page), MyConstant.MAXRESULT);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		//触发分页
		p.setQueryResult(qr);
				
		List<Poem> resultlist = qr.getResultlist();
		
		map.addAttribute("pageView", p);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/poem");
		map.addAttribute("page", page);
		
		//获取标签模块
		//getTags(map,request);
		

		return result;
	}
    
    
    

	/**
	 * 按照朝代计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/dynasty/{tagscode}")
	public String tagsearch(ModelMap map, @PathVariable String tagscode ,HttpServletRequest request) {
		String rs = "redirect:/poem/dynasty/"+tagscode+"/page/1";
		return rs;
	}
	
	/**
	 * 按照朝代分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dynasty/{tagscode}/page/{page}")
	public String tagsearchpage(ModelMap map, @PathVariable String page,@PathVariable String tagscode,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/poem";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
 		String whereSql = " where years_code = '"+tagscode+"' ";
 		
 		map.put("seltag", tagscode);
		
		
		
		LOGGER.debug("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("id", "asc");
		
		//获取pageview
		PageView<Poem> p =  new PageView<Poem>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		List<Poem> resultlist = qr.getResultlist();

		//触发分页
		p.setQueryResult(qr);
		map.addAttribute("pageView", p);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/poem/dynasty/"+tagscode);
		

		return result;
	}
	
	
	/**
	 * 按照诗词类型计算
	 * @param map
	 * @param retcode
	 * @param requestk
	 * @return
	 */
	@RequestMapping("/types/{tagscode}")
	public String typessearch(ModelMap map, @PathVariable String tagscode ,HttpServletRequest request) {
		String rs = "redirect:/poem/types/"+tagscode+"/page/1";
		return rs;
	}
	
	/**
	 * 按照诗词类型分页计算
	 * @param map
	 * @param retcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/types/{tagscode}/page/{page}")
	public String typessearchpage(ModelMap map, @PathVariable String page,@PathVariable String tagscode,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/poem";
		//防止sql注入
		tagscode = WAQ.forSQL().escapeSql(tagscode);
 		String whereSql = " where types_code = '"+tagscode+"' ";
 		
 		map.put("seltag", tagscode);
		
		
		
		LOGGER.debug("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("id", "asc");
		
		//获取pageview
		PageView<Poem> p =  new PageView<Poem>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);
		QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
		List<Poem> resultlist = qr.getResultlist();
		
		//触发分页
		p.setQueryResult(qr);
		map.addAttribute("pageView", p);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/poem/types/"+tagscode);
		

		return result;
	}
	
    


}
