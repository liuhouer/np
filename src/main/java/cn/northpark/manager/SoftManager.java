
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Soft;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public interface SoftManager {
	
	public Soft findSoft(Integer id);

	public List<Soft> findAll();

	public void addSoft(Soft soft);

	public boolean delSoft(Integer id);

	public boolean updateSoft(Soft soft);
	
	public QueryResult<Soft> findByCondition(PageView<Soft> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Soft> findByCondition(
			String wheresql);
			
	/**
	 * sql+
	 * @return
	 */
	public List<Soft> querySql(String sql,Object... obj);
	
	/**
	 * sql
	 * @return
	 */
	public List<Soft> querySql(String sql);
	
	/**
	 * sql
	 * @return
	 */
	public List<Map<String, Object>> querySqlMap(String sql);
	
	
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
	public  int countHql(Soft m,String wheresql);
	

	
}


