
package cn.northpark.dao.impl;

import cn.northpark.dao.LyricsCommentDao;
import cn.northpark.model.LyricsComment;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("LyricsCommentDao")
public class LyricsCommentDaoImpl extends HibernateDaoImpl<LyricsComment, Serializable> implements LyricsCommentDao {

}