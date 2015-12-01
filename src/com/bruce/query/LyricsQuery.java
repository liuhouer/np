
package com.bruce.query;
import com.bruce.model.Lyrics;
import com.bruce.query.condition.LyricsQueryCondition;

public interface LyricsQuery {
	public String getSql(LyricsQueryCondition lyricsQueryCondition);
}
