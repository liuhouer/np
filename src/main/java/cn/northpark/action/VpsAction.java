
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.manager.VpsManager;
import cn.northpark.model.Vps;
import cn.northpark.query.VpsQuery;
import cn.northpark.query.condition.VpsQueryCondition;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;


/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/vps")
public class VpsAction {

private static final Logger LOGGER = Logger
         .getLogger(Vps.class);
 @Autowired	
 private VpsManager vpsManager;
 @Autowired	
 private VpsQuery vpsQuery;

	
	/**
	 * 查看列表
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/list")
	public String list1(ModelMap map,VpsQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/vps";
		String sql = vpsQuery.getMixSql(condition);
		
		LOGGER.info("sql ---"+sql);
		
		//定义pageview
		PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(1,MyConstant.MAXRESULT);
		
		//获取分页结构不获取数据
		
		pageview = this.vpsManager.getMixMapPage(pageview, sql);
		
		map.addAttribute("pageView", pageview);
		map.put("condition", condition);
		map.addAttribute("actionUrl","/vps/list");
		
		

		return result;
	}
	
	

	/**
	 * 查看列表分页
	 * @param map
	 * @param condition
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/list/page/{page}")
	public String list2(ModelMap map,VpsQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		String result="/vps";
		String sql = vpsQuery.getMixSql(condition);
		
		LOGGER.info("sql ---"+sql);
		int currentpage = Integer.parseInt(page);
		
		//定义pageview
		PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(currentpage,MyConstant.MAXRESULT);
		
		//获取分页结构不获取数据
		
		pageview = this.vpsManager.getMixMapPage(pageview, sql);
		
		map.addAttribute("pageView", pageview);
		map.put("condition", condition);
		map.addAttribute("actionUrl","/vps/list");
		map.addAttribute("page", page);
		

		return result;
	}
	
	
	
	//异步分页查询数据
	@RequestMapping(value="/query")
	public String plazzquery(ModelMap map,HttpServletRequest request,VpsQueryCondition condition, HttpSession session,String userid) {
		String currentpage = request.getParameter("currentpage");
		
		String sql = vpsQuery.getMixSql(condition);
		
		//定义pageview
		PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(Integer.parseInt(currentpage),MyConstant.MAXRESULT);
		
		
		//根据分页仅仅获取数据 
		List<Map<String, Object>> list = this.vpsManager.findmixByCondition(pageview,sql);
		
		
		
		map.addAttribute("list", list);
		
		
		return "/page/vps/vpsdata";
	}
	
	
	
	@RequestMapping("/list3")
	public String list3(ModelMap map,VpsQueryCondition condition, String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  {
		String result="/equp";
		try {
			session.removeAttribute("tabs");
			String whereSql = vpsQuery.getSql(condition);
			
			
			//定义pageview
			PageView<Vps> pageview  =  new PageView<Vps>(1, MyConstant.MAXRESULT); 
			
			LOGGER.info("sql ---"+whereSql);
			
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("date", "desc");
			
			
			QueryResult<Vps> qr = this.vpsManager.findByCondition(pageview, whereSql, order);
			List<Vps> resultlist = qr.getResultlist();
			
			
			//触发生成页码等等
			pageview.setQueryResult(qr);
			map.addAttribute("pageView", pageview);
			map.addAttribute("list", resultlist);
			map.addAttribute("actionUrl","/vps");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("eqacton------>",e);
		}
		
		

		return result;
	}
	
	@RequestMapping(value="/vps/page/{page}")
	public String list4(ModelMap map,VpsQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		String result="/vps";
		session.removeAttribute("tabs");
		String whereSql = vpsQuery.getSql(condition);
		
		
		
		//定义pageview
		PageView<Vps> pageview  =  new PageView<Vps>(Integer.parseInt(page), MyConstant.MAXRESULT); 
		
		String keyword = request.getParameter("keyword");
		if(StringUtils.isNotEmpty(keyword)){
			keyword = WAQ.forSQL().escapeSql(keyword);
			whereSql+=" and title like '%"+keyword+"%' ";
			
			map.addAttribute("keyword", keyword);
			
		}
		LOGGER.info("sql ---"+whereSql);
		
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("date", "desc");
		
		
		QueryResult<Vps> qr = this.vpsManager.findByCondition(pageview, whereSql, order);
		List<Vps> resultlist = qr.getResultlist();
		//触发生成页码等等
		pageview.setQueryResult(qr);
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/romeo");
		map.addAttribute("page", page);
		

		return result;
	}

}
