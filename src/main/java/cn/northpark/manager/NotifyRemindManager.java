
package cn.northpark.manager;
import java.util.List;
import java.util.Map;
import cn.northpark.model.NotifyRemind;
import java.util.LinkedHashMap;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public interface NotifyRemindManager {
	
	NotifyRemind findNotifyRemind(Integer id);

	List<NotifyRemind> findAll();

	void addNotifyRemind(NotifyRemind notifyremind);

	boolean delNotifyRemind(Integer id);

	boolean updateNotifyRemind(NotifyRemind notifyremind);
	
	
	/**
	 * 单表返回clazz
	 * 同步：返回分页数据和分页结构
	 *
	 * @param p
	 * @param wheresql
	 * @param order
	 * @return
	 */
	QueryResult<NotifyRemind> findByCondition(PageView<NotifyRemind> p,
			String wheresql, LinkedHashMap<String, String> order);

	QueryResult<NotifyRemind> findByCondition(
			String wheresql);
			
	/**
	 * 根据sql语句预查询？？？返回实体结果集
	 * @return
	 */
	List<NotifyRemind> querySql(String sql,Object... obj);
	
	/**
	 * 根据sql语句返回实体结果集
	 * @return
	 */
	List<NotifyRemind> querySql(String sql);
	
	
	/**
	 * 根据sql语句返回map结果
	 * @return
	 */
	List<Map<String, Object>> querySqlMap(String sql);
	
	

	
	/**
	 * 根据sql语句查询条数
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 * @return int
	 */
	int countSql(String sql) ;
	
	
	/**
	 * 根据hql查询条数
	 * 
	 * @param whereSql
	 *
	 * @return int
	 */
	 int countHql(String whereSql);
	
	
    /**
	 * 直接执行sql语句  更新、删除..
	 * 
	 * @param sql
	 *            SQL语句
	 * 
	 */
	 void executeSql(String sql);
	
	
	/**
	 * 多表关联mix
	 * 
	 * 异步：根据页码获取当前页数据
	 *
	 * @param pageview
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview,String sql) ;
	
	/**
	 * 多表关联mix
	 * 
	 * 同步：获取分页结构不获取数据
	 * @param pageview
	 * @param sql
	 * @return
	 */
	PageView<List<Map<String, Object>>>  getMixMapPage(PageView<List<Map<String, Object>>> pageview, String sql);
	

	
}


