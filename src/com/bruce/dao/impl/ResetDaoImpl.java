
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Reset;
import com.bruce.dao.ResetDao;

@Service("ResetDao")
public class ResetDaoImpl extends HibernateDaoImpl<Reset, Serializable> implements ResetDao {

}