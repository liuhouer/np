package cn.northpark.mapper;

import cn.northpark.model.UserLyrics;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserLyricsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLyrics record);

    UserLyrics selectByPrimaryKey(Integer id);

    List<UserLyrics> selectAll();

    int updateByPrimaryKey(UserLyrics record);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String sql);

}