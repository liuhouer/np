
package cn.northpark.manager;

import cn.northpark.model.Poem;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface PoemManager {

     Poem findPoem(Integer id);

     List<Poem> findAll();

     void addPoem(Poem poem);

     boolean delPoem(Integer id);

     boolean updatePoem(Poem poem);

     QueryResult<Poem> findByCondition(PageView<Poem> p,
                                             String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Poem> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
     List<Poem> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
     List<Poem> querySql(String sql);


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


