package cn.northpark.mapper;

import cn.northpark.model.LyricsComment;

import java.util.List;

public interface LyricsCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LyricsComment record);

    LyricsComment selectByPrimaryKey(Integer id);

    List<LyricsComment> selectAll();

    int updateByPrimaryKey(LyricsComment record);
}