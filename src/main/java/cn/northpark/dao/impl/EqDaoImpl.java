
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.EqDao;
import cn.northpark.model.Eq;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("EqDao")
public class EqDaoImpl extends HibernateDaoImpl<Eq, Serializable> implements EqDao {


}