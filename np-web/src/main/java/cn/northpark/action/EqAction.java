
package cn.northpark.action;

import cn.northpark.manager.EqManager;
import cn.northpark.model.Eq;
import cn.northpark.query.EqQuery;
import cn.northpark.query.condition.EqQueryCondition;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import com.google.common.collect.Maps;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;


/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@Slf4j
public class EqAction {

    @Autowired
    private EqManager eqManager;
    @Autowired
    private EqQuery eqQuery;


    /**
     * 阅读文章页面|||通用的样式
     *
     * @param map
     * @return
     */
    @RequestMapping("/romeo/{eqID}.html")
    public String article(ModelMap map, @PathVariable Integer eqID) {
        try {

            Eq eq = eqManager.findEq(eqID);
            //SEO 优化
            if(StringUtils.isNotEmpty(eq.getArticle())) map.put("eq_desc", Jsoup.parse(eq.getArticle()).text());

            map.addAttribute("model", eq);
        } catch (Exception e) {
            log.error("eq action------>", e);
        }
        return "/page/eq/article";
    }


    @RequestMapping("/romeo")
    public String list(ModelMap map, EqQueryCondition condition, String page, HttpServletRequest request,
                       HttpServletResponse response, HttpSession session) {
        String result = "/equp";
        try {
            session.removeAttribute("tabs");
            String whereSql = eqQuery.getSql(condition);


            //定义pageView
            PageView<Eq> pageView = new PageView<Eq>(1, MyConstant.MAX_RESULT);

            log.info("sql ---" + whereSql);

            //排序条件
            LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
            order.put("date", "desc");


            QueryResult<Eq> qr = this.eqManager.findByCondition(pageView, whereSql, order);
            List<Eq> result_list = qr.getResultList();


            //触发生成页码等等
            pageView.setQueryResult(qr);
            map.addAttribute("pageView", pageView);
            map.addAttribute("list", result_list);
            map.addAttribute("actionUrl", "/romeo");
        } catch (Exception e) {
            log.error("eq action------>", e);
        }


        return result;
    }

    @RequestMapping(value = "/romeo/page/{page}")
    public String listPage(ModelMap map, EqQueryCondition condition, @PathVariable String page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/equp";
        session.removeAttribute("tabs");
        String whereSql = eqQuery.getSql(condition);


        //定义pageView
        PageView<Eq> pageView = new PageView<Eq>(Integer.parseInt(page), MyConstant.MAX_RESULT);

        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotEmpty(keyword)) {
            keyword = WAQ.forSQL().escapeSql(keyword);
            whereSql += " and title like '%" + keyword + "%' ";

            map.addAttribute("keyword", keyword);

        }
        log.info("sql ---" + whereSql);

        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("date", "desc");


        QueryResult<Eq> qr = this.eqManager.findByCondition(pageView, whereSql, order);
        List<Eq> result_list = qr.getResultList();
        //触发生成页码等等
        pageView.setQueryResult(qr);
        map.addAttribute("pageView", pageView);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/romeo");
        map.addAttribute("page", page);


        return result;
    }


    /**
     * @return
     * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
     */
    public static Integer getRandomOne(List<?> list) {


        Random random = new Random();
        int number = -1;
        int max = list.size();

        //size 为  10 ，取得类似0-9的区间数
        number = Math.abs(random.nextInt() % max);

        return number;

    }
}
