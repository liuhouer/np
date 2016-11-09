
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.QuanQuery;
import cn.northpark.query.condition.QuanQueryCondition;
@Service("QuanQuery")

public class QuanQueryImpl implements QuanQuery {

	@Override
	public String getSql(QuanQueryCondition quanQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



