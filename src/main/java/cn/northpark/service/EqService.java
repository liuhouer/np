
package cn.northpark.service;

import cn.northpark.model.Eq;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


