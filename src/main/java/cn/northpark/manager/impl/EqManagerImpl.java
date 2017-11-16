
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.EqDao;
import cn.northpark.manager.EqManager;
import cn.northpark.model.Eq;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Service("EqManager")
public class EqManagerImpl implements EqManager {

    @Autowired
	private EqDao eqDao;

	@Override
	public Eq findEq(Integer id) {
		return eqDao.find(id);
	}

	@Override
	public List<Eq> findAll() {
		return eqDao.findAll();
	}

	@Override
	public void addEq(Eq eq) {
		eqDao.save(eq);
	}

	@Override
	public boolean delEq(Integer id) {
		Eq eq = eqDao.find(id);
		eqDao.delete(eq);
		return true;
	}

	@Override
	public boolean updateEq(Eq eq) {
		eqDao.update(eq);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Eq> findByCondition(PageView<Eq> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = eqDao.findByCondition(p.getFirstResult(),
				p.getMaxresult(), wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Eq> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = eqDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<Eq> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return eqDao.querySql(sql, Eq.class, obj);
	}

	@Override
	public List<Eq> querySql(String sql) {
		// TODO Auto-generated method stub
		return eqDao.querySql(sql, Eq.class);
	}

	@Override
	public List<Map<String, Object>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView) {
		// TODO Auto-generated method stub
		return eqDao.QuerySQLForMapList(sql, pageView);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.EqManager#countSql(java.lang.String)
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return eqDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.EqManager#countHql(cn.northpark.model.Eq, java.lang.String)
	 */
	@Override
	public int countHql(Eq eq, String wheresql) {
		// TODO Auto-generated method stub
		return eqDao.countHql(eq.getClass(), wheresql);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.EqManager#executeSql(java.lang.String)
	 */
	@Override
	public void executeSql(String sql) {
		// TODO Auto-generated method stub
		 eqDao.executess(sql);
	}
}

