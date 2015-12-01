
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.LyricsZan;
import com.bruce.dao.LyricsZanDao;

@Service("LyricsZanDao")
public class LyricsZanDaoImpl extends HibernateDaoImpl<LyricsZan, Serializable> implements LyricsZanDao {

}