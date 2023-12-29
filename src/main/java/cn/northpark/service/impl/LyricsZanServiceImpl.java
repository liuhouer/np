//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.LyricsCommentMapper;
//import cn.northpark.dao.LyricsZanMapper;
//import cn.northpark.manager.LyricsZanManager;
//import cn.northpark.model.LyricsZan;
//import cn.northpark.utils.page.MyConstant;
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
//public class LyricsZanManagerImpl implements LyricsZanManager {
//
//    @Autowired
//    private LyricsZanMapper lyricszanMapper;
//    @Autowired
//    private LyricsCommentMapper lyricsCommentMapper;
//
//    @Override
//    public LyricsZan findLyricsZan(Integer id) {
//        return lyricszanMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<LyricsZan> findAll() {
//        return lyricszanMapper.selectAll();
//    }
//
//    @Override
//    public void addLyricsZan(LyricsZan lyricszan) {
//        lyricszanMapper.insert(lyricszan);
//    }
//
//    @Override
//    public boolean delLyricsZan(Integer id) {
//        LyricsZan lyricszan = lyricszanMapper.selectByPrimaryKey(id);
//        lyricszanMapper.deleteByPrimaryKey(lyricszan);
//        return true;
//    }
//
//    @Override
//    public boolean updateLyricsZan(LyricsZan lyricszan) {
//        lyricszanMapper.updateByPrimaryKey(lyricszan);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<LyricsZan> findByCondition(PageView<LyricsZan> p,
//                                                  String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = lyricszanMapper.findByCondition(p.getFirstResult(),
//                MyConstant.MAX_RESULT, wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<LyricsZan> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = lyricszanMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public int getCommentNumByLRC(String lyricsid) {
//        // TODO Auto-generated method stub
//        return lyricsCommentMapper.findByCondition(" where lyricsid = '" + lyricsid + "' ").getResultList().size();
//    }
//
//    @Override
//    public int getZanNumByLRC(String lyricsid) {
//        // TODO Auto-generated method stub
//        return lyricszanMapper.findByCondition(" where lyricsid = '" + lyricsid + "' ").getResultList().size();
//    }
//
//    @Override
//    public List<Map<String, Object>> mixSqlQuery(String sql,Object... objects) {
//        // TODO Auto-generated method stub
//
//        return lyricszanMapper.querySql(sql,objects);
//    }
//
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return lyricszanMapper.countHql(LyricsZan.class, wheresql);
//    }
//}
//
