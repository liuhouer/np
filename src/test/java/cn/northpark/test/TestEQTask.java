package cn.northpark.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.northpark.manager.EqManager;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.manager.VpsManager;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml",
        "classpath:spring-hibernate.xml"})
public class TestEQTask {

    @Autowired
    public EqManager EqManager;

    @Autowired
    public VpsManager vpsManager;
    //
    @Autowired
    public SoftManager softManager;
    //
    @Autowired
    public MoviesManager moviesManager;
//	
//	
//	@Autowired
//	public  TagsManager tagsManager;
//	
//	@Autowired
//	public  LyricsManager lyricsManager;
//	
//	@Autowired
//	public UserManager userManager;
//	
//	@Autowired
//	public UserLyricsManager ulManager;
//	
//	@Autowired
//	public UserprofileManager upManager;
//	
//	@Autowired
//	public LyricsZanManager lzManager;
//	
//	@Autowired
//	public LyricsCommentManager lcManager;
//	@Autowired
//	public  PoemManager poemManager;
//	
//
//	@Autowired
//	public  PoemEnjoyManager poemenjoyManager;


    Logger LOGGER = Logger.getLogger(TestEQTask.class);

    public void runTask() {
    	
    	//=========================================================新url的sitemap===========================================================================================

    	 //添加新url的sitemap
//		StringBuilder sb = new StringBuilder();
//		List<Map<String, Object>> list = softManager.querySqlMap(" select retcode from bc_soft where 1=1 order by id desc ");
//		for(Map<String, Object> map :list){
//			String retcode = (String) map.get("retcode");
//			sb.append("<url>");
//			sb.append("<loc>https://northpark.cn/soft/");
//			sb.append(retcode+".html</loc>");
//			sb.append("</url>");
//		}
//		
//		try {
//			org.apache.commons.io.FileUtils.writeStringToFile(new File("d:\\newsoft.xml"), sb.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//电影的网站地图
//		StringBuilder sb = new StringBuilder();
//		List<Map<String, Object>> list = softManager.querySqlMap(" select id from bc_movies where 1=1 order by id desc ");
//		for(Map<String, Object> map :list){
//			Object retcode = (Object) map.get("id");
//			sb.append("<url>");
//			sb.append("<loc>https://northpark.cn/movies/post-");
//			sb.append(retcode+".html</loc>");
//			sb.append("</url>");
//		}
//		
//		try {
//			org.apache.commons.io.FileUtils.writeStringToFile(new File("d:\\movies-sitemap.xml"), sb.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    	//=========================================================处理软件的下载样式===========================================================================================

    	//=========================================================处理软件的下载样式===========================================================================================
//    	 List<Soft> resultlist = softManager.findByCondition(" where retcode like '%wskso%' ").getResultlist();
//    	 for(Soft s:resultlist) {
//    		 String content = s.getContent();
//    		 Document parse = Jsoup.parse(content);
//    		 Elements select = parse.select("a");
//    		 for(Element e:select) {
//    			 if(e.attr("href").contains("http://www.wskso.com/wp-content/themes/begin/inc/go.php")) {
//    				 e.addClass("btn");
//    			 }
//    		 }
//    		 s.setContent(parse.html());
//    		 softManager.updateSoft(s);
////    		 System.out.println(parse);
//    	 }
    	
    	//=========================================================处理软件的下载样式===========================================================================================


    	//=========================================================情圣===========================================================================================


//			LOGGER.info("情圣定时任务开始"+TimeUtils.getNowTime());
//			try {
//
//	    		List<Map<String, String>> lift = HTMLParserUtil.retEQArticle(1);
//	    		for (int i = 0; i < lift.size(); i++) {
//	    			
//	    			String title = lift.get(i).get("title");
//	    			System.out.println(title);
//					String brief = lift.get(i).get("brief");
//					String date = lift.get(i).get("date");
//					String article = lift.get(i).get("article");
//					String retcode = lift.get(i).get("retcode");
//					//是不存在的文章
//					int flag = EqManager.countHql(" where o.retcode= '"+retcode+"' and o.date = '"+date+"' ");
//					
//					if(flag<=0){
//						//生成并且设置图片
//						List<String> meizi = HTMLParserUtil.gegeMEIZITU();
//						
//						int index = HTMLParserUtil.getRandomOne(meizi);
//						String img = meizi.get(index);
//						
//			    		Eq eq = new Eq();
//			    		eq.setArticle(article);
//			    		eq.setBrief(brief);
//			    		eq.setDate(date);
//			    		eq.setTitle(title);
//			    		eq.setImg(img);
//			    		eq.setRetcode(retcode);
//			    		EqManager.addEq(eq);
//					}
//					
//				}
//	    		
//	    		
//				
//				//去重
////				String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM bc_eq GROUP BY date HAVING ( COUNT(retcode) > 1 )) AS p)" ;
////				
////				EqManager.executeSql(delsql);
////				
////				
//				
//				
//				
//
//	    	} catch (Exception e) {
//	    		// TODO: handle exception
//	    		LOGGER.error("TestEQTask=======>"+e);
//	    	}
//
//			LOGGER.info("情圣定时任务结束"+TimeUtils.getNowTime());

    	//=========================================================情圣===========================================================================================


    	//=========================================================主机===========================================================================================

//			LOGGER.info("VPS任务开始"+TimeUtils.getNowTime());
//			try {
//
//				
//				for (int k = 5; k <= 10; k++) {
//					List<Map<String, String>> lift = HTMLParserUtil.retCoupon(k, BC_Constant.Coupon_VPS_7);
//		    		for (int i = 0; i < lift.size(); i++) {
//		    			
//		    			String title = lift.get(i).get("title");
//						String brief = lift.get(i).get("brief");
//						String date = lift.get(i).get("date");
//						String article = lift.get(i).get("article");
//						String retcode = lift.get(i).get("retcode");
//						String returl = lift.get(i).get("aurl");
//						String tags = lift.get(i).get("tags");
//						//是不存在的文章
//						int flag = vpsManager.countHql(" where o.retcode= '"+retcode+"' ");
//						
//						if(flag<=0){
//				    		Vps model = new Vps();
//				    		model.setArticle(article);
//				    		model.setBrief(brief);
//				    		model.setDate(date);
//				    		model.setTitle(title);
//				    		model.setReturl(returl);
//				    		model.setTags(tags);
//				    		model.setColor(PinyinUtil.getFanyi1(model.getTitle()));
//				    		model.setRetcode(retcode);
//				    		vpsManager.addVps(model);
//						}
//						
//					}
//		    		
//		    		
//		    		//休眠
//		    		try {
//					    Thread.sleep(1000*1);
//					    LOGGER.info("第"+k+"页================");
//					} catch (InterruptedException e) {
//					    // TODO Auo-generated catch block
//					    e.printStackTrace();
//					}
//		    		
//				}
//	    		
//	    		
//	    		
//				
//				//去重
//				String delsql = "DELETE FROM bc_vps WHERE id IN (SELECT * FROM (SELECT id FROM bc_vps GROUP BY date HAVING ( COUNT(retcode) > 1 )) AS p)" ;
//				
//				vpsManager.executeSql(delsql);
//				
//				
//
//	    	} catch (Exception e) {
//	    		// TODO: handle exception
//	    		LOGGER.error("TestEQTask=======>"+e);
//	    	}
//
//			LOGGER.info("VPS任务结束"+TimeUtils.getNowTime());
    	//=========================================================主机===========================================================================================

    	//=========================================================软件===========================================================================================
//        try {
//
//            LOGGER.info("soft task==============start=" + TimeUtils.getNowTime());
//            Map<String, String> map = null;
//
//
//            for (int k = 3; k <= 5; k++) {
//
//                try {
//
//                    List<Map<String, String>> list = HTMLParserUtil.retSoftNew(k);
////                    List<Map<String, String>> list = HTMLParserUtil.retSoft_WSKSO(k);
//
//                    if (!CollectionUtils.isEmpty(list)) {
//                        for (int i = 0; i < list.size(); i++) {
//                            map = list.get(i);
//
//                            String title = map.get("title");
//                            String aurl = map.get("aurl");
//                            String brief = map.get("brief");
//                            String date = map.get("date");
//                            String article = map.get("article");
//                            String tag = map.get("tag");
//                            String code = map.get("code");
//                            String os = map.get("os");
//                            String month = map.get("month");
//                            String year = map.get("year");
//                            String tagcode = map.get("tagcode");
//
//
//                            //是不存在的文章
//								int flag = softManager.countHql(  " where o.retcode= '"+code+"' ");
//								
//								if(flag<=0){
//
//                            Soft model = new Soft();
//                            model.setBrief(brief);
//                            model.setContent(article);
//                            model.setOs(os);
//                            model.setPostdate(date);
//                            model.setRetcode(code);
//                            model.setReturl(aurl);
//                            model.setTags(tag);
//                            model.setTitle(title);
//                            model.setMonth(month);
//                            model.setYear(year);
//                            model.setTagscode(tagcode);
//                            softManager.addSoft(model);
//								}
//                        }
//                    }
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    LOGGER.error(e);
//                    continue;
//                }
//
//
//                try {
//                    Thread.sleep(1000);
//                    LOGGER.info("第" + k + "页================");
//                } catch (InterruptedException e) {
//                    // TODO Auo-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            LOGGER.info(e);
//        }
//
//        LOGGER.info("soft task==============end=" + TimeUtils.getNowTime());
//
//        LOGGER.info("软件定时任务结束" + TimeUtils.getNowTime());

      	//=========================================================软件===========================================================================================
       
        
      	//=========================================================电影===========================================================================================
        //TODO ..爬虫电影代码

//				try {
//				
//				LOGGER.info("爬虫电影代码 开始==============start="+TimeUtils.getNowTime());
//				Map<String,String> map = null;
//					
//				
//				
//				for (int k = 1; k <=10; k++) {
//					try {
//						
//						List<Map<String, String>> list = HTMLParserUtil.retMovies(k,"http://m.vip588660.com/category/movie/page/");
//						
//						
//						if(!CollectionUtils.isEmpty(list)){
//							for (int i = 0; i < list.size(); i++) {
//								try {
//									map  = list.get(i);
//									
//									String title = map.get("title");
////									String aurl = map.get("aurl");
//									String date = map.get("date");
//									String article = map.get("article");
//								    String retcode = map.get("retcode");
//								    String tag = map.get("tag");
//								    String tagcode = map.get("tagcode");
//									
//
//									//是不存在的电影
//									int flag = moviesManager.countHql( " where o.retcode= '"+retcode+"' ");
//									
//									if(flag<=0){
//										
//
//										Movies model = new Movies();
//										model.setMoviename(title);
//										model.setAddtime(date);
//										model.setDescription(article);
//										model.setPath("");
//										model.setPrice(1);
//										model.setRetcode(retcode);
//										model.setTag(tag);
//										model.setTagcode(tagcode);
//										model.setViewnum(HTMLParserUtil.geneViewNum());
//										model.setColor(PinyinUtil.getFanyi1(title.trim()));
//										moviesManager.addMovies(model);
//									}
//								} catch (Exception e) {
//									// TODO: handle exception
//									continue;
//								}
//								
//							}
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//						continue;
//					}
//					
//					
//					try {
//					    Thread.sleep(1000*5);
//					    LOGGER.info("第"+k+"页================");
//					} catch (InterruptedException e) {
//					    // TODO Auo-generated catch block
//					    e.printStackTrace();
//					}
//				}
//				
//				
//				
//				//重复记录每个只保留一条
//				
//				String delmovie_sql = "DELETE FROM bc_movies "
//						+ "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(retcode) AS count "
//						+ "FROM bc_movies GROUP BY retcode HAVING count > 1 ORDER BY count DESC ) AS tab )";
//				
//				EqManager.executeSql(delmovie_sql);
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//				LOGGER.info("爬虫电影代码==============结束="+TimeUtils.getNowTime());


        //TODO ..爬虫电影代码
      //=========================================================电影===========================================================================================

      //=========================================================诗词===========================================================================================
        //TODO ..爬虫诗词代码

//				try {
//				
//				LOGGER.info("poem task==============start="+TimeUtils.getNowTime());
//				Map<String,String> map = null;
//					
//				
//				
//				for (int k = 1; k <=191; k++) {
//					try {
//						
//						List<Map<String, String>> list = HTMLParserUtil.retPoem(k);
//						
//						
//						if(!CollectionUtils.isEmpty(list)){
//							for (int i = 0; i < list.size(); i++) {
//								try {
//									map  = list.get(i);
//									
//								    
//								   String title       =  map.get("title"      );
//			                       String author      =  map.get("author"     );
//			                       String content     =  map.get("content"    );
//			                       String content1    =  map.get("content1"    );
//			                       String types       =  map.get("types"      );
//			                       String enjoys      =  map.get("enjoys"     );
//			                       String pic_poem    =  map.get("pic_poem"   );
//			                       String retcode     =  map.get("retcode"   );
//			                       String detail_url  =  map.get("detail_url"    );
//
//									//是不存在的诗词
//									int flag = poemManager.countHql(new Poem(), " where o.retcode= '"+retcode+"' ");
//									
//									if(flag<=0){
//										Poem p = new Poem();
//										p.setAuthor(author);
//										p.setContent(content);
//										p.setCreatetime(TimeUtils.nowTime());
//										p.setEnjoys(enjoys);
//										p.setPic_poem(pic_poem);
//										p.setRetcode(retcode);
//										p.setTitle(title);
//										p.setYears("南北朝");
//										p.setTypes(types);
//										p.setContent1(content1);
//										p.setReturl(detail_url);
//										
//										poemManager.addPoem(p);
//										
//										PoemEnjoy pe = new PoemEnjoy();
//										pe.setPoem_id(p.getId());
//										pe.setTitle(title);
//										pe.setEnjoying(enjoys);
//										poemenjoyManager.addPoemEnjoy(pe);
//
//									}
//								} catch (Exception e) {
//									// TODO: handle exception
//									continue;
//								}
//								
//							}
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//						continue;
//					}
//					
//					
//					try {
//					    Thread.sleep(1000*5);
//					    LOGGER.info("第"+k+"页================");
//					} catch (InterruptedException e) {
//					    // TODO Auo-generated catch block
//					    e.printStackTrace();
//					}
//				}
//				
//				
//				
//				
//				LOGGER.info("poem task==============end="+TimeUtils.getNowTime());
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			

        //批量处理诗词标签
//			try {
//				int count = poemManager.countSql("select * from bc_poem ");
//				
//				LOGGER.info("count______>"+count);
//				   
//				   int pagecount = count / 1000+1;
//				   
//				   
//				   
//				   
//				   //按照页码更新数据
//				   for (int i = 0; i < pagecount; i++) {
//					   List<Poem> lst100 = poemManager.querySql(" select * from bc_poem limit "+i*1000+",100");
//					   //按照分页更新数据
//					   for (Poem p:lst100) {
//						  String types = p.getTypes();
//						  String types_code   = PinyinUtil.paraseStringToPinyin(types).toLowerCase();
//						  LOGGER.info("types_code------》"+types_code);
//						  p.setTypes_code(types_code);
//						  poemManager.updatePoem(p);
//					  }
//				   }
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			   


        //TODO ..爬虫诗词代码
        
      //=========================================================诗词===========================================================================================
//			

      //=========================================================微信星座===========================================================================================

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
//						LOGGER.info("jsonstr--"+jsostr);
//						LOGGER.info("result--"+result);
//						// 打印结果
//						if (0 == result) {
//							LOGGER.info("操作成功");
//						} else {
//							LOGGER.info("操作失败");
//						}
//					}
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}

