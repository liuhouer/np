
package cn.northpark.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.PoemEnjoy;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface PoemEnjoyManager {

    public PoemEnjoy findPoemEnjoy(Integer id);

    public List<PoemEnjoy> findAll();

    public void addPoemEnjoy(PoemEnjoy poemenjoy);

    public boolean delPoemEnjoy(Integer id);

    public boolean updatePoemEnjoy(PoemEnjoy poemenjoy);

    public QueryResult<PoemEnjoy> findByCondition(PageView<PoemEnjoy> p,
                                                  String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<PoemEnjoy> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
    public List<PoemEnjoy> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
    public List<PoemEnjoy> querySql(String sql);


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


