
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.VpsDao;
import cn.northpark.manager.VpsManager;
import cn.northpark.model.Vps;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service("VpsManager")
public class VpsManagerImpl implements VpsManager {

    @Autowired
    private VpsDao vpsDao;

    @Override
    public Vps findVps(Integer id) {
        return vpsDao.find(id);
    }

    @Override
    public List<Vps> findAll() {
        return vpsDao.findAll();
    }

    @Override
    public void addVps(Vps vps) {
        vpsDao.save(vps);
    }

    @Override
    public boolean delVps(Integer id) {
        Vps vps = vpsDao.find(id);
        vpsDao.delete(vps);
        return true;
    }

    @Override
    public boolean updateVps(Vps vps) {
        vpsDao.update(vps);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Vps> findByCondition(PageView<Vps> p,
                                            String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = vpsDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Vps> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = vpsDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Vps> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return vpsDao.querySql(sql, Vps.class, obj);
    }

    @Override
    public List<Vps> querySql(String sql) {
        // TODO Auto-generated method stub
        return vpsDao.querySql(sql, Vps.class);
    }


    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return vpsDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return vpsDao.countHql(Vps.class, wheresql);
    }


    @Override
    public void executeSql(String sql) {
        // TODO Auto-generated method stub
        vpsDao.executess(sql);
    }

    @Override
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview, String sql) {


        List<Map<String, Object>> list = vpsDao.QuerySQLForMapList(sql, pageview);

        return list;

    }


    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageview, String sql) {

        return vpsDao.QuerySQLCountForMapList(sql, pageview);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        // TODO Auto-generated method stub
        return vpsDao.querySql(sql);
    }

}

