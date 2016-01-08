
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Orders;
import com.bruce.dao.OrdersDao;

@Service("OrdersDao")
public class OrdersDaoImpl extends HibernateDaoImpl<Orders, Serializable> implements OrdersDao {

}