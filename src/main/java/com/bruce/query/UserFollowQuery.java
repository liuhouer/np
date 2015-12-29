
package com.bruce.query;
import com.bruce.query.condition.UserFollowQueryCondition;

public interface UserFollowQuery {
	public String getSql(UserFollowQueryCondition userfollowQueryCondition);
}
