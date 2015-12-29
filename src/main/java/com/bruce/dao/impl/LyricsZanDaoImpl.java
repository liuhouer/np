
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.LyricsZanDao;
import com.bruce.model.LyricsZan;

@Service("LyricsZanDao")
public class LyricsZanDaoImpl extends HibernateDaoImpl<LyricsZan, Serializable> implements LyricsZanDao {


}