package cn.northpark.test;

import cn.northpark.manager.MoviesManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
        "classpath:application-transaction.xml"})
public class Gen_Movies_Sitemap {

    @Autowired
    public MoviesManager moviesManager;


    public void runTask(Integer lastNum, Integer NewNum) {

        //=========================================================新url的sitemap===========================================================================================

        List<Map<String, Object>> mapList = moviesManager.querySqlMap("select id from bc_movies where id > " + lastNum + " and id <=" + NewNum + " order by id desc");
//        List<Map<String, Object>> mapList = moviesManager.querySqlMap("select id from bc_movies  order by id desc");

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


    @Test
    public void save() {
        runTask(732353,732793);//NEWLY 2020年12月26日
    }

}
