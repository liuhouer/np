package cn.northpark.mapper;

import cn.northpark.model.Lyrics;
import java.util.List;

public interface LyricsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lyrics record);

    Lyrics selectByPrimaryKey(Integer id);

    List<Lyrics> selectAll();

    int updateByPrimaryKey(Lyrics record);
}