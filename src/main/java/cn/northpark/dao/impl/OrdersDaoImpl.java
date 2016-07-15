
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.OrdersDao;
import cn.northpark.model.Orders;

@Service("OrdersDao")
public class OrdersDaoImpl extends HibernateDaoImpl<Orders, Serializable> implements OrdersDao {

}