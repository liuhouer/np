
package cn.northpark.service.impl;

import cn.northpark.mapper.NoteMapper;
import cn.northpark.model.Note;
import cn.northpark.service.NoteService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

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
    public QueryResult<Note> findByCondition(PageView<Note> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<Note> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql) {
        return null;
    }

    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
        return null;
    }

    @Override
    public int findmixCount(String whereSql) {
        return 0;
    }

    @Override
    public int countSql(String sql) {
        return 0;
    }

    @Override
    public int countHql(String wheresql) {
        return 0;
    }

    @Override
    public List<Note> querySql(String sql) {
        return null;
    }

//    @Override
//    public QueryResult<Note> findByCondition(PageView<Note> p,
//                                             String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = noteMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Note> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = noteMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql) {
//        // TODO Auto-generated method stub
//
//
//        List<Map<String, Object>> list = noteMapper.querySQLForMapList(sql, pageView);
//
//        return list;
//
//    }
//
//
//    @Override
//    public int findmixCount(String whereSql) {
//        // TODO Auto-generated method stub
//        return noteMapper.querySql(whereSql).size();
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.NoteManager#countSql(java.lang.String)
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return noteMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.NoteManager#countHql(cn.northpark.model.User, java.lang.String)
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return noteMapper.countHql(Note.class, wheresql);
//    }
//
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.NoteManager#querySql(java.lang.String)
//     */
//    @Override
//    public List<Note> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return noteMapper.querySql(sql, Note.class);
//
//    }

    @Override
    public List<Map<String, Object>> getHotNoteList() {
        return noteMapper.getHotNoteList();
    }

//    @Override
//    public PageView<List<Map<String, Object>>> getMixMapPage(
//            PageView<List<Map<String, Object>>> pageView, String sql) {
//        // TODO Auto-generated method stub
//
//        return noteMapper.querySQLCountForMapList(sql, pageView);
//    }
}

