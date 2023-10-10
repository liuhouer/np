
package cn.northpark.action;

import cn.northpark.annotation.CheckLogin;
import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.CookieConstant;
import cn.northpark.constant.RedisConstant;
import cn.northpark.constant.ResultEnum;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.ResetManager;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.manager.UserManager;
import cn.northpark.manager.UserprofileManager;
import cn.northpark.model.*;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.*;
import cn.northpark.utils.encrypt.EnDecryptUtils;
import cn.northpark.utils.safe.WAQ;
import cn.northpark.vo.UserVO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
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
public class UserAction {

    private final String HOME_ACTION = "redirect:/";
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserprofileManager userprofileManager;
    @Autowired
    private UserFollowManager userfollowManager;
    @Autowired
    private ResetManager resetManager;

	/**
	 * mq发消息
	 */
//	@Resource
//    private MQProducerManager messageProducer;


    public static ImmutableList<String> PASS_ID =
            ImmutableList.of("507723","508200","518821","519802","518518","507630");




    /**
     * @param request
     * @return 0、1 【0：没有 | 1：有】
     */
    @RequestMapping("/cm/loginFlag")
    @ResponseBody
    public Result<String> loginFlag(HttpServletRequest request) {
    	
    	String msg = "0";
    	//判断是否已登录
        UserVO user = RequestHolder.getUserInfo(request);
    	if(user!=null) {

    		msg = "1";
    	}

        return ResultGenerator.genSuccessResult(msg);

    }

    /**
     * @param email
     * @return 0、1 【0：没有 | 1：有】
     * @ 判断email的存在性
     */
    @RequestMapping("/cm/emailFlag")
    @ResponseBody
    @RateLimit
    public Result<String> emailFlag( String email) {
    	int num = userManager.countHql(" where email = '" + email + "' ");
        
        String msg = "exist";//存在；
        if (num <= 0) {
            msg = "not_exist";//不存在
        }
        return ResultGenerator.genSuccessResult(msg);

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
        int num = userManager.countHql(" where tail_slug = '" + tail + "' ");
        String msg = "exist";//存在；
        if (num <= 0) {
            msg = "not_exist";//不存在
        }
        return ResultGenerator.genSuccessResult(msg);

    }

    /**
     * 发送”修改密码“邮件
     */
    @RequestMapping("/cm/resetEmail")
    @ResponseBody
    @RateLimit
    public Result<String> resetEmail(String email) throws ParseException {
        String userid = "";
        List<User> list = userManager.findByCondition(" where email = '" + email + "' ").getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            userid = String.valueOf(list.get(0).getId());
        }
        //添加重置的信息============================================
        Reset reset = new Reset();
        reset.setCreated_time(TimeUtils.nowTime());
        String code = IDUtils.getInstance().geneInt();
        reset.setAuth_code(code);
        reset.setInvalid_time(TimeUtils.getDayAfter(TimeUtils.nowTime()));
        reset.setIs_email_authed(0);

        reset.setUser_id(Integer.parseInt(userid));
        resetManager.addReset(reset);
        //添加重置的信息============================================
        
        
        
