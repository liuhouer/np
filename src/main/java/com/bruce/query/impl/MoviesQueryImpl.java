
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.MoviesQuery;
import com.bruce.query.condition.MoviesQueryCondition;
@Service("MoviesQuery")

public class MoviesQueryImpl implements MoviesQuery {

	@Override
	public String getSql(MoviesQueryCondition moviesQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



