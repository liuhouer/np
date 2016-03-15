
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.MoviesDao;
import com.bruce.model.Movies;

@Service("MoviesDao")
public class MoviesDaoImpl extends HibernateDaoImpl<Movies, Serializable> implements MoviesDao {

}