package cn.northpark.mapper;

import cn.northpark.model.NotifyRemind;
import java.util.List;

public interface NotifyRemindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NotifyRemind record);

    NotifyRemind selectByPrimaryKey(Integer id);

    List<NotifyRemind> selectAll();

    int updateByPrimaryKey(NotifyRemind record);
}