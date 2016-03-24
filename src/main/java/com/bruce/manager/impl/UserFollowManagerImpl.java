
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.UserFollowDao;
import com.bruce.manager.UserFollowManager;
import com.bruce.model.UserFollow;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("UserFollowManager")
public class UserFollowManagerImpl implements UserFollowManager {

    @Autowired
	private UserFollowDao userfollowDao;

	@Override
	public UserFollow findUserFollow(String id) {
		return userfollowDao.find(id);
	}

	@Override
	public List<UserFollow> findAll() {
		return userfollowDao.findAll();
	}

	@Override
	public void addUserFollow(UserFollow userfollow) {
		userfollowDao.save(userfollow);
	}

	@Override
	public boolean delUserFollow(String id) {
		UserFollow userfollow = userfollowDao.find(id);
		userfollowDao.delete(userfollow);
		return true;
	}

	@Override
	public boolean updateUserFollow(UserFollow userfollow) {
		userfollowDao.update(userfollow);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = userfollowDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UserFollow> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = userfollowDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<Map<String, Object>> getFansList(String sql) {
		// TODO Auto-generated method stub
		return userfollowDao.querySql(sql);
	}
}

