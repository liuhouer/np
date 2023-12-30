package cn.northpark.mapper;

import cn.northpark.model.Tags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tags record);

    Tags selectByPrimaryKey(Integer id);

    List<Tags> selectAll();

    int updateByPrimaryKey(Tags record);

    List<Tags> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}