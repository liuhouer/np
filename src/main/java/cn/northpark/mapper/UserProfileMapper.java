package cn.northpark.mapper;

import cn.northpark.model.UserProfile;
import java.util.List;

public interface UserProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserProfile record);

    UserProfile selectByPrimaryKey(Integer id);

    List<UserProfile> selectAll();

    int updateByPrimaryKey(UserProfile record);

    UserProfile getModelByUserid(Integer userid);
}