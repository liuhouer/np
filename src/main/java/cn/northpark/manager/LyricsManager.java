
package cn.northpark.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Lyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

public interface LyricsManager {

    public Lyrics findLyrics(Integer id);

    public List<Lyrics> findAll();

    public void addLyrics(Lyrics lyrics);

    public boolean delLyrics(Integer id);

    public boolean updateLyrics(Lyrics lyrics);

    public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
                                               String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<Lyrics> findByCondition(
            String wheresql);

    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countSql(String sql);


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countHql(String wheresql);


    /**
     * 根据sql语句预查询？？？
     *
     * @return
     */
    public List<Lyrics> querySql(String sql, Object... obj);

    /**
     * 根据sql语句查询
     *
     * @return
     */
    public List<Lyrics> querySql(String sql);

    /**
     * 根据sql语句返回map结果集
     *
     * @return
     */
    public List<Map<String, Object>> querySqlMap(String sql);


}


