
package com.bruce.query;
import com.bruce.model.Reset;
import com.bruce.query.condition.ResetQueryCondition;

public interface ResetQuery {
	public String getSql(ResetQueryCondition resetQueryCondition);
}
