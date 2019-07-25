
package cn.northpark.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import cn.northpark.dao.NoteDao;
import cn.northpark.model.Note;

@Service("NoteDao")
public class NoteDaoImpl extends HibernateDaoImpl<Note, Serializable> implements NoteDao {


}