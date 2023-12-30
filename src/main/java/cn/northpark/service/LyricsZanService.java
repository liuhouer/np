
package cn.northpark.service;

import cn.northpark.model.LyricsZan;

import java.util.List;

public interface LyricsZanService {

     LyricsZan findLyricsZan(Integer id);

     List<LyricsZan> findAll();

     void addLyricsZan(LyricsZan lyricszan);

     boolean delLyricsZan(Integer id);

     boolean updateLyricsZan(LyricsZan lyricszan);

}


