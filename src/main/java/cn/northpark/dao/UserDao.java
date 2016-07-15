package cn.northpark.dao;

import java.io.Serializable;

import cn.northpark.model.User;

public interface UserDao extends HibernateDao<User, Serializable> {
	
	public User login(String email, String password );
	
}