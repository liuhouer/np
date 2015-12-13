
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.GetImgDao;
import com.bruce.manager.GetImgManager;
import com.bruce.model.GetImg;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("GetImgManager")
public class GetImgManagerImpl implements GetImgManager {

    @Autowired
	private GetImgDao imgDao;

	@Override
	public GetImg findGetImg(String id) {
		// TODO Auto-generated method stub
		return imgDao.find(id);
	}

	@Override
	public List<GetImg> findAll() {
		// TODO Auto-generated method stub
		return imgDao.findAll();
	}

	@Override
	public void addGetImg(GetImg note) {
		// TODO Auto-generated method stub
		imgDao.save(note);
	}

	@Override
	public boolean delGetImg(String id) {
		// TODO Auto-generated method stub
		imgDao.delete(id);
		return false;
	}

	@Override
	public boolean updateGetImg(GetImg note) {
		// TODO Auto-generated method stub
		imgDao.update(note);
		return false;
	}

	@Override
	public QueryResult<GetImg> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = imgDao.findByCondition(
				 wheresql);
		return qrs;
	}

}

