
package cn.northpark.service;

import cn.northpark.model.PoemEnjoy;

import java.util.List;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface PoemEnjoyService {

    PoemEnjoy findPoemEnjoy(Integer id);

    List<PoemEnjoy> findAll();

    void addPoemEnjoy(PoemEnjoy poemenjoy);

    boolean delPoemEnjoy(Integer id);

    boolean updatePoemEnjoy(PoemEnjoy poemenjoy);

}


