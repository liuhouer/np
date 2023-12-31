
package cn.northpark.service;

import cn.northpark.model.UserLyrics;

import java.util.List;
import java.util.Map;

public interface UserLyricsService {

    UserLyrics findUserLyrics(Integer id);

    List<UserLyrics> findAll();

    void addUserLyrics(UserLyrics userlyrics);

    boolean delUserLyrics(Integer id);

    boolean updateUserLyrics(UserLyrics userlyrics);

    String getMixSql(String userid);

    String getRandSql();

    List<Map<String, Object>> querySqlMap(String mixSQL);

    List<UserLyrics> findByCondition(String whereSql, String orderBy);
}


