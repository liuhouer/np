
package com.bruce.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.bruce.dao.GetImgDao;
import com.bruce.model.GetImg;

@Service("GetImgDao")
public class GetImgDaoImpl extends HibernateDaoImpl<GetImg, Serializable> implements GetImgDao {

}