
package cn.northpark.manager.impl;

import cn.northpark.dao.TopicCommentDao;
import cn.northpark.manager.TopicCommentManager;
import cn.northpark.model.TopicComment;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service("TopicCommentManager")
public class TopicCommentManagerImpl implements TopicCommentManager {

    @Autowired
    private TopicCommentDao topiccommentDao;

    @Override
    public TopicComment findTopicComment(Integer id) {
        return topiccommentDao.find(id);
    }

    @Override
    public List<TopicComment> findAll() {
        return topiccommentDao.findAll();
    }

    @Override
    public void addTopicComment(TopicComment topiccomment) {
        topiccommentDao.save(topiccomment);
    }

    @Override
    public boolean delTopicComment(Integer id) {
        TopicComment topiccomment = topiccommentDao.find(id);
        topiccommentDao.delete(topiccomment);
        return true;
    }

    @Override
    public boolean updateTopicComment(TopicComment topiccomment) {
        topiccommentDao.update(topiccomment);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<TopicComment> findByCondition(PageView<TopicComment> p,
                                                     String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = topiccommentDao.findByCondition(p.getFirstResult(),
                p.getMaxresult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<TopicComment> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = topiccommentDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<TopicComment> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return topiccommentDao.querySql(sql, TopicComment.class, obj);
    }

    @Override
    public List<TopicComment> querySql(String sql) {
        // TODO Auto-generated method stub
        return topiccommentDao.querySql(sql, TopicComment.class);
    }


    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        // TODO Auto-generated method stub
        return topiccommentDao.querySql(sql);
    }


    /* (non-Javadoc)
     * 根据sql查询条数
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return topiccommentDao.countSql(sql);
    }

    /* (non-Javadoc)
     * 根据hql查询条数
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return topiccommentDao.countHql(TopicComment.class, wheresql);
    }


    @Override
    public void executeSql(String sql) {
        // TODO Auto-generated method stub
        topiccommentDao.execSQL(sql);
    }

    @Override
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview, String sql) {


        List<Map<String, Object>> list = topiccommentDao.querySQLForMapList(sql, pageview);

        return list;

    }


    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageview, String sql) {

        return topiccommentDao.querySQLCountForMapList(sql, pageview);
    }

}