      //=========================================================微信星座===========================================================================================

        
      //=========================================================采麦图册===========================================================================================



        //插入采麦

//			for (int k = 1; k < 398; k++) {
//				
//				
//				try {
//					List<Map<String, String>> lift = HTMLParserUtil.retCaiMai(k);
//					
//					for (int i = 0; i < lift.size(); i++) {
////		    			
//		    			String title = lift.get(i).get("title");
//						String titlecode = lift.get(i).get("titlecode");
//						String albumImg = lift.get(i).get("albumImg");
//						String zan = lift.get(i).get("zan");
//						String pl = lift.get(i).get("pl");
//						
//				    		Lyrics model = new Lyrics();
//				    		model.setTitle(title);
//				    		model.setAlbumImg(albumImg);
//				    		model.setPl(Integer.parseInt(pl.replaceAll(" ", "")));
//				    		model.setZan(Integer.parseInt(zan.replaceAll(" ", "")));
//				    		model.setTitlecode(titlecode);
//				    		lyricsManager.addLyrics(model);
//						
//					}
//		    		
//		    		
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				 //休眠10秒
//                try {
//    			    Thread.sleep(500);
//    			    LOGGER.info("第"+k+"页================");
//    			} catch (InterruptedException e) {
//    			    // TODO Auo-generated catch block
//    			    e.printStackTrace();
//    			}
//			}


