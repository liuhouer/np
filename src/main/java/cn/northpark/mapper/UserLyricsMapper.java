package cn.northpark.mapper;

import cn.northpark.model.UserLyrics;
import java.util.List;
import java.util.Map;

public interface UserLyricsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLyrics record);

    UserLyrics selectByPrimaryKey(Integer id);

    List<UserLyrics> selectAll();

    int updateByPrimaryKey(UserLyrics record);

    List<Map<String, Object>> execSql(String sql);
}