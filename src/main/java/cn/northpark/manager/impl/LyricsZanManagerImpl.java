
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsCommentDao;
import cn.northpark.dao.LyricsZanDao;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.model.LyricsZan;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@Service("LyricsZanManager")
public class LyricsZanManagerImpl implements LyricsZanManager {

    @Autowired
    private LyricsZanDao lyricszanDao;
    @Autowired
    private LyricsCommentDao lyricsCommentDao;

    @Override
    public LyricsZan findLyricsZan(Integer id) {
        return lyricszanDao.find(id);
    }

    @Override
    public List<LyricsZan> findAll() {
        return lyricszanDao.findAll();
    }

    @Override
    public void addLyricsZan(LyricsZan lyricszan) {
        lyricszanDao.save(lyricszan);
    }

    @Override
    public boolean delLyricsZan(Integer id) {
        LyricsZan lyricszan = lyricszanDao.find(id);
        lyricszanDao.delete(lyricszan);
        return true;
    }

    @Override
    public boolean updateLyricsZan(LyricsZan lyricszan) {
        lyricszanDao.update(lyricszan);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<LyricsZan> findByCondition(PageView<LyricsZan> p,
                                                  String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = lyricszanDao.findByCondition(p.getFirstResult(),
                MyConstant.MAXRESULT, wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<LyricsZan> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = lyricszanDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public int getCommentNumByLRC(String lyricsid) {
        // TODO Auto-generated method stub
        return lyricsCommentDao.findByCondition(" where lyricsid = '" + lyricsid + "' ").getResultlist().size();
    }

    @Override
    public int getZanNumByLRC(String lyricsid) {
        // TODO Auto-generated method stub
        return lyricszanDao.findByCondition(" where lyricsid = '" + lyricsid + "' ").getResultlist().size();
    }

    @Override
    public List<Map<String, Object>> mixSqlQuery(String sql) {
        // TODO Auto-generated method stub

        return lyricszanDao.querySql(sql);
    }

    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return lyricszanDao.countHql(LyricsZan.class, wheresql);
    }
}

