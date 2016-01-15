
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Movies;
import com.bruce.dao.MoviesDao;

@Service("MoviesDao")
public class MoviesDaoImpl extends HibernateDaoImpl<Movies, Serializable> implements MoviesDao {

}