
package cn.northpark.action;

import cn.northpark.annotation.BruceOperation;
import cn.northpark.annotation.Desc;
import cn.northpark.constant.BC_Constant;
import cn.northpark.constant.ResultEnum;
import cn.northpark.exception.NorthParkException;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Movies;
import cn.northpark.model.NotifyRemind;
import cn.northpark.model.Tags;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.EmailUtils;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.encrypt.MD5Utils;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.utils.safe.WAQ;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/*
 *@author bruce
 *
 **/

@Controller
@Slf4j
public class MoviesAction {

    @Autowired
    private MoviesManager moviesManager;

    @Autowired
    private TagsManager tagsManager;


    /**
     * 每页展示多少条电影数
     */
    private static int MoviesCount = 12;
    
    /**
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/movies/getFeedBack")
    @Desc(value="异步获取资源失效反馈")
	public String getFeedBack(HttpServletRequest request, ModelMap map) {

		String result = "feedback_list";

		List<Map<String, Object>> rs = new ArrayList<>();

		RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).forEach(i -> {
			rs.add(JsonUtil.json2map(i));
		});

		map.put("feedback_list", rs);

		return "/page/common/" + result;
	}
    
    /**
     * @param map
     * @return
     * @author Bruce
     * 资源失效反馈，找站长
     */
    @RequestMapping("/movies/resFeedBack")
    @ResponseBody
    @Desc("资源失效反馈，找站长")
	public Result<String> resFeedBack(HttpServletRequest request, @RequestBody Object map) {
		String rs = "success";
		try {
			System.out.println(String.valueOf(map));
			if (RedisUtil.getInstance().sIsMember(BC_Constant.REDIS_FEEDBACK, String.valueOf(map))) {
				return ResultGenerator.genSuccessResult(rs);
			} else {


                //===================================异步操作=================================================
                ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                        //发送异步站长通知消息
                        try {
                            //=================================消息提醒====================================================

                            //判断主题类型
                            NotifyEnum match = NotifyEnum.WEBMASTER;

                            //提醒系统赋值
                            NotifyRemind nr = new NotifyRemind();

                            //common
                            nr.setMessage(map.toString()+"---"+TimeUtils.nowTime()+"---提醒资源失效---");
                            nr.setStatus("0");


                            match.notifyInstance.execute(nr);

                            // 给站长发邮件
                            try {
                                EmailUtils.getInstance().resFeedBack(String.valueOf(map));

                            } catch (Exception ignore) {
                                log.error(ignore.getMessage());
                            }

                            //=================================消息提醒====================================================
                        }catch (Exception ig){
                            log.error("feedBack-notice-has-ignored-------:",ig);
                        }
                    }



                });
                //===================================异步操作=================================================

