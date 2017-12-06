
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.model.Vps;
import cn.northpark.dao.VpsDao;
/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Service("VpsDao")
public class VpsDaoImpl extends HibernateDaoImpl<Vps, Serializable> implements VpsDao {

}