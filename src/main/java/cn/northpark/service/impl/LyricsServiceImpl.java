//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.LyricsMapper;
//import cn.northpark.manager.LyricsManager;
//import cn.northpark.model.Lyrics;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class LyricsManagerImpl implements LyricsManager {
//
//    @Autowired
//    private LyricsMapper lyricsMapper;
//
//    @Override
//    public Lyrics findLyrics(Integer id) {
//        return lyricsMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Lyrics> findAll() {
//        return lyricsMapper.selectAll();
//    }
//
//    @Override
//    public void addLyrics(Lyrics lyrics) {
//        lyricsMapper.insert(lyrics);
//    }
//
//    @Override
//    public boolean delLyrics(Integer id) {
//        Lyrics lyrics = lyricsMapper.selectByPrimaryKey(id);
//        lyricsMapper.deleteByPrimaryKey(lyrics);
//        return true;
//    }
//
//    @Override
//    public boolean updateLyrics(Lyrics lyrics) {
//        lyricsMapper.updateByPrimaryKey(lyrics);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
//                                               String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = lyricsMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Lyrics> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = lyricsMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.LyricsManager#countSql(java.lang.String)
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return lyricsMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.LyricsManager#countHql(cn.northpark.model.Lyrics, java.lang.String)
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return lyricsMapper.countHql(Lyrics.class, wheresql);
//    }
//
//    @Override
//    public List<Lyrics> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return lyricsMapper.querySql(sql, Lyrics.class, obj);
//    }
//
//    @Override
//    public List<Lyrics> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return lyricsMapper.querySql(sql, Lyrics.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql) {
//        // TODO Auto-generated method stub
//        return lyricsMapper.querySql(sql);
//    }
//
//}
//
