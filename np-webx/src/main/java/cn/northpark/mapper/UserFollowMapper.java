package cn.northpark.mapper;

import cn.northpark.model.UserFollow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(Integer id);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);

    List<UserFollow> findByCondition(@Param(value = "whereSql") String whereSql, @Param(value = "orderBy")String orderBy);
}