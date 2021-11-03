package cn.northpark.dao.impl;

import cn.northpark.dao.HibernateDao;
import cn.northpark.utils.ReflectManager;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class HibernateDaoImpl<T, PK extends Serializable> implements
        HibernateDao<T, PK> {

    private LinkedHashMap<String, Object> temp = new LinkedHashMap<String, Object>();

    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Class<?> t;

    public HibernateDaoImpl() {
        t = ReflectManager.getFirstGenericTypeSuperclass(this.getClass());
    }


    /* (non-Javadoc)保存
     * @see cn.northpark.dao.HibernateDao#save(java.io.Serializable)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> PK save(T entity) {
        PK pk = null;
        pk = (PK) sessionFactory.getCurrentSession().save(entity);
        sessionFactory.getCurrentSession().flush();
        return pk;
    }

    /* (non-Javadoc)更新
     * @see cn.northpark.dao.HibernateDao#update(java.io.Serializable)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> void update(T entity) {
        // sessionFactory.getCurrentSession().update(entity);
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> void update(LinkedHashMap<String, T> update) {

        this.update(update, null, null);

    }

    /* (non-Javadoc)g根据参数更新
     * @see cn.northpark.dao.HibernateDao#update(java.util.LinkedHashMap, java.lang.String, java.lang.Object[])
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> void update(
            LinkedHashMap<String, T> update, String where, Object[] parameter) {

        String entityName = t.getName();

        // this.sessionFactory.getCurrentSession().createQuery("update "+entityName+"  o  o.name=:name").setParameter("name","xiaoxiao").executeUpdate();

        StringBuilder hql = new StringBuilder();

        hql.append("update " + entityName + " as o set "
                + this.getUpdateStr(update));

        if (where != null && parameter != null) {
            hql.append(" ").append(where);
        }

        System.out.println(hql.toString());
        Query query = sessionFactory.getCurrentSession().createQuery(
                hql.toString());

        if (parameter != null) {
            this.setQueryParameter(query, parameter);
        }
        if (update != null) {
            this.setUpdateParameter(query);
        }

        query.executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void execSQL(String sql) {
        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
        sessionFactory.getCurrentSession().flush();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void execSQL(String sql,Object... obj) {

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);

        //SET PARA
        for (int i = 0; i < obj.length; i++)
        query.setParameter(i, obj[i]);

        //EXEC
        query.executeUpdate();

        sessionFactory.getCurrentSession().flush();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(PK... primaryKeyId) {
        for (PK pkId : primaryKeyId) {
            Object entity = sessionFactory.getCurrentSession().get(t, pkId);
            sessionFactory.getCurrentSession().delete(entity);
            sessionFactory.getCurrentSession().flush();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String where, Object[] parameter) {
        String entityName = t.getName();
        StringBuilder hql = new StringBuilder();
        hql.append("delete " + entityName + " as o");
        if (where != null && parameter != null) {
            hql.append(" ").append(where);
        }

        Query query = sessionFactory.getCurrentSession().createQuery(
                hql.toString());
        if (parameter != null) {
            this.setQueryParameter(query, parameter);
        }
        query.executeUpdate();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public T find(PK primarKeyId) {
        T findObj = null;
        findObj = (T) sessionFactory.getCurrentSession().get(t, primarKeyId);
        sessionFactory.getCurrentSession().flush();
        return findObj;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<T> findAll() {
        String entityName = t.getName();
        String hql = "from " + entityName;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        sessionFactory.getCurrentSession().flush();
        return query.list();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> QueryResult<T> findByCondition(
            int firstResult, int maxResults, String where,
            LinkedHashMap<String, String> orderBy) {

        String entityName = t.getName();

        StringBuilder hql = new StringBuilder();

        hql.append("select o from " + entityName + " as o");

        if (StringUtils.isNotEmpty(where)) {
            hql.append(where);
        }

        if (orderBy != null) {
            hql.append(" order by ").append(this.getOrderStr(orderBy));
        }

        Query query = null;

        if (firstResult != -1 && maxResults != -1) {
            query = sessionFactory.getCurrentSession()
                    .createQuery(hql.toString()).setFirstResult(firstResult)
                    .setMaxResults(maxResults);

        } else {
            query = sessionFactory.getCurrentSession().createQuery(
                    hql.toString());
        }

        QueryResult<T> queryResult = new QueryResult<T>();
        queryResult.setResultlist(query.list());

        sessionFactory.getCurrentSession().flush();

        //查询count

        setTotalRecordByHql(where, entityName, queryResult);


        return queryResult;

    }


    /**
     * @param sql
     * @param pageView
     * @return PageView<List < Map < String, Object>>>
     * @desc 多表联合查询 包含分页 只返回结果集List<map>集合 by bruce
     */
    public List<Map<String, Object>> querySQLForMapList(String sql, PageView<List<Map<String, Object>>> pageView) {
        String resultQueryString = (new StringBuilder(" select t.* from (")).append(sql).append(") as t LIMIT " + pageView.getFirstResult() + "," + pageView.getMaxresult()).toString();
        List<Map<String, Object>> resultlist = querySql(resultQueryString);
        sessionFactory.getCurrentSession().flush();
        return resultlist;
    }


    /**
     * @param sql
     * @param pageView
     * @return PageView<List < Map < String, Object>>>
     * @desc 多表联合查询 只返回封装的pageview  by bruce
     */
    public PageView<List<Map<String, Object>>> querySQLCountForMapList(String sql, PageView<List<Map<String, Object>>> pageView) {

        int countSql = countSql(sql);
        pageView.setTotalrecord(countSql);
        return pageView;
    }


    /**
     * SQL查询操作
     *
     * @param sql SQL语句
     * @param obj 参数列表(顺序对应SQL)
     * @return List<Map < String, Object>>
     */
    public List<Map<String, Object>> querySql(String sql, Object... obj) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0; i < obj.length; i++)
            query.setParameter(i, obj[i]);


        sessionFactory.getCurrentSession().flush();
        return query.list();
    }


    /**
     * SQL查询操作
     *
     * @param clazz 返回Model.clazz
     * @return List<Model>
     */
    public <T extends Serializable> List<T> querySql(String sql, Class<T> clazz, Object... obj) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        for (int i = 0; i < obj.length; i++)
            query.setParameter(i, obj[i]);


        sessionFactory.getCurrentSession().flush();
        return query.addEntity(clazz).list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> QueryResult<T> findByCondition(
            String where) {

        String entityName = t.getName();

        StringBuilder hql = new StringBuilder();

        hql.append("select o from " + entityName + " as o");

        if (StringUtils.isNotEmpty(where)) {
            hql.append(where);
        }


        Query query = null;


        query = sessionFactory.getCurrentSession().createQuery(
                hql.toString());

        QueryResult<T> queryResult = new QueryResult<T>();

        queryResult.setResultlist(query.list());

        sessionFactory.getCurrentSession().flush();

        return queryResult;

    }

    /**
     * 根据where条件通过hql设置总条数
     *
     * @param where
     * @param entityName
     * @param queryResult
     */
    private <T extends Serializable> void setTotalRecordByHql(String where,
                                                              String entityName, QueryResult<T> queryResult) {
        StringBuilder hql2 = new StringBuilder();

        hql2.append("select count(*) from " + entityName + " as o");

        if (StringUtils.isNotEmpty(where)) {
            hql2.append(where);
        }

        Query query2 = sessionFactory.getCurrentSession().createQuery(hql2.toString());

        Long count = (Long) query2.uniqueResult();

        sessionFactory.getCurrentSession().flush();

        queryResult.setTotalrecord(count);
    }


    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int countSql(String sql) {

        String countQueryString = (new StringBuilder(" select count(*) as total from (")).append(sql).append(") as t").toString();
        List<?> countlist = (List<?>) querySql(countQueryString);

        int n = 0;
        if (countlist != null && countlist.size() > 0) {
            n = Integer.valueOf(((Map<String, Object>) countlist.get(0)).get("total").toString());
        }

        return n;

    }

    /**
     * 根据实体查询条数
     *
     * @param clazz
     * @param whereSql
     * @return int
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Serializable> int countHql(Class<T> clazz, String whereSql) {
        String entityName = t.getName();

        StringBuilder hql = new StringBuilder();

        hql.append("select count(*) from " + entityName + " as o");

        if (StringUtils.isNotEmpty(whereSql)) {
            hql.append(whereSql);
        }

        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());

        Long count = (Long) query.uniqueResult();

        sessionFactory.getCurrentSession().flush();
        int nums = count.intValue();

        return nums;

    }


    //抽取的工具类====================================================================================

    private <T extends Serializable> String getUpdateStr(
            LinkedHashMap<String, T> update) {
        StringBuilder updateStr = new StringBuilder();
        int i = 1;
        for (Map.Entry<String, T> entry : update.entrySet()) {
            updateStr.append(entry.getKey()).append("=").append(":u").append(i)
                    .append(",");
            this.temp.put("u" + i, entry.getValue());
            i++;
        }

        updateStr.deleteCharAt(updateStr.length() - 1);

        return updateStr.toString();
    }

    private void setUpdateParameter(Query query) {
        for (Map.Entry<String, Object> entry : this.temp.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    private void setQueryParameter(Query query, Object[] parameter) {
        if (parameter != null) {
            for (int i = 0; i < parameter.length; i++) {
                query.setParameter("p" + (i + 1), parameter[i]);
            }
        }

    }

    private String getOrderStr(LinkedHashMap<String, String> orderBy) {
        StringBuilder orderStr = new StringBuilder();

        for (String key : orderBy.keySet()) {
            orderStr.append(key).append(" ").append(orderBy.get(key))
                    .append(",");
        }

        orderStr.deleteCharAt(orderStr.length() - 1);

        return orderStr.toString();
    }

}
