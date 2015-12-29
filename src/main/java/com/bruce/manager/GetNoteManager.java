
package com.bruce.manager;
import java.util.List;

import com.bruce.model.GetNote;
import com.bruce.utils.QueryResult;

public interface GetNoteManager {
	
	public GetNote findGetNote(String id);

	public List<GetNote> findAll();

	public void addGetNote(GetNote note);

	public boolean delGetNote(String id);

	public boolean updateGetNote(GetNote note);
	

	public QueryResult<GetNote> findByCondition(
			String wheresql);
	
	
	
	
}


