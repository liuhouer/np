
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.UserFollowQuery;
import cn.northpark.query.condition.UserFollowQueryCondition;
@Service("UserFollowQuery")

public class UserFollowQueryImpl implements UserFollowQuery {

	@Override
	public String getSql(UserFollowQueryCondition userfollowQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



