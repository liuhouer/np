
package cn.northpark.manager.impl;

import cn.northpark.dao.UserprofileDao;
import cn.northpark.manager.UserprofileManager;
import cn.northpark.model.Userprofile;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("UserprofileManager")
public class UserprofileManagerImpl implements UserprofileManager {

    @Autowired
    private UserprofileDao userprofileDao;

    @Override
    public Userprofile findUserprofile(Integer id) {
        return userprofileDao.find(id);
    }

    @Override
    public List<Userprofile> findAll() {
        return userprofileDao.findAll();
    }

    @Override
    public void addUserprofile(Userprofile userprofile) {
        userprofileDao.save(userprofile);
    }

    @Override
    public boolean delUserprofile(Integer id) {
        Userprofile userprofile = userprofileDao.find(id);
        userprofileDao.delete(userprofile);
        return true;
    }

    @Override
    public boolean updateUserprofile(Userprofile userprofile) {
        userprofileDao.update(userprofile);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Userprofile> findByCondition(PageView<Userprofile> p,
                                                    String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = userprofileDao.findByCondition(p.getFirstResult(),
                MyConstant.MAX_RESULT, wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Userprofile> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = userprofileDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public Userprofile getModelByUserid(String userid) {
        // TODO Auto-generated method stub
        return userprofileDao.getModelByUserid(userid);
    }
}

