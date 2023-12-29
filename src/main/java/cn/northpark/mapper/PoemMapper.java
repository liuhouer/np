package cn.northpark.mapper;

import cn.northpark.model.Poem;
import java.util.List;

public interface PoemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Poem record);

    Poem selectByPrimaryKey(Integer id);

    List<Poem> selectAll();

    int updateByPrimaryKey(Poem record);
}