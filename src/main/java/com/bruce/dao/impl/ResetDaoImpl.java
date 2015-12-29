
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.ResetDao;
import com.bruce.model.Reset;

@Service("ResetDao")
public class ResetDaoImpl extends HibernateDaoImpl<Reset, Serializable> implements ResetDao {

}