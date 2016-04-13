
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.UserDao;
import com.bruce.manager.UserManager;
import com.bruce.model.User;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("UserManager")
public class UserManagerImpl implements UserManager {

    @Autowired
	private UserDao userDao;

	@Override
	public User findUser(String id) {
		return userDao.find(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public boolean delUser(String id) {
		User user = userDao.find(id);
		userDao.delete(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		userDao.update(user);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<User> findByCondition(PageView<User> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = userDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<User> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = userDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.login(email, password);
	}

	@Override
	public void pwddd(String sql) {
		// TODO Auto-generated method stub
		userDao.executess(sql);
	}

}

