package cn.northpark.mapper;

import cn.northpark.model.LyricsZan;

import java.util.List;

public interface LyricsZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LyricsZan record);

    LyricsZan selectByPrimaryKey(Integer id);

    List<LyricsZan> selectAll();

    int updateByPrimaryKey(LyricsZan record);
}