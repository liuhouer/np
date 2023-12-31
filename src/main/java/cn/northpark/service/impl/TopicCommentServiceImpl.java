
package cn.northpark.service.impl;

import cn.northpark.mapper.TopicCommentMapper;
import cn.northpark.model.TopicComment;
import cn.northpark.service.TopicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class TopicCommentServiceImpl implements TopicCommentService {

    @Autowired
    TopicCommentMapper topiccommentMapper;

    @Override
    public TopicComment findTopicComment(Integer id) {
        return topiccommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TopicComment> findAll() {
        return topiccommentMapper.selectAll();
    }

    @Override
    public void addTopicComment(TopicComment topiccomment) {
        topiccommentMapper.insert(topiccomment);
    }

    @Override
    public boolean delTopicComment(Integer id) {
        topiccommentMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateTopicComment(TopicComment topiccomment) {
        topiccommentMapper.updateByPrimaryKey(topiccomment);
        return true;
    }

}