        //插入最爱主题的用户

//			String sql = "select  id,titlecode from bc_lyrics where updatedate is null order by id desc limit 4593,5000 ";
//			
//			List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//			
//			for (Map<String, Object> m:list) {
//				Integer lyricsid = (Integer) m.get("id");
//				String titlecode = (String) m.get("titlecode");
//				try {
//					Map<String, String> retCaiMaiZT = HTMLParserUtil.retCaiMaiZT(titlecode);
//					
//					String username = retCaiMaiZT.get("username" );
//					String tailslug = retCaiMaiZT.get("tailslug" );
//					String courseware = retCaiMaiZT.get("courseware");
//					String date =retCaiMaiZT.get("date");
//					String meta =retCaiMaiZT.get("meta");
//					String headpath =retCaiMaiZT.get("headpath");
//					
//					if(StringUtils.isNotEmpty(username)){
//						
//						int num = userManager.countHql(" where username = '"+username+"' ");
//						int userid = 0;
//						if(num<=0){
//							User user = new User();
//							int num_tail = userManager.countHql(" where tail_slug = '"+tailslug+"' ");
//							if(num_tail>0){
//								
//								user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//							}else{
//								user.setTail_slug(tailslug);
//							}
//							user.setUsername(username);
//							user.setDate_joined(date);
//							
//							
//							user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//							user.setPassword("MTIzNDU2MDAwMDAw");
//							user.setHeadpath(headpath);
//							if(StringUtils.isNotEmpty(username)){
//								user.setHeadspan(username.substring(0,1).toUpperCase());
//								user.setHeadspanclass("text-"+username.substring(0,1).toLowerCase());
//							}
//							userManager.addUser(user);
//							
//							userid = user.getId();
//						}else{
////							List<User> ul = userManager.findByCondition("  where username = '"+username+"' ").getResultlist();
////							if(!CollectionUtils.isEmpty(ul)){
////								User user = ul.get(0);
////								userid = user.getId();
////							}
//							
//							continue;
//							
//						}
//						
//						if(userid!=0){
//							//更新或者添加profile
//							Userprofile up = upManager.getModelByUserid(String.valueOf(userid));
//							
//							up.setMeta(meta);
//							up.setCourseware(courseware);
//							up.setUser_id(userid);
//							if(up.getId()!=null){
//								upManager.updateUserprofile(up);
//								
//							}else{
//								upManager.addUserprofile(up);
//							}
//							
//							
//							//添加user lyrics关联
//							UserLyrics ul = new UserLyrics();
//							ul.setLyricsid(lyricsid);
//							ul.setUserid(userid);
//							ulManager.addUserLyrics(ul);
//						}
//						
//						//
//					}
//					
//					
//					
//					
//					
//					
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					continue;
//				}
//				
//				
//				
//			}


