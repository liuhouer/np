
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.AstroDao;
import cn.northpark.model.Astro;

@Service("AstroDao")
public class AstroDaoImpl extends HibernateDaoImpl<Astro, Serializable> implements AstroDao {

}