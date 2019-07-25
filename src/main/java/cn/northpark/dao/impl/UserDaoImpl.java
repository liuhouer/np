
package cn.northpark.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.northpark.dao.UserDao;
import cn.northpark.model.User;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;

@Service("UserDao")
@Slf4j
public class UserDaoImpl extends HibernateDaoImpl<User, Serializable> implements UserDao {

    public User login(String email, String password ,String ip) {
        // TODO Auto-generated method stub
        String sql = "select * from bc_user where email=?  and password = ? ";
        List<User> list = querySql(sql, User.class, new Object[]{email, password});
        User user = null;
        if(!CollectionUtils.isEmpty(list)) {
            user = list.get(0);
            
            //更新登录时间 +地址信息
            user.setLast_login(JsonUtil.object2json(TimeUtils.nowTime()+ip));
            update(user);
        }else {
        	log.error("错误的登录尝试----->："+JsonUtil.object2json(TimeUtils.nowTime()+ip));
        } 
        
        return user;
    }

}