
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.LyricsCommentQuery;
import com.bruce.query.condition.LyricsCommentQueryCondition;
@Service("LyricsCommentQuery")

public class LyricsCommentQueryImpl implements LyricsCommentQuery {

	@Override
	public String getSql(LyricsCommentQueryCondition lyricscommentQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



