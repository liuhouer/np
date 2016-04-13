
package com.bruce.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bruce.model.LyricsZan;
import com.bruce.utils.PageView;
import com.bruce.dao.LyricsZanDao;

@Service("LyricsZanDao")
public class LyricsZanDaoImpl extends HibernateDaoImpl<LyricsZan, Serializable> implements LyricsZanDao {


}