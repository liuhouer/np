
package cn.northpark.service;

import cn.northpark.model.Lyrics;

import java.util.List;
import java.util.Map;

public interface LyricsService {

     Lyrics findLyrics(Integer id);

     List<Lyrics> findAll();

     void addLyrics(Lyrics lyrics);

     boolean delLyrics(Integer id);

     boolean updateLyrics(Lyrics lyrics);

    /**
     * 根据sql语句返回map结果集
     *
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql);


}


