
package cn.northpark.service;

import cn.northpark.model.Tags;

import java.util.List;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface TagsService {

    Tags findTags(Integer id);

    List<Tags> findAll();

    void addTags(Tags tags);

    boolean delTags(Integer id);

    boolean updateTags(Tags tags);

    List<Tags> findByCondition(String whereSql, String orderBy);

}


