package cn.northpark.mapper;

import cn.northpark.model.Reset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reset record);

    Reset selectByPrimaryKey(Integer id);

    List<Reset> selectAll();

    int updateByPrimaryKey(Reset record);

    List<Reset> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);

}