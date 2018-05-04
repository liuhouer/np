
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.AstroDao;
import cn.northpark.model.Astro;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Service("AstroDao")
public class AstroDaoImpl extends HibernateDaoImpl<Astro, Serializable> implements AstroDao {

}