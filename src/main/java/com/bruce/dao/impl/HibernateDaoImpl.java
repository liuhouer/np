package com.bruce.dao.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.dao.HibernateDao;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;
import com.bruce.utils.ReflectManager;

@SuppressWarnings({ "unchecked", "hiding" })
public abstract class HibernateDaoImpl<T, PK extends Serializable> implements
		HibernateDao<T, PK> {

	private LinkedHashMap<String, Object> temp = new LinkedHashMap<String, Object>();

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends Serializable> PK save(T entity) {
		PK pk = null;
		pk = (PK) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends Serializable> void update(T entity) {
		// sessionFactory.getCurrentSession().update(entity);
				sessionFactory.getCurrentSession().saveOrUpdate(entity);
				sessionFactory.getCurrentSession().flush();
	}

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

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends Serializable> void update(LinkedHashMap<String, T> update) {

		this.update(update, null, null);

	}

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
	public  void executess(String sql) {
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
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
		queryResult.setTotalrecord((long) query.list().size());

		sessionFactory.getCurrentSession().flush();
		return queryResult;

	}
	
	/**
	 * 
	 * @desc  多表联合查询 包含分页 返回List<map>集合 by bruce 
	 * @param sql
	 * @param pageView
	 * @return PageView<List<Map<String, Object>>>
	 */
	@SuppressWarnings("rawtypes")
	public PageView<List<Map<String, Object>>> QuerySQLForMapList(String sql, PageView<List<Map<String, Object>>> pageView) {
		int totalCount = pageView.getTotalrecord();
			if (totalCount >= 1) {
				String resultQueryString = (new StringBuilder(" select t.* from (")).append(sql).append(") as t LIMIT " +((pageView.getCurrentpage()) * pageView.getMaxresult() )+ "," + pageView.getMaxresult()).toString();
				List<Map<String, Object>> resultlist = querySql(resultQueryString);
				pageView.setMapRecords(resultlist);
				sessionFactory.getCurrentSession().flush();
			}
		return pageView;
	}
	
	
	/**
	 * SQL查询操作
	 * 
	 * @param sql
	 *            SQL语句
	 * @param obj
	 *            参数列表(顺序对应SQL)
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
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
	 * @param clazz
	 *            返回Model.clazz
	 * @return List<Model>
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> List<T> querySql(String sql, Class<T> clazz, Object... obj) {
		SQLQuery  query = sessionFactory.getCurrentSession().createSQLQuery(sql);
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
		queryResult.setTotalrecord((long) query.list().size());

		
		sessionFactory.getCurrentSession().flush();
		return queryResult;

	}

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

}
