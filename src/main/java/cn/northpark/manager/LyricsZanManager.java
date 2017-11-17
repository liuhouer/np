
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.LyricsZan;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

public interface LyricsZanManager {
	
	public LyricsZan findLyricsZan(Integer id);

	public List<LyricsZan> findAll();

	public void addLyricsZan(LyricsZan lyricszan);

	public boolean delLyricsZan(Integer id);

	public boolean updateLyricsZan(LyricsZan lyricszan);
	
	public QueryResult<LyricsZan> findByCondition(PageView<LyricsZan> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<LyricsZan> findByCondition(
			String wheresql);
	
	
	public int getCommentNumByLRC(String lyricsid);
	public int getZanNumByLRC(String lyricsid);
	
	public List<Map<String,Object>> mixSqlQuery(String sql);
	
	
	/**
	 * 根据实体查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public  int countHql(LyricsZan lyricszan,String wheresql);
}


