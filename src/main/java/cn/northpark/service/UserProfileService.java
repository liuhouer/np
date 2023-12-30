
package cn.northpark.service;

import cn.northpark.model.UserProfile;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserProfileService {

    UserProfile findUserProfile(Integer id);

    List<UserProfile> findAll();

    void addUserProfile(UserProfile UserProfile);

    boolean delUserProfile(Integer id);

    boolean updateUserProfile(UserProfile UserProfile);

    UserProfile getModelByUserid(Integer userid);
}


