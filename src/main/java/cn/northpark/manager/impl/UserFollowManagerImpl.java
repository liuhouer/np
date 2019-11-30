
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.UserFollowDao;
import cn.northpark.manager.UserFollowManager;
import cn.northpark.model.UserFollow;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@Service("UserFollowManager")
public class UserFollowManagerImpl implements UserFollowManager {

    @Autowired
    private UserFollowDao userfollowDao;

    @Override
    public UserFollow findUserFollow(Integer id) {
        return userfollowDao.find(id);
    }

    @Override
    public List<UserFollow> findAll() {
        return userfollowDao.findAll();
    }

    @Override
    public void addUserFollow(UserFollow userfollow) {
        userfollowDao.save(userfollow);
    }

    @Override
    public boolean delUserFollow(Integer id) {
        UserFollow userfollow = userfollowDao.find(id);
        userfollowDao.delete(userfollow);
        return true;
    }

    @Override
    public boolean updateUserFollow(UserFollow userfollow) {
        userfollowDao.update(userfollow);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
                                                   String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = userfollowDao.findByCondition(p.getFirstResult(),
                MyConstant.MAXRESULT, wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<UserFollow> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = userfollowDao.findByCondition(
                wheresql);
        return qrs;
    }

    @Override
    public List<Map<String, Object>> getFansList(String sql) {
        // TODO Auto-generated method stub
        return userfollowDao.querySql(sql);
    }

    @Override
    public List<Map<String, Object>> querySql(String sql, Object... obj) {
        // TODO Auto-generated method stub
        return userfollowDao.querySql(sql, obj);
    }

    @Override
    public int getCountByCondition(String wheresql) {
        // TODO Auto-generated method stub
        return userfollowDao.countHql(UserFollow.class, wheresql);
    }
}

