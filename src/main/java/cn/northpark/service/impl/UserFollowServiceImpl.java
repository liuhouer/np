
package cn.northpark.service.impl;

import cn.northpark.mapper.UserFollowMapper;
import cn.northpark.model.UserFollow;
import cn.northpark.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    UserFollowMapper ufMapper;

    @Override
    public UserFollow findUserFollow(Integer id) {
        return ufMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserFollow> findAll() {
        return ufMapper.selectAll();
    }

    @Override
    public void addUserFollow(UserFollow userfollow) {
        ufMapper.insert(userfollow);
    }

    @Override
    public boolean delUserFollow(Integer id) {
        ufMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUserFollow(UserFollow userfollow) {
        ufMapper.updateByPrimaryKey(userfollow);
        return true;
    }

    @Override
    public List<Map<String, Object>> getFansList(String sql) {
        return null;
    }

    @Override
    public List<UserFollow> findByCondition(String whereSql, String orderBy) {
        return ufMapper.findByCondition(whereSql,orderBy);
    }

}

