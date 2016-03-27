
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Getnotes;
import com.bruce.dao.GetnotesDao;

@Service("GetnotesDao")
public class GetnotesDaoImpl extends HibernateDaoImpl<Getnotes, Serializable> implements GetnotesDao {

}