package com.bruce.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bruce.manager.LyricsCommentManager;

	
	/**
	 * 创建时间：2015年12月30日 13:39:12
	 * 
	 * @author bruce
	 * @version 2.2
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(locations = { "classpath:spring.xml",
			"classpath:spring-hibernate.xml" })
	public class TestService {

		private static final Logger LOGGER = Logger
				.getLogger(TestService.class);

		 @Autowired	
		 private LyricsCommentManager commentManager;
		 
		 

		@Test
		public void save() {
			//评论
//			LyricsComment cm =  new LyricsComment();
//			cm.setComment("junit test by bruce.2015-12-30");
//			cm.setCreate_time(TimeUtils.getNowTime());
//			cm.setLyricsid("4028800246289d330146289eeecb0000");
//			cm.setUserid("000000005194bfe6015194c0c2fc000c");
//			commentManager.addLyricsComment(cm);
			
//			Getinfo m = new Getinfo();
//			m.setIsGened(1+"");
//			m.setPath("xxxx");
//			m.setType(1+"");
//			m.setCreatetime(TimeUtils.nowTime());
//			infoManager.addGetinfo(m);
//			LOGGER.info(JSON.toJSONString(m));
		}

	}