        //插入最爱主题的点赞用户

//			String sql = "select  id,titlecode from bc_lyrics where updatedate is null order by id desc ";
//			
//			List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//			
//			for (Map<String, Object> m:list) {
//				Integer lyricsid = (Integer) m.get("id");
//				String titlecode = (String) m.get("titlecode");
//				try {
//					String retCaiMaiZAN = HTMLParserUtil.retCaiMaiZT_ZAN(titlecode);
//					
//					//拼接sql 查询userid列表；
//					String sql2 = "select id from bc_user where tail_slug in ("+retCaiMaiZAN+")";
//					
//					
//					List<Map<String, Object>> useridlist = vpsManager.querySqlMap(sql2);
//					
//					for (int i = 0; i < useridlist.size(); i++) {
//						LyricsZan lz = new LyricsZan();
//						lz.setLyricsid(lyricsid);
//						lz.setUserid((Integer)useridlist.get(i).get("id"));
//						lzManager.addLyricsZan(lz);
//					}
//					
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}	


        //插入醉爱主题评论信息

//			List<Map<String, String>> retCaiMaiZT_PL = HTMLParserUtil.retCaiMaiZT_PL("shui-jue");
//			
//			for (int i = 0; i < retCaiMaiZT_PL.size(); i++) {
//				String username = retCaiMaiZT_PL.get(i).get("username");
//				String tailslug = retCaiMaiZT_PL.get(i).get("tailslug");
//				String comment = retCaiMaiZT_PL.get(i).get("comment");
//				String shijian = retCaiMaiZT_PL.get(i).get("shijian");
//				
//				if(StringUtils.isNotEmpty(username)){
//					
//					int num = userManager.countHql(" where username = '"+username+"' ");
//					if(num<=0){//不存在插入用户
//						User user = new User();
//						user.setUsername(username);
//						user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//						user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//						user.setPassword("MTIzNDU2MDAwMDAw");
//						userManager.addUser(user);
//						
//						LyricsComment lc = new LyricsComment();
//						lc.setComment(comment);
//						lc.setLyricsid(519527);
//						lc.setUserid(user.getId());
//						lc.setCreate_time(shijian);
//						lcManager.addLyricsComment(lc);
//						
//					}else{
//						User user = userManager.findByCondition(" where username = '"+username+"' ").getResultlist().get(0);
//						Integer userid = user.getId();
//						
//						LyricsComment lc = new LyricsComment();
//						lc.setComment(comment);
//						lc.setLyricsid(519527);
//						lc.setUserid(userid);
//						lc.setCreate_time(shijian);
//						lcManager.addLyricsComment(lc);
//						
//					}
//				}
//			}

        //插入醉爱主题评论信息
