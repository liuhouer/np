
package cn.northpark.manager.impl;

import cn.northpark.dao.LyricsCommentDao;
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.model.LyricsComment;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("LyricsCommentManager")
public class LyricsCommentManagerImpl implements LyricsCommentManager {

    @Autowired
    private LyricsCommentDao lyricscommentDao;

    @Override
    public LyricsComment findLyricsComment(Integer id) {
        return lyricscommentDao.find(id);
    }

    @Override
    public List<LyricsComment> findAll() {
        return lyricscommentDao.findAll();
    }

    @Override
    public void addLyricsComment(LyricsComment lyricscomment) {
        lyricscommentDao.save(lyricscomment);
    }

    @Override
    public boolean delLyricsComment(Integer id) {
        LyricsComment lyricscomment = lyricscommentDao.find(id);
        lyricscommentDao.delete(lyricscomment);
        return true;
    }

    @Override
    public boolean updateLyricsComment(LyricsComment lyricscomment) {
        lyricscommentDao.update(lyricscomment);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<LyricsComment> findByCondition(PageView<LyricsComment> p,
                                                      String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = lyricscommentDao.findByCondition(p.getFirstResult(),
                MyConstant.MAX_RESULT, wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<LyricsComment> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = lyricscommentDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<LyricsComment> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return lyricscommentDao.querySql(sql, LyricsComment.class, obj);
    }

    @Override
    public List<LyricsComment> querySql(String sql) {
        // TODO Auto-generated method stub
        return lyricscommentDao.querySql(sql, LyricsComment.class);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
        // TODO Auto-generated method stub
        return lyricscommentDao.querySQLForMapList(sql, pageView);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.LyricsCommentManager#countSql(java.lang.String)
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return lyricscommentDao.countSql(sql);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.LyricsCommentManager#countHql(cn.northpark.model.Lyrics, java.lang.String)
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return lyricscommentDao.countHql(LyricsComment.class, wheresql);
    }

    @Override
    public List<Map<String, Object>> findmixByCondition(
            PageView<List<Map<String, Object>>> pageView, String sql) {
        // TODO Auto-generated method stub
        return lyricscommentDao.querySQLForMapList(sql, pageView);
    }

    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(
            PageView<List<Map<String, Object>>> pageView, String sql) {
        // TODO Auto-generated method stub
        return lyricscommentDao.querySQLCountForMapList(sql, pageView);
    }
}

