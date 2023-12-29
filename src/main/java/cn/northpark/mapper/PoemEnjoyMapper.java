package cn.northpark.mapper;

import cn.northpark.model.PoemEnjoy;
import java.util.List;

public interface PoemEnjoyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoemEnjoy record);

    PoemEnjoy selectByPrimaryKey(Integer id);

    List<PoemEnjoy> selectAll();

    int updateByPrimaryKey(PoemEnjoy record);
}