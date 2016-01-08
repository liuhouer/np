
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.OrdersQuery;
import com.bruce.query.condition.OrdersQueryCondition;
@Service("OrdersQuery")

public class OrdersQueryImpl implements OrdersQuery {

	@Override
	public String getSql(OrdersQueryCondition ordersQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



