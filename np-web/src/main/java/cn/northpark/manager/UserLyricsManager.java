
package cn.northpark.manager;

import cn.northpark.model.UserLyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UserLyricsManager {

    UserLyrics findUserLyrics(Integer id);

    List<UserLyrics> findAll();

    void addUserLyrics(UserLyrics userlyrics);

    boolean delUserLyrics(Integer id);

    boolean updateUserLyrics(UserLyrics userlyrics);

    QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p,
                                                   String whereSql, LinkedHashMap<String, String> order);

    QueryResult<UserLyrics> findByCondition(
            String whereSql);


    List<Map<String, Object>> getMixMapData(PageView<List<Map<String, Object>>> pageView, String userid);


    /**
     * 获取分页结构不获取数据
     *
     * @param pageView
     * @param userid
     * @return
     */
    PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String userid);


    List<Map<String, Object>> querySql(String sql, Object... obj);

    String getRandSql();

    List<Map<String, Object>> findMixByCondition(PageView<List<Map<String, Object>>> pageView, String randSql);
}