				// 添加到集合中
				RedisUtil.getInstance().sAdd(BC_Constant.REDIS_FEEDBACK, String.valueOf(map));
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("resFeedBack------>", e);
			rs = "ex";
		}
		return ResultGenerator.genSuccessResult(rs);
	}


    /**
     * @param request
     * @return
     * @author Bruce
     * 置顶的方法
     */
    @RequestMapping("/movies/handup")
    @ResponseBody
    @BruceOperation
    public Result<String> handup(HttpServletRequest request) {
        String rs = "success";
        try {
            String id = request.getParameter("id");
            String max_hot_sql_id = "select max(hotindex) as hotindex from bc_movies ";
            List<Map<String, Object>> list = moviesManager.querySqlMap(max_hot_sql_id);
            Integer hotindex = 0;
            if (!CollectionUtils.isEmpty(list)) {
                hotindex = (Integer) list.get(0).get("hotindex");
                hotindex++;
            }
            if (hotindex > 0) {
                Movies m = moviesManager.findMovies(Integer.parseInt(id));
                if (m != null) {
                    m.setHotindex(hotindex);
                    moviesManager.updateMovies(m);
                }
            }

        } catch (Exception e) {
            log.error("moviesacton------>", e);
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
    @RequestMapping("/movies/hideup")
    @ResponseBody
    @BruceOperation
    public Result<String> hideup(HttpServletRequest request) {
        String rs = "success";
        try {
            String id = request.getParameter("id");
            
            Movies m = moviesManager.findMovies(Integer.parseInt(id));
            if (m != null) {
                m.setDisplayed("N");
                moviesManager.updateMovies(m);
            }

        } catch (Exception e) {
            log.error("moviesacton------>", e);
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
    @RequestMapping("/movies/add")
    @BruceOperation
    public String toAdd(ModelMap map, HttpServletRequest request) {
    	
        String rs = "/page/admin/movies/moviesAdd";
        return rs;
    }
    
    
    /**
     * 跳转到电影编辑页面
     *
     * @param map
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/movies/edit/{id}")
    @BruceOperation
    public String edit(ModelMap map, @PathVariable String id, HttpServletRequest request ) {

        if (StringUtils.isNotEmpty(id)) {
            //sql注入处理
            id = WAQ.forSQL().escapeSql(id);
            Movies  model = moviesManager.findMovies(Integer.valueOf(id));
            if(model!=null) {
            	map.addAttribute("model", model);
            }
        }


        return "/page/admin/movies/moviesAdd";
    }


    /**
     * 保存电影的方法
     *
     * @param map
     * @return
     */
    @RequestMapping("/movies/addItem")
    @ResponseBody
    @BruceOperation
    public Result<String> addItem(ModelMap map, Movies model) {

        assert Objects.nonNull(model)
                && StringUtils.isNotBlank(model.getDescription())
                && StringUtils.isNotBlank(model.getMoviename())
                && StringUtils.isNotBlank(model.getPath())
                && StringUtils.isNotBlank(model.getColor());
        String rs = "success";
        try {
        	//更新
        	if(model.getId()!=null && model.getId()!=0) {
        		Movies old = moviesManager.findMovies(model.getId());
        		old.setMoviename(model.getMoviename());
        		old.setPath(model.getPath());
        		old.setColor(model.getColor());
        		old.setDescription(model.getDescription());
        		moviesManager.updateMovies(old);

                //从redis set里面删除更新的失效资源
                if(RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).toString().contains(model.getId().toString())){
                    RedisUtil.getInstance().sMembers(BC_Constant.REDIS_FEEDBACK).forEach(item->{
                        if(item.contains(model.getId().toString())) {
                            RedisUtil.getInstance().sRem(BC_Constant.REDIS_FEEDBACK, item);
                        }
                    });
                }
        			
        	}else {//新增

                 model.setRetcode(MD5Utils.encrypt(model.getMoviename(),MD5Utils.MD5_KEY));
        		 model.setAddtime(TimeUtils.nowdate());
                 moviesManager.addMovies(model);
        	}
           
        } catch (Exception e) {
            log.error("moviesacton------>", e);
            rs = "ex";
        }
        return ResultGenerator.genSuccessResult(rs);
    }


    /**
     * 按照日期计算
     *
     * @param map
     * @param tagscode
     * @param request
     * @return
     */
    @RequestMapping("/movies/date/{tagscode}")
    public String datesearch(ModelMap map, @PathVariable String tagscode, HttpServletRequest request) {
        String rs = "redirect:/movies/date/" + tagscode + "/page/1";
        return rs;
    }

    /**
     * 按照日期分页计算
     *
     * @param map
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/movies/date/{tagscode}/page/{page}")
    public String datelistpage(ModelMap map, @PathVariable String page, HttpServletRequest request, @PathVariable String tagscode,
                               HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/movies2";
        //防止sql注入
        tagscode = WAQ.forSQL().escapeSql(tagscode);
        String whereSql = " where addtime = '" + tagscode + "' ";

        map.put("seldate", tagscode);


        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("hotindex,id", "desc");

        //获取pageview
        PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
        QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
        List<Movies> resultlist = qr.getResultlist();

        //生成分页信息
        pageview.setQueryResult(qr);

        //处理标签列表
        handleTag(resultlist);


        map.addAttribute("pageView", pageview);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/movies/date/" + tagscode);

        //获取标签模块
        getTags(map, request);
        return result;
    }


    /**
     * 按照标签计算
     *
     * @param map
     * @param tagscode
     * @param request
     * @return
     */
    @RequestMapping("/movies/tag/{tagscode}")
    public String tagsearch(ModelMap map, @PathVariable String tagscode, HttpServletRequest request) {
        String rs = "redirect:/movies/tag/" + tagscode + "/page/1";
        return rs;
    }

    /**
     * 按照标签分页计算
     *
     * @param map
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/movies/tag/{tagscode}/page/{page}")
    public String taglistpage(ModelMap map, @PathVariable String page, HttpServletRequest request, @PathVariable String tagscode,
                              HttpServletResponse response, HttpSession session) throws IOException {

        String result = "/movies2";
        //防止sql注入
        tagscode = WAQ.forSQL().escapeSql(tagscode);
        String whereSql = " where tagcode like '%" + tagscode + "%' ";

        map.put("seltag", tagscode);


        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("hotindex,id", "desc");

        //获取pageview
        PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
        QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
        List<Movies> resultlist = qr.getResultlist();

        //生成分页信息
        pageview.setQueryResult(qr);

        //处理标签列表
        handleTag(resultlist);

        int pages = 0;
        try {
            pages = Integer.parseInt(page) + 1;

        } catch (Exception e) {
            pages = 1;
        }
        map.put("page", pages);

        map.addAttribute("pageView", pageview);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/movies/tag/" + tagscode);
        //获取标签模块
        getTags(map, request);

        return result;
    }

    /**
     * 查询列表--首页
     *
     * @return
     */
    @RequestMapping(value = "/movies")
    //@UseCK
    public String list(ModelMap map,  HttpServletRequest request, HttpSession session) throws Exception {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "movies");

        String result = "/movies2";
        String whereSql = " where displayed is null ";


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
                whereSql += " and moviename like '%" + keyword + "%' or moviename like '%" + keyword2 + "%' ";
            } else {
                whereSql += " and moviename like '%" + keyword + "%' ";
            }


        }

        log.info("sql ---" + whereSql);
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        String orderby = request.getParameter("orderby");
        if (StringUtils.isNotEmpty(orderby)) {
            if ("hot".equals(orderby)) {
                order.put("hotindex", "desc");
            } else if ("latest".equals(orderby)) {
                order.put("id", "desc");
            }
            map.put("orderby", orderby);
        } else {
            order.put("addtime", "desc");
            order.put("id", "desc");
        }


        //获取pageview
        PageView<Movies> pageview = new PageView<Movies>(1, MoviesCount);
        QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
        List<Movies> resultlist = qr.getResultlist();

        //生成分页信息
        pageview.setQueryResult(qr);
        //处理标签列表
        handleTag(resultlist);

        map.put("page", 1);

        map.addAttribute("pageView", pageview);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/movies");

        //获取标签模块
        getTags(map, request);

        return result;
    }

    /**
     * 电影分页列表
     *
     * @param map
     * @param page
     * @param request
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/movies/page/{page}")
    public String listpage(ModelMap map, @PathVariable String page, HttpServletRequest request,
                           HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "movies");

        String result = "/movies2";
        String whereSql = " where displayed is  null ";
        
        
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
                whereSql += " and moviename like '%" + keyword + "%' or moviename like '%" + keyword2 + "%' ";
            } else {
                whereSql += " and moviename like '%" + keyword + "%' ";
            }


        }

        log.info("sql ---" + whereSql);
        String currentpage = page;
        //排序条件
        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        String orderby = request.getParameter("orderby");
        if (StringUtils.isNotEmpty(orderby)) {
            if ("hot".equals(orderby)) {
                order.put("hotindex", "desc");
            } else if ("latest".equals(orderby)) {
                order.put("id", "desc");
            }
            map.put("orderby", orderby);
        } else {
            order.put("addtime", "desc");
            order.put("id", "desc");
        }


        //获取pageview
        PageView<Movies> pageview = new PageView<Movies>(Integer.parseInt(currentpage), MoviesCount);
        QueryResult<Movies> qr = this.moviesManager.findByCondition(pageview, whereSql, order);
        List<Movies> resultlist = qr.getResultlist();

        //生成分页信息
        pageview.setQueryResult(qr);
        //处理标签列表
        handleTag(resultlist);

        map.put("page", page);

        map.addAttribute("pageView", pageview);
        map.addAttribute("list", resultlist);
        map.addAttribute("actionUrl", "/movies");

        //获取标签模块
        getTags(map, request);

        return result;
    }


    /**
     * 处理标签列表
     *
     * @param resultlist
     */
    private void handleTag(List<Movies> resultlist) {
        if (!CollectionUtils.isEmpty(resultlist)) {

            for (Movies m : resultlist) {
                String tag = m.getTag();
                String tagcode = m.getTagcode();
                if (StringUtils.isNotEmpty(tag) && StringUtils.isNotEmpty(tagcode)) {
                    String[] tags = tag.split(",");
                    String[] tagcodes = tagcode.split(",");
                    if (tags.length != tagcodes.length) {
                    	throw new NorthParkException(ResultEnum.Movie_Tag_Not_Match);
                    }
                    List<Map<String, String>> taglist = Lists.newArrayList();
                    for (int i = 0; i < tags.length; i++) {
                        Map<String, String> map = Maps.newHashMap();
                        map.put("tag", tags[i]);
                        map.put("tagcode", tagcodes[i]);
                        taglist.add(map);
                        map = null;
                    }
                    m.setTaglist(taglist);
                }

            }
        }
    }



    /**
     * 跳转到电影详情页面
     *
     * @param map
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/movies/post-{id}.html")
    public String postdetail(ModelMap map, @PathVariable String id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        if (StringUtils.isNotEmpty(id)) {
            //sql注入处理
            id = WAQ.forSQL().escapeSql(id);
            Movies  model = moviesManager.findMovies(Integer.valueOf(id));
            if(model!=null) {
                //页面描述
            	if(StringUtils.isNotEmpty(model.getDescription())) map.put("description", Jsoup.parse(model.getDescription()).select("p").text());
            	map.addAttribute("model", model);
            }
        }


        return "/movies-article";
    }


    //、、、、、、、、、、、、、、、、、、、、、、以上为用到的方法、、、、、、、、、、、、、、、、、、、、、、、、、、、

    /**
     * 获取标签模块
     *
     * @param map
     */
    private void getTags(ModelMap map, HttpServletRequest request) {
        List<Tags> tags = null;
        List<Map<String, Object>> movies_hot_list = null;

        //从redis取
        String tags_str = RedisUtil.getInstance().get("movies_tags");
        String movies_hot_list_str = RedisUtil.getInstance().get("movies_hot_list");
        if(StringUtils.isNotEmpty(tags_str) && StringUtils.isNotEmpty(movies_hot_list_str)) {
        	
        	movies_hot_list = JsonUtil.json2ListMap(movies_hot_list_str);
        	tags = JsonUtil.json2list(tags_str, Tags.class);
        }

        //从数据库取
        if (CollectionUtils.isEmpty(tags) && CollectionUtils.isEmpty(movies_hot_list)) {
            //获取标签

            tags = tagsManager.findByCondition(" where tagtype = '1' ").getResultlist();

            //获取热门电影
            String hotsql = "select id,moviename,color from bc_movies order by rand() desc limit 0,70";
            movies_hot_list = moviesManager.querySql(hotsql);

            RedisUtil.getInstance().set("movies_hot_list", JsonUtil.object2json(movies_hot_list), 24 * 60 * 60);
            RedisUtil.getInstance().set("movies_tags", JsonUtil.object2json(tags), 24 * 60 * 60);

        }

        
        map.put("movies_hot_list", movies_hot_list);
        map.put("movies_tags", tags);

    }


}
