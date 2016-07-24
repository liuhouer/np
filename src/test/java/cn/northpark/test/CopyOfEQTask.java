//package cn.northpark.test;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import cn.northpark.manager.EqManager;
//import cn.northpark.model.Eq;
//import cn.northpark.utils.HTMLParserUtil;
//
///**
// * @author zhangyang
// *
// * 定时爬取今日情圣文章
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
// "classpath:spring-hibernate.xml" })
//public class CopyOfEQTask {
//	
//	@Autowired
//	public EqManager EqManager;
//
//	public void runTask(){
//		try {
//			List<Map<String, String>> list = HTMLParserUtil.retEQArticle();
//			
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, String> map = list.get(i);
//	    		
//	    		String title = map.get("title");
//				String brief = map.get("brief");
//				String date = map.get("date");
//				String article = map.get("article");
//				//是今天的文章
//					//生成并且设置图片
//					List<String> meizi = HTMLParserUtil.gegeMEIZITU();
//					
//					int index = HTMLParserUtil.getRandomOne(meizi);
//					String img = meizi.get(index);
//					
//		    		Eq eq = new Eq();
//		    		eq.setArticle(article);
//		    		eq.setBrief(brief);
//		    		eq.setDate(date);
//		    		eq.setTitle(title);
//		    		eq.setImg(img);
//		    		EqManager.addEq(eq);
//			}
//    		
//			
//			
//
//    	} catch (Exception e) {
//    		// TODO: handle exception
//    		e.printStackTrace();
//    	}
//
//
//		
//	}
//	
//	
//	@Test
//	public void save() {
//		runTask();
//	}
//	
//}
