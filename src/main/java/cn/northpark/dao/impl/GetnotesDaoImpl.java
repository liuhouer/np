
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.GetnotesDao;
import cn.northpark.model.Getnotes;

@Service("GetnotesDao")
public class GetnotesDaoImpl extends HibernateDaoImpl<Getnotes, Serializable> implements GetnotesDao {

}