
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Eq;
import com.bruce.dao.EqDao;
/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Service("EqDao")
public class EqDaoImpl extends HibernateDaoImpl<Eq, Serializable> implements EqDao {

}