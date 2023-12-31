
package cn.northpark.service.impl;

import cn.northpark.mapper.UserLyricsMapper;
import cn.northpark.model.UserLyrics;
import cn.northpark.service.UserLyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserLyricsServiceImpl implements UserLyricsService {

    @Autowired
    UserLyricsMapper userlyricsMapper;

    @Override
    public UserLyrics findUserLyrics(Integer id) {
        return userlyricsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserLyrics> findAll() {
        return userlyricsMapper.selectAll();
    }

    @Override
    public void addUserLyrics(UserLyrics userlyrics) {
        userlyricsMapper.insert(userlyrics);
    }

    @Override
    public boolean delUserLyrics(Integer id) {
        userlyricsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUserLyrics(UserLyrics userlyrics) {
        userlyricsMapper.updateByPrimaryKey(userlyrics);
        return true;
    }

    @Override
    public String getMixSql(String userid) {
        String sql = " select a.id,a.title,a.title_code,a.update_date,a.album_img,a.zan,a.pl,c.id as userid,c.username,c.email,  "

                + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '" + userid + "' )> 0 "
                + " then 'yizan' "
                + " else '' "
                + " end "
                + " as yizan "

                + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";

        sql += " order by a.update_date desc";

        return sql;
    }

    @Override
    public String getRandSql() {
        String sql = " select a.id,a.title,a.title_code,a.update_date,a.album_img,a.zan,a.pl,c.id as userid,c.username,c.email,  "

                + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '' )> 0 "
                + " then 'yizan' "
                + " else '' "
                + " end "
                + " as yizan "

                + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";

        sql += " order by a.zan desc ";
        return sql;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String mixSQL) {
        return userlyricsMapper.querySqlMap(mixSQL);
    }

    @Override
    public List<UserLyrics> findByCondition(String whereSql, String orderBy) {
        return userlyricsMapper.findByCondition(whereSql,orderBy);
    }

}

