
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.NoteDao;
import com.bruce.model.Note;

@Service("NoteDao")
public class NoteDaoImpl extends HibernateDaoImpl<Note, Serializable> implements NoteDao {



}