
package com.bruce.query;
import com.bruce.model.LyricsZan;
import com.bruce.query.condition.LyricsZanQueryCondition;

public interface LyricsZanQuery {
	public String getSql(LyricsZanQueryCondition lyricszanQueryCondition);
}
