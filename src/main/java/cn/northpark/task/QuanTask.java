package cn.northpark.task;

import org.springframework.beans.factory.annotation.Autowired;

import cn.northpark.manager.QuanManager;
import cn.northpark.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时爬取红包
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
// "classpath:spring-hibernate.xml" })
public class QuanTask {
	
	@Autowired
	public QuanManager quanManager;

	public void runTask(){
		System.out.println("定时任务开始"+TimeUtils.getNowTime());
		try {

//    		HTMLParserUtil.retQuan();
    		

    	} catch (Exception e) {
    		// TODO: handle exception
    			e.printStackTrace();
    	}


		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
//	public void upTask(){
//		System.out.println("定时任务开始"+TimeUtils.getNowTime());
//		try {
//
//			//去重
//			  List<Quan> list_q =  quanManager.findAll();
//			  Set<String> set = new HashSet<String>();
//			  for(Quan q:list_q){
//				  set.add(q.getPath());
//			  }
//			  
//			  
//			  byte[] b = JedisUtil.getListByte("B_quan");
//		      List<Map<String,String> > list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
//		      if(list==null){
//		    	  HTMLParserUtil.retQuan();
//		    	  b = JedisUtil.getListByte("B_quan");
//			      list = (List<Map<String, String>>) SerializationUtil.deserialize(b);
//		      }
//		      if(list!=null){
//				    for (int i = 0; i <list.size(); i++) {
//				    	Map<String,String> map = list.get(i);
//				    	if(set.add(map.get("path"))){//判断去重
//						    	Quan model = new Quan();
//						    	model.setFromwhere(map.get("from"));     
//								model.setPublistime( map.get("publishtime"));   
//								model.setAuthorIP(map.get("authorIP"));     
//								model.setPath(map.get("path"));    
//								model.setTitle(map.get("title"));    
//								model.setPath_mt(map.get("path_mt"));    
//								model.setAddtime(map.get("addtime"));
//								quanManager.addQuan(model);
//				    	}
//					}
//		      }
//
//    	} catch (Exception e) {
//    		// TODO: handle exception
//    			e.printStackTrace();
//    	}
//
//
//		
//		System.out.println("定时任务结束"+TimeUtils.getNowTime());
//	}
//	
//	@Test
//	public void save() {
//		upTask();
//	}
//	
}
