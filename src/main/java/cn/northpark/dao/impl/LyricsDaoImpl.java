
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsDao;
import cn.northpark.model.Lyrics;

@Service("LyricsDao")
public class LyricsDaoImpl extends HibernateDaoImpl<Lyrics, Serializable> implements LyricsDao {

}