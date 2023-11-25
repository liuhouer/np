package cn.northpark.task;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.SoftManager;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.HttpGetUtils;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章+软件
 */
@Slf4j
public class EQTask {

    @Autowired
    public SoftManager softManager;


    public void runTask() {

        //TODO ..爬虫电影代码 2页

//        try {
//
//            log.info("movies task==============start=" + TimeUtils.getNowTime());
//
//            List<Map<String, String>> collect = new ArrayList<>();
//            for (int k = 1; k <= 2; k++) {
//
//                try {
//
//                    List<Map<String, String>> list = HTMLParserUtil.retRRMovies(k, BC_Constant.RET_RR_MOVIES);
//                    List<Map<String, String>> list_tv = HTMLParserUtil.retRRMovies(k, BC_Constant.RET_RR_TV);
//
//                    collect.addAll(list);
//                    collect.addAll(list_tv);
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }
//
//                try {
//                    Thread.sleep(2000);
//                    log.info("第" + k + "页================");
//                } catch (InterruptedException e) {
//                    // TODO Auo-generated catch block
//                    e.printStackTrace();
//                }
//            }
//
//            //混淆
//            collect = collect.stream().unordered().collect(Collectors.toList());
//            String jsonData = JsonUtil.object2json(collect);
//
//
//            log.info("爬取的数据----》,{}", jsonData);
//            String url = "https://northpark.cn/ret/movies/data";
//            try {
//                HttpGetUtils.sendPostJsonData(url, jsonData);
//            } catch (ClientProtocolException e) {
//
//                e.printStackTrace();
//            } catch (IOException e) {
//
//                e.printStackTrace();
//            }
//
//            // =================================删除重复的记录=======================================
//            // DELETE                                                                 /
//            // FROM                                                                   /
//            // 	bc_movies                                                          /
//            // WHERE                                                                  /
//            // 	id IN (                                                            /
//            // 		SELECT                                                         /
//            // 			id                                                         /
//            // 		FROM                                                           /
//            // 			(                                                          /
//            // 				SELECT                                                 /
//            // 					max(id) AS id,                                     /
//            // 					count(movie_name) AS count                          /
//            // 				FROM                                                   /
//            // 					bc_movies                                          /
//            // 				GROUP BY                                               /
//            // 					movie_name                                          /
//            // 				HAVING                                                 /
//            // 					count > 1                                          /
//            // 				ORDER BY                                               /
//            // 					count DESC                                         /
//            // 			) AS tab                                                   /
//            // 	);                                                                 /
//            //=================================删除重复的记录=======================================
//
//            //重复记录每个只保留一条
//            String delmovie_sql = "DELETE FROM bc_movies "
//                    + "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(movie_name) AS count "
//                    + "FROM bc_movies GROUP BY movie_name HAVING count > 1 ORDER BY count DESC ) AS tab )";
//
//            softManager.executeSql(delmovie_sql);
//
//            log.info("movies task==============end=" + TimeUtils.getNowTime());
//        } catch (Exception e) {
//
//            log.error("movies task  Exception==============" + e);
//        }

        // ..爬虫电影代码end--

    }
}