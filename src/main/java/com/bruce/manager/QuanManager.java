
package com.bruce.manager;
import java.util.List;
import java.util.Map;
import com.bruce.model.Quan;
import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface QuanManager {
	
	public Quan findQuan(Integer id);

	public List<Quan> findAll();

	public void addQuan(Quan quan);

	public boolean delQuan(Integer id);

	public boolean updateQuan(Quan quan);
	
	public QueryResult<Quan> findByCondition(PageView<Quan> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Quan> findByCondition(
			String wheresql);
			
	/**
	 * sql+
	 * @return
	 */
	public List<Quan> querySql(String sql,Object... obj);
	
	/**
	 * sql
	 * @return
	 */
	public List<Quan> querySql(String sql);
	
	
	/**
	 * sqlmap
	 * @return
	 */
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView);

	
}


