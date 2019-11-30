package cn.northpark.dao;

import java.io.Serializable;

import cn.northpark.model.Userprofile;

public interface UserprofileDao extends HibernateDao<Userprofile, Serializable> {

    public Userprofile getModelByUserid(String userid);

}