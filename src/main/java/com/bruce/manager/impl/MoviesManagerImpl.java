
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.MoviesDao;
import com.bruce.manager.MoviesManager;
import com.bruce.model.Movies;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("MoviesManager")
public class MoviesManagerImpl implements MoviesManager {

    @Autowired
	private MoviesDao moviesDao;

	@Override
	public Movies findMovies(String id) {
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
	public boolean delMovies(String id) {
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
}

