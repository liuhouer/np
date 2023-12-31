package cn.northpark.mapper;

import cn.northpark.model.NotifyRemindB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotifyRemindBMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NotifyRemindB record);

    NotifyRemindB selectByPrimaryKey(Integer id);

    List<NotifyRemindB> selectAll();

    int updateByPrimaryKey(NotifyRemindB record);

    List<NotifyRemindB> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}