
package cn.northpark.service;

import cn.northpark.model.Note;

import java.util.List;
import java.util.Map;

public interface NoteService {

    Note findNote(Integer id);

    List<Note> findAll();

    void addNote(Note note);

    boolean delNote(Integer id);

    boolean updateNote(Note note);

    List<Map<String, Object>> getHotNoteList();

    List<Note> findByCondition(String whereSql, String orderBy);

    /**
     * sql
     *
     * @return
     */
    List<Map<String, Object>> querySqlMap(String sql);
}


