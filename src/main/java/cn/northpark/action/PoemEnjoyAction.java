
package cn.northpark.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.PoemEnjoyManager;
import cn.northpark.model.PoemEnjoy;
import cn.northpark.query.PoemEnjoyQuery;
import cn.northpark.query.condition.PoemEnjoyQueryCondition;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Controller
@RequestMapping("/poemenjoyAction")
@SessionAttributes({ "list", "poemenjoy" })
public class PoemEnjoyAction {

 private final String LIST_ACTION = "redirect:/poemenjoyAction/findAll";
 @Autowired	
 private PoemEnjoyManager poemenjoyManager;
 @Autowired	
 private PoemEnjoyQuery poemenjoyQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<PoemEnjoy> Pidlist = poemenjoyManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/poemenjoy/poemenjoyAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") Integer id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<PoemEnjoy> Pidlist = poemenjoyManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(null!=id && 0!=id){
			PoemEnjoy model = poemenjoyManager.findPoemEnjoy(id);
			map.put("model", model);
		}
		return "admin/poemenjoy/poemenjoyEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") Integer id) {
		this.poemenjoyManager.delPoemEnjoy(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.poemenjoyManager.delPoemEnjoy(Integer.parseInt(s));
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(PoemEnjoy model) {
		this.poemenjoyManager.updatePoemEnjoy(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addPoemEnjoy")
	public String addPoemEnjoy(PoemEnjoy poemenjoy) {
		this.poemenjoyManager.addPoemEnjoy(poemenjoy);
		return LIST_ACTION;
	}

	@RequestMapping("/findPoemEnjoy")
	private String findPoemEnjoy(@RequestParam("id") Integer id, ModelMap map) {
		PoemEnjoy poemenjoy = this.poemenjoyManager.findPoemEnjoy(id);
		map.addAttribute("poemenjoy", poemenjoy);
		return "findresult";
	}

	@RequestMapping("/delPoemEnjoy")
	public String delPoemEnjoy(@RequestParam("id") Integer id) {
		this.poemenjoyManager.delPoemEnjoy(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updatePoemEnjoy")
	public String updatePoemEnjoy(@RequestParam("id") Integer id) {
		PoemEnjoy poemenjoy = this.poemenjoyManager.findPoemEnjoy(id);
		this.poemenjoyManager.updatePoemEnjoy(poemenjoy);
		return LIST_ACTION;
	}

	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map,PoemEnjoyQueryCondition condition,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String whereSql = poemenjoyQuery.getSql(condition);
		
		PageView<PoemEnjoy> pageView = getPageView(null, whereSql);
		

		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");

		QueryResult<PoemEnjoy> qrs = this.poemenjoyManager.findByCondition(pageView,
				whereSql, order);
		List<PoemEnjoy> list = qrs.getResultlist();
		map.addAttribute("pageView", pageView);
		map.put("condition", condition);
		map.addAttribute("list", list);
		map.addAttribute("actionUrl","poemenjoyAction/findAll" );

		return "admin/poemenjoy/poemenjoyList";
	}

	private PageView<PoemEnjoy> getPageView(String page,
			String whereSql) {
		PageView<PoemEnjoy> pageView = new PageView<PoemEnjoy>();
		int currentpage = 0; //当前页码
		int pages = 0; //总页数
		int n = this.poemenjoyManager.countHql(new PoemEnjoy(), whereSql);;
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
		pageView.setMaxresult(maxresult);
		return pageView;
	}

}
