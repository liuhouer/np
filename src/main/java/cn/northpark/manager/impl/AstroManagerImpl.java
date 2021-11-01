
package cn.northpark.manager.impl;

import cn.northpark.dao.AstroDao;
import cn.northpark.manager.AstroManager;
import cn.northpark.model.Astro;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service("AstroManager")
public class AstroManagerImpl implements AstroManager {

    @Autowired
    private AstroDao astroDao;

    @Override
    public Astro findAstro(Integer id) {
        return astroDao.find(id);
    }

    @Override
    public List<Astro> findAll() {
        return astroDao.findAll();
    }

    @Override
    public void addAstro(Astro astro) {
        astroDao.save(astro);
    }

    @Override
    public boolean delAstro(Integer id) {
        Astro astro = astroDao.find(id);
        astroDao.delete(astro);
        return true;
    }

    @Override
    public boolean updateAstro(Astro astro) {
        astroDao.update(astro);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Astro> findByCondition(PageView<Astro> p,
                                              String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = astroDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Astro> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = astroDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Astro> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return astroDao.querySql(sql, Astro.class, obj);
    }

    @Override
    public List<Astro> querySql(String sql) {
        // TODO Auto-generated method stub
        return astroDao.querySql(sql, Astro.class);
    }


    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return astroDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(Astro m, String wheresql) {
        // TODO Auto-generated method stub
        return astroDao.countHql(m.getClass(), wheresql);
    }
}

