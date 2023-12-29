
package cn.northpark.service.impl;

import cn.northpark.mapper.TopicCommentMapper;
import cn.northpark.model.TopicComment;
import cn.northpark.service.TopicCommentService;
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
@Service
public class TopicCommentServiceImpl implements TopicCommentService {

    @Autowired
    private TopicCommentMapper topiccommentMapper;

    @Override
    public TopicComment findTopicComment(Integer id) {
        return topiccommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TopicComment> findAll() {
        return topiccommentMapper.selectAll();
    }

    @Override
    public void addTopicComment(TopicComment topiccomment) {
        topiccommentMapper.insert(topiccomment);
    }

    @Override
    public boolean delTopicComment(Integer id) {
        topiccommentMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateTopicComment(TopicComment topiccomment) {
        topiccommentMapper.updateByPrimaryKey(topiccomment);
        return true;
    }

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<TopicComment> findByCondition(PageView<TopicComment> p,
//                                                     String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = topiccommentMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<TopicComment> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = topiccommentMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<TopicComment> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return topiccommentMapper.querySql(sql, TopicComment.class, obj);
//    }
//
//    @Override
//    public List<TopicComment> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return topiccommentMapper.querySql(sql, TopicComment.class);
//    }
//
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql) {
//        // TODO Auto-generated method stub
//        return topiccommentMapper.querySql(sql);
//    }
//
//
//    /* (non-Javadoc)
//     * 根据sql查询条数
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return topiccommentMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * 根据hql查询条数
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return topiccommentMapper.countHql(TopicComment.class, wheresql);
//    }
//
//
//    @Override
//    public void executeSql(String sql) {
//        // TODO Auto-generated method stub
//        topiccommentMapper.execSQL(sql);
//    }
//
//    @Override
//    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql) {
//
//
//        List<Map<String, Object>> list = topiccommentMapper.querySQLForMapList(sql, pageView);
//
//        return list;
//
//    }
//
//
//    @Override
//    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
//
//        return topiccommentMapper.querySQLCountForMapList(sql, pageView);
//    }


    @Override
    public QueryResult<TopicComment> findByCondition(PageView<TopicComment> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<TopicComment> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public List<TopicComment> querySql(String sql, Object... obj) {
        return null;
    }

    @Override
    public List<TopicComment> querySql(String sql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
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

    @Override
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql) {
        return null;
    }

    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
        return null;
    }
}

