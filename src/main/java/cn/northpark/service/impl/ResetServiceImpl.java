//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.ResetMapper;
//import cn.northpark.manager.ResetManager;
//import cn.northpark.model.Reset;
//import cn.northpark.utils.page.MyConstant;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//
//@Service
//public class ResetManagerImpl implements ResetManager {
//
//    @Autowired
//    private ResetMapper resetMapper;
//
//    @Override
//    public Reset findReset(Integer id) {
//        return resetMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Reset> findAll() {
//        return resetMapper.selectAll();
//    }
//
//    @Override
//    public void addReset(Reset reset) {
//        resetMapper.insert(reset);
//    }
//
//    @Override
//    public boolean delReset(Integer id) {
//        Reset reset = resetMapper.selectByPrimaryKey(id);
//        resetMapper.deleteByPrimaryKey(reset);
//        return true;
//    }
//
//    @Override
//    public boolean updateReset(Reset reset) {
//        resetMapper.updateByPrimaryKey(reset);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Reset> findByCondition(PageView<Reset> p,
//                                              String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = resetMapper.findByCondition(p.getFirstResult(),
//                MyConstant.MAX_RESULT, wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Reset> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = resetMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//}
//
