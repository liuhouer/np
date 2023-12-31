package cn.northpark.mapper;

import cn.northpark.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> login(@Param(value = "email") String email, @Param(value = "password") String password);
}