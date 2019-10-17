package cn.northpark.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.northpark.manager.SoftManager;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
public class Test_Movies_Sitemap{



    public void runTask(Integer lastNum ,Integer NewNum) {
    	
    	//=========================================================新url的sitemap===========================================================================================

    	//电影的网站地图
		StringBuilder sb = new StringBuilder();
		for(int i=NewNum;i>lastNum;i--){
			sb.append("<url>");
			sb.append("<loc>https://northpark.cn/movies/post-");
			sb.append(i+".html</loc>");
			sb.append("</url>");
		}
		
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(new File("d:\\movies-sitemap.xml"), sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		



    }


    //测试多页


    @Test
    public void save() {
        runTask(682282,682585);//NEWLY 2019年3月29日
    }

}
