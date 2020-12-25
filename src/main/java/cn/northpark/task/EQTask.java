package cn.northpark.task;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.model.Movies;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章+软件
 */
@Slf4j
public class EQTask {

    @Autowired
    public SoftManager softManager;
    @Autowired
    public MoviesManager moviesManager;


    public void runTask() {


//=================================删除重复的记录=======================================
//        DELETE                                                                  /
//        FROM                                                                    /
//        	bc_vps                                                                /
//        WHERE                                                                   /
//        	id IN (                                                               /
//        		SELECT                                                            /
//        			id                                                            /
//        		FROM                                                              /
//        			(                                                             /
//        				SELECT                                                    /
//        					max(id) AS id,                                        /
//        					count(title) AS count                                 /
//        				FROM                                                      /
//        					bc_vps                                                /
//        				GROUP BY                                                  /
//        					title                                                 /
//        				HAVING                                                    /
//        					count > 1                                             /
//        				ORDER BY                                                  /
//        					count DESC                                            /
//        			) AS tab                                                      /
//        	);                                                                    /
//=================================删除重复的记录=======================================        

        //爬虫软件资源代码---2页---start---------------------------------------------------------------------
		/*try {
			
			log.info("soft task==============start="+TimeUtils.getNowTime());
			Map<String,String> map = null;
			
			
			
			for (int k = 1; k <= 2; k++) {
				List<Map<String, String>> list = HTMLParserUtil.retSoft(k);
				
				
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
						int flag = softManager.countHql(  " where o.retcode= '"+code+"' ");
						
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
				
				
			
				try {
				    Thread.sleep(1000*5);
				    log.info("第"+k+"页================");
				} catch (InterruptedException e) {
				    // TODO Auo-generated catch block
				    e.printStackTrace();
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("EQtask---->retsoft--->ex::"+e);
		}
			
		log.info("soft task==============end="+TimeUtils.getNowTime());*/


        //爬虫软件资源代码end---------------------------------------------------------------------


        //TODO ..爬虫电影代码 2页

        try {

            log.info("movies task==============start=" + TimeUtils.getNowTime());

            for (int k = 1; k <= 2; k++) {
                try {

                    //电影
                    List<Map<String, String>> dianying = HTMLParserUtil.retMovies(k, BC_Constant.RET_dianying);


                    handleMoiveList(dianying);


                    //电视剧
                    List<Map<String, String>> dianshiju = HTMLParserUtil.retMovies(k, BC_Constant.RET_meiju);


                    handleMoiveList(dianshiju);
                    
                    //电视剧
                    List<Map<String, String>> guochanju = HTMLParserUtil.retMovies(k, BC_Constant.RET_guochanju);


                    handleMoiveList(guochanju);

                    //动漫
//				List<Map<String, String>> dongman = HTMLParserUtil.retMovies(k,BC_Constant.RET_dongman);
//				
//				
//				handleMoiveList(dongman);


                } catch (Exception e) {
                    // TODO: handle exception
                    continue;
                }


                try {
                    Thread.sleep(1000 * 5);
                    System.out.println("第" + k + "页================");
                } catch (InterruptedException e) {
                    // TODO Auo-generated catch block

                    log.error("movies task InterruptedException==============" + e);
                }
            }
//=================================删除重复的记录=======================================
//          DELETE                                                                 /
//          FROM                                                                   /
//          	bc_movies                                                          /
//          WHERE                                                                  /
//          	id IN (                                                            /
//          		SELECT                                                         /
//          			id                                                         /
//          		FROM                                                           /
//          			(                                                          /
//          				SELECT                                                 /
//          					max(id) AS id,                                     /
//          					count(moviename) AS count                          /
//          				FROM                                                   /
//          					bc_movies                                          /
//          				GROUP BY                                               /
//          					moviename                                          /
//          				HAVING                                                 /
//          					count > 1                                          /
//          				ORDER BY                                               /
//          					count DESC                                         /
//          			) AS tab                                                   /
//          	);                                                                 /
  //=================================删除重复的记录=======================================   
            
            //重复记录每个只保留一条
            String delmovie_sql = "DELETE FROM bc_movies "
					+ "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(moviename) AS count "
					+ "FROM bc_movies GROUP BY moviename HAVING count > 1 ORDER BY count DESC ) AS tab )";
			
			softManager.executeSql(delmovie_sql);

            log.info("movies task==============end=" + TimeUtils.getNowTime());
        } catch (Exception e) {
            // TODO: handle exception
            log.error("movies task  Exception==============" + e);
        }
//	


        // ..爬虫电影代码end--


        try {

            /////////////////////推送微信定时星座运势塔罗牌天气、、、、、、、、、、、、、、、、、、、、、、、、

//			log.info("send wx astro msg task==============start="+TimeUtils.getNowTime());
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
//						log.info("send wx astro msg info log result==============="+result);
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
//			log.info("send wx astro msg task==============end="+TimeUtils.getNowTime());
        } catch (Exception e) {
            // TODO: handle exception
        }


    }


    //tools===========================================================================================================================

    /**
     * 电影：处理爬取的页内容
     *
     * @param list
     */
    private void handleMoiveList(List<Map<String, String>> list) {
        Map<String, String> map;
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    map = list.get(i);

                    String title = map.get("title");
                    //							String aurl = map.get("aurl");
                    String date = map.get("date");
                    String article = map.get("article");
                    String retcode = map.get("retcode");
                    String tag = map.get("tag");
                    String tagcode = map.get("tagcode");
                    String path = map.get("path");


                    //是不存在的电影
                    int flag = moviesManager.countHql(" where o.retcode= '" + retcode + "' ");

                    if (flag <= 0) {


                        Movies model = new Movies();
                        model.setMoviename(title);
                        model.setAddtime(date);
                        model.setDescription(article);
                        model.setPath(path);
                        model.setPrice(1);
                        model.setRetcode(retcode);
                        model.setTag(tag);
                        model.setTagcode(tagcode);
                        model.setViewnum(HTMLParserUtil.geneViewNum());
                        model.setColor(PinyinUtil.getFirstChar(title.trim()));
                        moviesManager.addMovies(model);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    continue;
                }

            }
            
            
            
         
            
            
            
            
        }
    }


}
