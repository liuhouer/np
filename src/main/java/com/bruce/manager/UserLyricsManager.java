
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.UserLyrics;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface UserLyricsManager {
	
	public UserLyrics findUserLyrics(Integer id);

	public List<UserLyrics> findAll();

	public void addUserLyrics(UserLyrics userlyrics);

	public boolean delUserLyrics(Integer id);

	public boolean updateUserLyrics(UserLyrics userlyrics);
	
	public QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<UserLyrics> findByCondition(
			String wheresql);
	

	public PageView<List<Map<String, Object>>> getMixMapData(String currentpage, String userid);
	
}


