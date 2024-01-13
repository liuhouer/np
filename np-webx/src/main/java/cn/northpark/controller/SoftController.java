
package cn.northpark.controller;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.BC_Constant;
import cn.northpark.model.NotifyRemindB;
import cn.northpark.model.Soft;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.SoftService;
import cn.northpark.threadPool.AsyncThreadPool;
import cn.northpark.utils.*;
import cn.northpark.utils.safe.WAQ;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
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
@RequestMapping("soft")
@Slf4j
public class SoftController {

    @Autowired
    SoftService softService;


    /**
     * 每页展示多少条mac数
     */
    private static int SoftCount = 20;
    


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
            List<Map<String, Object>> list = softService.querySqlMap(max_hot_sql_id);
            Integer hot_index = 0;
            if (!CollectionUtils.isEmpty(list) && Objects.nonNull(list.get(0).get("hot_index"))) {
                hot_index = (Integer) list.get(0).get("hot_index");
                hot_index++;
            }

            if (hot_index > 0) {
                Soft m = softService.findSoft(Integer.parseInt(id));
                if (m != null) {
                    m.setHotIndex(hot_index);
                    softService.updateSoft(m);
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

            Soft m = softService.findSoft(Integer.parseInt(id));
            if (m != null) {
                m.setDisplayed("N");
                softService.updateSoft(m);
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
    	
        return "/page/admin/soft/softAdd";
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
            Soft  model = softService.findSoft(Integer.valueOf(id));

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
                //BRUCETIPS! 富文本处理
                if(StringUtils.equals("1",model.getUseMinio())){
                    model.setContentMinio(MinioUtils.uploadText(model.getContent()));
                    model.setUseMinio(model.getUseMinio());
                }
        		softService.updateSoft(model);


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
                                        NotifyRemindB nr = new NotifyRemindB();

                                        //common
                                        nr.setRecipientId(feed_map.get("uID").toString());
                                        nr.setObject(feed_map.get("title").toString());
                                        nr.setObjectId(feed_map.get("spanID").toString());
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
        		
        		 model.setPostDate(TimeUtils.nowdate());
        		 model.setYear(TimeUtils.getYear(TimeUtils.nowdate()));
                 model.setMonth(TimeUtils.getMonth(TimeUtils.nowdate()));

                 //BRUCETIPS! 富文本处理
                 if(StringUtils.equals("1",model.getUseMinio())){
                     model.setContentMinio(MinioUtils.uploadText(model.getContent()));
                 }
        		 softService.addSoft(model);
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
        String orderBy = " hot_index desc,UNIX_TIMESTAMP(post_date) desc,id desc";

        PageHelper.startPage(1,SoftCount);
        List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);
        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", 1);

        //获取标签模块
        getTags(map, request);


        return "soft";
    }

    @RequestMapping(value = "/mac/page/{page}")
    @RateLimit
    public String listPage(ModelMap map, @PathVariable Integer page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "soft");
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
        String orderBy = " hot_index desc,UNIX_TIMESTAMP(post_date) desc,id desc";

        PageHelper.startPage(page,SoftCount);
        List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);
        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", page);

        //获取标签模块
        getTags(map, request);


        return "soft";
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
            //开启驼峰映射
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            List<Soft> list = NPQueryRunner.query("select * from bc_soft where ret_code = ?",new BeanListHandler<Soft>(Soft.class,processor), ret_code);
            if (!CollectionUtils.isEmpty(list)) {
                map.addAttribute("article", list.get(0));
                //SEO 优化
                if(StringUtils.isNotEmpty(list.get(0).getBrief())) map.put("soft_desc", Jsoup.parse(list.get(0).getBrief()).text());

                //BRUCETIPS! 富文本处理 -- 从minio读取
                if(StringUtils.equals("1",list.get(0).getUseMinio())){
                    list.get(0).setContent(MinioUtils.readText(list.get(0).getContentMinio()));
                }
                //查历史下载
                if(StringUtils.isNotBlank(list.get(0).getTitle())) {
                    String mergeSQL = "select title,path from bc_soft_merge where title like ? and path is not null and path !='' order by id desc ";
                    String searchTerm = "%" + SoftUtils.buildMergeSearchTile(list.get(0).getRetCode()) + "%";
                    List<Map<String, Object>> soft_merge_list = NPQueryRunner.query(mergeSQL, new MapListHandler(), searchTerm);
                    map.addAttribute("soft_merge_list",soft_merge_list);
                }
            }

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "soft-detail";
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
            //开启驼峰映射
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            List<Soft> list = NPQueryRunner.query("select * from bc_soft where post_date=?",new BeanListHandler<Soft>(Soft.class,processor), post_date);
            if (!CollectionUtils.isEmpty(list)) {
                map.addAttribute("list", list);
                map.addAttribute("pagein", "no");
            }
            
