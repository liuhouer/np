package cn.northpark.mapper;

import cn.northpark.model.Lyrics;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LyricsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lyrics record);

    Lyrics selectByPrimaryKey(Integer id);

    List<Lyrics> selectAll();

    int updateByPrimaryKey(Lyrics record);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String sql);
}