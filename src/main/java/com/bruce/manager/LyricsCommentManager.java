
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.LyricsComment;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface LyricsCommentManager {
	
	public LyricsComment findLyricsComment(Integer id);

	public List<LyricsComment> findAll();

	public void addLyricsComment(LyricsComment lyricscomment);

	public boolean delLyricsComment(Integer id);

	public boolean updateLyricsComment(LyricsComment lyricscomment);
	
	public QueryResult<LyricsComment> findByCondition(PageView<LyricsComment> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<LyricsComment> findByCondition(
			String wheresql);
	
	/**
	 * 根据自己写的sql查询+条件查询
	 * @return
	 */
	public List<LyricsComment> querySql(String sql,Object... obj);
	
	/**
	 * 根据自己写的sql查询
	 * @return
	 */
	public List<LyricsComment> querySql(String sql);
	
	
	/**
	 * 根据自己写的sql查询返回map
	 * @return
	 */
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView);

}


