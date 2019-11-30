
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.SoftDao;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Soft;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;


/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service("SoftManager")
public class SoftManagerImpl implements SoftManager {

    @Autowired
    private SoftDao softDao;

    @Override
    public Soft findSoft(Integer id) {
        return softDao.find(id);
    }

    @Override
    public List<Soft> findAll() {
        return softDao.findAll();
    }

    @Override
    public void addSoft(Soft soft) {
        softDao.save(soft);
    }

    @Override
    public boolean delSoft(Integer id) {
        Soft soft = softDao.find(id);
        softDao.delete(soft);
        return true;
    }

    @Override
    public boolean updateSoft(Soft soft) {
        softDao.update(soft);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Soft> findByCondition(PageView<Soft> p,
                                             String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = softDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Soft> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = softDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Soft> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return softDao.querySql(sql, Soft.class, obj);
    }

    @Override
    public List<Soft> querySql(String sql) {
        // TODO Auto-generated method stub
        return softDao.querySql(sql, Soft.class);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
        // TODO Auto-generated method stub
        return softDao.QuerySQLForMapList(sql, pageView);
    }

    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return softDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return softDao.countHql(Soft.class, wheresql);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        // TODO Auto-generated method stub
        return softDao.querySql(sql);
    }
}

