
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.PoemDao;
import cn.northpark.manager.PoemManager;
import cn.northpark.model.Poem;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service("PoemManager")
public class PoemManagerImpl implements PoemManager {

    @Autowired
    private PoemDao poemDao;

    @Override
    public Poem findPoem(Integer id) {
        return poemDao.find(id);
    }

    @Override
    public List<Poem> findAll() {
        return poemDao.findAll();
    }

    @Override
    public void addPoem(Poem poem) {
        poemDao.save(poem);
    }

    @Override
    public boolean delPoem(Integer id) {
        Poem poem = poemDao.find(id);
        poemDao.delete(poem);
        return true;
    }

    @Override
    public boolean updatePoem(Poem poem) {
        poemDao.update(poem);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Poem> findByCondition(PageView<Poem> p,
                                             String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = poemDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Poem> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = poemDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Poem> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return poemDao.querySql(sql, Poem.class, obj);
    }

    @Override
    public List<Poem> querySql(String sql) {
        // TODO Auto-generated method stub
        return poemDao.querySql(sql, Poem.class);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
        // TODO Auto-generated method stub
        return poemDao.QuerySQLForMapList(sql, pageView);
    }

    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return poemDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return poemDao.countHql(Poem.class, wheresql);
    }
}

