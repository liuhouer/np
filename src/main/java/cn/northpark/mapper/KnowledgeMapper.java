package cn.northpark.mapper;

import cn.northpark.model.Knowledge;
import java.util.List;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Knowledge record);

    Knowledge selectByPrimaryKey(Integer id);

    List<Knowledge> selectAll();

    int updateByPrimaryKey(Knowledge record);
}