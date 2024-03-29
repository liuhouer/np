
package cn.northpark.manager.impl;

import cn.northpark.dao.MoviesDao;
import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Movies;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Movies> findByCondition(PageView<Movies> p,
                                               String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = moviesDao.findByCondition(p.getFirstResult(),
                p.getMaxResult(), wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Movies> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = moviesDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Movies> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return moviesDao.querySql(sql, Movies.class, obj);
    }

    @Override
    public List<Map<String, Object>> querySql(String sql) {
        // TODO Auto-generated method stub
        return moviesDao.querySql(sql);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.MoviesManager#countSql(java.lang.String)
     */
    @Override
    public int countSql(String sql) {
        // TODO Auto-generated method stub
        return moviesDao.countSql(sql);
    }

    /* (non-Javadoc)
     * @see cn.northpark.manager.MoviesManager#countHql(cn.northpark.model.Movies, java.lang.String)
     */
    @Override
    public int countHql(String wheresql) {
        // TODO Auto-generated method stub
        return moviesDao.countHql(Movies.class, wheresql);
    }


    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        // TODO Auto-generated method stub
        return moviesDao.querySql(sql);
    }

    @Override
    public int exeSql(String up_sql) {
        moviesDao.execSQL(up_sql);
        return 0;
    }

    @Override
	public List<Movies> querySqlEntity(String sql) {
		// TODO Auto-generated method stub
		return moviesDao.querySql(sql, Movies.class);
	}
}

