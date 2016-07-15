
package cn.northpark.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.northpark.query.OrdersQuery;
import cn.northpark.query.condition.OrdersQueryCondition;
@Service("OrdersQuery")

public class OrdersQueryImpl implements OrdersQuery {

	@Override
	public String getSql(OrdersQueryCondition ordersQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



