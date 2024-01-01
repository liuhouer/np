
package cn.northpark.controller;

import cn.northpark.constant.MyConstant;
import cn.northpark.model.Eq;
import cn.northpark.service.EqService;
import cn.northpark.utils.safe.WAQ;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@Slf4j
public class EqController {

    @Autowired
    EqService eqService;


    /**
     * 阅读文章页面|||通用的样式
     *
     * @param map
     * @return
     */
    @RequestMapping("/romeo/{eqID}.html")
    public String article(ModelMap map, @PathVariable Integer eqID) {
        try {

            Eq eq = eqService.findEq(eqID);
            //SEO 优化
            if(StringUtils.isNotEmpty(eq.getArticle())) map.put("eq_desc", Jsoup.parse(eq.getArticle()).text());

            map.addAttribute("model", eq);
        } catch (Exception e) {
            log.error("eq action------>", e);
        }
        return "/page/eq/article";
    }


    @RequestMapping("/romeo")
    public String list(ModelMap map,
                       HttpSession session) {
        String result = "/equp";
        try {
            session.removeAttribute("tabs");

            PageHelper.startPage(1,MyConstant.MAX_RESULT);
            List<Eq> result_list = eqService.findAll();
            PageInfo pageInfo = new PageInfo(result_list);

            map.addAttribute("pageInfo", pageInfo);
            map.addAttribute("list", result_list);
            map.addAttribute("actionUrl", "/romeo");
        } catch (Exception e) {
            log.error("eq action------>", e);
        }


        return result;
    }

    @RequestMapping(value = "/romeo/page/{page}")
    public String listPage(ModelMap map,@PathVariable Integer page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/equp";
        session.removeAttribute("tabs");
        String whereSql = "";

        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotEmpty(keyword)) {
            keyword = WAQ.forSQL().escapeSql(keyword);
            whereSql += " title like '%" + keyword + "%' ";

            map.addAttribute("keyword", keyword);

        }
        log.info("sql ---" + whereSql);

        //排序条件
        String _orderBy = "date desc";

        PageHelper.startPage(page,MyConstant.MAX_RESULT);

        List<Eq> result_list  = eqService.findByCondition(whereSql, _orderBy);

        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/romeo");
        map.addAttribute("page", page);


        return result;
    }

}
