
package com.bruce.manager;
import java.util.List;
import com.bruce.model.Userprofile;
import java.util.LinkedHashMap;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface UserprofileManager {
	
	public Userprofile findUserprofile(String id);

	public List<Userprofile> findAll();

	public void addUserprofile(Userprofile userprofile);

	public boolean delUserprofile(String id);

	public boolean updateUserprofile(Userprofile userprofile);
	
	public QueryResult<Userprofile> findByCondition(PageView<Userprofile> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Userprofile> findByCondition(
			String wheresql);
	
	public Userprofile getModelByUserid(String userid) ;
}


