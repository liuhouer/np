
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.LyricsZan;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

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
}


