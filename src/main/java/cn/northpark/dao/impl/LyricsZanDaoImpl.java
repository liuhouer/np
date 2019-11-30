
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsZanDao;
import cn.northpark.model.LyricsZan;

@Service("LyricsZanDao")
public class LyricsZanDaoImpl extends HibernateDaoImpl<LyricsZan, Serializable> implements LyricsZanDao {


}