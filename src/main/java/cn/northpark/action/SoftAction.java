
package cn.northpark.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Soft;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import lombok.extern.slf4j.Slf4j;


/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@RequestMapping("/soft")
@Slf4j
public class SoftAction {

    @Autowired
    private SoftManager softManager;


    /**
     * 每页展示多少条mac数
     */
    private static int SoftCount = 10;
    
    
    /**
     * 跳转后台添加
     *
     * @param map
     * @return
     */
    @RequestMapping("/add")
    @BruceOperation
    public String toAdd(ModelMap map, HttpServletRequest request) {
    	
        String rs = "/page/admin/soft/softAdd";
        return rs;
    }
    
    
    /**
     * 跳转到软件编辑页面
     *
     * @param map
     * @param condition
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    @BruceOperation
    public String edit(ModelMap map, @PathVariable String id, HttpServletRequest request ) {

        if (StringUtils.isNotEmpty(id)) {
            //sql注入处理
            id = WAQ.forSQL().escapeSql(id);
            Soft  model = softManager.findSoft(Integer.valueOf(id));
            if(model!=null) {
            	map.addAttribute("model", model);
            }
        }


        return "/page/admin/soft/softAdd";
    }
    
    /**
     * 保存软件的方法
     *
     * @param map
     * @return
     */
    @RequestMapping("/addItem")
    @ResponseBody
    @BruceOperation
    public Result<String> addItem(ModelMap map, Soft model) {
        String rs = "success";
        try {
        	//更新
        	if(model.getId()!=null && model.getId()!=0) {
        		Soft old = softManager.findSoft(model.getId());
        		old.setTitle(model.getTitle());
        		old.setPath(model.getPath());
        		old.setContent(model.getContent());
        		old.setBrief(model.getBrief());
        		old.setRetcode(model.getRetcode());
        		softManager.updateSoft(old);
        			
        	}else {//新增
        		
        		 model.setPostdate(TimeUtils.nowdate());
        		 model.setYear(TimeUtils.getYear(TimeUtils.nowdate()));
                 model.setMonth(TimeUtils.getMonth(TimeUtils.nowdate()));
        		 softManager.addSoft(model);
        	}
           
        } catch (Exception e) {
            // TODO: handle exception
            log.error("Softacton------>", e);
            rs = "ex";
        }
        return ResultGenerator.genSuccessResult(rs);
    }

    /**
     * 查询列表
     *
     * @param map
     * @param condition
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/mac")
    public String list(HttpServletRequest request) {

        //搜索

        String rs = "redirect:/soft/mac/page/1";

        return rs;
    }

    @RequestMapping(value = "/mac/page/{page}")
    public String listpage(ModelMap map, @PathVariable String page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "soft");
        String result = "/soft";
        String whereSql = "";

        //搜索
        String keyword = request.getParameter("keyword");
        if (StringUtils.isNotEmpty(keyword)) {
            keyword = URLDecoder.decode(keyword, "UTF-8");
        }
        map.put("keyword", keyword);
        if (StringUtils.isNotEmpty(keyword)) {
            keyword = WAQ.forSQL().escapeSql(keyword);
            if (keyword.contains(" ")) {
                String keyword2 = keyword.replaceAll(" ", "");
                whereSql += " where title like '%" + keyword + "%' or title like '%" + keyword2 + "%' ";
            } else {
                whereSql += " where title like '%" + keyword + "%' ";
            }


        }

        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("UNIX_TIMESTAMP(postdate)", "desc");

        //获取pageview
        PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentpage), SoftCount);
        QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
        List<Soft> resultlist = qr.getResultlist();

        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", page);

        //获取标签模块
        getTags(map, request);


        return result;
    }


    /**
     * 查看全文
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping("/{retcode}.html")
    public String softdetail(ModelMap map, @PathVariable String retcode, HttpServletRequest request) {
        try {
            //根据retcode获取文章内容
            List<Soft> list = softManager.querySql("select * from bc_soft where retcode=?", retcode);
            if (!CollectionUtils.isEmpty(list)) {
                map.addAttribute("article", list.get(0));
                if(StringUtils.isNotEmpty(list.get(0).getBrief())) map.put("description", Jsoup.parse(list.get(0).getBrief()).text());
            }

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "/softdetail";
    }


    /**
     * 按照日期计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping("/date/{postdate}")
    public String datesearch(ModelMap map, @PathVariable String postdate, HttpServletRequest request) {
        try {
            //根据retcode获取文章内容
            List<Soft> list = softManager.querySql("select * from bc_soft where postdate=?", postdate);
            if (!CollectionUtils.isEmpty(list)) {
                map.addAttribute("list", list);
                map.addAttribute("pagein", "no");
            }
            
            //获取标签模块
            getTags(map, request);


        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "/soft";
    }


    /**
     * 按照月份计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping("/month/{month}")
    public String monthsearch(ModelMap map, @PathVariable String month, HttpServletRequest request) {
        return "redirect:/soft/month/" + month + "/page/1";
    }

    /**
     * 按照月份计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping("/month/{month}/page/{page}")
    public String monthsearch(ModelMap map, @PathVariable String month, @PathVariable String page, HttpServletRequest request) {
        String result = "/soft";
        try {
            month = WAQ.forSQL().escapeSql(month);
            String whereSql = " where month='" + month + "' ";

            map.put("selmonth", month);

            log.info("sql ---" + whereSql);
            String currentpage = page;
            //排序条件
            LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
            order.put("UNIX_TIMESTAMP(postdate)", "desc");

            //获取pageview
            PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentpage), SoftCount);
            QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
            List<Soft> resultlist = qr.getResultlist();

            //触发分页
            p.setQueryResult(qr);

            map.addAttribute("pageView", p);
            map.addAttribute("list", resultlist);
            map.addAttribute("actionUrl", "/soft/month/" + month);
            map.addAttribute("page", page);
            
            //获取标签模块
            getTags(map, request);

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return result;
    }


    /**
     * 按照标签计算
     *
     * @param map
     * @param retcode
     * @param requestk
     * @return
     */
    @RequestMapping("/tag/{tagscode}")
    public String tagsearch(ModelMap map, @PathVariable String tagscode, HttpServletRequest request) {
        String rs = "redirect:/soft/tag/" + tagscode + "/page/1";
        return rs;
    }

