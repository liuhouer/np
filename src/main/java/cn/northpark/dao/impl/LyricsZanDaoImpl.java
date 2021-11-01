
package cn.northpark.dao.impl;

import cn.northpark.dao.LyricsZanDao;
import cn.northpark.model.LyricsZan;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("LyricsZanDao")
public class LyricsZanDaoImpl extends HibernateDaoImpl<LyricsZan, Serializable> implements LyricsZanDao {


}