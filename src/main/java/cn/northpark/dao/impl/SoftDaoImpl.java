
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.SoftDao;
import cn.northpark.model.Soft;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("SoftDao")
public class SoftDaoImpl extends HibernateDaoImpl<Soft, Serializable> implements SoftDao {

}