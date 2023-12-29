package cn.northpark.mapper;

import cn.northpark.model.Reset;
import java.util.List;

public interface ResetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reset record);

    Reset selectByPrimaryKey(Integer id);

    List<Reset> selectAll();

    int updateByPrimaryKey(Reset record);
}