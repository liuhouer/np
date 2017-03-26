
package cn.northpark.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.PoemManager;
import cn.northpark.model.Poem;
import cn.northpark.query.PoemQuery;
import cn.northpark.query.condition.PoemQueryCondition;
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
@RequestMapping("/poemAction")
@SessionAttributes({ "list", "poem" })
public class PoemAction {

 private final String LIST_ACTION = "redirect:/poemAction/findAll";
 @Autowired	
 private PoemManager poemManager;
 @Autowired	
 private PoemQuery poemQuery;

	
	@RequestMapping("/toAdd")
	public String toAdd(ModelMap map) {
		//List<Poem> Pidlist = poemManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		return "admin/poem/poemAdd";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(HttpServletRequest request, @RequestParam("id") Integer id,ModelMap map) {
		//String id = request.getParameter("id");
		//List<Poem> Pidlist = poemManager.findAll();
		//map.addAttribute("Pidlist",Pidlist);
		if(null!=id && 0!=id){
			Poem model = poemManager.findPoem(id);
			map.put("model", model);
		}
		return "admin/poem/poemEdit";
	}
	
	@RequestMapping("/remove")
	public String remove(@RequestParam("id") Integer id) {
		this.poemManager.delPoem(id);
		return LIST_ACTION;
	}
	
	@RequestMapping("/removes")
	public String removes(@RequestParam("ids") String ids) {
		String[] str = ids.split(",");
		for(String s :str){
			this.poemManager.delPoem(Integer.parseInt(s));
		}
		return LIST_ACTION;
	}
	
	@RequestMapping("/update")
	public String update(Poem model) {
		this.poemManager.updatePoem(model);
		return LIST_ACTION;
	}
	

	@RequestMapping("/addPoem")
	public String addPoem(Poem poem) {
		this.poemManager.addPoem(poem);
		return LIST_ACTION;
	}

	@RequestMapping("/findPoem")
	private String findPoem(@RequestParam("id") Integer id, ModelMap map) {
		Poem poem = this.poemManager.findPoem(id);
		map.addAttribute("poem", poem);
		return "findresult";
	}

	@RequestMapping("/delPoem")
	public String delPoem(@RequestParam("id") Integer id) {
		this.poemManager.delPoem(id);
		return LIST_ACTION;
	}

	@RequestMapping("/updatePoem")
	public String updatePoem(@RequestParam("id") Integer id) {
		Poem poem = this.poemManager.findPoem(id);
		this.poemManager.updatePoem(poem);
		return LIST_ACTION;
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
