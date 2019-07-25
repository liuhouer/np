
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.EqDao;
import cn.northpark.model.Eq;

@Service("EqDao")
public class EqDaoImpl extends HibernateDaoImpl<Eq, Serializable> implements EqDao {


}