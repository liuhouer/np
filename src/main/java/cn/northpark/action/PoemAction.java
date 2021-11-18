
package cn.northpark.action;

import cn.northpark.manager.PoemManager;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Poem;
import cn.northpark.model.Tags;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@RequestMapping("/poem")
@Slf4j
public class PoemAction {

    @Autowired
    private PoemManager poemManager;

    @Autowired
    private TagsManager tagManager;



    /**
     * 首页
     *
     * @param map
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/index.html")
    public String index(ModelMap map, HttpServletRequest request,
                        HttpServletResponse response, HttpSession session) {


        //查询诗词
        request.getSession().removeAttribute("poem");
        String sql = "select * from bc_poem order by rand() limit 6";
        List<Poem> list = poemManager.querySql(sql);
        if (!CollectionUtils.isEmpty(list)) {
            request.getSession().setAttribute("poem", list);
        }

        //查询标签
        List<Tags> years_tag = tagManager.findByCondition(" where tag_type = 2 ").getResultList();

        List<Tags> types_tag = tagManager.findByCondition(" where tag_type = 3 ").getResultList();

        request.getSession().setAttribute("years_tag", years_tag);
        request.getSession().setAttribute("types_tag", types_tag);

        //get the page  data
        String whereSql = "";


        String currentPage = "1";
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("rand()", "asc");

        //获取pageView
        PageView<Poem> p = new PageView<Poem>(Integer.parseInt(currentPage), MyConstant.MAX_RESULT);
        QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);

        //触发分页
        p.setQueryResult(qr);

        List<Poem> result_list = qr.getResultList();

        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem");


        return "/poem";
    }

    /**
     * 诗词赏析页面
     *
     * @param map
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/enjoy/{id}.html")
    public String detail(ModelMap map, HttpServletRequest request, @PathVariable String id,
                         HttpServletResponse response, HttpSession session) {

        try {
            Poem poem = poemManager.findPoem(Integer.parseInt(id));
            map.put("poem_enjoy", poem);

        } catch (Exception e) {
            // TODO: handle exception
            log.error("poemAction------>", e);
        }


        return "/poem-enjoy";
    }


    /**
     * 列表页面
     *
     * @param map
     * @param page
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/page/{page}")
    public String listPage(ModelMap map, @PathVariable String page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) throws IOException {


        String result = "/poem";
        String whereSql = "";

        //搜索
        String keyword = request.getParameter("keyword");

        map.put("keyword", keyword);
        if (StringUtils.isNotEmpty(keyword)) {
            keyword = WAQ.forSQL().escapeSql(keyword);
            if (keyword.contains(" ")) {
                String keyword2 = keyword.replaceAll(" ", "");
                whereSql += " where title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
                whereSql += " or author like '%" + keyword + "%' or author like '%" + keyword2 + "%' ";
                whereSql += " or content1 like '%" + keyword + "%' or content1 like '%" + keyword2 + "%' ";
            } else {
                whereSql += " where title like '%" + keyword + "%' ";
                whereSql += " or author like '%" + keyword + "%' ";
                whereSql += " or content1 like '%" + keyword + "%' ";
            }


        }

        log.info("sql ---" + whereSql);
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("rand()", "asc");


        //获取pageView
        PageView<Poem> p = new PageView<Poem>(Integer.parseInt(page), MyConstant.MAX_RESULT);
        QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
        //触发分页
        p.setQueryResult(qr);

        List<Poem> result_list = qr.getResultList();

        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem");
        map.addAttribute("page", page);

        //获取标签模块
        //getTags(map,request);


        return result;
    }


    /**
     * 按照朝代计算
     *
     * @param map
     * @param tags_code
     * @param request
     * @return
     */
    @RequestMapping("/dynasty/{tags_code}")
    public String tagSearch(ModelMap map, @PathVariable String tags_code, HttpServletRequest request) {
        String rs = "redirect:/poem/dynasty/" + tags_code + "/page/1";
        return rs;
    }

    /**
     * 按照朝代分页计算
     *
     * @param map
     * @param tags_code
     * @param request
     * @return
     */
    @RequestMapping(value = "/dynasty/{tags_code}/page/{page}")
    public String tagSearchPage(ModelMap map, @PathVariable String page, @PathVariable String tags_code, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where years_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);
        String currentPage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("id", "asc");

        //获取pageView
        PageView<Poem> p = new PageView<Poem>(Integer.parseInt(currentPage), MyConstant.MAX_RESULT);
        QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
        List<Poem> result_list = qr.getResultList();

        //触发分页
        p.setQueryResult(qr);
        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem/dynasty/" + tags_code);


        return result;
    }


    /**
     * 按照诗词类型计算
     *
     * @param map
     * @param tags_code
     * @param request
     * @return
     */
    @RequestMapping("/types/{tags_code}")
    public String typeSearch(ModelMap map, @PathVariable String tags_code, HttpServletRequest request) {
        String rs = "redirect:/poem/types/" + tags_code + "/page/1";
        return rs;
    }

    /**
     * 按照诗词类型分页计算
     *
     * @param map
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/types/{tags_code}/page/{page}")
    public String typeSearchPage(ModelMap map, @PathVariable String page, @PathVariable String tags_code, HttpServletRequest request,
                                  HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where types_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);
        String currentPage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("id", "asc");

        //获取pageView
        PageView<Poem> p = new PageView<Poem>(Integer.parseInt(currentPage), MyConstant.MAX_RESULT);
        QueryResult<Poem> qr = this.poemManager.findByCondition(p, whereSql, order);
        List<Poem> result_list = qr.getResultList();

        //触发分页
        p.setQueryResult(qr);
        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem/types/" + tags_code);


        return result;
    }


}
