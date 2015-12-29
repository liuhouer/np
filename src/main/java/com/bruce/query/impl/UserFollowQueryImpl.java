
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.UserFollowQuery;
import com.bruce.query.condition.UserFollowQueryCondition;
@Service("UserFollowQuery")

public class UserFollowQueryImpl implements UserFollowQuery {

	@Override
	public String getSql(UserFollowQueryCondition userfollowQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



