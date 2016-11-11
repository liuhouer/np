
package cn.northpark.manager;
import java.util.LinkedHashMap;
import java.util.List;

import cn.northpark.model.Orders;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

public interface OrdersManager {
	
	public Orders findOrders(Integer id);

	public List<Orders> findAll();

	public void addOrders(Orders orders);

	public boolean delOrders(Integer id);

	public boolean updateOrders(Orders orders);
	
	public QueryResult<Orders> findByCondition(PageView<Orders> p,
			String wheresql, LinkedHashMap<String, String> order);

	public QueryResult<Orders> findByCondition(
			String wheresql);
	
}


