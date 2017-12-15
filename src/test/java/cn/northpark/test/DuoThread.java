package cn.northpark.test;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.manager.UserManager;
import cn.northpark.manager.UserprofileManager;
import cn.northpark.model.LyricsComment;
import cn.northpark.model.User;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.TimeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
 "classpath:spring-hibernate.xml" })
public class DuoThread implements Runnable {
	
	@Autowired
	public UserManager userManager;
	
	@Autowired
	public UserLyricsManager ulManager;
	
	@Autowired
	public UserprofileManager upManager;
	
	@Autowired
	public LyricsZanManager lzManager;
	
	@Autowired
	public LyricsCommentManager lcManager;
	
	public int lyricsid;
	public String titlecode;
	
	//设置线程的编号
	public DuoThread(int lyricsid,String titlecode){
		this.lyricsid = lyricsid;
		this.titlecode = titlecode;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub 多线程插入数据
		try {
			
			List<Map<String, String>> retCaiMaiZT_PL = HTMLParserUtil.retCaiMaiZT_PL(titlecode);
			
			for (int i = 0; i < retCaiMaiZT_PL.size(); i++) {
				String username = retCaiMaiZT_PL.get(i).get("username");
				String tailslug = retCaiMaiZT_PL.get(i).get("tailslug");
				String comment = retCaiMaiZT_PL.get(i).get("comment");
				String shijian = retCaiMaiZT_PL.get(i).get("shijian");
				
				if(StringUtils.isNotEmpty(username)){
					System.out.println("username----------"+username);
					int num = userManager.countHql(" where username = '"+username+"' ");
					if(num<=0){//不存在插入用户
						User user = new User();
						user.setUsername(username);
						user.setTail_slug(tailslug+TimeUtils.getRandomDay());
						user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
						user.setPassword("MTIzNDU2MDAwMDAw");
						userManager.addUser(user);
						
						LyricsComment lc = new LyricsComment();
						lc.setComment(comment);
						lc.setLyricsid(lyricsid);
						lc.setUserid(user.getId());
						lc.setCreate_time(shijian);
						lcManager.addLyricsComment(lc);
						
					}else{
						User user = userManager.findByCondition(" where username = '"+username+"' ").getResultlist().get(0);
						Integer userid = user.getId();
						
						LyricsComment lc = new LyricsComment();
						lc.setComment(comment);
						lc.setLyricsid(lyricsid);
						lc.setUserid(userid);
						lc.setCreate_time(shijian);
						lcManager.addLyricsComment(lc);
						
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}

}
