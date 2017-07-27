package cn.northpark.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import cn.northpark.manager.EqManager;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Movies;
import cn.northpark.model.Soft;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.TimeUtils;


/**
 * @author zhangyang
 *
 * 定时爬取今日情圣文章+软件
 */
public class EQTask {
	
	@Autowired
	public EqManager EqManager;
	@Autowired
	public SoftManager softManager;
	@Autowired
	public MoviesManager moviesManager;
	


	public void runTask(){
		
		Logger logger = Logger.getLogger(EQTask.class);  
		System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
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
//			
//
//    	} catch (Exception e) {
//    		// TODO: handle exception
//    		e.printStackTrace();
//    	}

		
		//爬虫软件资源代码start---------------------------------------------------------------------
		try {
			
			System.out.println("soft task==============start="+TimeUtils.getNowTime());
			logger.debug("soft task==============start="+TimeUtils.getNowTime());
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
			
			
			//重复记录每个只保留一条
			
			String delsoft_sql = "DELETE FROM bc_soft WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(retcode) AS count FROM bc_soft GROUP BY retcode HAVING count > 1 ORDER BY count DESC ) AS tab )";
			
			EqManager.executeSql(delsoft_sql);
			
			logger.debug("soft task==============end="+TimeUtils.getNowTime());
			logger.trace("soft task==============end="+TimeUtils.getNowTime());
			System.out.println("soft task==============end="+TimeUtils.getNowTime());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//爬虫软件资源代码end---------------------------------------------------------------------
		
		
		//TODO ..爬虫电影代码
		
		try {
		
		System.out.println("movies task==============start="+TimeUtils.getNowTime());
		logger.debug("movies task==============start="+TimeUtils.getNowTime());
		Map<String,String> map = null;
			
		
		
		for (int k = 1; k <=2; k++) {
			try {
				
				List<Map<String, String>> list = HTMLParserUtil.retMovies(k);
				
				
				if(!CollectionUtils.isEmpty(list)){
					for (int i = 0; i < list.size(); i++) {
						try {
							map  = list.get(i);
							
							String title = map.get("title");
							String aurl = map.get("aurl");
							String date = map.get("date");
							String article = map.get("article");
						    String retcode = map.get("retcode");
						    String tag = map.get("tag");
						    String tagcode = map.get("tagcode");
							

							//是不存在的电影
							int flag = moviesManager.countHql(new Movies(), " where o.retcode= '"+retcode+"' ");
							
							if(flag<=0){
								

								Movies model = new Movies();
								model.setMoviename(title);
								model.setAddtime(date);
								model.setDescription(article);
								model.setPath("");
								model.setPrice(1);
								model.setRetcode(retcode);
								model.setTag(tag);
								model.setTagcode(tagcode);
								model.setViewnum(HTMLParserUtil.geneViewNum());
								model.setColor(PinyinUtil.getFanyi1(title.trim()));
								moviesManager.addMovies(model);
							}
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
						
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			
			
			try {
			    Thread.sleep(1000*30);
			    System.out.println("第"+k+"页================");
			} catch (InterruptedException e) {
			    // TODO Auo-generated catch block
			    e.printStackTrace();
			}
		}
		
		
		
		
		logger.debug("movies task==============end="+TimeUtils.getNowTime());
		System.out.println("movies task==============end="+TimeUtils.getNowTime());
	} catch (Exception e) {
		// TODO: handle exception
	}
//	
	   
		
		
		// ..爬虫电影代码end--
		
		
		try {
			
			/////////////////////推送微信定时星座运势塔罗牌天气、、、、、、、、、、、、、、、、、、、、、、、、
			
//			logger.debug("send wx astro msg task==============start="+TimeUtils.getNowTime());
//			try {
//				List<Astro> astrolist = astroManager.findByCondition(" where status = 1").getResultlist();
//				if(!CollectionUtils.isEmpty(astrolist)){
//					for (int i = 0; i < astrolist.size(); i++) {
//						Astro astro = astrolist.get(i);
//						String wx_cop_userid = astro.getWx_cop_userid();
//						String xzname = astro.getXzname();
//						
//						JSONObject json = WXTokenUtil.getXZYS(xzname, "today");
//
//						// 调取凭证
//						String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
//						
//						StringBuffer buffer = new StringBuffer();
//						buffer.append("\ue423").append(xzname).append("\ue31f").append("\n\n");
//						buffer.append("\ue21c").append(TimeUtils.nowdate()).append("\n");
//						buffer.append("\ue21d 综合运势:").append(json.get("summary")).append("\n");
//						buffer.append("\ue21e 贵人星座:").append(json.get("QFriend")).append("\n");
//						buffer.append("\ue21f 爱情运势:").append(json.get("love")).append("\n");
//						buffer.append("\ue220 幸运颜色:").append(json.get("color")).append("\n");
//						buffer.append("\ue221 工作运势:").append(json.get("work")).append("\n");
//						buffer.append("\ue222 幸运数字:").append(json.get("number")).append("\n");
//						buffer.append("\ue223 财运运势:").append(json.get("money")).append("\n");
//						buffer.append("\ue224 健康运势:").append(json.get("health")).append("\n");
//						String content = buffer.toString();  
//						
//						                      
//						//发送消息的jsontext
//						String jsostr = Message.getSendJsonText(wx_cop_userid, "@all", "@all", content);
//						String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
//						int result = WeixinQyhUtil.PostMessage(access_token, "POST", POST_URL, jsostr);
//						System.out.println("jsonstr--"+jsostr);
//						System.out.println("result--"+result);
//						logger.debug("send wx astro msg info log result==============="+result);
//						// 打印结果
//						if (0 == result) {
//							System.out.println("操作成功");
//						} else {
//							System.out.println("操作失败");
//						}
//					}
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			logger.debug("send wx astro msg task==============end="+TimeUtils.getNowTime());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

		
		System.out.println("情圣定时任务结束"+TimeUtils.getNowTime());
	}
	
	
	
}
