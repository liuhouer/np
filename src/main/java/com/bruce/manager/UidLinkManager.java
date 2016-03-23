
package com.bruce.manager;
import java.util.List;
import com.bruce.model.UidLink;
import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface UidLinkManager {
	
	public UidLink findUidLink(String id);

	public List<UidLink> findAll();

	public void addUidLink(UidLink uidlink);

	public boolean delUidLink(String id);

	public boolean updateUidLink(UidLink uidlink);
	
	public QueryResult<UidLink> findByCondition(PageView<UidLink> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<UidLink> findByCondition(
			String wheresql);
	
}


