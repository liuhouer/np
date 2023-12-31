//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.PoemEnjoyMapper;
//import cn.northpark.manager.PoemEnjoyManager;
//import cn.northpark.model.PoemEnjoy;
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
//public class PoemEnjoyManagerImpl implements PoemEnjoyManager {
//
//    @Autowired
//    private PoemEnjoyMapper poemenjoyMapper;
//
//    @Override
//    public PoemEnjoy findPoemEnjoy(Integer id) {
//        return poemenjoyMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<PoemEnjoy> findAll() {
//        return poemenjoyMapper.selectAll();
//    }
//
//    @Override
//    public void addPoemEnjoy(PoemEnjoy poemenjoy) {
//        poemenjoyMapper.insert(poemenjoy);
//    }
//
//    @Override
//    public boolean delPoemEnjoy(Integer id) {
//        PoemEnjoy poemenjoy = poemenjoyMapper.selectByPrimaryKey(id);
//        poemenjoyMapper.deleteByPrimaryKey(poemenjoy);
//        return true;
//    }
//
//    @Override
//    public boolean updatePoemEnjoy(PoemEnjoy poemenjoy) {
//        poemenjoyMapper.updateByPrimaryKey(poemenjoy);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<PoemEnjoy> findByCondition(PageView<PoemEnjoy> p,
//                                                  String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = poemenjoyMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<PoemEnjoy> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = poemenjoyMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<PoemEnjoy> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return poemenjoyMapper.querySql(sql, PoemEnjoy.class, obj);
//    }
//
//    @Override
//    public List<PoemEnjoy> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return poemenjoyMapper.querySql(sql, PoemEnjoy.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
//        // TODO Auto-generated method stub
//        return poemenjoyMapper.querySQLForMapList(sql, pageView);
//    }
//
//    /* (non-Javadoc)
//     * 根据sql查询条数
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return poemenjoyMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * 根据hql查询条数
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return poemenjoyMapper.countHql(PoemEnjoy.class, wheresql);
//    }
//}
//
