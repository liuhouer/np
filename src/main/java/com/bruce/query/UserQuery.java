
package com.bruce.query;
import com.bruce.query.condition.UserQueryCondition;

public interface UserQuery {
	public String getSql(UserQueryCondition userQueryCondition);
}
