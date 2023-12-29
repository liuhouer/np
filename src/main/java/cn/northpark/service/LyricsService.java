
package cn.northpark.service;

import cn.northpark.model.Lyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface LyricsService {

     Lyrics findLyrics(Integer id);

     List<Lyrics> findAll();

     void addLyrics(Lyrics lyrics);

     boolean delLyrics(Integer id);

     boolean updateLyrics(Lyrics lyrics);

     QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
                                               String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Lyrics> findByCondition(
            String wheresql);

    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
     int countSql(String sql);


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
     int countHql(String wheresql);


    /**
     * 根据sql语句预查询？？？
     *
     * @return
     */
     List<Lyrics> querySql(String sql, Object... obj);

    /**
     * 根据sql语句查询
     *
     * @return
     */
     List<Lyrics> querySql(String sql);

    /**
     * 根据sql语句返回map结果集
     *
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql);


}


