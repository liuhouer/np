
package cn.northpark.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;

import cn.northpark.manager.VpsManager;
import cn.northpark.model.Vps;
import cn.northpark.query.VpsQuery;
import cn.northpark.query.condition.VpsQueryCondition;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import lombok.extern.slf4j.Slf4j;


/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@Slf4j
public class VpsAction {

    @Autowired
    private VpsManager vpsManager;
    @Autowired
    private VpsQuery vpsQuery;


    /**
     * 查看全文
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping("/vps/post-{id}.html")
    public String softdetail(ModelMap map, @PathVariable Integer id, HttpServletRequest request) {
        try {
            //根据retcode获取文章内容
            Vps article = vpsManager.findVps(id);
            if (null != article) {
                //触发标签设置
                article.setTags(article.getTags());

                map.addAttribute("article", article);
            }

        } catch (Exception e) {
            log.error("vpsAction------>", e);

        }
        return "/vpsdetail";
    }


    /**
     * 列表页
     *
     * @param map
     * @param condition
     * @param page
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/vps")
    public String list3(ModelMap map, VpsQueryCondition condition, String page, HttpServletRequest request,
                        HttpServletResponse response, HttpSession session) {
        String result = "/vps";
        try {
            session.removeAttribute("tabs");
            String whereSql = vpsQuery.getSql(condition);


            //定义pageview
            PageView<Vps> pageview = new PageView<Vps>(1, MyConstant.MAXRESULT);

            log.info("sql ---" + whereSql);

            //排序条件
            LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
            order.put("date", "desc");


            QueryResult<Vps> qr = this.vpsManager.findByCondition(pageview, whereSql, order);
            List<Vps> resultlist = qr.getResultlist();

            //触发标签设置
            for (int i = 0; i < resultlist.size(); i++) {
                resultlist.get(i).setTags(resultlist.get(i).getTags());
            }

            //触发生成页码等等
            pageview.setQueryResult(qr);
            map.addAttribute("pageView", pageview);
            map.addAttribute("list", resultlist);
            map.addAttribute("actionUrl", "/vps");

            List<Map<String, Object>> tagCloud = getTagCloud();

            map.addAttribute("tagCloud", tagCloud);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("eqacton------>", e);
        }


        return result;
    }

    /**
     * 列表页 -分页
     *
     * @param map
     * @param condition
     * @param page
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/vps/page/{page}")
    public String list4(ModelMap map, VpsQueryCondition condition, @PathVariable String page, HttpServletRequest request,
                        HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/vps";
        session.removeAttribute("tabs");
        String whereSql = vpsQuery.getSql(condition);


        //定义pageview
        PageView<Vps> pageview = new PageView<Vps>(Integer.parseInt(page), MyConstant.MAXRESULT);

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


        QueryResult<Vps> qr = this.vpsManager.findByCondition(pageview, whereSql, order);
        List<Vps> resultlist = qr.getResultlist();

        //触发标签设置
        for (int i = 0; i < resultlist.size(); i++) {
            resultlist.get(i).setTags(resultlist.get(i).getTags());
        }

        //触发生成页码等等
        pageview.setQueryResult(qr);
        map.addAttribute("pageView", pageview);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/vps");
        map.addAttribute("page", page);


        List<Map<String, Object>> tagCloud = getTagCloud();

        map.addAttribute("tagCloud", tagCloud);
        return result;
    }


    /**
     * 按照标签分页计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping(value = "/vps/tag/{tag}")
    public String tag(ModelMap map, @PathVariable String tag, HttpServletRequest request,
                      HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/vps";
        //防止sql注入
        tag = WAQ.forSQL().escapeSql(tag);
        String whereSql = " where tags like '%" + tag + "%' ";

        map.put("seltag", tag);


        log.info("sql ---" + whereSql);
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("date", "desc");

        //获取pageview
        PageView<Vps> p = new PageView<Vps>(1, MyConstant.MAXRESULT);
        QueryResult<Vps> qr = this.vpsManager.findByCondition(p, whereSql, order);
        List<Vps> resultlist = qr.getResultlist();

        //触发标签设置
        for (int i = 0; i < resultlist.size(); i++) {
            resultlist.get(i).setTags(resultlist.get(i).getTags());
        }
        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/vps/tag/" + tag);


        List<Map<String, Object>> tagCloud = getTagCloud();

        map.addAttribute("tagCloud", tagCloud);

        return result;
    }

    /**
     * 按照标签分页计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping(value = "/vps/tag/{tag}/page/{page}")
    public String tagsearchpage(ModelMap map, @PathVariable String page, @PathVariable String tag, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/vps";
        //防止sql注入
        tag = WAQ.forSQL().escapeSql(tag);
        String whereSql = " where tags like '%" + tag + "%' ";

        map.put("seltag", tag);


        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("date", "desc");

        //获取pageview
        PageView<Vps> p = new PageView<Vps>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);
        QueryResult<Vps> qr = this.vpsManager.findByCondition(p, whereSql, order);
        List<Vps> resultlist = qr.getResultlist();

        //触发标签设置
        for (int i = 0; i < resultlist.size(); i++) {
            resultlist.get(i).setTags(resultlist.get(i).getTags());
        }
        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/vps/tag/" + tag);
        map.addAttribute("page", page);


        List<Map<String, Object>> tagCloud = getTagCloud();

        map.addAttribute("tagCloud", tagCloud);

        return result;
    }

    /**
     * 获取标签云
     *
     * @return
     */
    private List<Map<String, Object>> getTagCloud() {

        //char(97+ceiling(rand()*25)) --随机字母（小写）
    	
    	List<Map<String, Object>> rs = null;
    	
    	//从redis取
    	String str = RedisUtil.get("vps_tag_cloud");
		if(StringUtils.isNotEmpty(str)) {
			rs = JsonUtil.json2ListMap(str);
		}
    	
    	//从数据库取
    	if(CollectionUtils.isEmpty(rs)) {
    		StringBuilder tagsqlBuilder = new StringBuilder();


    		tagsqlBuilder.append("select  b.tag as tagscloud,round(rand()*(9)) AS color  ,count(b.tag) as nums from ")
    		.append( "(                                                                                      ")
    		.append( "select substring_index(substring_index(a.tags,',',b.help_topic_id+1),',',-1) as tag    ")
    		.append( "from                                                                                   ")
    		.append( "bc_vps a                                                                               ")
    		.append( "join                                                                                   ")
    		.append( "mysql.help_topic b                                                                     ")
    		.append( "on b.help_topic_id < (length(a.tags) - length(replace(a.tags,',',''))+1)               ")
    		.append( "where a.tags!=''                                                                       ")
    		.append( "order by a.ID )  as b   group by b.tag order by nums desc      limit 0,50              ");


    		rs = vpsManager.querySqlMap(tagsqlBuilder.toString());
    		
    		RedisUtil.set("vps_tag_cloud", JsonUtil.object2json(rs), 24 * 60 * 60);
    	}
        return rs;

    }

}
