
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;

import com.bruce.model.Getinfo;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface GetinfoManager {
	
	public Getinfo findGetinfo(String id);

	public List<Getinfo> findAll();

	public void addGetinfo(Getinfo getinfo);

	public boolean delGetinfo(String id);

	public boolean updateGetinfo(Getinfo getinfo);
	
	public QueryResult<Getinfo> findByCondition(PageView<Getinfo> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Getinfo> findByCondition(
			String wheresql);
	
}


