
package cn.northpark.action;

import cn.northpark.constant.TopicTypeEnum;
import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.TopicCommentManager;
import cn.northpark.model.NotifyRemind;
import cn.northpark.model.TopicComment;
import cn.northpark.notify.NotifyEnum;
import cn.northpark.threadpool.AsyncThreadPool;
import cn.northpark.utils.NotifyUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.StringCommon;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.safe.WAQ;
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
public class TopicCommentAction {


    @Autowired
    private TopicCommentManager topicCommentManager;


    /**
     * 保存主题评论的方法
     *
     * @param map
     * @return
     */
    @RequestMapping("/addTopicComment")
    @ResponseBody
    public Result<String> addItem(ModelMap map, TopicComment model) throws Exception {
        String rs = "success";

        assert  model != null
                && StringUtils.isNotEmpty(model.getContent())
                && Objects.nonNull(model.getFrom_uid())
                && StringUtils.isNotEmpty(model.getFrom_uname())
                && Objects.nonNull(model.getTopic_id())
                && StringUtils.isNotEmpty(model.getTopic_type());
        model.setContent(WAQ.forSQL().escapeSql(model.getContent()));
        //更新
        if (model.getId() != null && model.getId() != 0) {
            TopicComment old = topicCommentManager.findTopicComment(model.getId());

            BeanUtils.copyProperties(model, old);

            old.setAdd_time(TimeUtils.nowTime());
            topicCommentManager.updateTopicComment(old);

        } else {//新增

            //默认字符头像===================================================
            if (StringUtils.isNotEmpty(model.getFrom_uname())) {
                String abc = PinyinUtil.paraseStringToPinyin(model.getFrom_uname());
                if (StringUtils.isNotEmpty(abc)) {
                    String headspan = abc.substring(0, 1).toUpperCase();
                    model.setFrom_span(headspan);
                }
            }
            //默认字符头像===================================================

            model.setAdd_time(TimeUtils.nowTime());
            topicCommentManager.addTopicComment(model);

            //=================================异步消息提醒====================================================

            ThreadPoolExecutor threadPoolExecutor = AsyncThreadPool.getInstance().getThreadPoolExecutor();
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    //判断主题类型
                    String matchNotifyName = TopicTypeEnum.getMatchNotifyName(model.getTopic_type());
                    if(StringUtils.isNotEmpty(matchNotifyName)){

                        NotifyEnum match = NotifyEnum.match(matchNotifyName);

                        //提醒系统赋值
                        NotifyRemind nr = new NotifyRemind();

                        //common
                        nr.setSenderID(model.getFrom_uid().toString());
                        nr.setSenderName(model.getFrom_uname());
                        nr.setObjectID(model.getTopic_id().toString());
                        Map<String, String> objectContent = NotifyUtil.getObjectContent(model.getTopic_type(), model.getTopic_id());
                        nr.setObjectLinks(objectContent.get("href"));
                        nr.setMessage(model.getContent());
                        nr.setStatus("0");
                        //文章被留言/回复
                        // 在某文章界面评论被回复【通知-被回复人】
                        if(match.getName().equals("NOTE_REPLY")){
                            String title = objectContent.get("title");
                            String noteText = Jsoup.parse(title).text();
                            nr.setObject(StringCommon.getLenStr(noteText,200));
                            nr.setRecipientID(objectContent.get("by"));//通知树洞留言的创建者
                        }else{
                            nr.setObject(StringCommon.getLenStr(objectContent.get("title"),200));
                            if(StringUtils.isNotEmpty(model.getTo_uname())){
                                nr.setRecipientID(model.getTo_uid().toString());
                            }else{
                                // 在某文章界面留言 【通知-站长 507723】
                                nr.setRecipientID("507723");
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
    public String list1(ModelMap map, HttpServletRequest request) throws IOException {

        String topic_id = request.getParameter("topic_id");
        String topic_type = request.getParameter("topic_type");


        String sql = "select * from bc_topic_comment where topic_id = ? and topic_type = ? order by add_time desc ";


        List<TopicComment> list = this.topicCommentManager.querySql(sql, topic_id, topic_type);

        //处理时间戳
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                String datastr =  item.getAdd_time();
                if (StringUtils.isNotEmpty(datastr)) item.setAdd_time(TimeUtils.formatToNear(datastr));
            });
        }

        map.addAttribute("list", list);

        String result = "/page/common/topic-comment";

        return result;
    }


}
