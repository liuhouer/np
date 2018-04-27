
package cn.northpark.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.UserLyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

public interface UserLyricsManager {

    public UserLyrics findUserLyrics(Integer id);

    public List<UserLyrics> findAll();

    public void addUserLyrics(UserLyrics userlyrics);

    public boolean delUserLyrics(Integer id);

    public boolean updateUserLyrics(UserLyrics userlyrics);

    public QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p,
                                                   String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<UserLyrics> findByCondition(
            String wheresql);


    public List<Map<String, Object>> getMixMapData(PageView<List<Map<String, Object>>> pageview, String userid);


    /**
     * 获取分页结构不获取数据
     *
     * @param pageview
     * @param userid
     * @return
     */
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageview, String userid);


    /**
     * sql+
     *
     * @return
     */
    public List<Map<String, Object>> querySql(String sql, Object... obj);
}


