
package com.bruce.query;
import com.bruce.model.Quan;
import com.bruce.query.condition.QuanQueryCondition;

public interface QuanQuery {
	public String getSql(QuanQueryCondition quanQueryCondition);
}
