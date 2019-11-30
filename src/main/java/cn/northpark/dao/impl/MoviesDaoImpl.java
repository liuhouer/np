
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.MoviesDao;
import cn.northpark.model.Movies;

@Service("MoviesDao")
public class MoviesDaoImpl extends HibernateDaoImpl<Movies, Serializable> implements MoviesDao {

}