//package cn.northpark.test;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
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
//@ContextConfiguration(locations = {"classpath:spring.xml",
//        "classpath:spring-hibernate.xml"})
//public class Test_Soft_Sitemap {
//
//    //
//    @Autowired
//    public SoftManager softManager;
//
//    Logger LOGGER = Logger.getLogger(Test_Soft_Sitemap.class);
//
//    public void runTask(Integer lastNum) {
//    	
//    	//=========================================================新url的sitemap===========================================================================================
//
//    	 //添加新url的sitemap
//		StringBuilder sb = new StringBuilder();
//		List<Map<String, Object>> list = softManager.querySqlMap(" select retcode from bc_soft where id > "+lastNum+" order by id desc ");
//		for(Map<String, Object> map :list){
//			String retcode = (String) map.get("retcode");
//			sb.append("<url>");
//			sb.append("<loc>https://northpark.cn/soft/");
//			sb.append(retcode+".html</loc>");
//			sb.append("</url>");
//		}
//		
//		try {
//			FileUtils.writeStringToFile(new File("d:\\soft.xml"), sb.toString());
//		} catch (Exception e) {
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
//        runTask(517413);
//    }
//
//}
