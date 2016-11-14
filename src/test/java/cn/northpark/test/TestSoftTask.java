//package cn.northpark.test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.CollectionUtils;
//
//import cn.northpark.manager.SoftManager;
//import cn.northpark.model.Soft;
//import cn.northpark.utils.HTMLParserUtil;
//import cn.northpark.utils.TimeUtils;
//
///**
// * @author zhangyang
// *
// * 定时爬取软件
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
// "classpath:spring-hibernate.xml" })
//public class TestSoftTask {
//	
//	@Autowired
//	public SoftManager softManager;
//
//	Logger logger = Logger.getLogger(TestSoftTask.class);  
//	
//	public void runTask(){
//		System.out.println("soft task==============start="+TimeUtils.getNowTime());
//		logger.info("soft task==============start="+TimeUtils.getNowTime());
//		try {
//			
////			Map<String,String> map = null;
////			//测试5页的数据
////			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
////			for (int i = 1; i <= 3; i++) {
////				
////				List<Map<String, String>> list1 = HTMLParserUtil.retSoft(i);
////				
////				list.addAll(list1);
////			}
////			
////			
////			if(!CollectionUtils.isEmpty(list)){
////				for (int i = 0; i < list.size(); i++) {
////					map  = list.get(i);
////					
////					String title = map.get("title");
////					String aurl = map.get("aurl");
////					String brief = map.get("brief");
////					String date = map.get("date");
////					String article = map.get("article");
////					String tag = map.get("tag");
////					String code = map.get("code");
////					String os = map.get("os");
////					String month  = map.get("month");
////					String year  = map.get("year");
////					String tagcode = map.get("tagcode");
////					
////					
////
////					//是不存在的文章
////					int flag = softManager.countHql(new Soft(), " where o.retcode= '"+code+"' ");
////					
////					if(flag<=0){
////						
////			    		Soft model = new Soft();
////			    		model.setBrief(brief);
////			    		model.setContent(article);
////			    		model.setOs(os);
////			    		model.setPostdate(date);
////			    		model.setRetcode(code);
////			    		model.setReturl(aurl);
////			    		model.setTags(tag);
////			    		model.setTitle(title);
////			    		model.setMonth(month);
////			    		model.setYear(year);
////			    		model.setTagscode(tagcode);
////			    		softManager.addSoft(model);
////					}
////				}
////			}
//			
//			//更新字段信息
////			List<Soft> list = softManager.findAll();
////			for (int i = 0; i < list.size(); i++) {
////				Soft m =  list.get(i);
////				String tag = m.getTags();
////				tag = tag.replaceAll("发表在", "").replaceAll(" ", "");
////				m.setTags(tag);
////				softManager.updateSoft(m);
////			}
//			
//			
////			List<Soft> list = softManager.findAll();
////			for (int i = 0; i < list.size(); i++) {
////				Soft m = list.get(i);
////				String brief = m.getBrief();
////				String cont = m.getContent();
////				brief = brief.replaceAll("http://sep9.cn/bkmc3u", "http://www.sdifenzhou.com");
////				cont = cont.replaceAll("http://sep9.cn/bkmc3u", "http://www.sdifenzhou.com");
////				m.setBrief(brief);
////				m.setContent(cont);
////				softManager.updateSoft(m);
////			}
//			
//			
//    		
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
//		logger.info("soft task==============end="+TimeUtils.getNowTime());
//		logger.trace("soft task==============end="+TimeUtils.getNowTime());
//		System.out.println("soft task==============end="+TimeUtils.getNowTime());
//	}
//	
//	
//	@Test
//	public void save() {
//		runTask();
//	}
//	
//}
