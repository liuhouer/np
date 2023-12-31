
package cn.northpark.service.impl;

import cn.northpark.mapper.NoteMapper;
import cn.northpark.model.Note;
import cn.northpark.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteMapper noteMapper;

    @Override
    public Note findNote(Integer id) {
        return noteMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Note> findAll() {
        return noteMapper.selectAll();
    }

    @Override
    public void addNote(Note note) {
        noteMapper.insert(note);
    }

    @Override
    public boolean delNote(Integer id) {
        noteMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateNote(Note note) {
        noteMapper.updateByPrimaryKey(note);
        return true;
    }


    @Override
    public List<Map<String, Object>> getHotNoteList() {
        return noteMapper.getHotNoteList();
    }

    @Override
    public List<Note> findByCondition(String whereSql, String orderBy) {
        return noteMapper.findByCondition(whereSql,orderBy);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        return noteMapper.querySqlMap(sql);
    }

}

