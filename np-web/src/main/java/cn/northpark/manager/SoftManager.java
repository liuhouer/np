
package cn.northpark.manager;

import cn.northpark.model.Soft;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface SoftManager {

     Soft findSoft(Integer id);

     List<Soft> findAll();

     void addSoft(Soft soft);

     boolean delSoft(Integer id);

     boolean updateSoft(Soft soft);

     QueryResult<Soft> findByCondition(PageView<Soft> p,
                                             String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Soft> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
     List<Soft> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
     List<Soft> querySql(String sql);

    /**
     * sql
     *
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql);


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
     * 直接执行sql语句  更新、删除..
     *
     * @param sql SQL语句
     */
     void executeSql(String sql);


}