//			try {
//				
//				String sql = "select  id,titlecode from bc_lyrics where updatedate is null order by id desc limit 4000,6000";
//				
//				List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//				
//				String start = TimeUtils.nowTime();
//				System.out.println("开始时间："+start);
//				
//				
//				//把所有需要执行的放进线程池中
//				for (Map<String, Object> m:list) {
//					Integer lyricsid = (Integer) m.get("id");
//					String titlecode = (String) m.get("titlecode");
//					
//					try {
//						
//						
//						System.out.println("titlecode------------->"+titlecode);
//						
//						List<Map<String, String>> retCaiMaiZT_PL = HTMLParserUtil.retCaiMaiZT_PL(titlecode);
//						
//						for (int i = 0; i < retCaiMaiZT_PL.size(); i++) {
//							String username = retCaiMaiZT_PL.get(i).get("username");
//							String tailslug = retCaiMaiZT_PL.get(i).get("tailslug");
//							String comment = retCaiMaiZT_PL.get(i).get("comment");
//							String shijian = retCaiMaiZT_PL.get(i).get("shijian");
//							
//							if(StringUtils.isNotEmpty(username)){
//								System.out.println("username----------"+username);
//								int num = userManager.countHql(" where username = '"+username+"' ");
//								if(num<=0){//不存在插入用户
//									User user = new User();
//									user.setUsername(username);
//									user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//									user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//									user.setPassword("MTIzNDU2MDAwMDAw");
//									userManager.addUser(user);
//									
//									LyricsComment lc = new LyricsComment();
//									lc.setComment(comment);
//									lc.setLyricsid(lyricsid);
//									lc.setUserid(user.getId());
//									lc.setCreate_time(shijian);
//									lcManager.addLyricsComment(lc);
//									
//								}else{
//									User user = userManager.findByCondition(" where username = '"+username+"' ").getResultlist().get(0);
//									Integer userid = user.getId();
//									
//									LyricsComment lc = new LyricsComment();
//									lc.setComment(comment);
//									lc.setLyricsid(lyricsid);
//									lc.setUserid(userid);
//									lc.setCreate_time(shijian);
//									lcManager.addLyricsComment(lc);
//									
//								}
//							}
//						}
//						
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						LOGGER.error(e);
//						continue;
//					}
//				}
//				
//				
//				
//				String end = TimeUtils.nowTime();
//				System.out.println("结束时间："+end);
//				
//				System.out.println(TimeUtils.getPastTime(end, start));
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//				


        //插入点赞数据


