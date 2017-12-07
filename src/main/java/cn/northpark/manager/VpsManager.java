
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Vps;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public interface VpsManager {
	
	public Vps findVps(Integer id);

	public List<Vps> findAll();

	public void addVps(Vps vps);

	public boolean delVps(Integer id);

	public boolean updateVps(Vps vps);
	
	
	/**
	 * 单表返回clazz
	 * 同步：返回分页数据和分页结构
	 *
	 * @param p
	 * @param wheresql
	 * @param order
	 * @return
	 */
	public QueryResult<Vps> findByCondition(PageView<Vps> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Vps> findByCondition(
			String wheresql);
			
	/**
	 * 根据sql语句预查询？？？
	 * @return
	 */
	public List<Vps> querySql(String sql,Object... obj);
	
	/**
	 * 根据sql语句查询
	 * @return
	 */
	public List<Vps> querySql(String sql);
	
	/**
	 * 根据sql语句返回map结果集
	 * @return
	 */
	public List<Map<String, Object>> querySqlMap(String sql);
	

	
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
	 * 根据hql查询条数
	 * 
	 * @param hql
	 *            hql语句
	 * 
	 * @return int
	 */
	public  int countHql(String wheresql);
	
	
    /**
	 * 直接执行sql语句  更新、删除..
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 */
	public  void executeSql(String sql);
	
	
	/**
	 * 多表关联mix
	 * 
	 * 异步：根据页码获取当前页数据
	 *
	 * @param pageview
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview,String sql) ;
	
	/**
	 * 多表关联mix
	 * 
	 * 同步：获取分页结构不获取数据
	 * @param pageview
	 * @param sql
	 * @return
	 */
	public PageView<List<Map<String, Object>>>  getMixMapPage(PageView<List<Map<String, Object>>> pageview, String sql);
	

	
}


