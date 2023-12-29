package cn.northpark.mapper;

import cn.northpark.model.Movies;
import java.util.List;

public interface MoviesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Movies record);

    Movies selectByPrimaryKey(Integer id);

    List<Movies> selectAll();

    int updateByPrimaryKey(Movies record);
}