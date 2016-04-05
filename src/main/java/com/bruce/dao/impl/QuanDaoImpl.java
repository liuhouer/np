
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Quan;
import com.bruce.dao.QuanDao;

@Service("QuanDao")
public class QuanDaoImpl extends HibernateDaoImpl<Quan, Serializable> implements QuanDao {

}