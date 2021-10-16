package cn.northpark.test;

import cn.northpark.utils.NPQueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
public class Gen_Movies_Sitemap {

    public static void runTask(Integer lastNum, Integer NewNum) {

        //=========================================================新url的sitemap===========================================================================================
        String sql = "select id from bc_movies where id > " + lastNum + " and id <=" + NewNum + " order by id desc";
        List<Map<String, Object>> mapList = NPQueryRunner.query(sql, new MapListHandler());

        //电影的网站地图
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mapList.size(); i++) {
            sb.append("<url>");
            sb.append("<loc>https://northpark.cn/movies/post-");
            sb.append(mapList.get(i).get("id") + ".html</loc>");
            sb.append("</url>");
        }

        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File("C:\\Users\\Bruce\\Documents\\movies.xml"), sb.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    //测试多页


    public static void main(String[] args) {
        runTask(748960,749246);//NEWLY 2020年12月26日
    }


}
