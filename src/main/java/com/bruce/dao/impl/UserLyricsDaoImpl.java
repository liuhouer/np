
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.UserLyricsDao;
import com.bruce.model.UserLyrics;

@Service("UserLyricsDao")
public class UserLyricsDaoImpl extends HibernateDaoImpl<UserLyrics, Serializable> implements UserLyricsDao {

}