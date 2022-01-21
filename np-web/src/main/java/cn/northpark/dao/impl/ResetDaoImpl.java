
package cn.northpark.dao.impl;

import cn.northpark.dao.ResetDao;
import cn.northpark.model.Reset;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("ResetDao")
public class ResetDaoImpl extends HibernateDaoImpl<Reset, Serializable> implements ResetDao {

}