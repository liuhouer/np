
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;

import com.bruce.model.Lyrics;
import com.bruce.model.Note;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface LyricsManager {
	
	public Lyrics findLyrics(Integer id);

	public List<Lyrics> findAll();

	public void addLyrics(Lyrics lyrics);

	public boolean delLyrics(Integer id);

	public boolean updateLyrics(Lyrics lyrics);
	
	public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Lyrics> findByCondition(
			String wheresql);
	
	/**
	 * 根据sql语句查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public int countSql(String sql) ;
	
	
	/**
	 * 根据实体查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public  int countHql(Lyrics m,String wheresql);
	
}


