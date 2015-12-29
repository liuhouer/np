
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;

import com.bruce.model.LyricsComment;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface LyricsCommentManager {
	
	public LyricsComment findLyricsComment(String id);

	public List<LyricsComment> findAll();

	public void addLyricsComment(LyricsComment lyricscomment);

	public boolean delLyricsComment(String id);

	public boolean updateLyricsComment(LyricsComment lyricscomment);
	
	public QueryResult<LyricsComment> findByCondition(PageView<LyricsComment> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<LyricsComment> findByCondition(
			String wheresql);
	
}


