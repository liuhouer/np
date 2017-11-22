
package cn.northpark.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.northpark.dao.UserprofileDao;
import cn.northpark.model.Userprofile;

@Service("UserprofileDao")
public class UserprofileDaoImpl extends HibernateDaoImpl<Userprofile, Serializable> implements UserprofileDao {

	@Override
	public Userprofile getModelByUserid(String userid) {
		// TODO Auto-generated method stub
		String sql = "select * from bc_userprofile where user_id=?";
		List<Userprofile> list = querySql(sql, Userprofile.class, userid);
		if(list.size()>0){
			return (Userprofile) list.get(0);
		}else{
			return new Userprofile();
		}
		
	}

}