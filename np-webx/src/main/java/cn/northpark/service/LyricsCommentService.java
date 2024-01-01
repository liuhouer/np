
package cn.northpark.service;

import cn.northpark.model.LyricsComment;

import java.util.List;

public interface LyricsCommentService {

     LyricsComment findLyricsComment(Integer id);

     List<LyricsComment> findAll();

     void addLyricsComment(LyricsComment lyricscomment);

     boolean delLyricsComment(Integer id);

     boolean updateLyricsComment(LyricsComment lyricscomment);
}


