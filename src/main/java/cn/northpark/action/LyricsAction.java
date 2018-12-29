
package cn.northpark.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.Desc;
import cn.northpark.annotation.Redis;
import cn.northpark.constant.BC_Constant;
import cn.northpark.constant.BC_Constant.RedisReturnType;
import cn.northpark.constant.ResultEnum;
import cn.northpark.exception.NorthParkException;
import cn.northpark.form.LyricsForm;
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.model.Lyrics;
import cn.northpark.model.User;
import cn.northpark.model.UserLyrics;
import cn.northpark.utils.FileUtils;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.RedisUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LyricsAction {


    private final String LIST_ACTION2 = "redirect:/cm/pcentral";

    @Autowired
    private LyricsManager lyricsManager;
    @Autowired
    private UserLyricsManager userlyricsManager;
    @Autowired
    private LyricsCommentManager plManager;
    @Autowired
    private LyricsZanManager lyricszanManager;

    /**
     * 跳转到添加最爱页面
     *
     * @return
     */
    @CheckLogin
    @RequestMapping("/lyrics/add")
    public String toAdd() {
        String result = "/page/user/lyricAdd";
        return result;
    }


    @RequestMapping("/lyrics/remove/{lyricsid}/{userlyricsid}")
    public String remove(@PathVariable("lyricsid") Integer lyricsid, String userid, @PathVariable("userlyricsid") Integer userlyricsid, HttpServletRequest request) {
    	User u = (User) request.getSession().getAttribute("user");
        userid = String.valueOf(u.getId());
        if (StringUtils.isNotEmpty(userid)) {
            lyricsManager.delLyrics(lyricsid);
            userlyricsManager.delUserLyrics(userlyricsid);
        }
        return LIST_ACTION2;
    }

    @RequestMapping("/lyrics/removes")
    public String removes(@RequestParam("ids") String ids, String userid) {
        String result = LIST_ACTION2;
        if (StringUtils.isNotEmpty(userid)) {
            String[] glbstr = ids.split(",");
            for (String s : glbstr) {
                UserLyrics userlyrics = this.userlyricsManager.findUserLyrics(Integer.parseInt(s));
                if (userlyrics != null) {//批量删除删除歌词表
                    Integer lyid = userlyrics.getLyricsid();
                    if (null != lyid && lyid > 0) {
                        this.lyricsManager.delLyrics(lyid);
                    }
                }
                this.userlyricsManager.delUserLyrics(Integer.parseInt(s));//批量删除关联表
            }
            result = LIST_ACTION2 + "?userid=" + userid;
        }
        return result;
    }

    @RequestMapping("/lyrics/update")
    public String update(Lyrics model, HttpServletRequest request, String oldpath, @RequestParam(value = "file", required = false) MultipartFile[] file, ModelMap map, String userid) {
        // 执行删除
        FileUtils.removeOldFile(oldpath, file);


        //执行上传
        List<String> filelist = FileUtils.commonUpload(file, FileUtils.suffix_album);
        //执行上传end

        if (filelist.size() > 0) {
            model.setAlbumImg(filelist.get(0));
        } else {
            model.setAlbumImg(oldpath);
        }
        model.setUpdatedate(TimeUtils.nowTime());
        this.lyricsManager.updateLyrics(model);

        return LIST_ACTION2;
    }


    /**
     * 保存最爱
     *
     * @param lyricsForm
     * @param userid
     * @param request
     * @param file
     * @param map
     * @return
     */
    @RequestMapping("/lyrics/addLyrics")
    @CheckLogin
    public String addLyrics(@Valid LyricsForm lyricsForm,BindingResult bindingResult,  HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile[] file) {

    	//验证表单
    	if(bindingResult.hasErrors()) {
    		log.error("【添加主题】 参数不正确,lyricsForm={}", lyricsForm);
    		throw new NorthParkException(ResultEnum.Lyrics_Param_Not_Valid, bindingResult.getFieldError().getDefaultMessage());
    	}
    	
        User u = (User) request.getSession().getAttribute("user");
        if (u != null) {
        	Lyrics model = new Lyrics();
            //上传
            String albumpath = "";
            List<String> filelist = FileUtils.commonUpload(file, FileUtils.suffix_album);
            if (filelist != null && filelist.size() > 0) {
                albumpath = filelist.get(0);
            }
            model.setTitle(lyricsForm.getTitle());
            model.setUpdatedate(TimeUtils.nowTime());
            model.setAlbumImg(albumpath);
            //处理标题请求名
            String titlecode = PinyinUtil.paraseStringToFormatPinyin(lyricsForm.getTitle(),BC_Constant.FormatSpilt.hengxian.getNamestr());
            model.setTitlecode(titlecode);
            this.lyricsManager.addLyrics(model);

            UserLyrics userlyrics = new UserLyrics();
            userlyrics.setLyricsid(model.getId());
            userlyrics.setUserid(u.getId());
            this.userlyricsManager.addUserLyrics(userlyrics);
        } 
        return LIST_ACTION2;
    }


    /**
     * 根据图片lrc主题异步获取所有喜爱者
     *
     * @param request
     * @param lyricsid
     * @param map
     * @param userid
     * @return
     */
    @RequestMapping("/lyrics/getMoreZan")
    @ResponseBody
    @Redis(returnType=RedisReturnType.string,field=" #lyricsid " ,expire= 7*24*60*60)
    @Desc("某个主题赞的人数：redis缓存7天刷新")
    public String getMoreZan(HttpServletRequest request, HttpServletResponse response, String lyricsid, ModelMap map) {
        response.setContentType("text/html; charset=UTF-8");
        //取得zan的人的列表
        String sql = "select b.id,b.tail_slug,b.username from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = ? ";
        List<Map<String, Object>> zanList = lyricszanManager.mixSqlQuery(sql ,lyricsid);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zanList.size(); i++) {
            String href = "";
            String ym = (String) zanList.get(i).get("tail_slug");
            if (StringUtils.isNotEmpty(ym)) {
                href = "/people/" + ym;
            } else {
                href = "/cm/detail/" + zanList.get(i).get("id");
            }
            sb.append("<span><a href = '")
	            .append(href).append("' title= '" )
	            .append(zanList.get(i).get("username"))
	            .append("' >")
	            .append( zanList.get(i).get("username") )
	            .append("</a> &nbsp;</span>");
        }

        log.info(sb.toString());
        return sb.toString();
    }

    //异步分页查询#comment'数据
    @RequestMapping(value = "/lyrics/commentQuery")
    public String commentQuery(ModelMap map, HttpServletRequest request, HttpSession session, String userid) {
        String page = request.getParameter("currentpage");
        
        if(StringUtils.isEmpty(page)) page = "1";
        
        String lyricsid = request.getParameter("lrcid");


        //取得 评论 的列表
        String sql_ = "select b.username,b.tail_slug,b.email,b.headpath,b.headspan,b.headspanclass,a.* from bc_lyrics_comment a join bc_user b on a.userid = b.id where a.lyricsid = '" + lyricsid + "' order by a.create_time desc";

        //每页展示6条  获取分页信息
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(Integer.parseInt(page), 6);

        pageview = this.plManager.getMixMapPage(pageview, sql_);


        //  获取分页数据
        List<Map<String, Object>> plList = this.plManager.findmixByCondition(pageview, sql_);

        
        //批量处理时间
        if(!CollectionUtils.isEmpty(plList)) {
        	plList.forEach(i ->{
        		i.put("create_time", TimeUtils.formatToNear((String) i.get("create_time")));
        	});
        }
        map.put("plList", plList);

        map.put("pageview", pageview);


        //分页到尽头结束了
        if (pageview.getTotalpage() <= Integer.parseInt(page)) {
            map.put("tail", "tail");
        }


        return "/page/zancmt/loadcomment";
    }


    /**
     * 最爱主题页面-评论、爱上列表、点赞列表
     *
     * @param request
     * @param lyricsid
     * @param map
     * @param userid
     * @return
     */
    @RequestMapping("/love/{titlecode}.html")
    public String comment(HttpServletRequest request, @PathVariable String titlecode, ModelMap map, String userid) {
        String result = "/zancmmet";

        //取得歌词/图片的信息 +取得上传者的信息+取得zan的人数+取得评论的条数
        String sqlmap = "SELECT u.tail_slug as by_tail_slug, u.id as by_id, u.username as by_username,"
                + " lrc.id as lrc_id, lrc.albumImg as lrc_albumImg, lrc.title as lrc_title,lrc.titlecode, "
                + " lrc.updatedate as lrc_updatedate, lrc.zan as zanNum, lrc.pl as plNum "
                + "FROM bc_user_lyrics ul JOIN bc_user u ON ul.userid = u.id "
                + "join bc_lyrics lrc on lrc.id = ul.lyricsid where lrc.titlecode = ?";

        List<Map<String, Object>> querySql = userlyricsManager.querySql(sqlmap, titlecode);

        Integer lyricsid = 0;
        if (!CollectionUtils.isEmpty(querySql)) {
            map.put("datamap", querySql.get(0));
            lyricsid = (Integer) querySql.get(0).get("lrc_id");
        }

        //取得zan的人的列表
        String sql = "select b.id,b.tail_slug,b.username  from bc_lyrics_zan a join bc_user b on a.userid = b.id where a.lyricsid = '" + lyricsid + "' limit 0 , 10 ";
        List<Map<String, Object>> zanList = lyricszanManager.mixSqlQuery(sql);
        map.put("zanList", zanList);

        //取得谁爱上谁的一个列表 redis
        List<Map<String, Object>> loveList = getRandomLoveList();

        Collections.shuffle(loveList);
        map.put("loveList", loveList);


        //取得当前用户对这个主题的赞的状态
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer uid = user.getId();

            Integer num = lyricszanManager.countHql(" where userid=" + uid + " and lyricsid=" + lyricsid);

            if (num > 0) {
                map.put("yizan", "yizan");
            }

        }

        return result;
    }


	/**
	 * 取得谁爱上谁的一个列表
	 * @return
	 */
	private List<Map<String, Object>> getRandomLoveList() {
		List<Map<String, Object>> loveList = null;
		String str = RedisUtil.get("loveList");
		if(StringUtils.isNotEmpty(str)) {
			loveList = JsonUtil.getListMap(str);
		}
		
		if(CollectionUtils.isEmpty(loveList)) {
			//取得谁爱上谁的一个列表
			String sql_2 = "select b.id as userid,b.tail_slug,b.username,b.email,b.headpath,b.headspan,b.headspanclass,c.id as lyricsid,c.title,c.titlecode from bc_lyrics_zan a join bc_user b on a.userid = b.id join bc_lyrics c on a.lyricsid = c.id order by rand() desc limit 0 , 50 ";
			loveList = lyricszanManager.mixSqlQuery(sql_2);
			if(!CollectionUtils.isEmpty(loveList)) { 
				RedisUtil.set("loveList", JsonUtil.object2json(loveList), 24 * 60 * 60);
			}
		}
		return loveList;
	}


    /**
     * 最爱分页查询 【跳转】
     *
     * @param map
     * @param condition
     * @param page
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/love/page/{page}")
    public String listpage(ModelMap map, @PathVariable String page, HttpServletRequest request,
                           HttpServletResponse response, HttpSession session) {

        //获取域名+tab{selection}
        session.removeAttribute("tabs");
        session.setAttribute("tabs", "pic");

        User u = (User) session.getAttribute("user");

        String userid = (u == null ? "" : String.valueOf(u.getId()));

        //定义pageview
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(Integer.parseInt(page), MyConstant.MAXRESULT);

        //获取分页结构不获取数据

        pageview = this.userlyricsManager.getMixMapPage(pageview, userid);

        map.addAttribute("pageView", pageview);
        map.addAttribute("actionUrl", "/love");
        map.addAttribute("page", page);

        return "/welcome";
    }

    /**
     * 最爱查询 【跳转】
     *
     * @param map
     * @param condition
     * @param request
     * @param response
     * @param session
     * @param userid
     * @return
     */
    @RequestMapping(value = "/love")
    public String list(ModelMap map, HttpServletRequest request,
                       HttpSession session, String userid) {
        session.removeAttribute("tabs");
        session.setAttribute("tabs", "pic");

        User u = (User) session.getAttribute("user");

        userid = (u == null ? "" : String.valueOf(u.getId()));


        //定义pageview
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(1, MyConstant.MAXRESULT);

        //获取分页结构不获取数据

        pageview = this.userlyricsManager.getMixMapPage(pageview, userid);


        map.addAttribute("pageView", pageview);
        map.addAttribute("actionUrl", "/love");


        return "/welcome";
    }


    /**
     * 异步分页查询love'数据
     *
     * @param map
     * @param request
     * @param session
     * @param userid
     * @return
     */
    @RequestMapping(value = "/lovequery")
    public String lovequery(ModelMap map, HttpServletRequest request, HttpSession session, String userid) {
        String currentpage = request.getParameter("currentpage");

        User u = (User) session.getAttribute("user");

        userid = (u == null ? "" : String.valueOf(u.getId()));


        //定义pageview
        PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String, Object>>>(Integer.parseInt(currentpage), MyConstant.MAXRESULT);


        //获取分页数据
        List<Map<String, Object>> lovelist = this.userlyricsManager.getMixMapData(pageview, userid);


        if (!CollectionUtils.isEmpty(lovelist)) {

        	lovelist.forEach(item ->{
        		//处理标题截断
        		String title = (String) item.get("title");
        		if(StringUtils.isNotEmpty(title)) item.put("cuttitle", HTMLParserUtil.CutString(title, 12));

        		//处理日期显示格式
        		String updatedate = (String) item.get("updatedate");
        		if (StringUtils.isNotEmpty(updatedate)) item.put("engDate", TimeUtils.parse2EnglishDate(updatedate));
        	});
        }


        map.addAttribute("lovelist", lovelist);


        return "/page/love/lovedata";
    }


}
