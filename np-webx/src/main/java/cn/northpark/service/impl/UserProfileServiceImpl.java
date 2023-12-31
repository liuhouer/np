
package cn.northpark.service.impl;

import cn.northpark.mapper.UserProfileMapper;
import cn.northpark.model.UserProfile;
import cn.northpark.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileMapper upMapper;

    @Override
    public UserProfile findUserProfile(Integer id) {
        return upMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserProfile> findAll() {
        return upMapper.selectAll();
    }

    @Override
    public void addUserProfile(UserProfile UserProfile) {
        upMapper.insert(UserProfile);
    }

    @Override
    public boolean delUserProfile(Integer id) {
        upMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUserProfile(UserProfile UserProfile) {
        upMapper.updateByPrimaryKey(UserProfile);
        return true;
    }

    @Override
    public UserProfile getModelByUserid(Integer userid) {
        return upMapper.getModelByUserid(userid);
    }
}

