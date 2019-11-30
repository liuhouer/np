
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.ResetDao;
import cn.northpark.model.Reset;

@Service("ResetDao")
public class ResetDaoImpl extends HibernateDaoImpl<Reset, Serializable> implements ResetDao {

}