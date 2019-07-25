
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.UserFollowDao;
import cn.northpark.model.UserFollow;

@Service("UserFollowDao")
public class UserFollowDaoImpl extends HibernateDaoImpl<UserFollow, Serializable> implements UserFollowDao {

}