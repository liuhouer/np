package cn.northpark.mapper;

import cn.northpark.model.Movies;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoviesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Movies record);

    Movies selectByPrimaryKey(Integer id);

    List<Movies> selectAll();

    int updateByPrimaryKey(Movies record);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String sql);

    List<Movies>  findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}