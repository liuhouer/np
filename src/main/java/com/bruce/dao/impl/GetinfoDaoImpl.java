
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.getinfoDao;
import com.bruce.model.Getinfo;

@Service("getinfoDao")
public class GetinfoDaoImpl extends HibernateDaoImpl<Getinfo, Serializable> implements getinfoDao {

}