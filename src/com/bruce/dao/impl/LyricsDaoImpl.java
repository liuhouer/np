
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Lyrics;
import com.bruce.dao.LyricsDao;

@Service("LyricsDao")
public class LyricsDaoImpl extends HibernateDaoImpl<Lyrics, Serializable> implements LyricsDao {

}