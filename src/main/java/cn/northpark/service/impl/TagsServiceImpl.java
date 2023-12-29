
package cn.northpark.service.impl;

import cn.northpark.mapper.TagsMapper;
import cn.northpark.model.Tags;
import cn.northpark.service.TagsService;
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
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public Tags findTags(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tags> findAll() {
        return tagsMapper.selectAll();
    }

    @Override
    public void addTags(Tags tags) {
        tagsMapper.insert(tags);
    }

    @Override
    public boolean delTags(Integer id) {
        tagsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateTags(Tags tags) {
        tagsMapper.updateByPrimaryKey(tags);
        return true;
    }

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Tags> findByCondition(PageView<Tags> p,
//                                             String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = tagsMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Tags> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = tagsMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Tags> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return tagsMapper.querySql(sql, Tags.class, obj);
//    }
//
//    @Override
//    public List<Tags> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return tagsMapper.querySql(sql, Tags.class);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView) {
//        // TODO Auto-generated method stub
//        return tagsMapper.querySQLForMapList(sql, pageView);
//    }
//
//    /* (non-Javadoc)
//     * 根据sql查询条数
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return tagsMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * 根据hql查询条数
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return tagsMapper.countHql(Tags.class, wheresql);
//    }


    @Override
    public QueryResult<Tags> findByCondition(PageView<Tags> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<Tags> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public List<Tags> querySql(String sql, Object... obj) {
        return null;
    }

    @Override
    public List<Tags> querySql(String sql) {
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
}

