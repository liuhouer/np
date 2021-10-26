package cn.northpark.test.sitemap;

import cn.northpark.utils.NPQueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * <p>
 * 刷新学习地图
 */
public class Gen_Learn_Sitemap {

    public static void runTask(Integer lastNum) {

        //=========================================================新url的sitemap===========================================================================================
        String sql = "select id from bc_knowledge where id > " + lastNum + " order by id desc";
        List<Map<String, Object>> mapList = NPQueryRunner.query(sql, new MapListHandler());

        //电影的网站地图
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mapList.size(); i++) {
            sb.append("<url>");
            sb.append("<loc>https://northpark.cn/learning/post-");
            sb.append(mapList.get(i).get("id") + ".html</loc>");
            sb.append("</url>");
        }

        try {
            org.apache.commons.io.FileUtils.writeStringToFile(new File("C:\\Users\\Bruce\\Documents\\learn.xml"), sb.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    //测试多页


    public static void main(String[] args) {
        runTask(518554);//NEWLY 2020年12月26日
    }


}
