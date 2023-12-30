
package cn.northpark.service;

import cn.northpark.model.LyricsZan;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface LyricsZanService {

     LyricsZan findLyricsZan(Integer id);

     List<LyricsZan> findAll();

     void addLyricsZan(LyricsZan lyricszan);

     boolean delLyricsZan(Integer id);

     boolean updateLyricsZan(LyricsZan lyricszan);

}


