
package cn.northpark.controller;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.CookieConstant;
import cn.northpark.constant.RedisConstant;
import cn.northpark.model.*;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.result.Result;
import cn.northpark.result.ResultEnum;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.ResetService;
import cn.northpark.service.UserFollowService;
import cn.northpark.service.UserProfileService;
import cn.northpark.service.UserService;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.*;
import cn.northpark.utils.encrypt.EnDecryptUtils;
import cn.northpark.utils.safe.WAQ;
import cn.northpark.vo.UserVO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserProfileService profileService;
    @Autowired
    UserFollowService ufService;
    @Autowired
    ResetService resetService;


    public static ImmutableList<String> PASS_ID =
            ImmutableList.of("507723", "508200", "518821", "519802", "518518", "507630");


    /**
     * @param request
     * @return 0、1 【0：没有 | 1：有】
     */
    @RequestMapping("/cm/loginFlag")
    @ResponseBody
    public Result<String> loginFlag(HttpServletRequest request) {

        //判断是否已登录
        UserVO user = RequestHolder.getUserInfo(request);
        if (user != null) {
            return ResultGenerator.genSuccessResult("1");
        }

        return ResultGenerator.genSuccessResult("0");

    }

    /**
     * @param email
     * @return 0、1 【0：没有 | 1：有】
     * @ 判断email的存在性
     */
    @RequestMapping("/cm/emailFlag")
    @ResponseBody
    @RateLimit
    public Result<String> emailFlag(String email) {
        List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_user", "  email = '" + email + "' ");

        if (CollectionUtils.isNotEmpty(list)) {
            return ResultGenerator.genSuccessResult("exist");

        } else {
            return ResultGenerator.genSuccessResult("not_exist");
        }

    }

    /**
     * @param request
     * @param response
     * @param map
     * @return 0、1 【0：没有 | 1：有】
     * @ 判断tail_slug的存在性
     */
    @RequestMapping("/cm/tailFlag")
    @ResponseBody
    @RateLimit
    public Result<String> tailFlag(HttpServletRequest request, HttpServletResponse response, ModelMap map, String tail) {
        List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_user", " tail_slug = '" + tail + "' ");

        if (CollectionUtils.isNotEmpty(list)) {
            return ResultGenerator.genSuccessResult("exist");

        } else {
            return ResultGenerator.genSuccessResult("not_exist");
        }

    }

    /**
     * 发送”修改密码“邮件
     */
    @RequestMapping("/cm/resetEmail")
    @ResponseBody
    @RateLimit
    public Result<String> resetEmail(String email) throws ParseException {
        String userid = "";
        List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_user", "  email = '" + email + "' ");
        if (!CollectionUtils.isEmpty(list)) {
            userid = String.valueOf(list.get(0).get("id"));
        }
        //添加重置的信息============================================
        Reset reset = new Reset();
        reset.setCreatedTime(TimeUtils.nowTime());
        String code = IDUtils.getInstance().geneInt();
        reset.setAuthCode(code);
        reset.setInvalidTime(TimeUtils.getDayAfter(TimeUtils.nowTime()));
        reset.setIsEmailAuthed(0);

        reset.setUserId(Integer.parseInt(userid));
        resetService.addReset(reset);
        //添加重置的信息============================================


        //发送消息通知发邮件
        try {
            EmailUtils.getInstance().changePwd(email, userid, code);
        } catch (Exception e) {
            log.error("重置密码邮件错误========>{}", e);
        }
        return ResultGenerator.genSuccessResult("ok");
    }

    /**
     * 重置密码,从邮箱点击进来
     */
    @RequestMapping("/cm/reset")
    public String reset(HttpServletRequest request,
                        ModelMap map,
                        String userid,
                        String auth_code) throws ParseException {

        List<Reset> gtList = resetService.findByCondition(" where user_id='" + userid + "' and auth_code='" + auth_code + "' order by created_time desc ", "");
        try {
            if (!CollectionUtils.isEmpty(gtList)) {
                Reset gt_model = gtList.get(0);
                if (gt_model.getAuthCode().equals(auth_code)) {// Email认证通过
                    if (StringUtils.isNotEmpty(gt_model.getInvalidTime())) {// 设置了失效时间
                        if (TimeUtils.isInvalid(TimeUtils.nowTime(),
                                gt_model.getInvalidTime()) && (0 == gt_model.getIsEmailAuthed())) {// 时间未过期
                            User user = userService.findUser(Integer.parseInt(userid));
                            if (user != null) {
                                user.setPassword(EnDecryptUtils.diyEncrypt(auth_code));
                                userService.updateUser(user);

                                request.getSession().setAttribute("user", user);
                                //更新数据
                                gt_model.setIsEmailAuthed(1);
                                resetService.updateReset(gt_model);
                                map.addAttribute("msg", "success");
                                return "/forget";
                            }
                        } else {
                            map.addAttribute("msg", "is_old");
                            return "/forget";
                        }
                    } else {// 没有设置失效时间
                        if (0 == gt_model.getIsEmailAuthed()) {
                            User user = userService.findUser(Integer.parseInt(userid));
                            if (user != null) {
                                user.setPassword(EnDecryptUtils.diyEncrypt(auth_code));
                                userService.updateUser(user);
                                request.getSession().setAttribute("user", user);
                                //更新数据
                                gt_model.setIsEmailAuthed(1);
                                resetService.updateReset(gt_model);
                                map.addAttribute("msg", "success");
                                return "/forget";
                            }
                        } else {
                            map.addAttribute("msg", "is_old");
                            return "/forget";
                        }
                    }
                }
            } else {// 已失效，重新获取验证码
                map.addAttribute("msg", "invalid");
                return "/forget";
            }
        } catch (Exception e) {
            log.error("reset from email link ------>", e);
        }

        map.addAttribute("msg", "fku");
        return "/forget";
    }


    //成为粉丝
    @RequestMapping("/cm/follow")
    @ResponseBody
    @RateLimit
    public Result<String> follow(ModelMap map, String author_id, String follow_id) {
        String msg = "success";

        try {

            String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
            List<Map<String, Object>> ygz_nums_list = NPQueryRunner.query(ygz_nums_sql, new MapListHandler(), author_id, follow_id);
            Object nums = ygz_nums_list.get(0).get("nums");

            if (Integer.valueOf(String.valueOf(nums)) == 0) {
                UserFollow uf = new UserFollow();
                uf.setAuthorId(Integer.parseInt(author_id));
                uf.setFollowId(Integer.parseInt(follow_id));
                uf.setStatus(1);
                uf.setCreateTime(TimeUtils.nowTime());
                if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {
                    ufService.addUserFollow(uf);

                    try {
                        //=================================异步消息提醒====================================================

                        ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                        threadPoolExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                User follower = userService.findUser(Integer.parseInt(follow_id));


                                //判断主题类型

                                NotifyEnum match = NotifyEnum.FOLLOW;

                                //提醒系统赋值
                                NotifyRemind nr = new NotifyRemind();

                                //common
                                nr.setSenderID(follow_id);
                                nr.setSenderName(follower.getUsername());
                                nr.setObjectID(null);
                                nr.setObject(null);
                                nr.setObjectLinks("/cm/channel/" + follow_id);
                                nr.setMessage("关注了你");
                                nr.setStatus("0");
                                nr.setRecipientID(author_id);


                                match.notifyInstance.execute(nr);
                            }

                        });


                        //=================================消息提醒====================================================
                    } catch (Exception ig) {
                        log.error("follow-notice-has-ignored-------:", ig);
                    }

                }


            }
        } catch (Exception e) {

            log.error("cm--follow--ex::" + e);

            msg = "exp";
        }


        return ResultGenerator.genSuccessResult(msg);
    }

    //移除某个粉丝
    @RequestMapping("/cm/unFollow")
    @ResponseBody
    public Result<String> rmFollow(ModelMap map, String id) {

        try {
            if (StringUtils.isNotEmpty(id)) {
                ufService.delUserFollow(Integer.parseInt(id));
                return ResultGenerator.genSuccessResult("success");
            } else {
                return ResultGenerator.genSuccessResult("移除失败---null id");
            }
        } catch (Exception e) {
            log.error("cm--unfollow--ex::" + e);
            return ResultGenerator.genSuccessResult("exp");
        }


    }


    @RequestMapping("/cm/x_b_j_t")
    @RateLimit
    public String x_b_j_t() {
        return "/bruce-quiet-listen";
    }

    @RequestMapping("/cm/vlog")
    @RateLimit
    public String vlog() {
        return "/vlog";
    }


    /**
     * 404错误
     *
     * @return
     */
    @RequestMapping("/building")
    public String building() {

        return "/building";
    }


    /**
     * 跳转到登陆后的个人中心-最爱【需要判断登录】
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/cm/channel")
    @CheckLogin
    public String pCentral(ModelMap map, HttpServletRequest request) {

        UserVO user = RequestHolder.getUserInfo(request);
        request.getSession().removeAttribute("tabs");
        request.getSession().setAttribute("tabs", "channel");
        map.put("MyInfo", user);

        //查询个人歌词最爱历史
        String sql = "select c.love_date,c.id,c.title,c.title_code,c.album_img,b.id as userlyricsid " +
                " from  bc_user_lyrics b  join bc_lyrics c on b.lyricsid = c.id " +
                " where b.userid = ? order by c.love_date desc";

        List<Map<String, Object>> list = NPQueryRunner.query(sql,new MapListHandler(), user.getId());
        if (!CollectionUtils.isEmpty(list)) { //--批量处理时间
            list.forEach(item -> {
                String love_date = (String) item.get("love_date");
                if (StringUtils.isNotEmpty(love_date)) item.put("love_date", TimeUtils.formatToNear(love_date));
            });
        }

        map.addAttribute("channelList", list);
        return "myself";
    }

    /**
     * 跳转到登陆后的个人中心--我的粉丝【需要判断登录】
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/cm/myfans")
    @CheckLogin
    public String myfans(ModelMap map, HttpServletRequest request) {

        //取得当前用户
        UserVO user = RequestHolder.getUserInfo(request);

        map.put("MyInfo", user);

        //查询该用户的粉丝列表
        List<Map<String, Object>> fansList = Lists.newArrayList();
        List<UserFollow> fanBeanList = ufService.findByCondition(" where author_id = '" + user.getId() + "' ","");
        if (!CollectionUtils.isEmpty(fanBeanList)) {
            for (int i = 0; i < fanBeanList.size(); i++) {
                Map<String, Object> map_ = new HashMap<String, Object>();
                User uu = userService.findUser(fanBeanList.get(i).getFollowId());
                UserFollow ff = fanBeanList.get(i);
                //处理时间格式
                ff.setCreateTime(TimeUtils.formatToNear(ff.getCreateTime()));
                map_.put("user", uu);
                map_.put("follow", ff);
                fansList.add(map_);
            }
        }
        map.put("fansList", fansList);


        return "/fans";
    }

    /**
     * 跳转到某人个人中心--ta的粉丝
     *
     * @param map
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping("/cm/fans/{userid}")
    public String fans(ModelMap map, @PathVariable String userid, HttpServletRequest request) {
        try {
            User user = userService.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);

            //查询该用户的粉丝列表
            List<Map<String, Object>> fansList = Lists.newArrayList();
            List<UserFollow> fan_list = ufService.findByCondition(" where author_id = '" + userid + "' ","");
            if (!CollectionUtils.isEmpty(fan_list)) {
                for (int i = 0; i < fan_list.size(); i++) {
                    Map<String, Object> map_ = new HashMap<String, Object>();
                    User uu = userService.findUser(fan_list.get(i).getFollowId());
                    UserFollow ff = fan_list.get(i);
                    //处理时间格式
                    ff.setCreateTime(TimeUtils.formatToNear(ff.getCreateTime()));
                    map_.put("user", uu);
                    map_.put("follow", ff);
                    fansList.add(map_);
                }
            }
            map.put("fansList", fansList);

            //取得当前用户对作者的关注状态
            UserVO cur_user = RequestHolder.getUserInfo(request);
            if (cur_user != null) {
                String follow_id = String.valueOf(cur_user.getId());
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
        } catch (Exception e) {
            log.error("跳转到某人个人中心--ta的粉丝------>", e);

        }
        return "spacefans";
    }

    /**
     * 跳转到某人的个人中心【参数是用户id】
     *
     * @param map
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping("/cm/channel/{userid}")
    public String detail(ModelMap map, @PathVariable String userid, HttpServletRequest request) {
        try {

            User user = userService.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);

            //查询个人歌词最爱历史
            String sql = " SELECT t.* from ( " +
                    " (SELECT '点赞数据' as data_type, d.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_lyrics_zan d " +
                    " left join bc_lyrics c on d.lyricsid = c.id WHERE d.userid = ? and c.id is not null ) " +
                    " union (SELECT '创建数据' as data_type, c.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_user_lyrics b " +
                    " join bc_lyrics c on b.lyricsid = c.id WHERE b.userid = ? and c.id is not null ) " +
                    " ) as t order by t.data_type desc,t.love_date DESC";

            if (user != null) {
                List<Map<String, Object>> list = NPQueryRunner.query(sql, new MapListHandler(), user.getId(), user.getId());

                for (int i = 0; i < list.size(); i++) {
                    //--批量处理时间
                    list.get(i).put("love_date", TimeUtils.formatToNear((String) list.get(i).get("love_date")));
                }
                map.addAttribute("channelList", list);
                //取得当前用户对作者的关注状态
                UserVO cur_user = RequestHolder.getUserInfo(request);
                if (cur_user != null) {
                    String follow_id = String.valueOf(cur_user.getId());
                    String author_id = userid;
                    if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                        String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                        List<Map<String, Object>> ygz_nums_list = NPQueryRunner.query(ygz_nums_sql,new MapListHandler(), author_id, follow_id);
                        String nums = ygz_nums_list.get(0).get("nums").toString();
                        if (Integer.parseInt(nums) > 0) {
                            map.put("gz", "ygz");
                        }
                    }

                }
            }


        } catch (Exception e) {
            log.error("跳转到某人的个人中心【参数是用户id】------>", e);
        }
        return "space";
    }

    /**
     * 跳转到某人的个人中心【参数是用户名】
     *
     * @param map
     * @param tail_slug
     * @param request
     * @return
     */
    @RequestMapping("/people/{tail_slug}")
    public String people(ModelMap map, @PathVariable String tail_slug, HttpServletRequest request) throws Exception {

        //查询某人的最爱图册【自己创建的+点赞的】
        tail_slug = WAQ.forSQL().escapeSql(tail_slug);
        User user = null;
        //开启驼峰映射
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        List<User> ul = NPQueryRunner.query("select * from bc_user where tail_slug = ?",new BeanListHandler<User>(User.class,processor), tail_slug);
        if (!CollectionUtils.isEmpty(ul)) {
            user = ul.get(0);
        }
        map.put("MyInfo", user);


        //查询个人歌词最爱历史
        String sql = " SELECT t.* from ( " +
                " (SELECT '点赞数据' as data_type, d.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_lyrics_zan d " +
                " left join bc_lyrics c on d.lyricsid = c.id WHERE d.userid = ? and c.id is not null ) " +
                " union (SELECT '创建数据' as data_type, c.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_user_lyrics b " +
                " join bc_lyrics c on b.lyricsid = c.id WHERE b.userid = ? and c.id is not null ) " +
                " ) as t order by t.data_type desc,t.love_date DESC";

        if (user != null) {
            List<Map<String, Object>> list = NPQueryRunner.query(sql,new MapListHandler(), user.getId(), user.getId());
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(item -> {
                    String love_date = (String) item.get("love_date");
                    if (StringUtils.isNotEmpty(love_date)) item.put("love_date", TimeUtils.formatToNear(love_date));
                });
                map.addAttribute("channelList", list);
            }

        }


        //取得当前用户
        UserVO cur_user = RequestHolder.getUserInfo(request);

        //取得当前用户对作者的关注状态
        if (cur_user != null) {
            String follow_id = String.valueOf(cur_user.getId());
            String author_id = String.valueOf(user.getId());
            if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                List<Map<String, Object>> ygz_nums_list = NPQueryRunner.query(ygz_nums_sql, new MapListHandler(), author_id, follow_id);
                String nums = ygz_nums_list.get(0).get("nums").toString();
                if (Integer.parseInt(nums) > 0) {
                    map.put("gz", "ygz");
                }
            }

        }


        return "space";
    }

    /**
     * 跳转编辑个人资料【需要验证登录】
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/me/settings")
    @CheckLogin
    public String toEditInfo(ModelMap map, HttpServletRequest request) {
        UserVO u = RequestHolder.getUserInfo(request);
        map.put("MyInfo", u);
        UserProfile DT = profileService.getModelByUserid(u.getId());
        map.put("DT", DT);
        return "EditInfo";
    }

    /**
     * 保存个人资料
     *
     * @param map
     * @param file
     * @param new_password
     * @param new_password_confirmation
     * @param courseware
     * @param year_of_birth
     * @return
     */
    @RequestMapping("/cm/saveEditInfo")
    @RateLimit
    public String saveEditInfo(HttpServletRequest request, ModelMap map, String username, String tail_slug, String year_of_birth, String courseware, String new_password, String new_password_confirmation, @RequestParam(value = "file", required = false) MultipartFile[] file) {


        //获取当前user
        UserVO userVO = RequestHolder.getUserInfo(request);
        if (Objects.nonNull(userVO)) {
            User user = userService.findUser(userVO.getId());

            String old_path = user.getHeadPath();

            user.setUsername(username);

            user.setTailSlug(tail_slug);

            user.setBlogSite(courseware);

            if (Objects.nonNull(file) && file.length > 0) {
                // 执行删除图片缓存
                FileUtils.removeOldFile(old_path, file);

                //执行上传
                List<String> file_list = FileUtils.commonUpload(file, FileUtils.suffix_head);
                //执行上传end


                if (file_list.size() > 0) {
                    user.setHeadPath(file_list.get(0));
                } else {
                    user.setHeadPath(old_path);
                }

            }

            //处理密码信息
            if (!StringUtils.isEmpty(new_password) && !StringUtils.isEmpty(new_password_confirmation) && new_password.equals(new_password_confirmation)) {
                user.setPassword(EnDecryptUtils.diyEncrypt(new_password));
            }
            //处理密码信息
            userService.updateUser(user);


            //保存User表信息-------结束

            //保存User明细表信息-------start
            UserProfile up = profileService.getModelByUserid(user.getId());
            if (!StringUtils.isEmpty(courseware)) {
                up.setCourseware(courseware);
            }
            if (!StringUtils.isEmpty(year_of_birth)) {
                up.setYearOfBirth(year_of_birth);
            }
            profileService.updateUserProfile(up);


            //更新复制属性
            BeanUtils.copyProperties(user, userVO);
            request.getSession().setAttribute("user", userVO);
            RequestHolder.updateUserInfoInRedis(request,userVO);
        }


        //保存User明细表信息-------end
        return "redirect:/cm/channel";
    }

    /**
     * 跳转到登录页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(ModelMap map, HttpServletRequest request) {
        //redirect URI的设置
        String redirectURI = request.getParameter("redirectURI");

        log.error("Login for :=======>" + JsonUtil.object2json(redirectURI));
        if (StringUtils.isNotEmpty(redirectURI)) {
            if (redirectURI.equals("/login")) {
                redirectURI = "/";
            }
            map.put("redirectURI", redirectURI);
        }

        return "login2";
    }

    /**
     * 跳转到注册页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/signup")
    public String toReg(ModelMap map, HttpServletRequest request) {
        //redirect URI的设置
        String redirectURI = request.getParameter("redirectURI");

        log.error("signUp for :=======>" + JsonUtil.object2json(redirectURI));

        if (StringUtils.isNotEmpty(redirectURI)) {
            if (redirectURI.equals("/login")) {
                redirectURI = "/";
            }
            map.put("redirectURI", redirectURI);
        }

        return "reg2";
    }

    /**
     * @param map
     * @param request
     * @return
     * @function 忘记密码
     */
    @RequestMapping("/cm/forget")
    @RateLimit
    public String forget(ModelMap map, HttpServletRequest request) {
        return "forget";
    }


    @RequestMapping("/cm/logout")
    public String logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

        Enumeration<?> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String sessionName = (String) e.nextElement();
            session.removeAttribute(sessionName);
        }
        CookieUtil.clearAll(request, response);

        return "dashboard";
    }


    /**
     * 注册用户方法
     *
     * @param map
     * @param session
     * @return
     * @modifyedTime 2018-10-12
     */
    @RequestMapping("/cm/signup")
    @ResponseBody
    @RateLimit
    public Result<?> signup(ModelMap map, HttpSession session, HttpServletRequest request) {


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //获取ip信息
        String ipAndDetail = "";
        try {

            // 获取当前日期
            LocalDate currentDate = LocalDate.now();

            // 构建计数器键
            String counterKey = EnvCfgUtil.getValByCfgName("COUNTER_KEY_PREFIX") + currentDate.toString();

            Long counter = RedisUtil.getInstance().incrAndGet(counterKey);
            String gd_ip_max = EnvCfgUtil.getValByCfgName("GD_IP_MAX");
            if (counter > Integer.parseInt(gd_ip_max)) {
                log.error("当天统计ip信息数目已超过" + gd_ip_max + "，不再请求");
            } else {
                ipAndDetail = AddressUtils.getInstance().getIpAndDetail(request);
            }

        } catch (Exception ignore) {
            log.error(ignore.getMessage());
        }

        email = WAQ.forSQL().escapeSql(email);
        password = WAQ.forSQL().escapeSql(password);

        List<Map<String, Object>> list = NPQueryRunner.findByCondition("bc_user", "  email = '" + email + "' ");
        if(CollectionUtils.isNotEmpty(list)){
            return ResultGenerator.genErrorResult(ResultEnum.REG_Fail_Repeat);
        }else{
            //注册
            User user = new User();
            user.setEmail(email);
            user.setDateJoined(TimeUtils.nowTime());//日期
            //用户名=======================================================
            String username = "";
            if (email.contains("@")) {
                String[] strs = email.split("@");
                username = strs[0];
            } else {
                username = email;
            }
            user.setUsername(username);
            //用户名=======================================================
            //默认字符头像===================================================
            String abc = PinyinUtil.paraseStringToPinyin(username);
            if (StringUtils.isNotEmpty(abc)) {
                String head_span = abc.substring(0, 1).toUpperCase();
                String head_span_class = "text-" + head_span.toLowerCase();
                user.setHeadSpan(head_span);
                user.setHeadSpanClass(head_span_class);
            }
            //默认字符头像===================================================
            //设置注册者的详细信息
            user.setLastLogin(JsonUtil.object2json(user.getDateJoined() + ipAndDetail));
            user.setTailSlug(username + TimeUtils.getRandomDay());
            user.setPassword(EnDecryptUtils.diyEncrypt(password));
            user.setEmailFlag("1");//暂时设置为1， 邮件发送失败再禁用账户
            session.setAttribute("user", user);
            map.put("user", user);

            userService.addUser(user);

            //===================================异步操作=================================================
            ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
            String finalEmail = email;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //发送消息通知发邮件
                    try {
                        EmailUtils.getInstance().ThanksReg(finalEmail);
                    } catch (Exception e) {
                        log.error("发送注册邮件错误========>{}", e);
                    }

                    //发送异步站长通知消息
                    try {
                        //=================================消息提醒====================================================

                        //判断主题类型
                        NotifyEnum match = NotifyEnum.WEBMASTER;

                        //提醒系统赋值
                        NotifyRemind nr = new NotifyRemind();

                        //common
                        nr.setMessage(user.toString() + "---" + TimeUtils.nowTime() + "---注册了---");
                        nr.setStatus("0");


                        match.notifyInstance.execute(nr);

                        //=================================消息提醒====================================================
                    } catch (Exception ig) {
                        log.error("signup-notice-has-ignored-------:", ig);
                    }
                }


            });
            //===================================异步操作=================================================

            return ResultGenerator.genSuccessResult("ok");
        }


    }


    /**
     * 自动登录
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cm/autoLogin")
    @ResponseBody
    public Result<?> autoLogin(HttpServletRequest request) {

        //1.cookie登录
        UserVO userInfo = RequestHolder.getUserInfo(request);
        if (Objects.nonNull(userInfo)) {
            //2.本次session存放
            request.getSession().setAttribute("user", userInfo);

            if (!PASS_ID.contains(userInfo.getId().toString())) {
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
                            nr.setMessage(userInfo.toString() + "---" + TimeUtils.nowTime() + "---自动登录了---");
                            nr.setStatus("0");


                            match.notifyInstance.execute(nr);

                            //=================================消息提醒====================================================
                        } catch (Exception ig) {
                            log.error("auto-login-notice-has-ignored-------:", ig);
                            ig.printStackTrace();
                        }
                    }


                });
                //===================================异步操作=================================================
            }

            return ResultGenerator.genSuccessResult("自动登录成功");
        }

        return ResultGenerator.genErrorResult(ResultEnum.AUTO_LOGIN_FAIL);
    }


    /**
     * 重写登录逻辑
     * 1.登陆成功
     * 2.redis写token 和登录对象
     * 3.往cookie里写 token
     *
     * @param request
     * @param response
     * @param email
     * @param password
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cm/login")
    @ResponseBody
    @RateLimit
    public Result<?> login(HttpServletRequest request, HttpServletResponse response, String email, String password) throws Exception {

        //正常登录流程
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {

            //防止sql注入--email
            email = WAQ.forSQL().escapeSql(email);
            password = EnDecryptUtils.diyEncrypt(password);
            User user = userService.login(email, password);
            if (user != null && !user.getEmailFlag().equals("0")) {
                //1.登录成功

                //2.清除敏感信息
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);


                //3. 往redis设置key=UUID,value=xyz
                String token = UUID.randomUUID().toString();
                RedisUtil.getInstance().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), JsonUtil.object2json(userVO), CookieConstant.expire);

                //4. 设置cookie openid = abc
                CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

                //5.本次session存放
                request.getSession().setAttribute("user", userVO);


                if (!PASS_ID.contains(userVO.getId().toString())) {
                    //===================================异步操作=================================================
                    ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {

                            //获取IP+地址
                            String ipAndDetail = "";
                            try {

                                // 获取当前日期
                                LocalDate currentDate = LocalDate.now();

                                // 构建计数器键
                                String counterKey = EnvCfgUtil.getValByCfgName("COUNTER_KEY_PREFIX") + currentDate.toString();

                                Long counter = RedisUtil.getInstance().incrAndGet(counterKey);
                                String gd_ip_max = EnvCfgUtil.getValByCfgName("GD_IP_MAX");
                                if (counter > Integer.parseInt(gd_ip_max)) {
                                    log.error("当天统计ip信息数目已超过" + gd_ip_max + "，不再请求");
                                } else {
                                    ipAndDetail = AddressUtils.getInstance().getIpAndDetail(request);

                                    //更新登录时间 +地址信息
                                    user.setLastLogin(JsonUtil.object2json(TimeUtils.nowTime() + ipAndDetail));
                                    userService.updateUser(user);
                                }

                            } catch (Exception ignore) {
                                log.error(ignore.getMessage());
                            }


                            //发送异步站长通知消息
                            try {
                                //=================================消息提醒====================================================

                                //判断主题类型
                                NotifyEnum match = NotifyEnum.WEBMASTER;

                                //提醒系统赋值
                                NotifyRemind nr = new NotifyRemind();

                                //common
                                nr.setMessage(user.toString() + "---" + TimeUtils.nowTime() + "---登录了---");
                                nr.setStatus("0");


                                match.notifyInstance.execute(nr);

                                //=================================消息提醒====================================================
                            } catch (Exception ig) {
                                log.error("login-notice-has-ignored-------:", ig);
                            }
                        }


                    });
                    //===================================异步操作=================================================
                }


                return ResultGenerator.genSuccessResult("登录成功");

            } else if (user != null && user.getEmailFlag().equals("0")) {
                //"邮箱未通过验证，请重试或者联系站长解决登陆问题
                return ResultGenerator.genErrorResult(ResultEnum.Login_Email_Validate_Fail);
            } else {
                //登录失败
                return ResultGenerator.genErrorResult(ResultEnum.Login_Fail);
            }
        }
        return null;
    }

}
