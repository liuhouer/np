package cn.northpark.mapper;

import cn.northpark.model.Soft;
import java.util.List;

public interface SoftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Soft record);

    Soft selectByPrimaryKey(Integer id);

    List<Soft> selectAll();

    int updateByPrimaryKey(Soft record);
}