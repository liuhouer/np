
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.LyricsZanQuery;
import com.bruce.query.condition.LyricsZanQueryCondition;
@Service("LyricsZanQuery")

public class LyricsZanQueryImpl implements LyricsZanQuery {

	@Override
	public String getSql(LyricsZanQueryCondition lyricszanQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



