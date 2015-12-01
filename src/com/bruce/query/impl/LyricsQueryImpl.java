
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.LyricsQuery;
import com.bruce.query.condition.LyricsQueryCondition;
@Service("LyricsQuery")

public class LyricsQueryImpl implements LyricsQuery {

	@Override
	public String getSql(LyricsQueryCondition lyricsQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



