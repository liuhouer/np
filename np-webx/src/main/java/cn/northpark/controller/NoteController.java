
package cn.northpark.controller;
/*
 *@author bruce
 *
 **/

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.MyConstant;
import cn.northpark.model.Note;
import cn.northpark.model.User;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.NoteService;
import cn.northpark.service.UserService;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.safe.WAQ;
import cn.northpark.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
@Slf4j
public class NoteController {

    private final String LIST_ACTION = "redirect:/note/findAll";
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    /**
     * 添加碎碎念保存
     *
     * @param note
     * @return
     */
    @RequestMapping("/addNote")
    @CheckLogin
    public String addNote(Note note) {
        if (StringUtils.isNotEmpty(String.valueOf(note.getUserid()))) {
            if (StringUtils.isEmpty(note.getOpened())) {
                note.setOpened("yes");
            }
            note.setCreateTime(TimeUtils.nowTime());

            //处理笔记和介绍
            String note_ = note.getNote();
            //note_ = "<p>"+note_+"</p>";
            note_ = note_.replaceAll("script", "urshit").replaceAll("alert", "caonima").replaceAll("location", "tiaonima");
            note.setNote(note_);
            StringBuilder sb = handleNotes(note_);
            note.setBrief(sb.toString());
            //end-------------------
            this.noteService.addNote(note);
        }
        return LIST_ACTION;
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
    public String viewNotes(ModelMap map,
                            HttpServletRequest request,
                            @PathVariable String userid
                            ) {

        userid = WAQ.forSQL().escapeSql(userid);

        String whereSql = " where opened = 'yes' and userid = '"+userid+"' ";

        if (StringUtils.isNotEmpty(userid)) {
            map.addAttribute("userid", userid);
            User user = userService.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);
        } else {
            map.addAttribute("userid", "");
        }

        //排序条件
        String orderBy = " create_time desc";

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        List<Note> result_list = noteService.findByCondition(whereSql,orderBy);
        for (int i = 0; i < result_list.size(); i++) {
            if (result_list.get(i).getCreateTime().contains("-")) {
                String t = result_list.get(i).getCreateTime().substring(0, 10);
                result_list.get(i).setCreateTime(t);
            }
        }
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/note/viewNotes/" + userid);

        //取得当前用户对作者的关注状态
        UserVO lo_user = RequestHolder.getUserInfo(request);
        if (lo_user != null) {
            String follow_id = String.valueOf(lo_user.getId());
            String author_id = userid;
            if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                List<Map<String, Object>> ygz_nums_list = NPQueryRunner.query(ygz_nums_sql, new MapListHandler(), author_id, follow_id);
                String nums = ygz_nums_list.get(0).get("nums").toString();
                if (Integer.parseInt(nums) > 0) {
                    map.put("gz", "ygz");
                }
            }

        }

