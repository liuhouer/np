
package com.bruce.query;
import com.bruce.model.Orders;
import com.bruce.query.condition.OrdersQueryCondition;

public interface OrdersQuery {
	public String getSql(OrdersQueryCondition ordersQueryCondition);
}
