
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.UserFollow;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

public interface UserFollowManager {
	
	public UserFollow findUserFollow(Integer id);

	public List<UserFollow> findAll();

	public void addUserFollow(UserFollow userfollow);

	public boolean delUserFollow(Integer id);

	public boolean updateUserFollow(UserFollow userfollow);
	
	public QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<UserFollow> findByCondition(
			String wheresql);
	
	public List<Map<String,Object>> getFansList(String sql);
}

