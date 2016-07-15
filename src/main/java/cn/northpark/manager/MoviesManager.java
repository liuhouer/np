
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;

import cn.northpark.model.Movies;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

public interface MoviesManager {
	
	public Movies findMovies(Integer id);

	public List<Movies> findAll();

	public void addMovies(Movies movies);

	public boolean delMovies(Integer id);

	public boolean updateMovies(Movies movies);
	
	public QueryResult<Movies> findByCondition(PageView<Movies> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Movies> findByCondition(
			String wheresql);
	
	/**
	 * 根据关键词搜索
	 * @return
	 */
	public List<Movies> querySql(String sql,Object... obj);
	
}


