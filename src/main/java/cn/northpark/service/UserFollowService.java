
package cn.northpark.service;

import cn.northpark.model.UserFollow;

import java.util.List;
import java.util.Map;

public interface UserFollowService {

    UserFollow findUserFollow(Integer id);

    List<UserFollow> findAll();

    void addUserFollow(UserFollow userfollow);

    boolean delUserFollow(Integer id);

    boolean updateUserFollow(UserFollow userfollow);

    List<Map<String, Object>> getFansList(String sql);

    List<UserFollow> findByCondition(String whereSql, String orderBy);
}


