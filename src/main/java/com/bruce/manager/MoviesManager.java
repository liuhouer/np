
package com.bruce.manager;
import java.util.List;
import com.bruce.model.Movies;
import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface MoviesManager {
	
	public Movies findMovies(String id);

	public List<Movies> findAll();

	public void addMovies(Movies movies);

	public boolean delMovies(String id);

	public boolean updateMovies(Movies movies);
	
	public QueryResult<Movies> findByCondition(PageView<Movies> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Movies> findByCondition(
			String wheresql);
	
}


