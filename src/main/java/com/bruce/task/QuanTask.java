package com.bruce.task;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.manager.QuanManager;
import com.bruce.model.Quan;
import com.bruce.utils.HTMLParserUtil;
import com.bruce.utils.JedisUtil;
import com.bruce.utils.SerializationUtil;
import com.bruce.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时爬取红包
 */
public class QuanTask {
	
	@Autowired
	public QuanManager quanManager;

	public void runTask(){
		System.out.println("定时任务开始"+TimeUtils.getNowTime());
		try {

    		HTMLParserUtil.retQuan();
    		

    	} catch (Exception e) {
    		// TODO: handle exception
    			e.printStackTrace();
    	}


		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
	public void upTask(){
		System.out.println("定时任务开始"+TimeUtils.getNowTime());
		try {

			//去重
			  List<Quan> list_q =  quanManager.findAll();
			  Set<String> set = new HashSet<String>();
			  for(Quan q:list_q){
				  set.add(q.getPath());
			  }
			  
			  
			  byte[] b = JedisUtil.getListByte("B_quan");
		      List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
		      
		    for (int i = 0; i <list.size(); i++) {
		    	Map<String,String> map = list.get(i);
		    	if(set.add(map.get("path"))){//判断去重
				    	Quan model = new Quan();
				    	model.setFromwhere(map.get("from"));     
						model.setPublistime( map.get("publishtime"));   
						model.setAuthorIP(map.get("authorIP"));     
						model.setPath(map.get("path"));    
						model.setTitle(map.get("title"));    
						model.setPath_mt(map.get("path_mt"));    
						model.setAddtime(map.get("addtime"));
						quanManager.addQuan(model);
		    	}
			}
    		

    	} catch (Exception e) {
    		// TODO: handle exception
    			e.printStackTrace();
    	}


		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
}
