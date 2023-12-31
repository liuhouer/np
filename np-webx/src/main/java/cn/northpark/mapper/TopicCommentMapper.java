package cn.northpark.mapper;

import cn.northpark.model.TopicComment;

import java.util.List;

public interface TopicCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopicComment record);

    TopicComment selectByPrimaryKey(Integer id);

    List<TopicComment> selectAll();

    int updateByPrimaryKey(TopicComment record);
}