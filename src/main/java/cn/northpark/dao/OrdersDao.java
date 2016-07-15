package cn.northpark.dao;

import java.io.Serializable;

import cn.northpark.model.Orders;

public interface OrdersDao extends HibernateDao<Orders, Serializable> {
	
}