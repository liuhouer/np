
package cn.northpark.service.impl;

import cn.northpark.model.UserProfile;
import cn.northpark.service.UserProfileService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public UserProfile findUserProfile(Integer id) {
        return null;
    }

    @Override
    public List<UserProfile> findAll() {
        return null;
    }

    @Override
    public void addUserProfile(UserProfile UserProfile) {

    }

    @Override
    public boolean delUserProfile(Integer id) {
        return false;
    }

    @Override
    public boolean updateUserProfile(UserProfile UserProfile) {
        return false;
    }

    @Override
    public QueryResult<UserProfile> findByCondition(PageView<UserProfile> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<UserProfile> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public UserProfile getModelByUserid(String userid) {
        return null;
    }
}

