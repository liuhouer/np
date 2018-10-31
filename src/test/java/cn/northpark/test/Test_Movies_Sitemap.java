//package cn.northpark.test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import cn.northpark.manager.SoftManager;
//
///**
// * @author zhangyang
// * <p>
// * 定时爬取今日情圣文章
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml" , "classpath:application-transaction.xml"})
//public class Test_Movies_Sitemap {
//
//    //
//    @Autowired
//    public SoftManager softManager;
//
//    Logger LOGGER = LoggerFactory.getLogger(Test_Movies_Sitemap.class);
//
//    public void runTask(Integer lastNum) {
//    	
//    	//=========================================================新url的sitemap===========================================================================================
//
//    	//电影的网站地图
//		StringBuilder sb = new StringBuilder();
//		List<Map<String, Object>> list = softManager.querySqlMap(" select id from bc_movies where id > "+lastNum+" order by id desc ");
//		for(Map<String, Object> map :list){
//			Object retcode = (Object) map.get("id");
//			sb.append("<url>");
//			sb.append("<loc>https://northpark.cn/movies/post-");
//			sb.append(retcode+".html</loc>");
//			sb.append("</url>");
//		}
//		
//		try {
//			org.apache.commons.io.FileUtils.writeStringToFile(new File("d:\\movies-sitemap.xml"), sb.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//
//
//    }
//
//
//    //测试多页
//
//
//    @Test
//    public void save() {
//        runTask(680820);
//    }
//
//}