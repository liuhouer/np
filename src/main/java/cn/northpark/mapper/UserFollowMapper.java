package cn.northpark.mapper;

import cn.northpark.model.UserFollow;
import java.util.List;

public interface UserFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(Integer id);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);
}