
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bruce.model.Getnotes;
import com.bruce.manager.GetnotesManager;
import com.bruce.dao.GetnotesDao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("GetnotesManager")
public class GetnotesManagerImpl implements GetnotesManager {

    @Autowired
	private GetnotesDao getnotesDao;

	@Override
	public Getnotes findGetnotes(String id) {
		return getnotesDao.find(id);
	}

	@Override
	public List<Getnotes> findAll() {
		return getnotesDao.findAll();
	}

	@Override
	public void addGetnotes(Getnotes getnotes) {
		getnotesDao.save(getnotes);
	}

	@Override
	public boolean delGetnotes(String id) {
		Getnotes getnotes = getnotesDao.find(id);
		getnotesDao.delete(getnotes);
		return true;
	}

	@Override
	public boolean updateGetnotes(Getnotes getnotes) {
		getnotesDao.update(getnotes);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Getnotes> findByCondition(PageView<Getnotes> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = getnotesDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Getnotes> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = getnotesDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<Getnotes> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return getnotesDao.querySql(sql, Getnotes.class, obj);
	}

	@Override
	public List<Getnotes> querySql(String sql) {
		// TODO Auto-generated method stub
		return getnotesDao.querySql(sql, Getnotes.class);
	}

	@Override
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView) {
		// TODO Auto-generated method stub
		return getnotesDao.QuerySQLForMapList(sql, pageView);
	}

}

