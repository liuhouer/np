
package cn.northpark.action;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@RequestMapping("")
@SessionAttributes({ "list", "eq" })
public class EqAction {

 @Autowired	
 private EqManager eqManager;
 @Autowired	
 private EqQuery eqQuery;

 
 
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
			e.printStackTrace();
		}
		return "/page/eq/article";
	}
	
//	/**
//	 * 爬虫生成情圣养成日记
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("/romeo/geneArt")
//	public String geneArt(ModelMap map) {
//		try {
//			
//			List<Map<String, String>> retList = HTMLParserUtil.retEQArticle();
//			
//			for(int i=0;i<retList.size();i++){
//				Map<String, String> map_ = retList.get(i);
//				Eq model = new Eq();
//				model.setTitle(map_.get("title"));
//				model.setImg(map_.get("img") );
//				model.setBrief(map_.get("brief"));
//				model.setDate(map_.get("date"));
//				model.setArticle(map_.get("article"));
//				eqManager.addEq(model);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return "admin/eq/eqAdd";
//	}
//	
//	/**
//	 * 爬虫更新meizutu
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("/romeo/meizitu")
//	@ResponseBody
//	public String meizitu(ModelMap map) {
//		try {
//			List<String> meizi = new ArrayList<String>();
//			
//			for (int i = 1; i <= 10; i++) {
//				String img = "/img/eq/tumblr_o"+i+".png";
//				meizi.add(img);
//			}
//			
//			List<Eq> list  = eqManager.findAll();
//			for(Eq eq: list){
//				
////				//保证图片不重复
////				do {
////					index = getRandomOne(meizi);
////				} while (setindex.add(index));
////				
//				int index = getRandomOne(meizi);
//				//执行更新
//				String img = meizi.get(index);
//				eq.setImg(img);
//				eqManager.updateEq(eq);
//			}
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return "meizitu  success";
//	}

	
	
	@RequestMapping("/romeo")
	public String list(ModelMap map,EqQueryCondition condition, String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  {
		String result="/equp";
		try {
			session.removeAttribute("tabs");
			String whereSql = eqQuery.getSql(condition);
			
			
			//定义pageview
			PageView<Eq> pageview  =  new PageView<Eq>(1, MyConstant.MAXRESULT); 
			
			System.out.println("sql ---"+whereSql);
			
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
			e.printStackTrace();
		}
		
		

		return result;
	}
	
	@RequestMapping(value="/romeo/page{page}")
	public String listpage(ModelMap map,EqQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		String result="/equp";
		session.removeAttribute("tabs");
		String whereSql = eqQuery.getSql(condition);
		
		
		
		//定义pageview
		PageView<Eq> pageview  =  new PageView<Eq>(Integer.parseInt(page), MyConstant.MAXRESULT); 
		
		System.out.println("sql ---"+whereSql);
		
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
		
		

		return result;
	}
	
	//异步分页查询eq数据
		@RequestMapping(value="/romeo/equery")
		public String lovequery(ModelMap map,HttpServletRequest request,EqQueryCondition condition,  HttpSession session,String keyword) {
			String currentpage = request.getParameter("currentpage");
			
			String whereSql = eqQuery.getSql(condition);
			
			if(StringUtils.isNotEmpty(keyword)){
				keyword = WAQ.forSQL().escapeSql(keyword);
				whereSql+=" and title like '%"+keyword+"%' ";
			}
			PageView<Eq> pageview  =  new PageView<Eq>(Integer.parseInt(currentpage), MyConstant.MAXRESULT); 
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("date", "desc");
			
			QueryResult<Eq> qr = this.eqManager.findByCondition(pageview, whereSql, order);
			List<Eq> resultlist = qr.getResultlist();
			map.addAttribute("list", resultlist==null?"":resultlist);
			
			
			return "/page/eq/eqdata";
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
