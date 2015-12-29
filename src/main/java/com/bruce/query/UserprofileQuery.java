
package com.bruce.query;
import com.bruce.query.condition.UserprofileQueryCondition;

public interface UserprofileQuery {
	public String getSql(UserprofileQueryCondition userprofileQueryCondition);
}
