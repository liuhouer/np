
package com.bruce.manager;
import java.util.List;
import com.bruce.model.Note;
import java.util.LinkedHashMap;
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
	
}


