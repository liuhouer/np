package cn.northpark.mapper;

import cn.northpark.model.Eq;
import java.util.List;

public interface EqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Eq record);

    Eq selectByPrimaryKey(Integer id);

    List<Eq> selectAll();

    int updateByPrimaryKey(Eq record);

    void execSQL(String sql);

}