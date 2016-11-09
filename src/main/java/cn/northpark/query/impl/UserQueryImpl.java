
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.UserQuery;
import cn.northpark.query.condition.UserQueryCondition;
@Service("UserQuery")

public class UserQueryImpl implements UserQuery {

	@Override
	public String getSql(UserQueryCondition userQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



