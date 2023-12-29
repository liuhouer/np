package cn.northpark.mapper;

import cn.northpark.model.Lrcs;
import java.util.List;

public interface LrcsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lrcs record);

    Lrcs selectByPrimaryKey(Integer id);

    List<Lrcs> selectAll();

    int updateByPrimaryKey(Lrcs record);
}