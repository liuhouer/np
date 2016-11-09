
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.ResetQuery;
import cn.northpark.query.condition.ResetQueryCondition;
@Service("ResetQuery")

public class ResetQueryImpl implements ResetQuery {

	@Override
	public String getSql(ResetQueryCondition resetQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



