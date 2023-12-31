package cn.northpark.mapper;

import cn.northpark.model.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    Note selectByPrimaryKey(Integer id);

    List<Note> selectAll();

    int updateByPrimaryKey(Note record);

    List<Map<String, Object>> getHotNoteList();

    List<Note> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);

    List<Map<String, Object>> querySqlMap(@Param(value = "sqlExpr") String sql);

}