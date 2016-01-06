package com.bruce.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bruce.manager.NoteManager;
import com.bruce.utils.JedisUtil;

/**
 * 创建时间：2015年12月30日 13:39:12
 * 
 * @author bruce
 * @version 2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-hibernate.xml" })
public class TestRedis {

	private static final Logger LOGGER = Logger
			.getLogger(TestRedis.class);

	 @Autowired	
	 private NoteManager noteManager;
	 
	@Test
	public void test() {
		// fail("尚未实现");
//		List<Note> list  = noteManager.findAll();
//		    JedisUtil.pushList("notecache", JsonUtil.objectToJSONString(list));
//		    System.out.println(JedisUtil.getList("notecache"));
		
			JedisUtil.putString("test", "123456");
			String str1 = JedisUtil.getString("test");
			System.out.println(str1);
			
			JedisUtil.appendString("test", "-->append");
			String str2 = JedisUtil.getString("test");
			System.out.println(str2);
			
			System.out.println("--------------------");
			
			JedisUtil.remove("testList");
			JedisUtil.pushList("testList", "1111");
			JedisUtil.pushList("testList", "2222");
			JedisUtil.pushList("testList", "3333");
			List<String> list4 = JedisUtil.getList("testList");
			System.out.println(list4);
			
			String pop = JedisUtil.popList("testList");
			System.out.println(pop);
			List<String> list1 = JedisUtil.getList("testList");
			System.out.println(list1);

			System.out.println("--------------------");
			
			
			JedisUtil.remove("testList1");
			JedisUtil.pushList_stack("testList1", "1111");
			JedisUtil.pushList_stack("testList1", "2222");
			JedisUtil.pushList_stack("testList1", "3333");
			List<String> list_stack = JedisUtil.getList("testList1");
			System.out.println(list_stack);
			
			System.out.println("--------------------");
			
			JedisUtil.remove("testMap");
			Map<String,String> map = new HashMap<String,String>();
			map.put("name", "张三");
			map.put("age", "12312");
			map.put("sex", "男");
			JedisUtil.putMap("testMap", map);
			
			List<String> l1 = JedisUtil.getMapValue("testMap", "name");
			System.out.println(l1);
			Set<String> keySet = JedisUtil.getMapKeys("testMap");
			for (String key : keySet) {
				System.out.print(key+"--->");
			}
			System.out.println("");
			List<String> l2 = JedisUtil.getMapValues("testMap");
			System.out.println(l2);
		
	}

}
