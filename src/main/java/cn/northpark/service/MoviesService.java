
package cn.northpark.service;

import cn.northpark.model.Movies;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MoviesService {

     Movies findMovies(Integer id);

     List<Movies> findAll();

     void addMovies(Movies movies);

     boolean delMovies(Integer id);

     boolean updateMovies(Movies movies);

     QueryResult<Movies> findByCondition(PageView<Movies> p,
                                               String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Movies> findByCondition(
            String wheresql);

    /**
     * 根据关键词搜索
     *
     * @return
     */
     List<Movies> querySql(String sql, Object... obj);


    /**
     * sql
     *
     * @return
     */
     List<Map<String, Object>> querySql(String sql);
    
    /**
     * sql
     *
     * @return
     */
     List<Movies> querySqlEntity(String sql);


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
     * 根据sql语句查询map集合
     *
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql);


    int exeSql(String up_sql);
}


