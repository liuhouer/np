
package cn.northpark.action;
/*
 *@author bruce
 *
 **/

import cn.northpark.constant.TopicTypeEnum;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.model.*;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadLocal.RequestHolder;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.NotifyUtil;
import cn.northpark.utils.StringCommon;
import cn.northpark.utils.TimeUtils;
import cn.northpark.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
@RequestMapping("/zanAction")
@Slf4j
public class LyricsZanAction {

    @Autowired
    private LyricsZanManager lyricszanManager;
    @Autowired
    private LyricsCommentManager plManager;
    @Autowired
    private LyricsManager lyricsManager;

    @Autowired
    private UserLyricsManager userlyricsManager;





    /**
     * 最爱的主题点赞操作
     *
     * @param lyricsid
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping("/zan")
    @ResponseBody
    public Result<String> zan(String lyricsid, String userid, String loveDate, HttpServletRequest request) {

        UserVO u = RequestHolder.getUserInfo(request);
        if (Objects.nonNull(u)) {
            userid = String.valueOf(u.getId());
        }

        String msg = "success";
        int num = lyricszanManager.countHql(" where lyricsid='" + lyricsid + "' and userid = '" + userid + "' ");

        if (num > 0) {
            msg = "zanguole";
        } else {
            try {

                LyricsZan model = new LyricsZan();
                model.setLyricsid(Integer.parseInt(lyricsid));
                model.setUserid(Integer.parseInt(userid));
                model.setLove_date(loveDate);
                model.setLove_year(loveDate.substring(0, 4));
                lyricszanManager.addLyricsZan(model);


                Lyrics lrc = this.lyricsManager.findLyrics(Integer.parseInt(lyricsid));
                if (lrc != null) {
                    int zan_num = lrc.getZan() == null ? 0 : lrc.getZan();
                    zan_num += 1;
                    lrc.setZan(zan_num);
                    this.lyricsManager.updateLyrics(lrc);
                }

                //=================================异步消息提醒====================================================

                ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<UserLyrics> by = userlyricsManager.findByCondition(" where lyricsid = '" + lyricsid + "' ").getResultList();
                        //判断主题类型

                        NotifyEnum match = NotifyEnum.LOVE_ZAN;

                        //提醒系统赋值
                        NotifyRemind nr = new NotifyRemind();

                        //common
                        nr.setSenderID(u.getId().toString());
                        nr.setSenderName(u.getUsername());
                        nr.setObjectID(lyricsid);
                        Map<String, String> objectContent = NotifyUtil.getObjectContent(TopicTypeEnum.LOVE.getCode(), Integer.parseInt(lyricsid));
                        nr.setObject(StringCommon.getLenStr(objectContent.get("title"),200));
                        nr.setObjectLinks(objectContent.get("href"));
                        nr.setMessage("爱上了你创建的主题图册");
                        nr.setStatus("0");
                        if(!CollectionUtils.isEmpty(by)){
                            nr.setRecipientID(by.get(0).getUserid().toString());
                        }


                        match.notifyInstance.execute(nr);
                    }


                });


                //=================================异步消息提醒====================================================



                msg = "success";
            } catch (Exception e) {
                msg = "exception";
                log.error("zan action------>", e);
            }
        }
        return ResultGenerator.genSuccessResult(msg);
    }

    /**
     * 最爱的主题评论保存
     *
     * @param comment
     * @param userid
     * @param lyricsid
     * @param request
     * @return
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public Result<String> addComment(String comment, String userid, String lyricsid, HttpServletRequest request) {
        if (StringUtils.isEmpty(userid)) {
            UserVO u = RequestHolder.getUserInfo(request);
            userid = String.valueOf(u.getId());
        }

        String msg = "success";
        try {

            LyricsComment model = new LyricsComment();
            comment = comment.replaceAll("script", "urShit").replaceAll("alert", "caoNima").replaceAll("location", "jumpNima");
            model.setComment(comment);
            model.setUserid(Integer.parseInt(userid));
            model.setLyricsid(Integer.parseInt(lyricsid));
            model.setCreate_time(TimeUtils.nowTime());

            plManager.addLyricsComment(model);


            Lyrics lrc = this.lyricsManager.findLyrics(Integer.parseInt(lyricsid));
            if (lrc != null) {
                int pl_num = lrc.getPl() == null ? 0 : lrc.getPl();
                pl_num += 1;
                lrc.setPl(pl_num);
                this.lyricsManager.updateLyrics(lrc);
            }
            msg = "success";
        } catch (Exception e) {
            // TODO: handle exception
            msg = "exception";
            log.error("zan action------>", e);
        }
        return ResultGenerator.genSuccessResult(msg);
    }

}
