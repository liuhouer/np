
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.LyricsZan;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface LyricsZanManager {
	
	public LyricsZan findLyricsZan(String id);

	public List<LyricsZan> findAll();

	public void addLyricsZan(LyricsZan lyricszan);

	public boolean delLyricsZan(String id);

	public boolean updateLyricsZan(LyricsZan lyricszan);
	
	public QueryResult<LyricsZan> findByCondition(PageView<LyricsZan> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<LyricsZan> findByCondition(
			String wheresql);
	
	
	public int getCommentNumByLRC(String lyricsid);
	public int getZanNumByLRC(String lyricsid);
	
	public List<Map<String,Object>> mixSqlQuery(String sql);
}


