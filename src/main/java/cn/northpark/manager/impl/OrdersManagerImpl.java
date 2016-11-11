
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.OrdersDao;
import cn.northpark.manager.OrdersManager;
import cn.northpark.model.Orders;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

@Service("OrdersManager")
public class OrdersManagerImpl implements OrdersManager {

    @Autowired
	private OrdersDao ordersDao;

	@Override
	public Orders findOrders(Integer id) {
		return ordersDao.find(id);
	}

	@Override
	public List<Orders> findAll() {
		return ordersDao.findAll();
	}

	@Override
	public void addOrders(Orders orders) {
		ordersDao.save(orders);
	}

	@Override
	public boolean delOrders(Integer id) {
		Orders orders = ordersDao.find(id);
		ordersDao.delete(orders);
		return true;
	}

	@Override
	public boolean updateOrders(Orders orders) {
		ordersDao.update(orders);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Orders> findByCondition(PageView<Orders> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = ordersDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Orders> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = ordersDao.findByCondition(
				 wheresql);
		return qrs;
	}
}

