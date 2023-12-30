
package cn.northpark.service.impl;

import cn.northpark.mapper.UserMapper;
import cn.northpark.model.User;
import cn.northpark.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public boolean delUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
        return true;
    }


    @Override
    public User login(String email, String password) {
        List<User> logins = userMapper.login(email, password);
        return CollectionUtils.isNotEmpty(logins)?logins.get(0):null;
    }

}

