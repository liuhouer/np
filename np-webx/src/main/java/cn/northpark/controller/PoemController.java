
package cn.northpark.controller;

import cn.northpark.constant.MyConstant;
import cn.northpark.model.Poem;
import cn.northpark.model.Tags;
import cn.northpark.service.PoemService;
import cn.northpark.service.TagsService;
import cn.northpark.utils.safe.WAQ;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class PoemController {

    @Autowired
    PoemService poemService;

    @Autowired
    TagsService tagService;



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
        List<Poem> list = poemService.querySql(sql);
        if (!CollectionUtils.isEmpty(list)) {
            request.getSession().setAttribute("poem", list);
        }

        //查询标签
        List<Tags> years_tag = tagService.findByCondition(" where tag_type = 2 ","");

        List<Tags> types_tag = tagService.findByCondition(" where tag_type = 3 ","");

        request.getSession().setAttribute("years_tag", years_tag);
        request.getSession().setAttribute("types_tag", types_tag);

        //get the page  data
        String whereSql = "";

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " rand() asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
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
            Poem poem = poemService.findPoem(Integer.parseInt(id));
            map.put("poem_enjoy", poem);

        } catch (Exception e) {

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
    public String listPage(ModelMap map, @PathVariable Integer page, HttpServletRequest request,
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

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " rand() asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
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
        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where years_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " id asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem/dynasty/" + tags_code);


        return result;
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
    public String tagSearchPage(ModelMap map, @PathVariable Integer page, @PathVariable String tags_code, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where years_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " id asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
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
        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where types_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " id asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem/types/" + tags_code);


        return result;
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
    public String typeSearchPage(ModelMap map, @PathVariable Integer page, @PathVariable String tags_code, HttpServletRequest request,
                                  HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/poem";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where types_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        //排序条件
        String orderBy = " id asc";

        List<Poem> result_list = poemService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/poem/types/" + tags_code);


        return result;
    }


}
