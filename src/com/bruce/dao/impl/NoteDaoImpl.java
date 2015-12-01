
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.model.Note;
import com.bruce.dao.NoteDao;

@Service("NoteDao")
public class NoteDaoImpl extends HibernateDaoImpl<Note, Serializable> implements NoteDao {

}