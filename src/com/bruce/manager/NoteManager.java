
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bruce.model.Note;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface NoteManager {
	
	public Note findNote(String id);

	public List<Note> findAll();

	public void addNote(Note note);

	public boolean delNote(String id);

	public boolean updateNote(Note note);
	
	public QueryResult<Note> findByCondition(PageView<Note> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Note> findByCondition(
			String wheresql);
	
	
	public PageView<List<Map<String, Object>>> findmixByCondition(String currentpage,String wheresql) ;

	public int findmixCount(String whereSql);

	
}


