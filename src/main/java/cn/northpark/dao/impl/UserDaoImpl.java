
package cn.northpark.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.northpark.dao.UserDao;
import cn.northpark.model.User;

@Service("UserDao")
public class UserDaoImpl extends HibernateDaoImpl<User, Serializable> implements UserDao {

		public User login(String email, String password) {
			// TODO Auto-generated method stub
			String sql = "select * from bc_user where email=?  and password = ? ";
			List<User> list = querySql(sql, User.class, new Object[]{email,password});
			User user = null;
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return user;
			}
		}

}