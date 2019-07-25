
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsDao;
import cn.northpark.manager.LyricsManager;
import cn.northpark.model.Lyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@Service("LyricsManager")
public class LyricsManagerImpl implements LyricsManager {

    @Autowired
    private LyricsDao lyricsDao;

    @Override
    public Lyrics findLyrics(Integer id) {
        return lyricsDao.find(id);
    }

    @Override
    public List<Lyrics> findAll() {
        return lyricsDao.findAll();
    }

    @Override
    public void addLyrics(Lyrics lyrics) {
        lyricsDao.save(lyrics);
    }

    @Override
    public boolean delLyrics(Integer id) {
        Lyrics lyrics = lyricsDao.find(id);
        lyricsDao.delete(lyrics);
        return true;
    }

    @Override
    public boolean updateLyrics(Lyrics lyrics) {
        lyricsDao.update(lyrics);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
                                               String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = lyricsDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Lyrics> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = lyricsDao.findByCondition(
                wheresql);
        return qrs;
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.LyricsManager#countSql(java.lang.String)
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return lyricsDao.countSql(sql);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.LyricsManager#countHql(cn.northpark.model.Lyrics, java.lang.String)
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return lyricsDao.countHql(Lyrics.class, wheresql);
    }

    @Override
    public List<Lyrics> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return lyricsDao.querySql(sql, Lyrics.class, obj);
    }

    @Override
    public List<Lyrics> querySql(String sql) {
        // TODO Auto-generated method stub
        return lyricsDao.querySql(sql, Lyrics.class);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        // TODO Auto-generated method stub
        return lyricsDao.querySql(sql);
    }

}

