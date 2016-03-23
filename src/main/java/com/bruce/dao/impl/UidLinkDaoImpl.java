
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.UidLink;
import com.bruce.dao.UidLinkDao;

@Service("UidLinkDao")
public class UidLinkDaoImpl extends HibernateDaoImpl<UidLink, Serializable> implements UidLinkDao {

}