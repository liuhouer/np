package cn.northpark.mapper;

import cn.northpark.model.Knowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Knowledge record);

    Knowledge selectByPrimaryKey(Integer id);

    List<Knowledge> selectAll();

    int updateByPrimaryKey(Knowledge record);

    List<Knowledge> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String hot_sql);
}