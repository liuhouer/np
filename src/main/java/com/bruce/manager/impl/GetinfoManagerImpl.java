
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.manager.GetinfoManager;
import com.bruce.model.Getinfo;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("getinfoManager")
public class GetinfoManagerImpl implements GetinfoManager {

    @Autowired
	private com.bruce.dao.getinfoDao getinfoDao;

	@Override
	public Getinfo findGetinfo(String id) {
		return getinfoDao.find(id);
	}

	@Override
	public List<Getinfo> findAll() {
		return getinfoDao.findAll();
	}

	@Override
	public void addGetinfo(Getinfo getinfo) {
		getinfoDao.save(getinfo);
	}

	@Override
	public boolean delGetinfo(String id) {
		Getinfo getinfo = getinfoDao.find(id);
		getinfoDao.delete(getinfo);
		return true;
	}

	@Override
	public boolean updateGetinfo(Getinfo getinfo) {
		getinfoDao.update(getinfo);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Getinfo> findByCondition(PageView<Getinfo> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = getinfoDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Getinfo> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = getinfoDao.findByCondition(
				 wheresql);
		return qrs;
	}
}

