
package cn.northpark.action;
/*
 *@author bruce
 *
 **/

import cn.northpark.annotation.RateLimit;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.NoteManager;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.manager.UserManager;
import cn.northpark.model.Note;
import cn.northpark.model.User;
import cn.northpark.query.NoteQuery;
import cn.northpark.query.condition.NoteQueryCondition;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import cn.northpark.vo.UserVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
@Slf4j
public class NoteAction {

    private final String LIST_ACTION = "redirect:/note/findAll";
    private final String LOGIN_ACTION = "redirect:/login";
    @Autowired
    private NoteManager noteManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private NoteQuery noteQuery;
    @Autowired
    private UserFollowManager userfollowManager;




    /**
     * 添加碎碎念保存
     *
     * @param note
     * @return
     */
    @RequestMapping("/addNote")
    public String addNote(Note note) {
        String rs = LIST_ACTION;
        if (StringUtils.isNotEmpty(String.valueOf(note.getUserid()))) {
            if (StringUtils.isEmpty(note.getOpened())) {
                note.setOpened("yes");
            }
            note.setCreate_time(TimeUtils.nowTime());

            //处理笔记和介绍
            String note_ = note.getNote();
            //note_ = "<p>"+note_+"</p>";
            note_ = note_.replaceAll("script", "urshit").replaceAll("alert", "caonima").replaceAll("location", "tiaonima");
            note.setNote(note_);
            StringBuilder sb = handleNotes(note_);
            note.setBrief(sb.toString());
            //end-------------------
            this.noteManager.addNote(note);
        } else {
            rs = LOGIN_ACTION;
        }
        return rs;
    }

    /**
     * 处理笔记
     *
     * @param note_
     * @return
     */
    private StringBuilder handleNotes(String note_) {
        note_.replaceAll("<p><br></p>", "<br><br>");
        StringBuilder sb = new StringBuilder();
        String str[] = note_.split("</p>");
        if (str.length >= 3) {
            sb.append(str[0].replace("<p>", "")).append("<br>");
            sb.append(str[1].replace("<p>", "")).append("<br>");
            sb.append(str[2].replace("<p>", "")).append("<br>");
        } else {
            String rp_ = note_.replaceAll("<p>", "").replaceAll("</p>", "");
            String br[] = rp_.split("<br>");
            if (br.length >= 3) {
                sb.append(br[0]).append("<br>");
                sb.append(br[1]).append("<br>");
                sb.append(br[2]).append("<br>");
            } else {
                String space[] = rp_.split(" ");
                for (int i = 0; i < space.length; i++) {
                    if (i != space.length - 1) {
                        space[i] += "<br>";
                    }
                    sb.append(space[i]);
                }
            }
        }
        return sb;
    }


