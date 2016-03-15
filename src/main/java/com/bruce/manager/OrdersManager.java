
package com.bruce.manager;
import java.util.LinkedHashMap;
import java.util.List;

import com.bruce.model.Orders;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

public interface OrdersManager {
	
	public Orders findOrders(String id);

	public List<Orders> findAll();

	public void addOrders(Orders orders);

	public boolean delOrders(String id);

	public boolean updateOrders(Orders orders);
	
	public QueryResult<Orders> findByCondition(PageView<Orders> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Orders> findByCondition(
			String wheresql);
	
}


