
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.MoviesDao;
import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Eq;
import cn.northpark.model.Movies;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

@Service("MoviesManager")
public class MoviesManagerImpl implements MoviesManager {

    @Autowired
	private MoviesDao moviesDao;

	@Override
	public Movies findMovies(Integer id) {
		return moviesDao.find(id);
	}

	@Override
	public List<Movies> findAll() {
		return moviesDao.findAll();
	}

	@Override
	public void addMovies(Movies movies) {
		moviesDao.save(movies);
	}

	@Override
	public boolean delMovies(Integer id) {
		Movies movies = moviesDao.find(id);
		moviesDao.delete(movies);
		return true;
	}

	@Override
	public boolean updateMovies(Movies movies) {
		moviesDao.update(movies);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Movies> findByCondition(PageView<Movies> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = moviesDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Movies> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = moviesDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public List<Movies> querySql(String sql,  Object... obj) {
		// TODO Auto-generated method stub
		return moviesDao.querySql(sql, Movies.class, obj);
	}
	
	@Override
	public List<Movies> querySql(String sql) {
		// TODO Auto-generated method stub
		return moviesDao.querySql(sql, Movies.class);
	}

}
