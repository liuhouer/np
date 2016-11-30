package cn.northpark.test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import cn.northpark.manager.EqManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Eq;
import cn.northpark.model.Soft;
import cn.northpark.task.EQTask;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时爬取今日情圣文章
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
 "classpath:spring-hibernate.xml" })
public class TestEQTask {
	
	@Autowired
	public EqManager EqManager;

	@Autowired
	public SoftManager softManager;
		


		public void runTask(){
			
			Logger logger = Logger.getLogger(EQTask.class);  
			System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
			try {

	    		Map<String, String> map = HTMLParserUtil.retTodayEq();
	    		
	    		String title = map.get("title");
				String brief = map.get("brief");
				String date = map.get("date");
				String article = map.get("article");
				//是不存在的文章
				int flag = EqManager.countHql(new Eq(), " where o.date= '"+date+"' ");
				
				if(flag<=0){
					//生成并且设置图片
					List<String> meizi = HTMLParserUtil.gegeMEIZITU();
					
					int index = HTMLParserUtil.getRandomOne(meizi);
					String img = meizi.get(index);
					
		    		Eq eq = new Eq();
		    		eq.setArticle(article);
		    		eq.setBrief(brief);
		    		eq.setDate(date);
		    		eq.setTitle(title);
		    		eq.setImg(img);
		    		EqManager.addEq(eq);
				}
				
				//去重
				String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM bc_eq GROUP BY date HAVING ( COUNT(date) > 1 )) AS p)" ;
				
				EqManager.executeSql(delsql);
				
				
				
				
				

	    	} catch (Exception e) {
	    		// TODO: handle exception
	    		e.printStackTrace();
	    	}

			
			try {
				
				System.out.println("soft task==============start="+TimeUtils.getNowTime());
				logger.info("soft task==============start="+TimeUtils.getNowTime());
				Map<String,String> map = null;
					
				List<Map<String, String>> list = HTMLParserUtil.retSoft(1);
				
				
				if(!CollectionUtils.isEmpty(list)){
					for (int i = 0; i < list.size(); i++) {
						map  = list.get(i);
						
						String title = map.get("title");
						String aurl = map.get("aurl");
						String brief = map.get("brief");
						String date = map.get("date");
						String article = map.get("article");
						String tag = map.get("tag");
						String code = map.get("code");
						String os = map.get("os");
						String month  = map.get("month");
						String year  = map.get("year");
						String tagcode = map.get("tagcode");
						
						

						//是不存在的文章
						int flag = softManager.countHql(new Soft(), " where o.retcode= '"+code+"' ");
						
						if(flag<=0){
							
				    		Soft model = new Soft();
				    		model.setBrief(brief);
				    		model.setContent(article);
				    		model.setOs(os);
				    		model.setPostdate(date);
				    		model.setRetcode(code);
				    		model.setReturl(aurl);
				    		model.setTags(tag);
				    		model.setTitle(title);
				    		model.setMonth(month);
				    		model.setYear(year);
				    		model.setTagscode(tagcode);
				    		softManager.addSoft(model);
						}
					}
				}
				
				
				logger.info("soft task==============end="+TimeUtils.getNowTime());
				logger.trace("soft task==============end="+TimeUtils.getNowTime());
				System.out.println("soft task==============end="+TimeUtils.getNowTime());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			

			
			System.out.println("情圣定时任务结束"+TimeUtils.getNowTime());
		}
		
	
	
	@Test
	public void save() {
		runTask();
	}
	
}
