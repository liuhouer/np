package cn.northpark.dao;

import cn.northpark.model.User;

import java.io.Serializable;

public interface UserDao extends HibernateDao<User, Serializable> {

    User login(String email, String password, String ip);

}