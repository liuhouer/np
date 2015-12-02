
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bruce.model.Reset;
import com.bruce.manager.ResetManager;
import com.bruce.dao.ResetDao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("ResetManager")
public class ResetManagerImpl implements ResetManager {

    @Autowired
	private ResetDao resetDao;

	@Override
	public Reset findReset(String id) {
		return resetDao.find(id);
	}

	@Override
	public List<Reset> findAll() {
		return resetDao.findAll();
	}

	@Override
	public void addReset(Reset reset) {
		resetDao.save(reset);
	}

	@Override
	public boolean delReset(String id) {
		Reset reset = resetDao.find(id);
		resetDao.delete(reset);
		return true;
	}

	@Override
	public boolean updateReset(Reset reset) {
		resetDao.update(reset);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Reset> findByCondition(PageView<Reset> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = resetDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Reset> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = resetDao.findByCondition(
				 wheresql);
		return qrs;
	}
}

