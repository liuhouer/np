
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.User;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

public interface UserManager {
	
	
	public User findUser(Integer id);

	public List<User> findAll();

	public void addUser(User user);

	public boolean delUser(Integer id);

	public boolean updateUser(User user);
	
	public QueryResult<User> findByCondition(PageView<User> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<User> findByCondition(
			String wheresql);

	public User login(String email, String password );
	
	public void pwddd(String sql );
	
	/**
	 * 根据登录用户查询个人歌词集合
	 * @return
	 */
	public List<Map<String, Object>> querySql(String sql,String userid);
	
	
	/**
	 * 根据sql语句查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public int countSql(String sql) ;
	
	
	/**
	 * 根据实体查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	public  int countHql(String wheresql);
	
	

	/**
	 * 根绝sql 预查寻返回list<map
	 * @param sql
	 * @param objects
	 * @return
	 */
	public List<Map<String, Object>> querySqlMap(String sql, Object... objects);
	
}


