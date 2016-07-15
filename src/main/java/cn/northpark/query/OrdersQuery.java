
package cn.northpark.query;
import cn.northpark.query.condition.OrdersQueryCondition;

public interface OrdersQuery {
	public String getSql(OrdersQueryCondition ordersQueryCondition);
}
