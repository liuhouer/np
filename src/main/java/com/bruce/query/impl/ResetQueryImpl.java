
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.ResetQuery;
import com.bruce.query.condition.ResetQueryCondition;
@Service("ResetQuery")

public class ResetQueryImpl implements ResetQuery {

	@Override
	public String getSql(ResetQueryCondition resetQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



