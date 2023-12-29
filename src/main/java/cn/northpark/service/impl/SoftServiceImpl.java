
package cn.northpark.service.impl;

import cn.northpark.mapper.SoftMapper;
import cn.northpark.model.Soft;
import cn.northpark.service.SoftService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class SoftServiceImpl implements SoftService {

    @Autowired
    private SoftMapper softMapper;

    @Override
    public Soft findSoft(Integer id) {
        return softMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Soft> findAll() {
        return softMapper.selectAll();
    }

    @Override
    public void addSoft(Soft soft) {
        softMapper.insert(soft);
    }

    @Override
    public boolean delSoft(Integer id) {
        softMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateSoft(Soft soft) {
        softMapper.updateByPrimaryKey(soft);
        return true;
    }

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Soft> findByCondition(PageView<Soft> p,
//                                             String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = softMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Soft> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = softMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Soft> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return softMapper.querySql(sql, Soft.class, obj);
//    }
//
//    @Override
//    public List<Soft> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return softMapper.querySql(sql, Soft.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
//        // TODO Auto-generated method stub
//        return softMapper.querySQLForMapList(sql, pageView);
//    }
//
//    /* (non-Javadoc)
//     * 根据sql查询条数
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return softMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * 根据hql查询条数
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return softMapper.countHql(Soft.class, wheresql);
//    }
//
//    @Override
//    public void executeSql(String sql) {
//        softMapper.execSQL(sql);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql) {
//        // TODO Auto-generated method stub
//        return softMapper.querySql(sql);
//    }


    @Override
    public QueryResult<Soft> findByCondition(PageView<Soft> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<Soft> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public List<Soft> querySql(String sql, Object... obj) {
        return null;
    }

    @Override
    public List<Soft> querySql(String sql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
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

    @Override
    public void executeSql(String sql) {

    }
}

