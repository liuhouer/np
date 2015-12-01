
package com.bruce.query;
import com.bruce.model.Note;
import com.bruce.query.condition.NoteQueryCondition;

public interface NoteQuery {
	public String getSql(NoteQueryCondition noteQueryCondition);
}
