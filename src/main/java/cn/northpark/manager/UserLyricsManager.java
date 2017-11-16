
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.UserLyrics;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

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
	
	
	//只获取Page分页信息,不获取数据
	public PageView<List<Map<String, Object>>> getMixMapPage(String currentpage, String userid);
	
}


