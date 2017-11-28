
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

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

import cn.northpark.manager.EqManager;
import cn.northpark.model.Eq;
import cn.northpark.query.EqQuery;
import cn.northpark.query.condition.EqQueryCondition;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;


/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
public class EqAction {

 @Autowired	
 private EqManager eqManager;
 @Autowired	
 private EqQuery eqQuery;

 private static final Logger LOGGER = Logger
         .getLogger(EqAction.class);
 
 /**
	 * 阅读文章页面|||通用的样式
	 * @param map
	 * @return
	 */
	@RequestMapping("/romeo/{eqid}.html")
	public String article(ModelMap map,@PathVariable Integer eqid) {
		try {
			
			Eq eq = eqManager.findEq(eqid);
			map.addAttribute("model", eq);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("eqacton------>",e);
			LOGGER.error("eqacton------>",e);
		}
		return "/page/eq/article";
	}
	
	
	@RequestMapping("/romeo")
	public String list(ModelMap map,EqQueryCondition condition, String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  {
		String result="/equp";
		try {
			session.removeAttribute("tabs");
			String whereSql = eqQuery.getSql(condition);
			
			
			//定义pageview
			PageView<Eq> pageview  =  new PageView<Eq>(1, MyConstant.MAXRESULT); 
			
			LOGGER.info("sql ---"+whereSql);
			
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("date", "desc");
			
			
			QueryResult<Eq> qr = this.eqManager.findByCondition(pageview, whereSql, order);
			List<Eq> resultlist = qr.getResultlist();
			
			
			//触发生成页码等等
			pageview.setQueryResult(qr);
			map.addAttribute("pageView", pageview);
			map.addAttribute("list", resultlist);
			map.addAttribute("actionUrl","/romeo");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("eqacton------>",e);
		}
		
		

		return result;
	}
	
	@RequestMapping(value="/romeo/page/{page}")
	public String listpage(ModelMap map,EqQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		String result="/equp";
		session.removeAttribute("tabs");
		String whereSql = eqQuery.getSql(condition);
		
		
		
		//定义pageview
		PageView<Eq> pageview  =  new PageView<Eq>(Integer.parseInt(page), MyConstant.MAXRESULT); 
		
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
		
		
		QueryResult<Eq> qr = this.eqManager.findByCondition(pageview, whereSql, order);
		List<Eq> resultlist = qr.getResultlist();
		//触发生成页码等等
		pageview.setQueryResult(qr);
		map.addAttribute("pageView", pageview);
		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","/romeo");
		map.addAttribute("page", page);
		

		return result;
	}
	
	

	
	/**
	 * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
	 * @return
	 */
	public static Integer getRandomOne(List<?> list){
		
		
		Random ramdom =  new Random();
		int number = -1;
		int max = list.size();
		
		//size 为  10 ，取得类似0-9的区间数
		number = Math.abs(ramdom.nextInt() % max  );
		
		return number;
    
	}
}
