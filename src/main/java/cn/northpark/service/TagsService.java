
package cn.northpark.service;

import cn.northpark.model.Tags;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface TagsService {

    Tags findTags(Integer id);

    List<Tags> findAll();

    void addTags(Tags tags);

    boolean delTags(Integer id);

    boolean updateTags(Tags tags);

    QueryResult<Tags> findByCondition(PageView<Tags> p,
                                      String wheresql, LinkedHashMap<String, String> order);

    QueryResult<Tags> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
    List<Tags> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
    List<Tags> querySql(String sql);


    /**
     * sqlmap
     *
     * @return
     */
    List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView);


    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    int countSql(String sql);


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    int countHql(String wheresql);


}


