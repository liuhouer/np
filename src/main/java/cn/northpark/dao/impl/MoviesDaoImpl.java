
package cn.northpark.dao.impl;

import cn.northpark.dao.MoviesDao;
import cn.northpark.model.Movies;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("MoviesDao")
public class MoviesDaoImpl extends HibernateDaoImpl<Movies, Serializable> implements MoviesDao {

}