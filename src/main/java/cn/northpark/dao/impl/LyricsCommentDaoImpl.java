
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsCommentDao;
import cn.northpark.model.LyricsComment;

@Service("LyricsCommentDao")
public class LyricsCommentDaoImpl extends HibernateDaoImpl<LyricsComment, Serializable> implements LyricsCommentDao {

}