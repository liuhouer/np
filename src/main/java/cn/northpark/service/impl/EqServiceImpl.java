
package cn.northpark.service.impl;

import cn.northpark.mapper.EqMapper;
import cn.northpark.model.Eq;
import cn.northpark.service.EqService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class EqServiceImpl implements EqService {

    @Autowired
    private EqMapper eqMapper;

    @Override
    public Eq findEq(Integer id) {
        return eqMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Eq> findAll() {
        return eqMapper.selectAll();
    }

    @Override
    public void addEq(Eq eq) {
        eqMapper.insert(eq);
    }

    @Override
    public boolean delEq(Integer id) {
        eqMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateEq(Eq eq) {
        eqMapper.updateByPrimaryKey(eq);
        return true;
    }

    @Override
    public QueryResult<Eq> findByCondition(PageView<Eq> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<Eq> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public List<Eq> querySql(String sql, Object... obj) {
        return null;
    }

    @Override
    public List<Eq> querySql(String sql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
        return null;
    }

    @Override
    public int countSql(String sql) {
        return 0;
    }

    @Override
    public int countHql(String wheresql) {
        return 0;
    }

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Eq> findByCondition(PageView<Eq> p,
//                                           String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = eqMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Eq> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = eqMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Eq> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return eqMapper.querySql(sql, Eq.class, obj);
//    }
//
//    @Override
//    public List<Eq> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return eqMapper.querySql(sql, Eq.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
//        // TODO Auto-generated method stub
//        return eqMapper.querySQLForMapList(sql, pageView);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.EqManager#countSql(java.lang.String)
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return eqMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.EqManager#countHql(cn.northpark.model.Eq, java.lang.String)
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return eqMapper.countHql(Eq.class, wheresql);
//    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.EqManager#executeSql(java.lang.String)
     */
    @Override
    public void executeSql(String sql) {
        eqMapper.execSQL(sql);
    }
}

