package cn.northpark.dao;

import cn.northpark.model.Userprofile;

import java.io.Serializable;

public interface UserprofileDao extends HibernateDao<Userprofile, Serializable> {

    public Userprofile getModelByUserid(String userid);

}