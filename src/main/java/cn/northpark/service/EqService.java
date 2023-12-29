
package cn.northpark.service;

import cn.northpark.model.Eq;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface EqService {

     Eq findEq(Integer id);

     List<Eq> findAll();

     void addEq(Eq eq);

     boolean delEq(Integer id);

     boolean updateEq(Eq eq);

     QueryResult<Eq> findByCondition(PageView<Eq> p,
                                           String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Eq> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
     List<Eq> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
     List<Eq> querySql(String sql);


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


    /**
     * 执行sql语句
     *
     * @param sql SQL语句
     */
     void executeSql(String sql);


}


