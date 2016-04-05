
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.QuanQuery;
import com.bruce.query.condition.QuanQueryCondition;
@Service("QuanQuery")

public class QuanQueryImpl implements QuanQuery {

	@Override
	public String getSql(QuanQueryCondition quanQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



