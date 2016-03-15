
package com.bruce.query.impl;
import org.springframework.stereotype.Service;

import com.bruce.query.GetinfoQuery;
import com.bruce.query.condition.getinfoQueryCondition;
@Service("GetinfoQuery")

public class GetinfoQueryImpl implements GetinfoQuery {

	@Override
	public String getSql(getinfoQueryCondition getinfoQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



