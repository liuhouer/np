
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bruce.model.Quan;
import com.bruce.manager.QuanManager;
import com.bruce.dao.QuanDao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("QuanManager")
public class QuanManagerImpl implements QuanManager {

    @Autowired
	private QuanDao quanDao;

	@Override
	public Quan findQuan(Integer id) {
		return quanDao.find(id);
	}

	@Override
	public List<Quan> findAll() {
		return quanDao.findAll();
	}

	@Override
	public void addQuan(Quan quan) {
		quanDao.save(quan);
	}

	@Override
	public boolean delQuan(Integer id) {
		Quan quan = quanDao.find(id);
		quanDao.delete(quan);
		return true;
	}

	@Override
	public boolean updateQuan(Quan quan) {
		quanDao.update(quan);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Quan> findByCondition(PageView<Quan> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = quanDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Quan> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = quanDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<Quan> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return quanDao.querySql(sql, Quan.class, obj);
	}

	@Override
	public List<Quan> querySql(String sql) {
		// TODO Auto-generated method stub
		return quanDao.querySql(sql, Quan.class);
	}

	@Override
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView) {
		// TODO Auto-generated method stub
		return quanDao.QuerySQLForMapList(sql, pageView);
	}
}