    /**
     * 按照标签分页计算
     *
     * @param map
     * @param retcode
     * @param request
     * @return
     */
    @RequestMapping(value = "/tag/{tagscode}/page/{page}")
    public String tagsearchpage(ModelMap map, @PathVariable String page, @PathVariable String tagscode, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/soft";
        //防止sql注入
        tagscode = WAQ.forSQL().escapeSql(tagscode);
        String whereSql = " where tagscode = '" + tagscode + "' ";

        map.put("seltag", tagscode);


        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("UNIX_TIMESTAMP(postdate)", "desc");

        //获取pageview
        PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentpage), SoftCount);
        QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
        List<Soft> resultlist = qr.getResultlist();

        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/soft/tag/" + tagscode);
        map.addAttribute("page", page);
        
        //获取标签模块
        getTags(map, request);

        return result;
    }


    ///////common ----- method======================================================================================================================

    /**
     * 获取标签模块
     *
     * @param map
     */
    private void getTags(ModelMap map, HttpServletRequest request) {
        List<Map<String, Object>> tags = null;
        List<Map<String, Object>> hotlist = null;
        List<Map<String, Object>> datelist = null;

        
        //从redis取
        String soft_tags_str = RedisUtil.get("soft_tags");
        String soft_hot_list_str = RedisUtil.get("soft_hot_list");
        String soft_date_list_str = RedisUtil.get("soft_date_list");
        
        if(StringUtils.isNotEmpty(soft_tags_str) && StringUtils.isNotEmpty(soft_hot_list_str) && StringUtils.isNotEmpty(soft_date_list_str)) {
        	
        	tags = JsonUtil.json2ListMap(soft_tags_str);
        	hotlist = JsonUtil.json2ListMap(soft_hot_list_str);
        	datelist = JsonUtil.json2ListMap(soft_date_list_str);
        }

        //从数据库取
        if (CollectionUtils.isEmpty(tags) && CollectionUtils.isEmpty(hotlist) && CollectionUtils.isEmpty(datelist)) {
            //获取标签
            tags = softManager.querySqlMap("select count(tags) as num,tags,tagscode from bc_soft group by tags,tagscode order by num desc");


            //获取热门文章
            String hotsql = "select retcode,title from bc_soft order by postdate desc limit 0,10";
            hotlist = softManager.querySqlMap(hotsql);


            //获取月份排序
            String datesql = "select distinct(month) as month  from bc_soft  order by month  desc";
            datelist = softManager.querySqlMap(datesql);

            
            RedisUtil.set("soft_tags", JsonUtil.object2json(tags), 24 * 60 * 60);
            RedisUtil.set("soft_hot_list", JsonUtil.object2json(hotlist), 24 * 60 * 60);
            RedisUtil.set("soft_date_list", JsonUtil.object2json(datelist), 24 * 60 * 60);
        }
        
        map.put("soft_tags", tags);
        map.put("hot_list", hotlist);
        map.put("date_list", datelist);



    }


}
