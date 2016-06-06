package com.bruce.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.manager.EqManager;
import com.bruce.model.Eq;
import com.bruce.utils.HTMLParserUtil;
import com.bruce.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时爬取今日情圣文章
 */
public class EQTask {
	
	@Autowired
	public EqManager EqManager;

	public void runTask(){
		System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
		try {

    		Map<String, String> map = HTMLParserUtil.retTodayEq();
    		
    		String title = map.get("title");
			String brief = map.get("brief");
			String date = map.get("date");
			String article = map.get("article");
			//是不存在的文章
			int flag = EqManager.countHql(new Eq(), " date= '"+date+"' ");
			
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
			
			

    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    	}


		
		System.out.println("情圣定时任务结束"+TimeUtils.getNowTime());
	}
	
	
}
