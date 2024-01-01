package cn.northpark.mapper;

import cn.northpark.model.Poem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Poem record);

    Poem selectByPrimaryKey(Integer id);

    List<Poem> selectAll();

    int updateByPrimaryKey(Poem record);

    List<Poem> querySql(@Param(value = "sqlExpr") String sql);

    List<Poem> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}