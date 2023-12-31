
package cn.northpark.service;

import cn.northpark.model.LyricsComment;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface LyricsCommentService {

     LyricsComment findLyricsComment(Integer id);

     List<LyricsComment> findAll();

     void addLyricsComment(LyricsComment lyricscomment);

     boolean delLyricsComment(Integer id);

     boolean updateLyricsComment(LyricsComment lyricscomment);
}


