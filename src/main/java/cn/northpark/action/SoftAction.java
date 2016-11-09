
package cn.northpark.action;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.SoftManager;
import cn.northpark.model.Soft;
import cn.northpark.query.SoftQuery;
import cn.northpark.query.condition.SoftQueryCondition;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;


/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/softAction")
@SessionAttributes({ "list", "soft" })
public class SoftAction {

 private final String LIST_ACTION = "redirect:/softAction/findAll";
 @Autowired	
 private SoftManager softManager;
 @Autowired	
 private SoftQuery softQuery;

	

	@RequestMapping("/addSoft")
	public String addSoft(Soft soft) {
		this.softManager.addSoft(soft);
		return LIST_ACTION;
	}


	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,SoftQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = softQuery.getSql(condition);
		
		PageView<Soft> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<Soft> qrs = this.softManager.findByCondition(pageView,
				whereSql, order);
		List<Soft> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","softAction/findAll" );

		return "admin/soft/softList";
	}

	private PageView<Soft> getPageView(String page,
			String whereSql) {
		PageView<Soft> pageView = new PageView<Soft>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.softManager.countHql(new Soft(), whereSql);;
		int maxresult = MyConstant.MAXRESULT; /** 每页显示记录数**/
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
		return pageView;
	}

}
