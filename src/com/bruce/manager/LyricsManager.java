
package com.bruce.manager;
import java.util.List;
import java.util.Map;

import com.bruce.model.Lyrics;

import java.util.LinkedHashMap;

import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface LyricsManager {
	
	public Lyrics findLyrics(String id);

	public List<Lyrics> findAll();

	public void addLyrics(Lyrics lyrics);

	public boolean delLyrics(String id);

	public boolean updateLyrics(Lyrics lyrics);
	
	public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Lyrics> findByCondition(
			String wheresql);
	
	
	
}


