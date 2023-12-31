
package cn.northpark.service.impl;

import cn.northpark.mapper.MoviesMapper;
import cn.northpark.model.Movies;
import cn.northpark.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "MoviesService")
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesMapper moviesMapper;

    @Override
    public Movies findMovies(Integer id) {
        return moviesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Movies> findAll() {
        return moviesMapper.selectAll();
    }

    @Override
    public void addMovies(Movies movies) {
        moviesMapper.insert(movies);
    }

    @Override
    public boolean delMovies(Integer id) {
        moviesMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateMovies(Movies movies) {
        moviesMapper.updateByPrimaryKey(movies);
        return true;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        return moviesMapper.querySqlMap(sql);
    }

    @Override
    public List<Movies> findByCondition(String whereSql, String orderBy) {
        return moviesMapper.findByCondition(whereSql,orderBy);

    }

}

