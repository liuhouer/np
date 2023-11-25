
package cn.northpark.action;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.BC_Constant;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.NotifyRemind;
import cn.northpark.model.Soft;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.*;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;


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
    


    //===========================编辑资源================================

    /**
     * @param request
     * @return
     * @author Bruce
     * 置顶的方法
     */
    @RequestMapping("/handup")
    @ResponseBody
    @BruceOperation
    public Result<String> handUp(HttpServletRequest request) {
        String rs = "success";
        try {
            String id = request.getParameter("id");
            String max_hot_sql_id = "select max(hot_index) as hot_index from bc_soft ";
            List<Map<String, Object>> list = softManager.querySqlMap(max_hot_sql_id);
            Integer hot_index = 0;
            if (!CollectionUtils.isEmpty(list) && Objects.nonNull(list.get(0).get("hot_index"))) {
                hot_index = (Integer) list.get(0).get("hot_index");
                hot_index++;
            }

            hot_index = hot_index>0?hot_index:888;

            if (hot_index > 0) {
                Soft m = softManager.findSoft(Integer.parseInt(id));
                if (m != null) {
                    m.setHot_index(hot_index);
                    softManager.updateSoft(m);
                }
            }

        } catch (Exception e) {
            log.error("soft action------>", e);
            rs = "ex";
        }
        return ResultGenerator.genSuccessResult(rs);
    }


    /**
     * 隐藏电影的方法
     *
     * @param request
     * @return
     */
    @RequestMapping("/hideup")
    @ResponseBody
    @BruceOperation
    public Result<String> hideUp(HttpServletRequest request) {
        String rs = "success";
        try {
            String id = request.getParameter("id");

            Soft m = softManager.findSoft(Integer.parseInt(id));
            if (m != null) {
                m.setDisplayed("N");
                softManager.updateSoft(m);
            }

        } catch (Exception e) {
            log.error("soft action------>", e);
            rs = "ex";
        }
        return ResultGenerator.genSuccessResult(rs);
    }

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
     * @param id
     * @param request
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
        		old.setRet_code(model.getRet_code());
        		//added--2021年10月28日
                old.setOs(model.getOs());
        		old.setColor(model.getColor());
        		old.setDisplayed(model.getDisplayed());
        		old.setHot_index(model.getHot_index());
        		softManager.updateSoft(old);


                //从redis set里面删除更新的失效资源
                if(RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).toString().contains(model.getId().toString())){
                    RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).forEach(item->{
                        if(item.contains(model.getId().toString())) {
                            RedisUtil.getInstance().sRem(BC_Constant.REDIS_FEEDBACK, item);
                            //{"spanID":"746358","uID":"519795","href":"https://northpark.cn/movies/post-746358.html",
                            // "title":"《卡比利亚之夜》百度云网盘下载[MP4//mkv]蓝光"}
                            Map<String, Object> feed_map = JsonUtil.json2map(item);

                            //===================================异步操作=================================================
                            ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                            threadPoolExecutor.execute(new Runnable() {
                                @Override
                                public void run() {

                                    //发送异步站长通知消息
                                    try {
                                        //=================================消息提醒====================================================

                                        //判断主题类型
                                        NotifyEnum match = NotifyEnum.FEED;

                                        //提醒系统赋值
                                        NotifyRemind nr = new NotifyRemind();

                                        //common
                                        nr.setRecipientID(feed_map.get("uID").toString());
                                        nr.setObject(feed_map.get("title").toString());
                                        nr.setObjectID(feed_map.get("spanID").toString());
                                        nr.setObjectLinks(feed_map.get("href").toString());
                                        nr.setMessage("---"+TimeUtils.nowTime()+"---资源已更新，请知悉---");
                                        nr.setStatus("0");


                                        match.notifyInstance.execute(nr);

                                        //=================================消息提醒====================================================
                                    }catch (Exception ig){
                                        log.error("addItem-notice-has-ignored-------:",ig);
                                    }
                                }



                            });
                            //===================================异步操作=================================================
                        }
                    });
                }
        			
        	}else {//新增
        		
        		 model.setPost_date(TimeUtils.nowdate());
        		 model.setYear(TimeUtils.getYear(TimeUtils.nowdate()));
                 model.setMonth(TimeUtils.getMonth(TimeUtils.nowdate()));
        		 softManager.addSoft(model);
        	}
           
        } catch (Exception e) {

            log.error("Soft action------>", e);
            rs = "ex";
        }
        return ResultGenerator.genSuccessResult(rs);
    }

    //===========================编辑资源================================

    /**
     * 查询列表-首页数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/mac")
    @RateLimit
    public String list(ModelMap map, HttpServletRequest request, HttpSession session) throws IOException {

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

        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("hot_index", "desc");
        order.put("UNIX_TIMESTAMP(post_date)", "desc");
        order.put("id", "desc");

        //获取pageView
        PageView<Soft> p = new PageView<Soft>(1, SoftCount);
        QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
        List<Soft> result_list = qr.getResultList();

        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", 1);

        //获取标签模块
        getTags(map, request);


        return result;
    }

    @RequestMapping(value = "/mac/page/{page}")
    @RateLimit
    public String listPage(ModelMap map, @PathVariable String page, HttpServletRequest request,
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
        String currentPage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("hot_index", "desc");
        order.put("UNIX_TIMESTAMP(post_date)", "desc");
        order.put("id", "desc");

        //获取pageView
        PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentPage), SoftCount);
        QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
        List<Soft> result_list = qr.getResultList();

        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
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
     * @param ret_code
     * @param request
     * @return
     */
    @RequestMapping("/{ret_code}.html")
    @RateLimit
    public String softDetail(ModelMap map, @PathVariable String ret_code, HttpServletRequest request) {
        try {
            //根据ret_code获取文章内容
            List<Soft> list = softManager.querySql("select * from bc_soft where ret_code=?", ret_code);
            if (!CollectionUtils.isEmpty(list)) {
                map.addAttribute("article", list.get(0));
                //SEO 优化
                if(StringUtils.isNotEmpty(list.get(0).getBrief())) map.put("soft_desc", Jsoup.parse(list.get(0).getBrief()).text());
            }

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "/soft-detail";
    }


    /**
     * 按照日期计算
     *
     * @param map
     * @param post_date
     * @param request
     * @return
     */
    @RequestMapping("/date/{post_date}")
    public String dateSearch(ModelMap map, @PathVariable String post_date, HttpServletRequest request) {
        try {
            //根据ret_code获取文章内容
            List<Soft> list = softManager.querySql("select * from bc_soft where post_date=?", post_date);
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
     * @param month
     * @param request
     * @return
     */
    @RequestMapping("/month/{month}")
    public String monthSearch(ModelMap map, @PathVariable String month, HttpServletRequest request) {
        return "redirect:/soft/month/" + month + "/page/1";
    }

    /**
     * 按照月份计算
     *
     * @param map
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/month/{month}/page/{page}")
    public String monthSearch(ModelMap map, @PathVariable String month, @PathVariable String page, HttpServletRequest request) {
        String result = "/soft";
        try {
            month = WAQ.forSQL().escapeSql(month);
            String whereSql = " where month='" + month + "' ";

            map.put("sel_month", month);

            log.info("sql ---" + whereSql);
            String currentPage = page;
            //排序条件
            LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
            order.put("UNIX_TIMESTAMP(post_date)", "desc");

            //获取pageView
            PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentPage), SoftCount);
            QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
            List<Soft> result_list = qr.getResultList();

            //触发分页
            p.setQueryResult(qr);

            map.addAttribute("pageView", p);
            map.addAttribute("list", result_list);
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
     * @param tags_code
     * @param request
     * @return
     */
    @RequestMapping("/tag/{tags_code}")
    public String tagSearch(ModelMap map, @PathVariable String tags_code, HttpServletRequest request) {
        String rs = "redirect:/soft/tag/" + tags_code + "/page/1";
        return rs;
    }

    /**
     * 按照标签分页计算
     *
     * @param map
     * @param tags_code
     * @param request
     * @return
     */
    @RequestMapping(value = "/tag/{tags_code}/page/{page}")
    public String tagSearchPage(ModelMap map, @PathVariable String page, @PathVariable String tags_code, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/soft";
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where tags_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);
        String currentPage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("UNIX_TIMESTAMP(post_date)", "desc");

        //获取pageView
        PageView<Soft> p = new PageView<Soft>(Integer.parseInt(currentPage), SoftCount);
        QueryResult<Soft> qr = this.softManager.findByCondition(p, whereSql, order);
        List<Soft> result_list = qr.getResultList();

        //触发分页
        p.setQueryResult(qr);

        map.addAttribute("pageView", p);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/tag/" + tags_code);
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
        List<Map<String, Object>> hot_list = null;
        List<Map<String, Object>> date_list = null;

        
        //从redis取
        String soft_tags_str = RedisUtil.getInstance().getInstance().get("soft_tags");
        String soft_hot_list_str = RedisUtil.getInstance().getInstance().get("soft_hot_list");
        String soft_date_list_str = RedisUtil.getInstance().getInstance().get("soft_date_list");
        
        if(StringUtils.isNotEmpty(soft_tags_str) && StringUtils.isNotEmpty(soft_hot_list_str) && StringUtils.isNotEmpty(soft_date_list_str)) {
        	
        	tags = JsonUtil.json2ListMap(soft_tags_str);
        	hot_list = JsonUtil.json2ListMap(soft_hot_list_str);
        	date_list = JsonUtil.json2ListMap(soft_date_list_str);
        }

        //从数据库取
        if (CollectionUtils.isEmpty(tags) && CollectionUtils.isEmpty(hot_list) && CollectionUtils.isEmpty(date_list)) {
            //获取标签
            tags = softManager.querySqlMap("select count(tags) as num,tags,tags_code from bc_soft group by tags,tags_code order by num desc");


            //获取热门文章
            String hot_sql = "select ret_code,title from bc_soft order by hot_index desc limit 0,10";
            hot_list = softManager.querySqlMap(hot_sql);


            //获取月份排序
            String date_sql = "select distinct(month) as month  from bc_soft  order by month  desc";
            date_list = softManager.querySqlMap(date_sql);

            
            RedisUtil.getInstance().getInstance().set("soft_tags", JsonUtil.object2json(tags), 24 * 60 * 60);
            RedisUtil.getInstance().getInstance().set("soft_hot_list", JsonUtil.object2json(hot_list), 24 * 60 * 60);
            RedisUtil.getInstance().getInstance().set("soft_date_list", JsonUtil.object2json(date_list), 24 * 60 * 60);
        }
        
        map.put("soft_tags", tags);
        map.put("hot_list", hot_list);
        map.put("date_list", date_list);



    }

}
