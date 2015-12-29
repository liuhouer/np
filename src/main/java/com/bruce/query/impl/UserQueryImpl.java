
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.UserQuery;
import com.bruce.query.condition.UserQueryCondition;
@Service("UserQuery")

public class UserQueryImpl implements UserQuery {

	@Override
	public String getSql(UserQueryCondition userQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



