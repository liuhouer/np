//package cn.northpark.test;
//
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
//import cn.northpark.manager.AstroManager;
//import cn.northpark.manager.EqManager;
//import cn.northpark.manager.MoviesManager;
//import cn.northpark.manager.PoemEnjoyManager;
//import cn.northpark.manager.PoemManager;
//import cn.northpark.manager.SoftManager;
//import cn.northpark.manager.TagsManager;
//import cn.northpark.model.Movies;
//import cn.northpark.model.Soft;
//import cn.northpark.task.EQTask;
//import cn.northpark.utils.HTMLParserUtil;
//import cn.northpark.utils.PinyinUtil;
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
//	@Autowired
//	public SoftManager softManager;
//	
//	@Autowired
//	public MoviesManager moviesManager;
//	
//	@Autowired
//	public AstroManager astroManager;
//	
//	@Autowired
//	public  TagsManager tagsManager;
//
//	@Autowired
//	public  PoemManager poemManager;
//	
//
//	@Autowired
//	public  PoemEnjoyManager poemenjoyManager;
//		
//
//
//		public void runTask(){
//			
//			Logger logger = Logger.getLogger(EQTask.class);  
////			System.out.println("情圣定时任务开始"+TimeUtils.getNowTime());
////			try {
////
////	    		Map<String, String> map = HTMLParserUtil.retTodayEq();
////	    		
////	    		String title = map.get("title");
////				String brief = map.get("brief");
////				String date = map.get("date");
////				String article = map.get("article");
////				String retcode = map.get("retcode");
////				//是不存在的文章
////				int flag = EqManager.countHql(new Eq(), " where o.retcode= '"+retcode+"' ");
////				
////				if(flag<=0){
////					//生成并且设置图片
////					List<String> meizi = HTMLParserUtil.gegeMEIZITU();
////					
////					int index = HTMLParserUtil.getRandomOne(meizi);
////					String img = meizi.get(index);
////					
////		    		Eq eq = new Eq();
////		    		eq.setArticle(article);
////		    		eq.setBrief(brief);
////		    		eq.setDate(date);
////		    		eq.setTitle(title);
////		    		eq.setImg(img);
////		    		eq.setRetcode(retcode);
////		    		EqManager.addEq(eq);
////				}
////				
////				//去重
////				String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM bc_eq GROUP BY date HAVING ( COUNT(retcode) > 1 )) AS p)" ;
////				
////				EqManager.executeSql(delsql);
////				
////				
////				
////				
////				
////
////	    	} catch (Exception e) {
////	    		// TODO: handle exception
////	    		e.printStackTrace();
////	    	}
//
//			
//			try {
//				
//				System.out.println("soft task==============start="+TimeUtils.getNowTime());
//				logger.info("soft task==============start="+TimeUtils.getNowTime());
//				Map<String,String> map = null;
//				
//				
//				
//				for (int k = 1; k < 10; k++) {
//					List<Map<String, String>> list = HTMLParserUtil.retSoft(k);
//					
//					
//					if(!CollectionUtils.isEmpty(list)){
//						for (int i = 0; i < list.size(); i++) {
//							map  = list.get(i);
//							
//							String title = map.get("title");
//							String aurl = map.get("aurl");
//							String brief = map.get("brief");
//							String date = map.get("date");
//							String article = map.get("article");
//							String tag = map.get("tag");
//							String code = map.get("code");
//							String os = map.get("os");
//							String month  = map.get("month");
//							String year  = map.get("year");
//							String tagcode = map.get("tagcode");
//							
//							
//
//							//是不存在的文章
//							int flag = softManager.countHql(new Soft(), " where o.retcode= '"+code+"' ");
//							
//							if(flag<=0){
//								
//					    		Soft model = new Soft();
//					    		model.setBrief(brief);
//					    		model.setContent(article);
//					    		model.setOs(os);
//					    		model.setPostdate(date);
//					    		model.setRetcode(code);
//					    		model.setReturl(aurl);
//					    		model.setTags(tag);
//					    		model.setTitle(title);
//					    		model.setMonth(month);
//					    		model.setYear(year);
//					    		model.setTagscode(tagcode);
//					    		softManager.addSoft(model);
//							}
//						}
//					}
//					
//					logger.info("soft task==============end="+TimeUtils.getNowTime());
//					logger.trace("soft task==============end="+TimeUtils.getNowTime());
//					System.out.println("soft task==============end="+TimeUtils.getNowTime());
//				
//					try {
//					    Thread.sleep(1000*30);
//					    System.out.println("第"+k+"页================");
//					} catch (InterruptedException e) {
//					    // TODO Auo-generated catch block
//					    e.printStackTrace();
//					}
//					
//				}
//				
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				logger.debug(e);
//			}
//				
//
//			
//			System.out.println("软件定时任务结束"+TimeUtils.getNowTime());
//			
//			
//			//TODO ..爬虫电影代码
//			
////				try {
////				
////				System.out.println("movies task==============start="+TimeUtils.getNowTime());
////				logger.info("movies task==============start="+TimeUtils.getNowTime());
////				Map<String,String> map = null;
////					
////				
////				
////				for (int k = 1; k <=10; k++) {
////					try {
////						
////						List<Map<String, String>> list = HTMLParserUtil.retMovies(k);
////						
////						
////						if(!CollectionUtils.isEmpty(list)){
////							for (int i = 0; i < list.size(); i++) {
////								try {
////									map  = list.get(i);
////									
////									String title = map.get("title");
////									String aurl = map.get("aurl");
////									String date = map.get("date");
////									String article = map.get("article");
////								    String retcode = map.get("retcode");
////								    String tag = map.get("tag");
////								    String tagcode = map.get("tagcode");
////									
////
////									//是不存在的电影
////									int flag = moviesManager.countHql(new Movies(), " where o.retcode= '"+retcode+"' ");
////									
////									if(flag<=0){
////										
////
////										Movies model = new Movies();
////										model.setMoviename(title);
////										model.setAddtime(date);
////										model.setDescription(article);
////										model.setPath("");
////										model.setPrice(1);
////										model.setRetcode(retcode);
////										model.setTag(tag);
////										model.setTagcode(tagcode);
////										model.setViewnum(HTMLParserUtil.geneViewNum());
////										model.setColor(PinyinUtil.getFanyi1(title.trim()));
////										moviesManager.addMovies(model);
////									}
////								} catch (Exception e) {
////									// TODO: handle exception
////									continue;
////								}
////								
////							}
////						}
////					} catch (Exception e) {
////						// TODO: handle exception
////						continue;
////					}
////					
////					
////					try {
////					    Thread.sleep(1000*30);
////					    System.out.println("第"+k+"页================");
////					} catch (InterruptedException e) {
////					    // TODO Auo-generated catch block
////					    e.printStackTrace();
////					}
////				}
////				
////				
////				
////				//重复记录每个只保留一条
////				
////				String delmovie_sql = "DELETE FROM bc_movies "
////						+ "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(retcode) AS count "
////						+ "FROM bc_movies GROUP BY retcode HAVING count > 1 ORDER BY count DESC ) AS tab )";
////				
////				EqManager.executeSql(delmovie_sql);
////				
////				logger.info("movies task==============end="+TimeUtils.getNowTime());
////				logger.trace("movies task==============end="+TimeUtils.getNowTime());
////				System.out.println("movies task==============end="+TimeUtils.getNowTime());
////			} catch (Exception e) {
////				// TODO: handle exception
////			}
////			
//			   
//				
//				
//				//TODO ..爬虫电影代码
//				
//				
//				
//				
//				//TODO ..爬虫诗词代码
//				
////				try {
////				
////				System.out.println("poem task==============start="+TimeUtils.getNowTime());
////				logger.info("poem task==============start="+TimeUtils.getNowTime());
////				Map<String,String> map = null;
////					
////				
////				
////				for (int k = 1; k <=191; k++) {
////					try {
////						
////						List<Map<String, String>> list = HTMLParserUtil.retPoem(k);
////						
////						
////						if(!CollectionUtils.isEmpty(list)){
////							for (int i = 0; i < list.size(); i++) {
////								try {
////									map  = list.get(i);
////									
////								    
////								   String title       =  map.get("title"      );
////			                       String author      =  map.get("author"     );
////			                       String content     =  map.get("content"    );
////			                       String content1    =  map.get("content1"    );
////			                       String types       =  map.get("types"      );
////			                       String enjoys      =  map.get("enjoys"     );
////			                       String pic_poem    =  map.get("pic_poem"   );
////			                       String retcode     =  map.get("retcode"   );
////			                       String detail_url  =  map.get("detail_url"    );
////
////									//是不存在的诗词
////									int flag = poemManager.countHql(new Poem(), " where o.retcode= '"+retcode+"' ");
////									
////									if(flag<=0){
////										Poem p = new Poem();
////										p.setAuthor(author);
////										p.setContent(content);
////										p.setCreatetime(TimeUtils.nowTime());
////										p.setEnjoys(enjoys);
////										p.setPic_poem(pic_poem);
////										p.setRetcode(retcode);
////										p.setTitle(title);
////										p.setYears("南北朝");
////										p.setTypes(types);
////										p.setContent1(content1);
////										p.setReturl(detail_url);
////										
////										poemManager.addPoem(p);
////										
////										PoemEnjoy pe = new PoemEnjoy();
////										pe.setPoem_id(p.getId());
////										pe.setTitle(title);
////										pe.setEnjoying(enjoys);
////										poemenjoyManager.addPoemEnjoy(pe);
////
////									}
////								} catch (Exception e) {
////									// TODO: handle exception
////									continue;
////								}
////								
////							}
////						}
////					} catch (Exception e) {
////						// TODO: handle exception
////						continue;
////					}
////					
////					
////					try {
////					    Thread.sleep(1000*5);
////					    System.out.println("第"+k+"页================");
////					} catch (InterruptedException e) {
////					    // TODO Auo-generated catch block
////					    e.printStackTrace();
////					}
////				}
////				
////				
////				
////				
////				logger.info("poem task==============end="+TimeUtils.getNowTime());
////				logger.trace("poem task==============end="+TimeUtils.getNowTime());
////				System.out.println("poem task==============end="+TimeUtils.getNowTime());
////			} catch (Exception e) {
////				// TODO: handle exception
////			}
////			
//			   
//			   //批量处理诗词标签
////			try {
////				int count = poemManager.countSql("select * from bc_poem ");
////				
////				System.out.println("count______>"+count);
////				   
////				   int pagecount = count / 1000+1;
////				   
////				   
////				   
////				   
////				   //按照页码更新数据
////				   for (int i = 0; i < pagecount; i++) {
////					   List<Poem> lst100 = poemManager.querySql(" select * from bc_poem limit "+i*1000+",100");
////					   //按照分页更新数据
////					   for (Poem p:lst100) {
////						  String types = p.getTypes();
////						  String types_code   = PinyinUtil.paraseStringToPinyin(types).toLowerCase();
////						  System.out.println("types_code------》"+types_code);
////						  p.setTypes_code(types_code);
////						  poemManager.updatePoem(p);
////					  }
////				   }
////			} catch (Exception e) {
////				// TODO: handle exception
////				e.printStackTrace();
////			}
////			   
//			   
//			   
//				
//				//TODO ..爬虫诗词代码
////			
////			 	List<Movies> findAll = moviesManager.findAll();
////			 	
////			 	for (Movies m: findAll) {
////			 		String addtime = m.getAddtime();
////			 		if(addtime.length()>10){
////			 			addtime = addtime.substring(0,10);
////			 		}else if (addtime.length()==5){
////			 			addtime = "2016-"+addtime;
////			 		}
////			 		m.setAddtime(addtime);
////					moviesManager.updateMovies(m);
////				}
////			 	
////			 	List<Movies> resultlist = moviesManager.findByCondition(" where  addtime like '%2016-01%' and tag is not null ").getResultlist();
////				
////			 	for (Movies m: resultlist) {
////			 		String addtime = m.getAddtime();
////			 		m.setAddtime(addtime.replaceAll("2016", "2017"));
////					moviesManager.updateMovies(m);
////				}
//			 	
//			//插入标签库=======================================================
//			
////			System.out.println("tag-->"+"tagcode--->");
////			
////			HashSet<String> tagset = new HashSet<String>();
////			
////			List<Map<String,String> > movie_taglist  =  new ArrayList<Map<String,String>>();
////			
////			//获取标签
////			List<Map<String, Object>> tags111 = moviesManager.querySqlMap("select tag,tagcode   from bc_movies group by tag ");
////			if(!CollectionUtils.isEmpty(tags111)){
////				for (int i = 0; i < tags111.size(); i++) {
////					Map<String, Object> map111 = tags111.get(i);
////					Object object111 = map111.get("tag");
////					if(object111!=null){
////						String tags222 = String.valueOf(object111) ;
////						String tagscode222 = String.valueOf(map111.get("tagcode"));
////						String[] tag222 = tags222.split(",");
////						String[] tagcode222 = tagscode222.split(",");
////						
////						for (int j = 0; j < tag222.length; j++) {
////							if(tagset.add(tag222[j])){
////								Map<String,String> movietag = new HashMap<String, String>();
////								movietag.put("tag", tag222[j]);
////								movietag.put("tagcode", tagcode222[j]);
////								
////								
////								System.out.println(tag222[j]+"      "+tagcode222[j]);
////								Tags model =  new Tags();
////								model.setTag(tag222[j]);
////								model.setTagcode(tagcode222[j]);
////								tagsManager.addTags(model);
////								movie_taglist.add(movietag);
////							}
////						}
////					}
////					
////				}
////				
////			}
////			
//			
//			//插入标签库=======================================================
//			
//			
//			
////			logger.info("send wx astro msg task==============start="+TimeUtils.getNowTime());
////			try {
////				List<Astro> astrolist = astroManager.findByCondition(" where status = 1").getResultlist();
////				if(!CollectionUtils.isEmpty(astrolist)){
////					for (int i = 0; i < astrolist.size(); i++) {
////						Astro astro = astrolist.get(i);
////						String wx_cop_userid = astro.getWx_cop_userid();
////						String xzname = astro.getXzname();
////						
////						JSONObject json = WXTokenUtil.getXZYS(xzname, "today");
////
////						// 调取凭证
////						String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();
////						
////						StringBuffer buffer = new StringBuffer();
////						buffer.append("\ue423").append(xzname).append("\ue31f").append("\n\n");
////						buffer.append("\ue21c").append(TimeUtils.nowdate()).append("\n");
////						buffer.append("\ue21d 综合运势:").append(json.get("summary")).append("\n");
////						buffer.append("\ue21e 贵人星座:").append(json.get("QFriend")).append("\n");
////						buffer.append("\ue21f 爱情运势:").append(json.get("love")).append("\n");
////						buffer.append("\ue220 幸运颜色:").append(json.get("color")).append("\n");
////						buffer.append("\ue221 工作运势:").append(json.get("work")).append("\n");
////						buffer.append("\ue222 幸运数字:").append(json.get("number")).append("\n");
////						buffer.append("\ue223 财运运势:").append(json.get("money")).append("\n");
////						buffer.append("\ue224 健康运势:").append(json.get("health")).append("\n");
////						String content = buffer.toString();  
////						
////						                      
////						//发送消息的jsontext
////						String jsostr = Message.getSendJsonText(wx_cop_userid, "@all", "@all", content);
////						String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
////						int result = WeixinQyhUtil.PostMessage(access_token, "POST", POST_URL, jsostr);
////						System.out.println("jsonstr--"+jsostr);
////						System.out.println("result--"+result);
////						logger.info("send wx astro msg info log result==============="+result);
////						// 打印结果
////						if (0 == result) {
////							System.out.println("操作成功");
////						} else {
////							System.out.println("操作失败");
////						}
////					}
////				}
////			} catch (Exception e) {
////				// TODO: handle exception
////			}
////			logger.info("send wx astro msg task==============end="+TimeUtils.getNowTime());
//		}
//		
//		
//		
//	//测试多页	
////		public void soft(Integer index){
////          try {
////				
////				System.out.println("soft task==============start="+TimeUtils.getNowTime());
////				Map<String,String> map = null;
////					
////				List<Map<String, String>> list = HTMLParserUtil.retSoft(index);
////				
////				
////				if(!CollectionUtils.isEmpty(list)){
////					for (int i = 0; i < list.size(); i++) {
////						map  = list.get(i);
////						
////						String title = map.get("title");
////						String aurl = map.get("aurl");
////						String brief = map.get("brief");
////						String date = map.get("date");
////						String article = map.get("article");
////						String tag = map.get("tag");
////						String code = map.get("code");
////						String os = map.get("os");
////						String month  = map.get("month");
////						String year  = map.get("year");
////						String tagcode = map.get("tagcode");
////						
////						
////
////						//是不存在的文章
////						int flag = softManager.countHql(new Soft(), " where o.retcode= '"+code+"' ");
////						
////						if(flag<=0){
////							
////				    		Soft model = new Soft();
////				    		model.setBrief(brief);
////				    		model.setContent(article);
////				    		model.setOs(os);
////				    		model.setPostdate(date);
////				    		model.setRetcode(code);
////				    		model.setReturl(aurl);
////				    		model.setTags(tag);
////				    		model.setTitle(title);
////				    		model.setMonth(month);
////				    		model.setYear(year);
////				    		model.setTagscode(tagcode);
////				    		softManager.addSoft(model);
////						}
////					}
////				}
////				
////				System.out.println("soft task==============end="+TimeUtils.getNowTime());
////			} catch (Exception e) {
////				// TODO: handle exception
////			}
////		}
//	
//	
//	@Test
//	public void save() {
//		runTask();
//		
////		for (int i = 1; i < 65; i++) {
////			soft(i);
////		}
//	}
//	
//}
