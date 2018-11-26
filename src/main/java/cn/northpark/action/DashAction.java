
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
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.RedisUtil;
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
    @RequestMapping("/donate")
    public String weixin1(ModelMap map) {
    	
    	return "/donateMe";
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
    	List<Map<String, Object>> home_movieslist = null;
    	
    	//从redis取
    	String str = RedisUtil.get("home_movieslist");
		if(StringUtils.isNotEmpty(str)) {
			home_movieslist = JsonUtil.getListMap(str);
		}

		
		//从数据库取 :1天刷新
    	if(CollectionUtils.isEmpty(home_movieslist)) {
    		
    		String msql = "select id,moviename from bc_movies order by rand() limit 1,24";
    		home_movieslist = moviesManager.querySqlMap(msql);
    		
    		RedisUtil.set("home_movieslist", JsonUtil.object2json(home_movieslist), 24 * 60 * 60);
    	    
    	}

        map.addAttribute("movieslist", home_movieslist);
    }


    /**
     * @param map
     */
    public void pushEQ2Map(ModelMap map) {
        //取出一部分情圣日记
    	
    	List<Eq> home_eqlist = null;
    	
    	//从redis取
    	String str = RedisUtil.get("home_eqlist");
		if(StringUtils.isNotEmpty(str)) {
			home_eqlist = JsonUtil.json2list(str, Eq.class);
		}

		//从数据库取 :1天刷新
		if(CollectionUtils.isEmpty(home_eqlist)) {
			String eqsql = "select * from bc_eq  where date ='2016-07-21' or date ='2016-07-19' or date ='2016-07-15' order by date desc";
			home_eqlist  = this.eqManager.querySql(eqsql);
			RedisUtil.set("home_eqlist", JsonUtil.object2json(home_eqlist));
	    	
			
		}
        map.addAttribute("eqlist", home_eqlist);
    }


    /**
     * 把首页的日记数据查询出来并且添加到缓存里去
     *
     * @param map
     */
    public void pushNote2Map(ModelMap map) {
        //取出一部分日记

    	
    	List<Map<String, Object>> home_notelist = null;
    	
    	//从redis取
    	String str = RedisUtil.get("home_notelist");
		if(StringUtils.isNotEmpty(str)) {
			home_notelist = JsonUtil.getListMap(str);
		}	
		
		//从数据库取 :1天刷新
		if(CollectionUtils.isEmpty(home_notelist)) {
			NoteQueryCondition notecondition = new NoteQueryCondition();
			notecondition.setOpened("yes");
			String noteSql = noteQuery.getMixSql(notecondition);
			noteSql = noteSql.replace("order by a.createtime desc", "order by a.id ");
			PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(1, 16);
			List<Map<String, Object>> notelist = this.noteManager.findmixByCondition(pageview, noteSql);

			//时间处理
			notelist.forEach(item ->{
				String createtime = (String) item.get("createtime");
				if (StringUtils.isNotEmpty(createtime)) item.put("createtime", TimeUtils.getHalfDate(createtime));
			});
			
			
			RedisUtil.set("home_notelist", JsonUtil.object2json(home_notelist));


		}
      

        map.addAttribute("notelist", home_notelist);
    }


    /**
     * 把首页的love数据查询出来并且添加到缓存里去
     *
     * @param map
     */
    public void pushLove2Map(ModelMap map) {
    	
    	List<Map<String, Object>> home_lovelist = null;
    	
    	//从redis取
    	String str = RedisUtil.get("home_lovelist");
		if(StringUtils.isNotEmpty(str)) {
			home_lovelist = JsonUtil.getListMap(str);
		}
    	
		//从数据库取
    	if(CollectionUtils.isEmpty(home_lovelist)) {
    		//取出一部分love数据
            PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(1, MyConstant.MAXRESULT);
            home_lovelist = this.userlyricsManager.getMixMapData(pageview, "");

            if (!CollectionUtils.isEmpty(home_lovelist)) {
            	
            	home_lovelist.forEach(item ->{
            		//处理标题截断
            		String title = (String) item.get("title");
            		if(StringUtils.isNotEmpty(title)) item.put("cuttitle", HTMLParserUtil.CutString(title, 12));
            		
            		 //处理日期显示格式
                    String updatedate = (String) item.get("updatedate");
                    if (StringUtils.isNotEmpty(updatedate)) item.put("engDate", TimeUtils.parse2EnglishDate(updatedate));
            	});
            }

            RedisUtil.set("home_lovelist", JsonUtil.object2json(home_lovelist),2 * 24 * 60 * 60 );
            
            
    	}
		
        

        map.addAttribute("lovelist", home_lovelist);
    }


}
