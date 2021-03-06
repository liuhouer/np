package cn.northpark.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@SuppressWarnings("hiding")
public interface HibernateDao<T, PK extends Serializable> {

    public <T extends Serializable> PK save(T entity);

    public <T extends Serializable> void update(T entity);

    public <T extends Serializable> void update(LinkedHashMap<String, T> update);

    public <T extends Serializable> void update(
            LinkedHashMap<String, T> update, String where, Object[] parameter);

    public <T extends Serializable> void delete(T entity);

    //执行sql返回void
    public void executess(String sql);

    public void delete(@SuppressWarnings("unchecked") PK... primaryKeyId);

    public void delete(String where, Object[] parameter);

    public T find(PK primarKeyId);

    public List<T> findAll();

    public <T extends Serializable> QueryResult<T> findByCondition(
            int firstResult, int maxResults, String where,
            LinkedHashMap<String, String> orderBy);


    public <T extends Serializable> QueryResult<T> findByCondition(String where);


    /**
     * SQL查询操作
     *
     * @param sql SQL语句
     * @param obj 参数列表(顺序对应SQL)
     * @return List<Map   <   String   ,       Object>>
     */
    public List<Map<String, Object>> querySql(String sql, Object... obj);

    /**
     * SQL查询操作
     *
     * @param clazz 返回Model.clazz
     * @return List<Model>
     */
    public <T extends Serializable> List<T> querySql(String sql, Class<T> clazz, Object... obj);


    public List<Map<String, Object>> QuerySQLForMapList(String sql, PageView<List<Map<String, Object>>> pageView);


    /**
     * @param sql
     * @param pageView
     * @return PageView<List   <   Map   <   String   ,       Object>>>
     * @desc 多表联合查询 只返回封装的pageview  by bruce
     */
    public PageView<List<Map<String, Object>>> QuerySQLCountForMapList(String sql, PageView<List<Map<String, Object>>> pageView);

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
    public <T extends Serializable> int countHql(Class<T> clazz, String wheresql);
}
