
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.UserprofileDao;
import com.bruce.manager.UserprofileManager;
import com.bruce.model.Userprofile;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("UserprofileManager")
public class UserprofileManagerImpl implements UserprofileManager {

    @Autowired
	private UserprofileDao userprofileDao;

	@Override
	public Userprofile findUserprofile(String id) {
		return userprofileDao.find(id);
	}

	@Override
	public List<Userprofile> findAll() {
		return userprofileDao.findAll();
	}

	@Override
	public void addUserprofile(Userprofile userprofile) {
		userprofileDao.save(userprofile);
	}

	@Override
	public boolean delUserprofile(String id) {
		Userprofile userprofile = userprofileDao.find(id);
		userprofileDao.delete(userprofile);
		return true;
	}

	@Override
	public boolean updateUserprofile(Userprofile userprofile) {
		userprofileDao.update(userprofile);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Userprofile> findByCondition(PageView<Userprofile> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = userprofileDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Userprofile> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = userprofileDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public Userprofile getModelByUserid(String userid) {
		// TODO Auto-generated method stub
		return userprofileDao.getModelByUserid(userid);
	}
}

