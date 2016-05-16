
package com.bruce.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.EqManager;
import com.bruce.model.Eq;
import com.bruce.query.EqQuery;
import com.bruce.query.condition.EqQueryCondition;
import com.bruce.query.condition.NoteQueryCondition;
import com.bruce.utils.HTMLParserUtil;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.TimeUtils;


/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmial.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/eq")
@SessionAttributes({ "list", "eq" })
public class EqAction {

 @Autowired	
 private EqManager eqManager;
 @Autowired	
 private EqQuery eqQuery;

	
	/**
	 * 爬虫生成情圣养成日记
	 * @param map
	 * @return
	 */
	@RequestMapping("/geneArt")
	public String geneArt(ModelMap map) {
		try {
			
			List<Map<String, String>> retList = HTMLParserUtil.retEQArticle();
			
			for(int i=0;i<retList.size();i++){
				Map<String, String> map_ = retList.get(i);
				Eq model = new Eq();
				model.setTitle(map_.get("title"));
				model.setImg(map_.get("img") );
				model.setBrief(map_.get("brief"));
				model.setDate(map_.get("date"));
				model.setArticle(map_.get("article"));
				eqManager.addEq(model);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "admin/eq/eqAdd";
	}
	
	/**
	 * 爬虫更新meizutu
	 * @param map
	 * @return
	 */
	@RequestMapping("/meizitu")
	@ResponseBody
	public String meizitu(ModelMap map) {
		try {
			List<String> meizi = new ArrayList<String>();
			
			for (int i = 1; i <= 10; i++) {
				String img = "/img/eq/tumblr_o"+i+".png";
				meizi.add(img);
			}
			
			List<Eq> list  = eqManager.findAll();
			for(Eq eq: list){
				
//				//保证图片不重复
//				do {
//					index = getRandomOne(meizi);
//				} while (setindex.add(index));
//				
				int index = getRandomOne(meizi);
				//执行更新
				String img = meizi.get(index);
				eq.setImg(img);
				eqManager.updateEq(eq);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "meizitu  success";
	}

	@RequestMapping("/list")
	public String list(ModelMap map,EqQueryCondition condition, String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  {
		String result="/equp";
		try {
			session.removeAttribute("tabs");
			session.setAttribute("tabs","equp");
			String whereSql = eqQuery.getSql(condition);
			
			System.out.println("sql ---"+whereSql);
			
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("date", "desc");
			
			//获取pageview
			PageView<Eq> p = getPageView(null, whereSql);
//			QueryResult<Eq> qr = this.eqManager.findByCondition(p, whereSql, order);
//			List<Eq> resultlist = qr.getResultlist();
			int pages = 0;
			try {
				pages = Integer.parseInt(page)+1;
				
			} catch (Exception e) {
				// TODO: handle exception
				pages = 1;
			}
			map.put("page", pages);
			
			map.addAttribute("pageView", p);
			map.put("condition", condition);
//			map.addAttribute("list", resultlist);
			map.addAttribute("actionUrl","eq/list");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

		return result;
	}
	
	@RequestMapping(value="/list/page{page}")
	public String listpage(ModelMap map,EqQueryCondition condition, @PathVariable String page,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		
		session.removeAttribute("tabs");
		session.setAttribute("tabs","equp");
		String result="/equp";
		String whereSql = eqQuery.getSql(condition);
		
		System.out.println("sql ---"+whereSql);
		String currentpage = page;
		//排序条件
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("date", "desc");
		
		//获取pageview
		PageView<Eq> p = getPageView(currentpage, whereSql);
//		QueryResult<Eq> qr = this.eqManager.findByCondition(p, whereSql, order);
//		List<Eq> resultlist = qr.getResultlist();
		int pages = 0;
		try {
			 pages = Integer.parseInt(page)+1;
			
		} catch (Exception e) {
			// TODO: handle exception
			pages = 1;
		}
		map.put("page", pages);
		
		map.addAttribute("pageView", p);
		map.put("condition", condition);
//		map.addAttribute("list", resultlist);
		map.addAttribute("actionUrl","eq/list");
		
		

		return result;
	}
	
	//异步分页查询eq数据
		@RequestMapping(value="/equery")
		public String lovequery(ModelMap map,HttpServletRequest request,EqQueryCondition condition,  HttpSession session,String userid) {
			String currentpage = request.getParameter("currentpage");
			
			String whereSql = eqQuery.getSql(condition);
			PageView<Eq> p = getPageView(currentpage, whereSql);
			//排序条件
			LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
			order.put("date", "desc");
			
			QueryResult<Eq> qr = this.eqManager.findByCondition(p, whereSql, order);
			List<Eq> resultlist = qr.getResultlist();
			map.addAttribute("list", resultlist==null?"":resultlist);
			
			
			return "/page/eq/eqdata";
		}
	
	
	private PageView<Eq> getPageView(String current,
			String whereSql) {
		PageView<Eq> pageView = new PageView<Eq>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		//总条数
		int n = eqManager.countHql(new Eq(), whereSql);
		int maxresult = MyConstant.MAXRESULT; /** 每页显示记录数**/
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
		return pageView;
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
