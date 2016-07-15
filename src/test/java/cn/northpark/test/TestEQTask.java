package cn.northpark.test;
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
//import cn.northpark.utils.TimeUtils;
//
///**
// * @author zhangyang
// *
// * 定时爬取今日情圣文章
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
// "classpath:spring-hibernate.xml" })
//public class TestEQTask {
//	
//	@Autowired
//	public EqManager EqManager;
//
//	public void runTask(){
//		System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
//		try {
//
//    		Map<String, String> map = HTMLParserUtil.retTodayEq();
//    		
//    		String title = map.get("title");
//			String brief = map.get("brief");
//			String date = map.get("date");
//			String article = map.get("article");
//			//是不存在的文章
//			int flag = EqManager.countHql(new Eq(), " where o.date= '"+date+"' ");
//			
//			if(flag<=0){
//				//生成并且设置图片
//				List<String> meizi = HTMLParserUtil.gegeMEIZITU();
//				
//				int index = HTMLParserUtil.getRandomOne(meizi);
//				String img = meizi.get(index);
//				
//	    		Eq eq = new Eq();
//	    		eq.setArticle(article);
//	    		eq.setBrief(brief);
//	    		eq.setDate(date);
//	    		eq.setTitle(title);
//	    		eq.setImg(img);
//	    		EqManager.addEq(eq);
//			}
//			
//			//去重
//			String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM bc_eq GROUP BY date HAVING ( COUNT(date) > 1 )) AS p)" ;
//			
//			EqManager.executeSql(delsql);
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
//		System.out.println("情圣定时任务结束"+TimeUtils.getNowTime());
//	}
//	
//	
//	@Test
//	public void save() {
//		runTask();
//	}
//	
//}
