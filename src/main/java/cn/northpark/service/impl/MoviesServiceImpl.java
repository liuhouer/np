//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.MoviesMapper;
//import cn.northpark.manager.MoviesManager;
//import cn.northpark.model.Movies;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class MoviesManagerImpl implements MoviesManager {
//
//    @Autowired
//    private MoviesMapper moviesMapper;
//
//    @Override
//    public Movies findMovies(Integer id) {
//        return moviesMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Movies> findAll() {
//        return moviesMapper.selectAll();
//    }
//
//    @Override
//    public void addMovies(Movies movies) {
//        moviesMapper.insert(movies);
//    }
//
//    @Override
//    public boolean delMovies(Integer id) {
//        Movies movies = moviesMapper.selectByPrimaryKey(id);
//        moviesMapper.deleteByPrimaryKey(movies);
//        return true;
//    }
//
//    @Override
//    public boolean updateMovies(Movies movies) {
//        moviesMapper.updateByPrimaryKey(movies);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Movies> findByCondition(PageView<Movies> p,
//                                               String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = moviesMapper.findByCondition(p.getFirstResult(),
//                p.getMaxResult(), wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<Movies> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = moviesMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Movies> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return moviesMapper.querySql(sql, Movies.class, obj);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySql(String sql) {
//        // TODO Auto-generated method stub
//        return moviesMapper.querySql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.MoviesManager#countSql(java.lang.String)
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//        return moviesMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.MoviesManager#countHql(cn.northpark.model.Movies, java.lang.String)
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return moviesMapper.countHql(Movies.class, wheresql);
//    }
//
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql) {
//        // TODO Auto-generated method stub
//        return moviesMapper.querySql(sql);
//    }
//
//    @Override
//    public int exeSql(String up_sql) {
//        moviesMapper.execSQL(up_sql);
//        return 0;
//    }
//
//    @Override
//	public List<Movies> querySqlEntity(String sql) {
//		// TODO Auto-generated method stub
//		return moviesMapper.querySql(sql, Movies.class);
//	}
//}
//
