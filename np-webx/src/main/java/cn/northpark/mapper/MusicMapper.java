package cn.northpark.mapper;

import cn.northpark.model.Music;

import java.util.List;

public interface MusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Music record);

    Music selectByPrimaryKey(Integer id);

    List<Music> selectAll();

    int updateByPrimaryKey(Music record);
}