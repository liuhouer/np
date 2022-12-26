
package cn.northpark.test.sitemap;

import cn.northpark.test.BaseTest;
import cn.northpark.utils.NPQueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
public class Gen_Soft_Sitemap extends BaseTest {



    public static void runTask(Integer lastNum) {

        //=========================================================新url的sitemap===========================================================================================

        //添加新url的sitemap
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = NPQueryRunner.query(" select id from bc_soft where id > " + lastNum + " order by id desc ",new MapListHandler());
        for (Map<String, Object> map : list) {
            String id = (String) map.get("id").toString();
            sb.append("<url>");
            sb.append("<loc>https://northpark.cn/soft/post-");
            sb.append(id + ".html</loc>");
            sb.append("</url>");
        }

        try {
            FileUtils.writeStringToFile(new File("C:\\Users\\Bruce\\Documents\\soft.xml"), sb.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            try {
                Desktop.getDesktop().open(new File("C:\\Users\\Bruce\\Documents"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    //测试多页

    public static void main(String[] args) {
        runTask(1);//NEWLY 2020年12月26日
    }

}
