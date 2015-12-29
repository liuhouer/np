
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.UserFollow;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface UserFollowManager {
	
	public UserFollow findUserFollow(String id);

	public List<UserFollow> findAll();

	public void addUserFollow(UserFollow userfollow);

	public boolean delUserFollow(String id);

	public boolean updateUserFollow(UserFollow userfollow);
	
	public QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<UserFollow> findByCondition(
			String wheresql);
	
	public List<Map<String,Object>> getFansList(String sql);
}


