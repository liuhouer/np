
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.LyricsDao;
import com.bruce.model.Lyrics;

@Service("LyricsDao")
public class LyricsDaoImpl extends HibernateDaoImpl<Lyrics, Serializable> implements LyricsDao {

}