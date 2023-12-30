package cn.northpark.mapper;

import cn.northpark.model.NotifyRemindB;

import java.util.List;

public interface NotifyRemindBMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NotifyRemindB record);

    NotifyRemindB selectByPrimaryKey(Integer id);

    List<NotifyRemindB> selectAll();

    int updateByPrimaryKey(NotifyRemindB record);
}