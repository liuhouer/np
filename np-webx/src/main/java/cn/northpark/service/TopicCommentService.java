
package cn.northpark.service;

import cn.northpark.model.TopicComment;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface TopicCommentService {

    TopicComment findTopicComment(Integer id);

    List<TopicComment> findAll();

    void addTopicComment(TopicComment topiccomment);

    boolean delTopicComment(Integer id);

    boolean updateTopicComment(TopicComment topiccomment);

}


