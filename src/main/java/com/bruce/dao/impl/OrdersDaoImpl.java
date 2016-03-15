
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.OrdersDao;
import com.bruce.model.Orders;

@Service("OrdersDao")
public class OrdersDaoImpl extends HibernateDaoImpl<Orders, Serializable> implements OrdersDao {

}