
package cn.northpark.service;

import cn.northpark.model.Eq;

import java.util.List;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface EqService {

     Eq findEq(Integer id);

     List<Eq> findAll();

     void addEq(Eq eq);

     boolean delEq(Integer id);

     boolean updateEq(Eq eq);


    List<Eq> findByCondition(String whereSql, String orderBy);
}


