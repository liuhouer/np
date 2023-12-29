
package cn.northpark.service;

import cn.northpark.model.Knowledge;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
public interface KnowledgeService{
	
	Knowledge findKnowledge(Integer id);

	List<Knowledge> findAll();

	void addKnowledge(Knowledge knowledge);

	boolean delKnowledge(Integer id);

	boolean updateKnowledge(Knowledge knowledge);
	
	
	/**
	 * 单表返回clazz
	 * 同步：返回分页数据和分页结构
	 *
	 * @param p
	 * @param wheresql
	 * @param order
	 * @return
	 */
	QueryResult<Knowledge> findByCondition(PageView<Knowledge> p,
										   String wheresql,
										   LinkedHashMap<String, String> order);

	QueryResult<Knowledge> findByCondition(
			String wheresql);
			
	/**
	 * 根据sql语句预查询？？？返回实体结果集
	 * @return
	 */
	List<Knowledge> querySql(String sql,Object... obj);
	
	/**
	 * 根据sql语句返回实体结果集
	 * @return
	 */
	List<Knowledge> querySql(String sql);
	
	
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
	 * @param wheresql
	 *
	 * @return int
	 */
	 int countHql(String wheresql);
	
	
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
	 * @param pageView
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView,String sql) ;
	
	/**
	 * 多表关联mix
	 * 
	 * 同步：获取分页结构不获取数据
	 * @param pageView
	 * @param sql
	 * @return
	 */
	PageView<List<Map<String, Object>>>  getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql);
	

	
}


