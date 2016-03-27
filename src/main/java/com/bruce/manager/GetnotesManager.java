
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.Getnotes;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface GetnotesManager {
	
	public Getnotes findGetnotes(String id);

	public List<Getnotes> findAll();

	public void addGetnotes(Getnotes getnotes);

	public boolean delGetnotes(String id);

	public boolean updateGetnotes(Getnotes getnotes);
	
	public QueryResult<Getnotes> findByCondition(PageView<Getnotes> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Getnotes> findByCondition(
			String wheresql);
			
	/**
	 * sql+
	 * @return
	 */
	public List<Getnotes> querySql(String sql,Object... obj);
	
	/**
	 * sql
	 * @return
	 */
	public List<Getnotes> querySql(String sql);
	
	
	/**
	 * sqlmap
	 * @return
	 */
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView);

	
}


