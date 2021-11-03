package cn.northpark.dao;

import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface HibernateDao<T, PK extends Serializable> {

    <T extends Serializable> PK save(T entity);

    <T extends Serializable> void update(T entity);

    <T extends Serializable> void update(LinkedHashMap<String, T> update);

    <T extends Serializable> void update(LinkedHashMap<String, T> update,
                                         String where,
                                         Object[] parameter);

    <T extends Serializable> void delete(T entity);

    /**
     * 执行sql返回void
     * @param sql
     */
    void execSQL(String sql);

    /**
     * 执行预编译sql返回void
     * @param sql
     */
    void execSQL(String sql, Object... obj);

    void delete(PK... primaryKeyId);

    void delete(String where, Object[] parameter);

    T find(PK primarKeyId);

    List<T> findAll();

    <T extends Serializable> QueryResult<T> findByCondition(
            int firstResult, int maxResults, String where,
            LinkedHashMap<String, String> orderBy);


    <T extends Serializable> QueryResult<T> findByCondition(String where);


    /**
     * SQL查询操作
     *
     * @param sql SQL语句
     * @param obj 参数列表(顺序对应SQL)
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> querySql(String sql, Object... obj);

    /**
     * SQL查询操作
     *
     * @param clazz 返回Model.clazz
     * @return List<Model>
     */
    <T extends Serializable> List<T> querySql(String sql, Class<T> clazz, Object... obj);


    /**
     * 根据定义的pageView+ sql返回集合
     *
     * @param sql
     * @param pageView
     * @return
     */
    List<Map<String, Object>> querySQLForMapList(String sql, PageView<List<Map<String, Object>>> pageView);


    /**
     * @param sql
     * @param pageView
     * @return PageView<List < Map < String, Object>>>
     * @desc 多表联合查询 只返回封装的pageView
     */
    PageView<List<Map<String, Object>>> querySQLCountForMapList(String sql, PageView<List<Map<String, Object>>> pageView);

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
     * @param clazz
     * @param whereSql
     * @return int
     */
    <T extends Serializable> int countHql(Class<T> clazz, String whereSql);
}
