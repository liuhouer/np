
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.GetNoteDao;
import com.bruce.model.GetNote;

@Service("GetNoteDao")
public class GetNoteDaoImpl extends HibernateDaoImpl<GetNote, Serializable> implements GetNoteDao {

}