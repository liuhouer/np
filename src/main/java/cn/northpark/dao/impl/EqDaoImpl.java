
package cn.northpark.dao.impl;

import cn.northpark.dao.EqDao;
import cn.northpark.model.Eq;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("EqDao")
public class EqDaoImpl extends HibernateDaoImpl<Eq, Serializable> implements EqDao {


}