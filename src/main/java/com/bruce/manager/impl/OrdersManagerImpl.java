
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bruce.model.Orders;
import com.bruce.manager.OrdersManager;
import com.bruce.dao.OrdersDao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("OrdersManager")
public class OrdersManagerImpl implements OrdersManager {

    @Autowired
	private OrdersDao ordersDao;

	@Override
	public Orders findOrders(String id) {
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
	public boolean delOrders(String id) {
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

