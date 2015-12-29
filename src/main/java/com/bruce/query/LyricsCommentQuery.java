
package com.bruce.query;
import com.bruce.query.condition.LyricsCommentQueryCondition;

public interface LyricsCommentQuery {
	public String getSql(LyricsCommentQueryCondition lyricscommentQueryCondition);
}
