package cn.northpark.mapper;

import cn.northpark.model.Soft;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SoftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Soft record);

    Soft selectByPrimaryKey(Integer id);

    List<Soft> selectAll();

    int updateByPrimaryKey(Soft record);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String sql);

    List<Soft> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}