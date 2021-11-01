
package cn.northpark.dao.impl;

import cn.northpark.dao.UserFollowDao;
import cn.northpark.model.UserFollow;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("UserFollowDao")
public class UserFollowDaoImpl extends HibernateDaoImpl<UserFollow, Serializable> implements UserFollowDao {

}