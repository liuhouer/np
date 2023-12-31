
package cn.northpark.service;

import cn.northpark.model.Movies;

import java.util.List;
import java.util.Map;

public interface MoviesService {

     Movies findMovies(Integer id);

     List<Movies> findAll();

     void addMovies(Movies movies);

     boolean delMovies(Integer id);

     boolean updateMovies(Movies movies);

    /**
     * 执行传入的sql，并返回 list map集合
     *
     * @return
     */
    List<Map<String, Object>> querySqlMap(String sql);

    List<Movies> findByCondition(String whereSql, String orderBy);
}