//			try {
//				
//				String sql = " select * from bc_lyrics_comment  order by id desc ";
//				
//				List<LyricsComment> list = lcManager.querySql(sql);
//				
//				String start = TimeUtils.nowTime();
//				System.out.println("开始时间："+start);
//				
//				
//				//把所有需要执行的放进线程池中
//				for (LyricsComment m:list) {
//					try {
//						String  create_time = m.getCreate_time();
//						String timeByFanyi = TimeUtils.getTimeByFanyi(create_time);
//						m.setCreate_time(timeByFanyi);
//						lcManager.updateLyricsComment(m);
//					
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						LOGGER.error(e);
//						continue;
//					}
//				}
//				
//				
//				
//				String end = TimeUtils.nowTime();
//				System.out.println("结束时间："+end);
//				
//				System.out.println(TimeUtils.getPastTime(end, start));
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


        //更新网络图片  替换主题为空的图片
//			try {
//				
//				String sql = "select * from bc_lyrics where albumImg ='' order by updatedate desc";
//				
//				List<Lyrics> list = lyricsManager.querySql(sql);
//				
//				String start = TimeUtils.nowTime();
//				System.out.println("开始时间："+start);
//				
//				
//				//把所有需要执行的放进线程池中
//				for (Lyrics m:list) {
//					try {
//						String  title = m.getTitle();
//						String  titlecode = m.getTitlecode();
//						System.out.println(title);
//						String retPicByName = HTMLParserUtil.retPicByName(title, titlecode);
//						
//						m.setAlbumImg(retPicByName);
//						
//						lyricsManager.updateLyrics(m);
//					
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						LOGGER.error(e);
//						continue;
//					}
//					
//				}
//				
//				
//				
//				String end = TimeUtils.nowTime();
//				System.out.println("结束时间："+end);
//				
//				System.out.println(TimeUtils.getPastTime(end, start));
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
    	//=========================================================采麦图册===========================================================================================



    }


    //测试多页


    @Test
    public void save() {
        runTask();
    }

}
