
package cn.northpark.test.sitemap;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.northpark.test.BaseTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.northpark.manager.SoftManager;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
public class Gen_Soft_Sitemap extends BaseTest {

    //
    @Autowired
    public SoftManager softManager;


    public void runTask(Integer lastNum) {

        //=========================================================新url的sitemap===========================================================================================

        //添加新url的sitemap
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = softManager.querySqlMap(" select ret_code from bc_soft where id > " + lastNum + " order by id desc ");
        for (Map<String, Object> map : list) {
            String ret_code = (String) map.get("ret_code");
            sb.append("<url>");
            sb.append("<loc>https://northpark.cn/soft/");
            sb.append(ret_code + ".html</loc>");
            sb.append("</url>");
        }

        try {
            FileUtils.writeStringToFile(new File("/Users/bruce/Downloads/soft.xml"), sb.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    //测试多页


    @Test
    public void save() {
        runTask(519603);//NEWLY 2020年12月26日
    }

}
