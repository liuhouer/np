
package com.bruce.manager;
import java.util.List;
import java.util.Map;
import com.bruce.model.Eq;
import com.bruce.model.Note;

import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmial.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public interface EqManager {
	
	public Eq findEq(Integer id);

	public List<Eq> findAll();

	public void addEq(Eq eq);

	public boolean delEq(Integer id);

	public boolean updateEq(Eq eq);
	
	public QueryResult<Eq> findByCondition(PageView<Eq> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Eq> findByCondition(
			String wheresql);
			
	/**
	 * sql+
	 * @return
	 */
	public List<Eq> querySql(String sql,Object... obj);
	
	/**
	 * sql
	 * @return
	 */
	public List<Eq> querySql(String sql);
	
	
	/**
	 * sqlmap
	 * @return
	 */
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView);
	
	
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
	public  int countHql(Eq eq,String wheresql);
	
	
	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 */
	public  void executeSql(String sql);
	

	

	
}


