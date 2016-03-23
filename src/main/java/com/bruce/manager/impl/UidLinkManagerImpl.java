
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bruce.model.UidLink;
import com.bruce.manager.UidLinkManager;
import com.bruce.dao.UidLinkDao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("UidLinkManager")
public class UidLinkManagerImpl implements UidLinkManager {

    @Autowired
	private UidLinkDao uidlinkDao;

	@Override
	public UidLink findUidLink(String id) {
		return uidlinkDao.find(id);
	}

	@Override
	public List<UidLink> findAll() {
		return uidlinkDao.findAll();
	}

	@Override
	public void addUidLink(UidLink uidlink) {
		uidlinkDao.save(uidlink);
	}

	@Override
	public boolean delUidLink(String id) {
		UidLink uidlink = uidlinkDao.find(id);
		uidlinkDao.delete(uidlink);
		return true;
	}

	@Override
	public boolean updateUidLink(UidLink uidlink) {
		uidlinkDao.update(uidlink);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UidLink> findByCondition(PageView<UidLink> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = uidlinkDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UidLink> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = uidlinkDao.findByCondition(
				 wheresql);
		return qrs;
	}
}