    /**
     * 查看某人的碎碎念列表
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/viewNotes/{userid}")
    public String viewNotes(@PathVariable String userid) {

        return "redirect:/note/viewNotes/" + userid + "/page/1";
    }

    /**
     * 查看某人的碎碎念列表 分页
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/viewNotes/{userid}/page/{page}")
    public String viewNotesPages(ModelMap map, NoteQueryCondition condition, HttpServletRequest request,
                                 HttpServletResponse response, HttpSession session, @PathVariable String userid, @PathVariable String page) {
        //condition.setOpened("yes");
        String result = "/spacenote";
        if (StringUtils.isNotEmpty(userid)) {
            condition.setUserid(userid);
            map.addAttribute("userid", userid);
            User user = userManager.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);
        } else {
            condition.setUserid("空");
            map.addAttribute("userid", "");
        }
        String whereSql = noteQuery.getSql(condition);

        //定义pageView
        PageView<Note> pageView = new PageView<Note>(Integer.parseInt(page), MyConstant.MAX_RESULT);


        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("create_time", "desc");

        QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
                whereSql, order);

        List<Note> list = qrs.getResultList();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCreate_time().contains("-")) {
                String t = list.get(i).getCreate_time().substring(0, 10);
                list.get(i).setCreate_time(t);
            }
        }
        //触发分页计算
        pageView.setQueryResult(qrs);

        map.addAttribute("pageView", pageView);
        map.put("condition", condition);
        map.addAttribute("list", list);
        map.addAttribute("actionUrl", "/note/viewNotes/" + userid);

        //取得当前用户对作者的关注状态
        UserVO lo_user = RequestHolder.getUserInfo(request);
        if (lo_user != null) {
            String follow_id = String.valueOf(lo_user.getId());
            String author_id = userid;
            if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {
                int nums = userfollowManager.getCountByCondition(" where author_id = '" + author_id + "' and follow_id = '" + follow_id + "' ");
                if (nums > 0) {
                    map.put("gz", "ygz");
                }
            }

        }

        return result;
    }

    /**
     * 查看自己的所有碎碎念 列表 -分页
     *
     * @param map
     * @param condition
     * @param page
     * @param request
     * @param response
     * @param session
     * @param userid
     * @return
     */
    @RequestMapping(value = "/findAll/page/{page}")
    public String findAllPages(ModelMap map, NoteQueryCondition condition, @PathVariable String page, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session, String userid) {
        //condition.setOpened("yes");

        //取得当前用户
        UserVO user = RequestHolder.getUserInfo(request);
        if (user != null) {
            userid = String.valueOf(user.getId());
        }
        String result = "/note";
        if (StringUtils.isNotEmpty(userid)) {
            condition.setUserid(userid);
            map.addAttribute("userid", userid);
            map.put("MyInfo", user);
        } else {
            condition.setUserid("空");
            map.addAttribute("userid", "");
        }
        String whereSql = noteQuery.getSql(condition);


        //定义pageView
        PageView<Note> pageView = new PageView<Note>(Integer.parseInt(page), MyConstant.MAX_RESULT);

        LinkedHashMap<String, String> order = Maps.newLinkedHashMap();
        order.put("create_time", "desc");

        QueryResult<Note> qrs = this.noteManager.findByCondition(pageView,
                whereSql, order);

        //触发分页计算
        pageView.setQueryResult(qrs);

        List<Note> list = qrs.getResultList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCreate_time().contains("-")) {
                String t = list.get(i).getCreate_time().substring(0, 10);
                list.get(i).setCreate_time(t);
            }
        }

        map.addAttribute("pageView", pageView);
        map.put("condition", condition);
        map.addAttribute("list", list);
        map.addAttribute("actionUrl", "/note/findAll");

        return result;
    }


    /**
     * 查看自己的所有碎碎念 列表 -分页
     *
     * @return
     */
    @RequestMapping(value = "/findAll")
    public String findAll() {
        return "redirect:/note/findAll/page/1";
    }


    /**
     * 查看碎碎念 广场列表
     *
     * @param map
     * @param condition
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "")
    public String notePlazz(ModelMap map, NoteQueryCondition condition, HttpServletRequest request,
                            HttpServletResponse response, HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "note");
        condition.setOpened("yes");
        String result = "/story";
        String sql = noteQuery.getMixSql(condition);

        log.info("sql ---" + sql);

        //定义pageView
        PageView<List<Map<String, Object>>> pageView = new PageView<List<Map<String, Object>>>(1, MyConstant.MAX_RESULT);

        //获取分页结构不获取数据

        pageView = this.noteManager.getMixMapPage(pageView, sql);

        map.addAttribute("pageView", pageView);
        map.put("condition", condition);
        map.addAttribute("actionUrl", "/note");


        return result;
    }


    /**
     * 查看碎碎念 广场列表
     *
     * @param map
     * @param condition
     * @param request
     * @param response
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/page/{page}")
    public String notePlazzPage(ModelMap map, NoteQueryCondition condition, @PathVariable String page, HttpServletRequest request,
                                HttpServletResponse response, HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "note");
        condition.setOpened("yes");
        String result = "/story";
        String sql = noteQuery.getMixSql(condition);

        log.info("sql ---" + sql);
        int currentPage = Integer.parseInt(page);

        //定义pageView
        PageView<List<Map<String, Object>>> pageView = new PageView<List<Map<String, Object>>>(currentPage, MyConstant.MAX_RESULT);

        //获取分页结构不获取数据

        pageView = this.noteManager.getMixMapPage(pageView, sql);

        map.addAttribute("pageView", pageView);
        map.put("condition", condition);
        map.addAttribute("actionUrl", "/note");
        map.addAttribute("page", page);


        return result;
    }


    //异步分页查询story数据
    @RequestMapping(value = "/storyquery")
    @RateLimit
    public String notePlazzQuery(ModelMap map, HttpServletRequest request, NoteQueryCondition condition, HttpSession session, String userid) {
        String currentPage = request.getParameter("currentPage");

        condition.setOpened("yes");

        String sql = noteQuery.getMixSql(condition);

        //定义pageView
        PageView<List<Map<String, Object>>> pageView = new PageView<List<Map<String, Object>>>(Integer.parseInt(currentPage), MyConstant.MAX_RESULT);


        //根据分页仅仅获取数据
        List<Map<String, Object>> list = this.noteManager.findmixByCondition(pageView, sql);


        for (int i = 0; i < list.size(); i++) {
            //时间处理
            String create_time = (String) list.get(i).get("create_time"); //e:/yunlu/upload/1399976848969.jpg
            if (StringUtils.isNotEmpty(create_time)) {
                create_time = TimeUtils.getHalfDate(create_time);
            }
            list.get(i).put("create_time", create_time);

        }

        map.addAttribute("list", list);


        return "/page/story/storydata";
    }

    //异步删除笔记内容
    @RequestMapping(value = "/remove")
    @ResponseBody
    public Result<String> remove(ModelMap map, HttpServletRequest request, String id, HttpSession session) {

        String result = "success.";
        try {
            if (StringUtils.isNotEmpty(id)) {

                Note note = noteManager.findNote(Integer.valueOf(id));
                if (note != null) {
                    //取得当前用户
                    UserVO user = RequestHolder.getUserInfo(request);
                    if (user != null) {
                        if (user.getId().equals(note.getUserid())) {
                            //登陆者和作者一样 | 执行删除
                            noteManager.delNote(Integer.parseInt(id));
                            result = "success.";
                        }
                    }
                }
            }
        } catch (Exception e) {
            result = "opps,发生了异常.";
            log.error("note action------>", e);
        }

        return ResultGenerator.genSuccessResult(result);
    }


}
