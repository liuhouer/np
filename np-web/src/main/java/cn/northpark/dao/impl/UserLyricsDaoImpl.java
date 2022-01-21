
package cn.northpark.dao.impl;

import cn.northpark.dao.UserLyricsDao;
import cn.northpark.model.UserLyrics;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("UserLyricsDao")
public class UserLyricsDaoImpl extends HibernateDaoImpl<UserLyrics, Serializable> implements UserLyricsDao {

}