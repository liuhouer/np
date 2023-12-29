//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.PoemMapper;
//import cn.northpark.manager.PoemManager;
//import cn.northpark.model.Poem;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author bruce
// * @date 2017-03-25
// * @email zhangyang226@gmail.com
// * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
// */
//@Service
//public class PoemManagerImpl implements PoemManager {
//
//    @Autowired
//    private PoemMapper poemMapper;
//
//    @Override
//    public Poem findPoem(Integer id) {
//        return poemMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Poem> findAll() {
//        return poemMapper.selectAll();
//    }
//
//    @Override
//    public void addPoem(Poem poem) {
//        poemMapper.insert(poem);
//    }
//
//    @Override
//    public boolean delPoem(Integer id) {
//        Poem poem = poemMapper.selectByPrimaryKey(id);
//        poemMapper.deleteByPrimaryKey(poem);
//        return true;
//    }
//
//    @Override
//    public boolean updatePoem(Poem poem) {
//        poemMapper.updateByPrimaryKey(poem);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Poem> findByCondition(PageView<Poem> p,
//                                             String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = poemMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Poem> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = poemMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Poem> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return poemMapper.querySql(sql, Poem.class, obj);
//    }
//
//    @Override
//    public List<Poem> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return poemMapper.querySql(sql, Poem.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
//        // TODO Auto-generated method stub
//        return poemMapper.querySQLForMapList(sql, pageView);
//    }
//
//    /* (non-Javadoc)
//     * 根据sql查询条数
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return poemMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * 根据hql查询条数
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return poemMapper.countHql(Poem.class, wheresql);
//    }
//}
//
