package cn.northpark.mapper;

import cn.northpark.model.Eq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Eq record);

    Eq selectByPrimaryKey(Integer id);

    List<Eq> selectAll();

    int updateByPrimaryKey(Eq record);

    List<Eq>  findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}