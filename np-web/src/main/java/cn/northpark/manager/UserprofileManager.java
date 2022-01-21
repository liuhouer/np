
package cn.northpark.manager;

import cn.northpark.model.Userprofile;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface UserprofileManager {

     Userprofile findUserprofile(Integer id);

     List<Userprofile> findAll();

     void addUserprofile(Userprofile userprofile);

     boolean delUserprofile(Integer id);

     boolean updateUserprofile(Userprofile userprofile);

     QueryResult<Userprofile> findByCondition(PageView<Userprofile> p,
                                                    String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Userprofile> findByCondition(
            String wheresql);

     Userprofile getModelByUserid(String userid);
}


