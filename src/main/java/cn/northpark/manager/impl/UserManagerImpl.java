
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.UserDao;
import cn.northpark.manager.UserManager;
import cn.northpark.model.User;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@Service("UserManager")
public class UserManagerImpl implements UserManager {

    @Autowired
	private UserDao userDao;

	@Override
	public User findUser(Integer id) {
		return userDao.find(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public boolean delUser(Integer id) {
		User user = userDao.find(id);
		userDao.delete(user);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		userDao.update(user);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<User> findByCondition(PageView<User> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = userDao.findByCondition(p.getFirstResult(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<User> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = userDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.login(email, password);
	}

	@Override
	public void pwddd(String sql) {
		// TODO Auto-generated method stub
		userDao.executess(sql);
	}

	@Override
	public List<Map<String, Object>> querySql(String sql,String userid) {
		// TODO Auto-generated method stub
		return userDao.querySql(sql,userid);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.UserManager#countSql(java.lang.String)
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		
		return userDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.UserManager#countHql(cn.northpark.model.User, java.lang.String)
	 */
	@Override
	public  int countHql(String wheresql) {
		// TODO Auto-generated method stub
		return userDao.countHql(User.class, wheresql);
	}

	@Override
	public List<Map<String, Object>> querySqlMap(String sql, Object... objects) {
		// TODO Auto-generated method stub
		return userDao.querySql(sql, objects);
	}

}

