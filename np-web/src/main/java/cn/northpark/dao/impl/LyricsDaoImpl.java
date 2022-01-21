
package cn.northpark.dao.impl;

import cn.northpark.dao.LyricsDao;
import cn.northpark.model.Lyrics;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("LyricsDao")
public class LyricsDaoImpl extends HibernateDaoImpl<Lyrics, Serializable> implements LyricsDao {

}