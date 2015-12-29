package com.bruce.dao;

import java.io.Serializable;

import com.bruce.model.Userprofile;

public interface UserprofileDao extends HibernateDao<Userprofile, Serializable> {
	
	public Userprofile getModelByUserid(String userid);
	
}