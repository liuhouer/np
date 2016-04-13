
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.LyricsComment;
import com.bruce.dao.LyricsCommentDao;

@Service("LyricsCommentDao")
public class LyricsCommentDaoImpl extends HibernateDaoImpl<LyricsComment, Serializable> implements LyricsCommentDao {

}