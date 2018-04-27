
package cn.northpark.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Tags;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface TagsManager {

    public Tags findTags(Integer id);

    public List<Tags> findAll();

    public void addTags(Tags tags);

    public boolean delTags(Integer id);

    public boolean updateTags(Tags tags);

    public QueryResult<Tags> findByCondition(PageView<Tags> p,
                                             String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<Tags> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
    public List<Tags> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
    public List<Tags> querySql(String sql);


    /**
     * sqlmap
     *
     * @return
     */
    public List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView);


    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countSql(String sql);


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countHql(String wheresql);


}


