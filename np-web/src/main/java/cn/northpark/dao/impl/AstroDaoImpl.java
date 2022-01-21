
package cn.northpark.dao.impl;

import cn.northpark.dao.AstroDao;
import cn.northpark.model.Astro;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("AstroDao")
public class AstroDaoImpl extends HibernateDaoImpl<Astro, Serializable> implements AstroDao {

}