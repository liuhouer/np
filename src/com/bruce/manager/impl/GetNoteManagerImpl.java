
package com.bruce.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.GetNoteDao;
import com.bruce.manager.GetNoteManager;
import com.bruce.model.GetNote;

@Service("GetNoteManager")
public class GetNoteManagerImpl implements GetNoteManager {

    @Autowired
	private GetNoteDao noteDao;

	@Override
	public GetNote findGetNote(String id) {
		// TODO Auto-generated method stub
		return noteDao.find(id);
	}

	@Override
	public List<GetNote> findAll() {
		// TODO Auto-generated method stub
		return noteDao.findAll();
	}

	@Override
	public void addGetNote(GetNote note) {
		// TODO Auto-generated method stub
		noteDao.save(note);
	}

	@Override
	public boolean delGetNote(String id) {
		// TODO Auto-generated method stub
		noteDao.delete(id);
		return false;
	}

	@Override
	public boolean updateGetNote(GetNote note) {
		// TODO Auto-generated method stub
		noteDao.update(note);
		return false;
	}


}

