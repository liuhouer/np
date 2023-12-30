package cn.northpark.mapper;

import cn.northpark.model.Note;

import java.util.List;
import java.util.Map;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    Note selectByPrimaryKey(Integer id);

    List<Note> selectAll();

    int updateByPrimaryKey(Note record);

    List<Map<String, Object>> getHotNoteList();

}