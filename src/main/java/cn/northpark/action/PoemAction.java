
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.northpark.manager.PoemManager;
import cn.northpark.model.Poem;
import cn.northpark.query.PoemQuery;
import cn.northpark.query.condition.PoemQueryCondition;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;


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

    @RequestMapping(value="/index.html")
	public String index(ModelMap map,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

    	String sql = "select * from bc_poem order by rand() limit 1";
    	List<Poem> list = poemManager.querySql(sql);
    	if(!CollectionUtils.isEmpty(list)){
    		request.setAttribute("poem", list.get(0));
    	}
    	
		return "/poem";
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