            //获取标签模块
            getTags(map, request);


        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "soft";
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
        try {
            month = WAQ.forSQL().escapeSql(month);
            String whereSql = " where month='" + month + "' ";

            map.put("sel_month", month);

            log.info("sql ---" + whereSql);
            //排序条件
            String orderBy = "UNIX_TIMESTAMP(post_date) desc";

            PageHelper.startPage(1,SoftCount);
            List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
            PageInfo pageInfo = new PageInfo(result_list);
            map.addAttribute("pageInfo", pageInfo);
            map.addAttribute("list", result_list);
            map.addAttribute("actionUrl", "/soft/mac");
            map.addAttribute("page", 1);

            //获取标签模块
            getTags(map, request);

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "soft";
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
    public String monthSearch(ModelMap map, @PathVariable String month, @PathVariable Integer page, HttpServletRequest request) {
        try {
            month = WAQ.forSQL().escapeSql(month);
            String whereSql = " where month='" + month + "' ";

            map.put("sel_month", month);

            log.info("sql ---" + whereSql);
            //排序条件
            String orderBy = "UNIX_TIMESTAMP(post_date) desc";

            PageHelper.startPage(page,SoftCount);
            List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
            PageInfo pageInfo = new PageInfo(result_list);
            map.addAttribute("pageInfo", pageInfo);
            map.addAttribute("list", result_list);
            map.addAttribute("actionUrl", "/soft/mac");
            map.addAttribute("page", page);

            //获取标签模块
            getTags(map, request);

        } catch (Exception e) {
            log.error("softAction------>", e);

        }
        return "soft";
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
        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where tags_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        //排序条件
        String orderBy = "UNIX_TIMESTAMP(post_date) desc";

        PageHelper.startPage(1,SoftCount);
        List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);
        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", 1);

        //获取标签模块
        getTags(map, request);

        return "soft";
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
    public String tagSearchPage(ModelMap map, @PathVariable Integer page, @PathVariable String tags_code, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        //防止sql注入
        tags_code = WAQ.forSQL().escapeSql(tags_code);
        String whereSql = " where tags_code = '" + tags_code + "' ";

        map.put("sel_tag", tags_code);


        log.info("sql ---" + whereSql);

        //排序条件
        String orderBy = "UNIX_TIMESTAMP(post_date) desc";

        PageHelper.startPage(page,SoftCount);
        List<Soft> result_list = softService.findByCondition(whereSql,orderBy);
        PageInfo pageInfo = new PageInfo(result_list);
        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/soft/mac");
        map.addAttribute("page", page);

        //获取标签模块
        getTags(map, request);

        return "soft";
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
            tags = softService.querySqlMap("select count(tags) as num,tags,tags_code from bc_soft group by tags,tags_code order by num desc");


            //获取热门文章
            String hot_sql = "select ret_code,title from bc_soft order by hot_index desc limit 0,10";
            hot_list = softService.querySqlMap(hot_sql);


            //获取月份排序
            String date_sql = "select distinct(month) as month  from bc_soft  order by month  desc";
            date_list = softService.querySqlMap(date_sql);

            
            RedisUtil.getInstance().getInstance().set("soft_tags", JsonUtil.object2json(tags), 24 * 60 * 60);
            RedisUtil.getInstance().getInstance().set("soft_hot_list", JsonUtil.object2json(hot_list), 24 * 60 * 60);
            RedisUtil.getInstance().getInstance().set("soft_date_list", JsonUtil.object2json(date_list), 24 * 60 * 60);
        }
        
        map.put("soft_tags", tags);
        map.put("hot_list", hot_list);
        map.put("date_list", date_list);



    }

}
