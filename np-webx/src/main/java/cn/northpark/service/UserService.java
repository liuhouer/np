
package cn.northpark.service;

import cn.northpark.model.User;

import java.util.List;

public interface UserService {


    User findUser(Integer id);

    List<User> findAll();

    void addUser(User user);

    boolean delUser(Integer id);

    boolean updateUser(User user);

    User login(String email, String password);


}