        return "spacenote";
    }

    /**
     * 查看某人的碎碎念列表 分页
     *
     * @param userid
     * @return
     */
    @RequestMapping(value = "/viewNotes/{userid}/page/{page}")
    public String viewNotesPages(ModelMap map,
                                 HttpServletRequest request,
                                 @PathVariable String userid,
                                 @PathVariable Integer page) {

        userid = WAQ.forSQL().escapeSql(userid);

        String whereSql = " where opened = 'yes' and userid = '"+userid+"' ";

        if (StringUtils.isNotEmpty(userid)) {
            map.addAttribute("userid", userid);
            User user = userService.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);
        } else {
            map.addAttribute("userid", "");
        }

        //排序条件
        String orderBy = " create_time desc";

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        List<Note> result_list = noteService.findByCondition(whereSql,orderBy);
        for (int i = 0; i < result_list.size(); i++) {
            if (result_list.get(i).getCreateTime().contains("-")) {
                String t = result_list.get(i).getCreateTime().substring(0, 10);
                result_list.get(i).setCreateTime(t);
            }
        }
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("page", page);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/note/viewNotes/" + userid);

        //取得当前用户对作者的关注状态
        UserVO lo_user = RequestHolder.getUserInfo(request);
        if (lo_user != null) {
            String follow_id = String.valueOf(lo_user.getId());
            String author_id = userid;
            if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                List<Map<String, Object>> ygz_nums_list = NPQueryRunner.query(ygz_nums_sql, new MapListHandler(), author_id, follow_id);
                String nums = ygz_nums_list.get(0).get("nums").toString();
                if (Integer.parseInt(nums) > 0) {
                    map.put("gz", "ygz");
                }
            }

        }

        return "spacenote";
    }

    /**
     * 查看自己的所有碎碎念 列表 -分页
     *
     * @param map
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAll/page/{page}")
    @CheckLogin
    public String findAllPages(ModelMap map,
                               @PathVariable Integer page,
                               HttpServletRequest request) {

        UserVO userInfo = RequestHolder.getUserInfo(request);

        String whereSql = " where opened = 'yes' and userid = '"+userInfo.getId()+"' ";

        map.addAttribute("userid", userInfo.getId());
        map.put("MyInfo", userInfo);


        //定义pageInfo
        //排序条件
        String orderBy = " create_time desc";

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        List<Note> result_list = noteService.findByCondition(whereSql,orderBy);
        for (int i = 0; i < result_list.size(); i++) {
            if (result_list.get(i).getCreateTime().contains("-")) {
                String t = result_list.get(i).getCreateTime().substring(0, 10);
                result_list.get(i).setCreateTime(t);
            }
        }
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/note/findAll");

        return "note";
    }


    /**
     * 查看自己的所有碎碎念 列表 -分页
     *
     * @return
     */
    @RequestMapping(value = "/findAll")
    @CheckLogin
    public String findAll(ModelMap map,
                          HttpServletRequest request
                          ) {

        UserVO userInfo = RequestHolder.getUserInfo(request);

        String whereSql = " where opened = 'yes' and userid = '"+userInfo.getId()+"' ";

        map.addAttribute("userid", userInfo.getId());
        map.put("MyInfo", userInfo);

        //定义pageInfo
        //排序条件
        String orderBy = " create_time desc";

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        List<Note> result_list = noteService.findByCondition(whereSql,orderBy);
        for (int i = 0; i < result_list.size(); i++) {
            if (result_list.get(i).getCreateTime().contains("-")) {
                String t = result_list.get(i).getCreateTime().substring(0, 10);
                result_list.get(i).setCreateTime(t);
            }
        }
        PageInfo pageInfo = new PageInfo(result_list);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("list", result_list);
        map.addAttribute("actionUrl", "/note/findAll");

        return "note";
    }


    /**
     * 查看碎碎念 广场列表
     *
     * @param map
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "")
    public String notePlazz(ModelMap map,
                            HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "note");

        PageHelper.startPage(1,MyConstant.MAX_RESULT);
        List<Map<String, Object>> mapList = noteService.querySqlMap(getPlazzSQL().toString());
        PageInfo pageInfo = new PageInfo(mapList);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("actionUrl", "/note");

        return "story";
    }


    /**
     * 查看碎碎念 广场列表
     *
     * @param map
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/page/{page}")
    public String notePlazzPage(ModelMap map,
                                @PathVariable Integer page,
                                HttpSession session) throws IOException {

        session.removeAttribute("tabs");
        session.setAttribute("tabs", "note");

        PageHelper.startPage(page,MyConstant.MAX_RESULT);
        List<Map<String, Object>> mapList = noteService.querySqlMap(getPlazzSQL().toString());
        PageInfo pageInfo = new PageInfo(mapList);

        map.addAttribute("pageInfo", pageInfo);
        map.addAttribute("page", page);
        map.addAttribute("actionUrl", "/note");
        return "story";
    }


    //异步分页查询story数据
    @RequestMapping(value = "/storyquery")
    @RateLimit
    public String notePlazzQuery(ModelMap map, HttpServletRequest request) {
        String currentPage = request.getParameter("currentPage");


        PageHelper.startPage(Integer.parseInt(currentPage),MyConstant.MAX_RESULT);
        List<Map<String, Object>> list = noteService.querySqlMap(getPlazzSQL().toString());

        for (int i = 0; i < list.size(); i++) {
            //时间处理
            String create_time = (String) list.get(i).get("create_time");
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

                Note note = noteService.findNote(Integer.valueOf(id));
                if (note != null) {
                    //取得当前用户
                    UserVO user = RequestHolder.getUserInfo(request);
                    if (user != null) {
                        if (user.getId().equals(note.getUserid())) {
                            //登陆者和作者一样 | 执行删除
                            noteService.delNote(Integer.parseInt(id));
                            result = "success.";
                        }
                    }
                }
            }
        } catch (Exception e) {
            result = "opps,发生了异常.";
            log.error("note action 异步删除笔记内容------>", e);
        }

        return ResultGenerator.genSuccessResult(result);
    }

    @NotNull
    private StringBuilder getPlazzSQL() {
        StringBuilder sql = new StringBuilder("SELECT a.id as noteid,a.brief as brief ,a.note as note,a.opened as openid,a.create_time as create_time,a.userid as userid,b.username as username,b.tail_slug as tail_slug,b.head_path as head_path ,b.email as email,b.head_span,b.head_span_class "
                + " FROM                                   	"
                + " bc_note a                              	"
                + " inner JOIN bc_user  b on a.userid = b.id where 1=1 ");

        sql.append(" and a.opened = 'yes' ");
        sql.append(" order by a.create_time desc ");
        return sql;
    }


}
