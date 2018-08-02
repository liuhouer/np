
package cn.northpark.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.EqManager;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.NoteManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.model.Eq;
import cn.northpark.query.NoteQuery;
import cn.northpark.query.condition.NoteQueryCondition;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;


/**
 * @author zhangyang
 * 处理首页的内容
 * 所有内容	
 * 从redis缓存获取
 * @date 2016-10-08 去掉缓存|内存占用有点大
 */
@Controller
public class DashAction {

    @Autowired
    private UserLyricsManager userlyricsManager;
    @Autowired
    private NoteManager noteManager;
    @Autowired
    private NoteQuery noteQuery;
    @Autowired
    private EqManager eqManager;
    @Autowired
    private MoviesManager moviesManager;
    
    /**
     * 跳转微信1
     */
    @RequestMapping("/weixin1")
    public String weixin1(ModelMap map) {
    	
    	return "/weixin1";
    }


    /**
     * 首页
     */
    @RequestMapping("/")
    public String dashborard(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

        //slider
        request.getSession().removeAttribute("tabs");

        //if www return no www
        String getDoamin = request.getServerName();
        if (getDoamin.startsWith("www")) {
            response.sendRedirect("http://" + BC_Constant.Domain);
        }

        return "/dashboard";

    }


    /**
     * 关于Northpark
     */
    @RequestMapping("/about.html")
    public String about(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {

        return "/about";

    }

//    /**
//     * 网站地图
//     */
//    @RequestMapping("/sitemap/{tag}")
//    public String sitemap(HttpServletRequest request, HttpServletResponse response, ModelMap map, @PathVariable("tag") String tag) throws Exception {
//        List<String> tags = Arrays.asList("eq", "love", "movies", "poem", "soft", "vps");
//        boolean contains = tags.contains(tag);
//        if (contains) {
//            return "/sitemap-" + tag;
//        } else {
//            return null;
//        }
//    }

    

    //异步获取首页的love数据
    @RequestMapping(value = "/dash/getLove")
    public String getLove(ModelMap map) {

        pushLove2Map(map);

        return "/page/love/lovedata";
    }

    //异步获取首页的《碎碎念》数据
    @RequestMapping(value = "/dash/getNote")
    public String getNote(ModelMap map) {


        pushNote2Map(map);


        return "/page/dash/notedata";
    }

    //异步获取首页的《情圣》数据
    @RequestMapping(value = "/dash/getRomeo")
    public String getRomeo(ModelMap map) {

        pushEQ2Map(map);


        return "/page/dash/romeodata";
    }

    //异步获取首页的《电影》数据
    @RequestMapping(value = "/dash/getMovies")
    public String getMovies(ModelMap map) {


        pushMovies2Map(map);


        return "/page/dash/moviesdata";
    }


    /**
     * @param map
     */
    public void pushMovies2Map(ModelMap map) {
        //取出热门电影


        String msql = "select id,moviename from bc_movies order by rand() limit 1,24";
        List<Map<String, Object>> movieslist = moviesManager.querySqlMap(msql);

        map.addAttribute("movieslist", movieslist == null ? "" : movieslist);
    }


    /**
     * @param map
     */
    public void pushEQ2Map(ModelMap map) {
        //取出一部分情圣日记

        String eqsql = "select * from bc_eq  where date ='2016-07-21' or date ='2016-07-19' or date ='2016-07-15' order by date desc";
        List<Eq> eqlist = this.eqManager.querySql(eqsql);

        map.addAttribute("eqlist", eqlist == null ? "" : eqlist);
    }


    /**
     * 把首页的日记数据查询出来并且添加到缓存里去
     *
     * @param map
     */
    public void pushNote2Map(ModelMap map) {
        //取出一部分日记

        NoteQueryCondition notecondition = new NoteQueryCondition();
        notecondition.setOpened("yes");
        String noteSql = noteQuery.getMixSql(notecondition);
        noteSql = noteSql.replace("order by a.createtime desc", "order by a.id ");
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(1, 16);
        List<Map<String, Object>> notelist = this.noteManager.findmixByCondition(pageview, noteSql);

        for (int i = 0; i < notelist.size(); i++) {
            //时间处理
            String createtime = (String) notelist.get(i).get("createtime"); //e:/yunlu/upload/1399976848969.jpg
            if (StringUtils.isNotEmpty(createtime)) {
                createtime = TimeUtils.getHalfDate(createtime);
            }
            notelist.get(i).put("createtime", createtime);

        }


        map.addAttribute("notelist", notelist);
    }


    /**
     * 把首页的love数据查询出来并且添加到缓存里去
     *
     * @param map
     */
    public void pushLove2Map(ModelMap map) {
        //取出一部分love数据
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(1, MyConstant.MAXRESULT);
        List<Map<String, Object>> lovelist = this.userlyricsManager.getMixMapData(pageview, "");

        if (!CollectionUtils.isEmpty(lovelist)) {
            for (int i = 0; i < lovelist.size(); i++) {
                Map<String, Object> map2 = lovelist.get(i);

                //处理标题截断
                String title = (String) map2.get("title");
                String cutString = HTMLParserUtil.CutString(title, 12);
                map2.put("cuttitle", cutString);

                //处理日期显示格式
                String updatedate = (String) map2.get("updatedate");
                if (StringUtils.isNotEmpty(updatedate)) {
                    String engDate = TimeUtils.parse2EnglishDate(updatedate);
                    map2.put("engDate", engDate);
                }
            }
        }


        map.addAttribute("lovelist", lovelist);
    }


}
