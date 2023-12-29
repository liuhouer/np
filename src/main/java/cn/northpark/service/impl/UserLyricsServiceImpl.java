
package cn.northpark.service.impl;

import cn.northpark.mapper.UserLyricsMapper;
import cn.northpark.model.UserLyrics;
import cn.northpark.service.UserLyricsService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserLyricsServiceImpl implements UserLyricsService {

    @Autowired
    private UserLyricsMapper userlyricsMapper;

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

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p,
//                                                   String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = userlyricsMapper.findByCondition(p.getFirstResult(),
//                MyConstant.MAX_RESULT, wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<UserLyrics> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = userlyricsMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Map<String, Object>> getMixMapData(PageView<List<Map<String, Object>>> pageView, String userid) {
//        String sql = " select a.id,a.title,a.title_code,a.update_date,a.album_img,a.zan,a.pl,c.id as userid,c.username,c.email,  "
//
//                + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '" + userid + "' )> 0 "
//                + " then 'yizan' "
//                + " else '' "
//                + " end "
//                + " as yizan "
//
//                + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";
//
//        sql += " order by a.update_date desc";
//
//        List<Map<String, Object>> list = userlyricsMapper.querySQLForMapList(sql, pageView);
//        return list;
//
//
//    }
//
//
//
//
//    @Override
//    public List<Map<String, Object>> findMixByCondition(PageView<List<Map<String, Object>>> pageView, String randSql) {
//
//        return userlyricsMapper.querySQLForMapList(randSql, pageView);
//    }
//
//    @Override
//    public List<Map<String, Object>> execSql(String sql) {
//        return userlyricsMapper.execSql(sql);
//
//    }
//
//    @Override
//    public PageView<List<Map<String, Object>>> getMixMapPage(
//            PageView<List<Map<String, Object>>> pageView, String userid) {
//        String sql = " select a.id,a.title,a.title_code,a.update_date,a.album_img,a.zan,a.pl,c.id as userid,c.username,c.email,  "
//
//                + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '" + userid + "' )> 0 "
//                + " then 'yizan' "
//                + " else '' "
//                + " end "
//                + " as yizan "
//
//                + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";
//
//        sql += " order by a.update_date desc";
//
//        pageView = userlyricsMapper.querySQLCountForMapList(sql, pageView);
//        return pageView;
//    }
//
//    @Override
//    public List<Map<String, Object>> querySql(String sql, Object... obj) {
//        return userlyricsMapper.querySql(sql, obj);
//    }


    @Override
    public QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p, String whereSql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<UserLyrics> findByCondition(String whereSql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getMixMapData(PageView<List<Map<String, Object>>> pageView, String userid) {
        return null;
    }

    @Override
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String userid) {
        return null;
    }

    @Override
    public List<Map<String, Object>> querySql(String sql, Object... obj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findMixByCondition(PageView<List<Map<String, Object>>> pageView, String randSql) {
        return null;
    }

    @Override
    public List<Map<String, Object>> execSql(String sql) {
        return null;
    }
}

