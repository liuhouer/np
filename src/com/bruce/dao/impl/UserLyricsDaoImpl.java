
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.UserLyrics;
import com.bruce.dao.UserLyricsDao;

@Service("UserLyricsDao")
public class UserLyricsDaoImpl extends HibernateDaoImpl<UserLyrics, Serializable> implements UserLyricsDao {

}