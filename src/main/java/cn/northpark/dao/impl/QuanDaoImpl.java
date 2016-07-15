
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.QuanDao;
import cn.northpark.model.Quan;

@Service("QuanDao")
public class QuanDaoImpl extends HibernateDaoImpl<Quan, Serializable> implements QuanDao {

}