package com.bruce.dao;

import java.io.Serializable;

import com.bruce.model.User;

public interface UserDao extends HibernateDao<User, Serializable> {
	
	public User login(String email, String password );
	
}