        //发送消息通知发邮件
        try {
			EmailUtils.getInstance().changePwd(email, userid, code);
        } catch (Exception e) {
        	log.error("重置密码邮件错误========>{}",e);
        }
//        Map<String,Object> mqData=new HashMap<String,Object>();
//        mqData.put("email", email);
//        mqData.put("userid",userid);
//        mqData.put("code",code);
//		
//        messageProducer.sendDataToQueue(BC_Constant.MQ_MAIL_RESET, mqData);
        return  ResultGenerator.genSuccessResult("ok");
    }

    /**
     * 重置密码,从邮箱点击进来
     */
    @RequestMapping("/cm/reset")
    public String reset(HttpServletRequest request,
                        HttpServletResponse response, ModelMap map, String userid,
                        String auth_code) throws ParseException {

        String result = "false";
        List<Reset> gtList = resetManager.findByCondition(" where user_id='" + userid + "' and auth_code='" + auth_code + "' order by created_time desc").getResultList();
        try {
            if (!CollectionUtils.isEmpty(gtList)) {
                Reset gt_model = gtList.get(0);
                if (gt_model.getAuth_code().equals(auth_code)) {// Email认证通过
                    if (StringUtils.isNotEmpty(gt_model.getInvalid_time())) {// 设置了失效时间
                        if (TimeUtils.isInvalid(TimeUtils.nowTime(),
                                gt_model.getInvalid_time()) && (0 == gt_model.getIs_email_authed())) {// 时间未过期
                            User user = userManager.findUser(Integer.parseInt(userid));
                            if (user != null) {
                                user.setPassword(EnDecryptUtils.diyEncrypt(auth_code));
                                userManager.updateUser(user);

                                request.getSession().setAttribute("user", user);
                                //更新数据
                                gt_model.setIs_email_authed(1);
                                resetManager.updateReset(gt_model);
                                result = "success";
                            }
                        } else {
                            result = "is_old";
                        }
                    } else {// 没有设置失效时间
                        if (0 == gt_model.getIs_email_authed()) {
                            User user = userManager.findUser(Integer.parseInt(userid));
                            if (user != null) {
                                user.setPassword(EnDecryptUtils.diyEncrypt(auth_code));
                                userManager.updateUser(user);
                                request.getSession().setAttribute("user", user);
                                //更新数据
                                gt_model.setIs_email_authed(1);
                                resetManager.updateReset(gt_model);
                                result = "success";
                            }
                        } else {
                            result = "is_old";
                        }
                    }
                }
            } else {// 已失效，重新获取验证码
                result = "invalid";
            }
        } catch (Exception e) {
            log.error("commonAction------>", e);
            result = "exception";
        } finally {
            map.addAttribute("msg", result);
        }
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
            List<Map<String, Object>> ygz_nums_list = userfollowManager.querySql(ygz_nums_sql, author_id, follow_id);
            Object nums = ygz_nums_list.get(0).get("nums");

            if (Integer.valueOf(String.valueOf(nums)) == 0) {
                UserFollow uf = new UserFollow();
                uf.setAuthor_id(Integer.parseInt(author_id));
                uf.setFollow_id(Integer.parseInt(follow_id));
                uf.setStatus(1);
                uf.setCreate_time(TimeUtils.nowTime());
                if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {
                    userfollowManager.addUserFollow(uf);

                    try {
                        //=================================异步消息提醒====================================================

                        ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                        threadPoolExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                User follower = userManager.findUser(Integer.parseInt(follow_id));


                                //判断主题类型

                                NotifyEnum match = NotifyEnum.FOLLOW;

                                //提醒系统赋值
                                NotifyRemind nr = new NotifyRemind();

                                //common
                                nr.setSenderID(follow_id);
                                nr.setSenderName(follower.getUsername());
                                nr.setObjectID(null);
                                nr.setObject(null);
                                nr.setObjectLinks("/cm/detail/"+follow_id);
                                nr.setMessage("关注了你");
                                nr.setStatus("0");
                                nr.setRecipientID(author_id);


                                match.notifyInstance.execute(nr);
                            }

                        });


                        //=================================消息提醒====================================================
                    }catch (Exception ig){
                        log.error("follow-notice-has-ignored-------:",ig);
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
    @RequestMapping("/cm/rmfollow")
    @ResponseBody
    public Result<String> rmFollow(ModelMap map, String id) {
        String msg = "success";

        try {
            if (StringUtils.isNotEmpty(id)) {
                userfollowManager.delUserFollow(Integer.parseInt(id));
            } else {
                msg = "null id";
            }
        } catch (Exception e) {

            log.error("cm--unfollow--ex::" + e);
            msg = "exp";
        }

        return ResultGenerator.genSuccessResult(msg);
    }


    @RequestMapping("/cm/xbjt")
    @RateLimit
    public String x_b_j_t(ModelMap map) {
        return "/bruce-quiet-listen";
    }

    @RequestMapping("/cm/vlog")
    @RateLimit
    public String vlog(ModelMap map) {
        return "/vlog";
    }


    /**
     * 404错误
     *
     * @param map
     * @return
     */
    @RequestMapping("/building")
    public String building(ModelMap map, HttpServletRequest request) {

        return "/building";
    }


    /**
     * 跳转到登陆后的个人中心-最爱【需要判断登录】
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/cm/pcentral")
    @CheckLogin
    public String pCentral(ModelMap map, HttpServletRequest request) {

        String rs = "/myself";
        UserVO user = RequestHolder.getUserInfo(request);
        request.getSession().removeAttribute("tabs");
        request.getSession().setAttribute("tabs", "pcenter");
        map.put("MyInfo", user);

        //查询个人歌词最爱历史
        String sql = "select c.love_date,c.id,c.title,c.title_code,c.album_img,b.id as userlyricsid " +
                " from  bc_user_lyrics b  join bc_lyrics c on b.lyricsid = c.id " +
                " where b.userid = ? order by c.love_date desc";

        List<Map<String, Object>> list = userManager.querySqlMap(sql, user.getId());
        if(!CollectionUtils.isEmpty(list)) { //--批量处理时间
        	list.forEach(item ->{
        		String love_date = (String) item.get("love_date");
        		if(StringUtils.isNotEmpty(love_date)) item.put("love_date", TimeUtils.formatToNear(love_date));
        	});
        }
        
        map.addAttribute("Lovelist", list);
        return rs;
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
        List<Map<String, Object>> fanlist = Lists.newArrayList();
        List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '" + user.getId() + "' ").getResultList();
        if (!CollectionUtils.isEmpty(fan_list)) {
            for (int i = 0; i < fan_list.size(); i++) {
                Map<String, Object> map_ = new HashMap<String, Object>();
                String u_id = String.valueOf(fan_list.get(i).getFollow_id());
                User uu = userManager.findUser(Integer.parseInt(u_id));

                UserFollow ff = fan_list.get(i);
                //处理时间格式
                ff.setCreate_time(TimeUtils.formatToNear(ff.getCreate_time()));
                map_.put("user", uu);
                map_.put("follow", ff);
                fanlist.add(map_);
            }
        }
        map.put("fanlist", fanlist);


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
            User user = userManager.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);

            //查询该用户的粉丝列表
            List<Map<String, Object>> fanlist = Lists.newArrayList();
            List<UserFollow> fan_list = userfollowManager.findByCondition(" where author_id = '" + userid + "' ").getResultList();
            if (!CollectionUtils.isEmpty(fan_list)) {
                for (int i = 0; i < fan_list.size(); i++) {
                    Map<String, Object> map_ = new HashMap<String, Object>();
                    String u_id = String.valueOf(fan_list.get(i).getFollow_id());
                    User uu = userManager.findUser(Integer.parseInt(u_id));

                    UserFollow ff = fan_list.get(i);
                    //处理时间格式
                    ff.setCreate_time(TimeUtils.formatToNear(ff.getCreate_time()));
                    map_.put("user", uu);
                    map_.put("follow", ff);
                    fanlist.add(map_);
                }
            }
            map.put("fanlist", fanlist);

            //取得当前用户对作者的关注状态
            UserVO lo_user = RequestHolder.getUserInfo(request);
            if (lo_user != null) {
                String follow_id = String.valueOf(lo_user.getId());
                String author_id = userid;
                if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                    String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                    List<Map<String, Object>> ygz_nums_list = userfollowManager.querySql(ygz_nums_sql, author_id, follow_id);
                    String nums = ygz_nums_list.get(0).get("nums").toString();
                    if (Integer.parseInt(nums) > 0) {
                        map.put("gz", "ygz");
                    }
                }

            }
        } catch (Exception e) {
            log.error("commonAction------>", e);

        }
        return "/spacefans";
    }

    /**
     * 跳转到某人的个人中心【参数是用户id】
     *
     * @param map
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping("/cm/detail/{userid}")
    public String detail(ModelMap map, @PathVariable String userid, HttpServletRequest request) {
        try {

            User user = userManager.findUser(Integer.parseInt(userid));
            map.put("MyInfo", user);

            //查询个人歌词最爱历史
            String sql = " SELECT t.* from ( " +
                    " (SELECT '点赞数据' as data_type, d.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_lyrics_zan d " +
                    " left join bc_lyrics c on d.lyricsid = c.id WHERE d.userid = ? and c.id is not null ) " +
                    " union (SELECT '创建数据' as data_type, c.love_date, c.id, c.title, c.title_code, c.album_img FROM bc_user_lyrics b " +
                    " join bc_lyrics c on b.lyricsid = c.id WHERE b.userid = ? and c.id is not null ) " +
                    " ) as t order by t.data_type desc,t.love_date DESC";
            
            if (user != null) {
                List<Map<String, Object>> list = userManager.querySqlMap(sql, user.getId(), user.getId());
                
                for (int i = 0; i < list.size(); i++) {
                    //--批量处理时间
                    list.get(i).put("love_date", TimeUtils.formatToNear((String) list.get(i).get("love_date")));
                }
                map.addAttribute("Lovelist", list);
                //取得当前用户对作者的关注状态
                UserVO lo_user = RequestHolder.getUserInfo(request);
                if (lo_user != null) {
                    String follow_id = String.valueOf(lo_user.getId());
                    String author_id = userid;
                    if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                        String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                        List<Map<String, Object>> ygz_nums_list = userfollowManager.querySql(ygz_nums_sql, author_id, follow_id);
                        String nums = ygz_nums_list.get(0).get("nums").toString();
                        if (Integer.parseInt(nums) > 0) {
                            map.put("gz", "ygz");
                        }
                    }

                }
            }


        } catch (Exception e) {
            log.error("commonAction------>", e);
        }
        return "/space";
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
        String rs = "/space";


        //查询某人的最爱图册【自己创建的+点赞的】
        tail_slug = WAQ.forSQL().escapeSql(tail_slug);
        User user = null;
        List<User> ul = userManager.querySql("select * from bc_user where tail_slug = ?", tail_slug);
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
            List<Map<String, Object>> list = userManager.querySqlMap(sql, user.getId(),user.getId());
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(item -> {
                    String love_date = (String) item.get("love_date");
                    if (StringUtils.isNotEmpty(love_date)) item.put("love_date", TimeUtils.formatToNear(love_date));
                });
                map.addAttribute("Lovelist", list);
            }


        }


        //取得当前用户
        UserVO c_user = RequestHolder.getUserInfo(request);

        //取得当前用户对作者的关注状态
        if (c_user != null) {
            String follow_id = String.valueOf(c_user.getId());
            String author_id = String.valueOf(user.getId());
            if (StringUtils.isNotEmpty(follow_id) && StringUtils.isNotEmpty(author_id)) {

                String ygz_nums_sql = "select count(*) nums from bc_user_follow where author_id = ? and follow_id = ?";
                List<Map<String, Object>> ygz_nums_list = userfollowManager.querySql(ygz_nums_sql, author_id, follow_id);
                String nums = ygz_nums_list.get(0).get("nums").toString();
                if (Integer.parseInt(nums) > 0) {
                    map.put("gz", "ygz");
                }
            }

        }


        return rs;
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
        Userprofile DT = userprofileManager.getModelByUserid(String.valueOf(u.getId()));
        map.put("DT", DT);
        return "/EditInfo";
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
        UserVO userVO =  RequestHolder.getUserInfo(request);
        if(Objects.nonNull(userVO)){
            User user = userManager.findUser(userVO.getId());

            String old_path = user.getHead_path();

            user.setUsername(username);

            user.setTail_slug(tail_slug);

            user.setBlog_site(courseware);


            // 执行删除图片缓存
            FileUtils.removeOldFile(old_path, file);

            //执行上传
            List<String> file_list = FileUtils.commonUpload(file, FileUtils.suffix_head);
            //执行上传end

            if (file_list.size() > 0) {
                user.setHead_path(file_list.get(0));
            } else {
                user.setHead_path(old_path);
            }

            //处理密码信息
            if (!StringUtils.isEmpty(new_password) && !StringUtils.isEmpty(new_password_confirmation) && new_password.equals(new_password_confirmation)) {
                user.setPassword(EnDecryptUtils.diyEncrypt(new_password));
            }
            //处理密码信息
            userManager.updateUser(user);


            //保存User表信息-------结束

            //保存User明细表信息-------start
            Userprofile up = userprofileManager.getModelByUserid(String.valueOf(user.getId()));
            if (!StringUtils.isEmpty(courseware)) {
                up.setCourseware(courseware);
            }
            if (!StringUtils.isEmpty(year_of_birth)) {
                up.setYear_of_birth(year_of_birth);
            }
            userprofileManager.updateUserprofile(up);


            //更新复制属性
            BeanUtils.copyProperties(user,userVO);
            request.getSession().setAttribute("user", userVO);
        }


        //保存User明细表信息-------end
        return "redirect:pcentral";
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
        
        log.error("Login for :=======>"+JsonUtil.object2json(redirectURI));
        if (StringUtils.isNotEmpty(redirectURI)) {
            if (redirectURI.equals("/login")) {
                redirectURI = "/";
            }
            map.put("redirectURI", redirectURI);
        }

        return "/login2";
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
        
        log.error("signUp for :=======>"+JsonUtil.object2json(redirectURI));
        
        if (StringUtils.isNotEmpty(redirectURI)) {
            if (redirectURI.equals("/login")) {
                redirectURI = "/";
            }
            map.put("redirectURI", redirectURI);
        }

        return "/reg2";
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

        return "/forget";
    }


    @RequestMapping("/cm/logout")
    public String logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

        Enumeration<?> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String sessionName = (String) e.nextElement();
            session.removeAttribute(sessionName);
        }
        CookieUtil.clearAll(request, response);

        return HOME_ACTION ;
    }



    /**
     * 注册用户方法
     * @modifyedTime 2018-10-12
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/cm/signup")
    @ResponseBody
    @RateLimit
    public Result<?> signup( ModelMap map, HttpSession session,HttpServletRequest request) throws Exception{
    	
    	
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	//获取ip信息
        String ipAndDetail = "";
        try {

            // 获取当前日期
            LocalDate currentDate = LocalDate.now();

            // 构建计数器键
            String counterKey =  EnvCfgUtil.getValByCfgName("COUNTER_KEY_PREFIX") + currentDate.toString();

            Long counter = RedisUtil.getInstance().incrAndGet(counterKey);
            String gd_ip_max = EnvCfgUtil.getValByCfgName("GD_IP_MAX");
            if(counter > Integer.parseInt(gd_ip_max)){
                log.error("当天统计ip信息数目已超过"+gd_ip_max+"，不再请求");
            }else{
                ipAndDetail = AddressUtils.getInstance().getIpAndDetail(request);
            }

        }catch (Exception ignore){
            log.error(ignore.getMessage());
        }

        email = WAQ.forSQL().escapeSql(email);
        password = WAQ.forSQL().escapeSql(password);
        int num = userManager.countHql(" where email= '" + email + "' ");
        if (num > 0) {
        	return ResultGenerator.genErrorResult(ResultEnum.REG_Fail_Repeat);
        } else {
        	//注册
        	User user = new User();
        	user.setEmail(email);
			user.setDate_joined(TimeUtils.nowTime());//日期
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
                user.setHead_span(head_span);
                user.setHead_span_class(head_span_class);
            }
            //默认字符头像===================================================
            //设置注册者的详细信息
            user.setLast_login(JsonUtil.object2json(user.getDate_joined()+ipAndDetail));
            user.setTail_slug(username + TimeUtils.getRandomDay());
            user.setPassword(EnDecryptUtils.diyEncrypt(password));
            user.setEmail_flag("1");//暂时设置为1， 邮件发送失败再禁用账户
            session.setAttribute("user", user);
            map.put("user", user);
            
            this.userManager.addUser(user);

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
                        log.error("发送注册邮件错误========>{}",e);
                    }

                    //发送异步站长通知消息
                    try {
                        //=================================消息提醒====================================================

                        //判断主题类型
                        NotifyEnum match = NotifyEnum.WEBMASTER;

                        //提醒系统赋值
                        NotifyRemind nr = new NotifyRemind();

                        //common
                        nr.setMessage(user.toString()+"---"+TimeUtils.nowTime()+"---注册了---");
                        nr.setStatus("0");


                        match.notifyInstance.execute(nr);

                        //=================================消息提醒====================================================
                    }catch (Exception ig){
                        log.error("signup-notice-has-ignored-------:",ig);
                    }
                }



            });
            //===================================异步操作=================================================

		    return ResultGenerator.genSuccessResult("ok");
           
        }
    }


    /**
     * 自动登录
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cm/autoLogin")
    @ResponseBody
    public Result<?> autoLogin(HttpServletRequest request) throws Exception {

        //1.cookie登录
        UserVO userInfo = RequestHolder.getUserInfo(request);
        if(Objects.nonNull(userInfo)){
            //2.本次session存放
            request.getSession().setAttribute("user", userInfo);

            if(!PASS_ID.contains(userInfo.getId().toString())) {
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
    public Result<?> login(HttpServletRequest request,HttpServletResponse response,String email,String password) throws Exception {
    	
        //正常登录流程
        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(password)) {

            //防止sql注入--email
            email = WAQ.forSQL().escapeSql(email);
            password = EnDecryptUtils.diyEncrypt(password);
            User user = userManager.login(email, password);
            if (user != null && !user.getEmail_flag().equals("0")) {
                 //1.登录成功

                 //2.清除敏感信息
                 UserVO userVO = new UserVO();
                 BeanUtils.copyProperties(user, userVO);

            		
            	 //3. 往redis设置key=UUID,value=xyz
                 String token = UUID.randomUUID().toString();
                 RedisUtil.getInstance().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), JsonUtil.object2json(userVO),  CookieConstant.expire);

                 //4. 设置cookie openid = abc
                 CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

            	 //5.本次session存放
            	 request.getSession().setAttribute("user", userVO);


            	 if(!PASS_ID.contains(userVO.getId().toString())){
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
                                 String counterKey =  EnvCfgUtil.getValByCfgName("COUNTER_KEY_PREFIX") + currentDate.toString();

                                 Long counter = RedisUtil.getInstance().incrAndGet(counterKey);
                                 String gd_ip_max = EnvCfgUtil.getValByCfgName("GD_IP_MAX");
                                 if(counter > Integer.parseInt(gd_ip_max)){
                                     log.error("当天统计ip信息数目已超过"+gd_ip_max+"，不再请求");
                                 }else{
                                     ipAndDetail = AddressUtils.getInstance().getIpAndDetail(request);

                                     //更新登录时间 +地址信息
                                     user.setLast_login(JsonUtil.object2json(TimeUtils.nowTime()+ipAndDetail));
                                     userManager.updateUser(user);
                                 }

                             }catch (Exception ignore){
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
                                 nr.setMessage(user.toString()+"---"+TimeUtils.nowTime()+"---登录了---");
                                 nr.setStatus("0");


                                 match.notifyInstance.execute(nr);

                                 //=================================消息提醒====================================================
                             }catch (Exception ig){
                                 log.error("login-notice-has-ignored-------:",ig);
                             }
                         }



                     });
                     //===================================异步操作=================================================
                 }




                return ResultGenerator.genSuccessResult("登录成功");

            }else if(user != null && user.getEmail_flag().equals("0")){
            	//"邮箱未通过验证，请重试或者联系站长解决登陆问题
            	return ResultGenerator.genErrorResult(ResultEnum.Login_Email_Validate_Fail);
            }else {
            	//登录失败
            	return ResultGenerator.genErrorResult(ResultEnum.Login_Fail);
            }
        }
		return null;
    }

}
