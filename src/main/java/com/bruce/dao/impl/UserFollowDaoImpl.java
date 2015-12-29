
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.UserFollowDao;
import com.bruce.model.UserFollow;

@Service("UserFollowDao")
public class UserFollowDaoImpl extends HibernateDaoImpl<UserFollow, Serializable> implements UserFollowDao {

}