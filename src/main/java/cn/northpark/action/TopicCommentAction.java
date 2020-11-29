
package cn.northpark.action;

import cn.northpark.exception.Result;
import cn.northpark.exception.ResultGenerator;
import cn.northpark.manager.TopicCommentManager;
import cn.northpark.model.TopicComment;
import cn.northpark.query.TopicCommentQuery;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.safe.WAQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


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
    @Autowired
    private TopicCommentQuery topiccommentQuery;


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

        assert model!=null && StringUtils.isNotEmpty(model.getContent());
        model.setContent(WAQ.forSQL().escapeSql(model.getContent()));
        //更新
        if (model.getId() != null && model.getId() != 0) {
            TopicComment old = topicCommentManager.findTopicComment(model.getId());

            BeanUtils.copyProperties(model, old);

            old.setAdd_time(TimeUtils.nowTime());
            topicCommentManager.updateTopicComment(old);

        } else {//新增

            model.setAdd_time(TimeUtils.nowTime());
            topicCommentManager.addTopicComment(model);
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

        map.addAttribute("list", list);

        String result = "/page/common/topic-comment";

        return result;
    }


}
