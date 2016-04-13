
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;

import com.bruce.model.User;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface UserManager {
	
	
	public User findUser(String id);

	public List<User> findAll();

	public void addUser(User user);

	public boolean delUser(String id);

	public boolean updateUser(User user);
	
	public QueryResult<User> findByCondition(PageView<User> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<User> findByCondition(
			String wheresql);

	public User login(String email, String password );
	
	public void pwddd(String sql );
	
}


