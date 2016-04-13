
package com.bruce.manager;
import java.util.List;
import com.bruce.model.Reset;
import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface ResetManager {
	
	public Reset findReset(String id);

	public List<Reset> findAll();

	public void addReset(Reset reset);

	public boolean delReset(String id);

	public boolean updateReset(Reset reset);
	
	public QueryResult<Reset> findByCondition(PageView<Reset> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Reset> findByCondition(
			String wheresql);
	
}


