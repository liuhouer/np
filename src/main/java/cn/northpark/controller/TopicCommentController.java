
package cn.northpark.controller;

import cn.northpark.annotation.RateLimit;
import cn.northpark.constant.TopicTypeEnum;
import cn.northpark.model.NotifyRemindB;
import cn.northpark.model.TopicComment;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.result.Result;
import cn.northpark.result.ResultGenerator;
import cn.northpark.service.TopicCommentService;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.*;
import cn.northpark.utils.safe.WAQ;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Controller
@RequestMapping("/topicComment")
public class TopicCommentController {


    @Autowired
    private TopicCommentService topicCommentService;


    /**
     * 保存主题评论的方法
     *
     * @param map
     * @return
     */
    @RequestMapping("/addTopicComment")
    @ResponseBody
    @RateLimit
    public Result<String> addItem(ModelMap map, TopicComment model) throws Exception {
        String rs = "success";

        assert  model != null
                && StringUtils.isNotEmpty(model.getContent())
                && Objects.nonNull(model.getFromUid())
                && StringUtils.isNotEmpty(model.getFromUname())
                && Objects.nonNull(model.getTopicId())
                && StringUtils.isNotEmpty(model.getTopicType());
        model.setContent(WAQ.forSQL().escapeSql(model.getContent()));
        //更新
        if (model.getId() != null && model.getId() != 0) {
            TopicComment old = topicCommentService.findTopicComment(model.getId());

            BeanUtils.copyProperties(model, old);

            old.setAddTime(TimeUtils.nowTime());
            topicCommentService.updateTopicComment(old);

        } else {//新增

            //默认字符头像===================================================
            if (StringUtils.isNotEmpty(model.getFromUname())) {
                String abc = PinyinUtil.paraseStringToPinyin(model.getFromUname());
                if (StringUtils.isNotEmpty(abc)) {
                    String head_span = abc.substring(0, 1).toUpperCase();
                    model.setFromSpan(head_span);
                }
            }
            //默认字符头像===================================================

            model.setAddTime(TimeUtils.nowTime());
            topicCommentService.addTopicComment(model);

            //=================================异步消息提醒====================================================

            ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    //判断主题类型
                    String matchNotifyName = TopicTypeEnum.getMatchNotifyName(model.getTopicType());
                    if(StringUtils.isNotEmpty(matchNotifyName)){

                        NotifyEnum match = NotifyEnum.match(matchNotifyName);

                        //提醒系统赋值
                        NotifyRemindB nr = new NotifyRemindB();

                        //common
                        nr.setSenderId(model.getFromUid().toString());
                        nr.setSenderName(model.getFromUname());
                        nr.setObjectId(model.getTopicId().toString());
                        Map<String, String> objectContent = NotifyUtil.getObjectContent(model.getTopicType(), model.getTopicId());
                        nr.setObjectLinks(objectContent.get("href"));
                        nr.setMessage(model.getContent());
                        nr.setStatus("0");
                        //文章被留言/回复
                        // 在某文章界面评论被回复【通知-被回复人】
                        if(match.getName().equals("NOTE_REPLY")){
                            String title = objectContent.get("title");
                            String noteText = Jsoup.parse(title).text();
                            nr.setObject(StringCommon.getLenStr(noteText,200));
                            nr.setRecipientId(objectContent.get("by"));//通知树洞留言的创建者
                        }else{
                            nr.setObject(StringCommon.getLenStr(objectContent.get("title"),200));
                            if(StringUtils.isNotEmpty(model.getToUname())){
                                nr.setRecipientId(model.getToUid().toString());
                            }else{
                                // 在某文章界面留言 【通知-站长 507723】
                                nr.setRecipientId("507723");
                            }
                        }


                        match.notifyInstance.execute(nr);

                    }
                }



            });

            //=================================异步消息提醒====================================================
        }

        return ResultGenerator.genSuccessResult(rs);
    }


    /**
     * 异步加载评论列表
     *
     * @param map
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/list")
    @RateLimit
    public String list1(ModelMap map, HttpServletRequest request) throws IOException {

        String TopicId = request.getParameter("TopicId");
        String TopicType = request.getParameter("TopicType");


        String sql = "select * from bc_topic_comment where TopicId = ? and TopicType = ? order by add_time desc ";

        //开启驼峰映射
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        List<TopicComment> list = NPQueryRunner.query(sql, new BeanListHandler<TopicComment>(TopicComment.class,processor), TopicId, TopicType);

        //处理时间戳
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                String add_time =  item.getAddTime();
                if (StringUtils.isNotEmpty(add_time)) item.setAddTime(TimeUtils.formatToNear(add_time));
            });
        }

        map.addAttribute("list", list);

        String result = "/page/common/topic-comment";

        return result;
    }


}
