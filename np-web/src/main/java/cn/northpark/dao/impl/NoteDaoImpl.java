
package cn.northpark.dao.impl;

import cn.northpark.dao.NoteDao;
import cn.northpark.model.Note;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("NoteDao")
public class NoteDaoImpl extends HibernateDaoImpl<Note, Serializable> implements NoteDao {


}