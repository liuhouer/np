
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.UserLyricsDao;
import cn.northpark.model.UserLyrics;

@Service("UserLyricsDao")
public class UserLyricsDaoImpl extends HibernateDaoImpl<UserLyrics, Serializable> implements UserLyricsDao {

}