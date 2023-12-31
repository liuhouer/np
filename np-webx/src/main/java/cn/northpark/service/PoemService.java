
package cn.northpark.service;

import cn.northpark.model.Poem;

import java.util.List;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface PoemService {

    Poem findPoem(Integer id);

    List<Poem> findAll();

    void addPoem(Poem poem);

    boolean delPoem(Integer id);

    boolean updatePoem(Poem poem);


    List<Poem> querySql(String sql);

    List<Poem> findByCondition(String whereSql, String orderBy);
}


