
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.LyricsCommentDao;
import com.bruce.model.LyricsComment;

@Service("LyricsCommentDao")
public class LyricsCommentDaoImpl extends HibernateDaoImpl<LyricsComment, Serializable> implements LyricsCommentDao {

}