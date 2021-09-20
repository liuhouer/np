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

        try {

            log.info("movies task==============start=" + TimeUtils.getNowTime());

            List<Map<String, String>> collect = new ArrayList<>();
            for (int k = 1; k <= 2; k++) {

                try {

                    List<Map<String, String>> list = HTMLParserUtil.retRRMovies(k, BC_Constant.RET_RR_MOVIES);
                    collect.addAll(list);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000);
                    log.info("第" + k + "页================");
                } catch (InterruptedException e) {
                    // TODO Auo-generated catch block
                    e.printStackTrace();
                }
            }
            String jsonData = JsonUtil.object2json(collect);


            log.info("爬取的数据----》,{}", jsonData);
            String url = "https://northpark.cn/ret/movies/data";
            try {
                HttpGetUtils.sendPostJsonData(url, jsonData);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // =================================删除重复的记录=======================================
            // DELETE                                                                 /
            // FROM                                                                   /
            // 	bc_movies                                                          /
            // WHERE                                                                  /
            // 	id IN (                                                            /
            // 		SELECT                                                         /
            // 			id                                                         /
            // 		FROM                                                           /
            // 			(                                                          /
            // 				SELECT                                                 /
            // 					max(id) AS id,                                     /
            // 					count(moviename) AS count                          /
            // 				FROM                                                   /
            // 					bc_movies                                          /
            // 				GROUP BY                                               /
            // 					moviename                                          /
            // 				HAVING                                                 /
            // 					count > 1                                          /
            // 				ORDER BY                                               /
            // 					count DESC                                         /
            // 			) AS tab                                                   /
            // 	);                                                                 /
            //=================================删除重复的记录=======================================

            //重复记录每个只保留一条
            String delmovie_sql = "DELETE FROM bc_movies "
                    + "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(moviename) AS count "
                    + "FROM bc_movies GROUP BY moviename HAVING count > 1 ORDER BY count DESC ) AS tab )";

            softManager.executeSql(delmovie_sql);

            log.info("movies task==============end=" + TimeUtils.getNowTime());
        } catch (Exception e) {
            // TODO: handle exception
            log.error("movies task  Exception==============" + e);
        }

        // ..爬虫电影代码end--

    }
}