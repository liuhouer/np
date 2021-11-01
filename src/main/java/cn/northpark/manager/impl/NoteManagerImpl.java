
package cn.northpark.manager.impl;

import cn.northpark.dao.NoteDao;
import cn.northpark.manager.NoteManager;
import cn.northpark.model.Note;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("NoteManager")
public class NoteManagerImpl implements NoteManager {

    @Autowired
    private NoteDao noteDao;

    @Override
    public Note findNote(Integer id) {
        return noteDao.find(id);
    }

    @Override
    public List<Note> findAll() {
        return noteDao.findAll();
    }

    @Override
    public void addNote(Note note) {
        noteDao.save(note);
    }

    @Override
    public boolean delNote(Integer id) {
        Note note = noteDao.find(id);
        noteDao.delete(note);
        return true;
    }

    @Override
    public boolean updateNote(Note note) {
        noteDao.update(note);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Note> findByCondition(PageView<Note> p,
                                             String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = noteDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Note> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = noteDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview, String sql) {
        // TODO Auto-generated method stub


        List<Map<String, Object>> list = noteDao.querySQLForMapList(sql, pageview);

        return list;

    }


    @Override
    public int findmixCount(String whereSql) {
        // TODO Auto-generated method stub
        return noteDao.querySql(whereSql).size();
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.NoteManager#countSql(java.lang.String)
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return noteDao.countSql(sql);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.NoteManager#countHql(cn.northpark.model.User, java.lang.String)
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return noteDao.countHql(Note.class, wheresql);
    }


    /* (non-Javadoc)
     * @see cn.northpark.manager.NoteManager#querySql(java.lang.String)
     */
    @Override
    public List<Note> querySql(String sql) {
        // TODO Auto-generated method stub
        return noteDao.querySql(sql, Note.class);

    }

    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(
            PageView<List<Map<String, Object>>> pageview, String sql) {
        // TODO Auto-generated method stub

        return noteDao.querySQLCountForMapList(sql, pageview);
    }
}

