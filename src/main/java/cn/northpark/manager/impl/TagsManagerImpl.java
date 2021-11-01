
package cn.northpark.manager.impl;

import cn.northpark.dao.TagsDao;
import cn.northpark.manager.TagsManager;
import cn.northpark.model.Tags;
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
@Service("TagsManager")
public class TagsManagerImpl implements TagsManager {

    @Autowired
    private TagsDao tagsDao;

    @Override
    public Tags findTags(Integer id) {
        return tagsDao.find(id);
    }

    @Override
    public List<Tags> findAll() {
        return tagsDao.findAll();
    }

    @Override
    public void addTags(Tags tags) {
        tagsDao.save(tags);
    }

    @Override
    public boolean delTags(Integer id) {
        Tags tags = tagsDao.find(id);
        tagsDao.delete(tags);
        return true;
    }

    @Override
    public boolean updateTags(Tags tags) {
        tagsDao.update(tags);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Tags> findByCondition(PageView<Tags> p,
                                             String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = tagsDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Tags> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = tagsDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Tags> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return tagsDao.querySql(sql, Tags.class, obj);
    }

    @Override
    public List<Tags> querySql(String sql) {
        // TODO Auto-generated method stub
        return tagsDao.querySql(sql, Tags.class);
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
        // TODO Auto-generated method stub
        return tagsDao.querySQLForMapList(sql, pageView);
    }

    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return tagsDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return tagsDao.countHql(Tags.class, wheresql);
    }
}

