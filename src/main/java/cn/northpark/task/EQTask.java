package cn.northpark.task;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import cn.northpark.constant.BC_Constant;
import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;
import cn.northpark.manager.VpsManager;
import cn.northpark.model.Movies;
import cn.northpark.model.Vps;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.TimeUtils;


/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章+软件
 */
public class EQTask {

    @Autowired
    public SoftManager softManager;
    @Autowired
    public MoviesManager moviesManager;
    @Autowired
    public VpsManager vpsManager;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EQTask.class);


    public void runTask() {

        //爬虫VPS资源代码start---------------------------------------------------------------------
        LOGGER.info("VPS任务开始" + TimeUtils.getNowTime());
        try {


            List<Map<String, String>> lift = HTMLParserUtil.retCoupon(1, BC_Constant.Coupon_VPS_7);
            for (int i = 0; i < lift.size(); i++) {

                String title = lift.get(i).get("title");
                String brief = lift.get(i).get("brief");
                String date = lift.get(i).get("date");
                String article = lift.get(i).get("article");
                String retcode = lift.get(i).get("retcode");
                String returl = lift.get(i).get("aurl");
                String tags = lift.get(i).get("tags");
                //是不存在的文章
                int flag = vpsManager.countHql(" where o.retcode= '" + retcode + "' ");

                if (flag <= 0) {
                    Vps model = new Vps();
                    model.setArticle(article);
                    model.setBrief(brief);
                    model.setDate(date);
                    model.setTitle(title);
                    model.setReturl(returl);
                    model.setTags(tags);
                    model.setColor(PinyinUtil.getFanyi1(model.getTitle()));
                    model.setRetcode(retcode);
                    vpsManager.addVps(model);
                }

            }


        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error("TestEQTask=======>" + e);
        }

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
        LOGGER.info("VPS任务结束" + TimeUtils.getNowTime());

        //爬虫软件资源代码---2页---start---------------------------------------------------------------------
		/*try {
			
			LOGGER.info("soft task==============start="+TimeUtils.getNowTime());
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
				    LOGGER.info("第"+k+"页================");
				} catch (InterruptedException e) {
				    // TODO Auo-generated catch block
				    e.printStackTrace();
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("EQtask---->retsoft--->ex::"+e);
		}
			
		LOGGER.info("soft task==============end="+TimeUtils.getNowTime());*/


        //爬虫软件资源代码end---------------------------------------------------------------------


        //TODO ..爬虫电影代码 2页

        try {

            LOGGER.info("movies task==============start=" + TimeUtils.getNowTime());

            for (int k = 1; k <= 2; k++) {
                try {

                    //电影
                    List<Map<String, String>> dianying = HTMLParserUtil.retMovies(k, BC_Constant.RET_dianying);


                    handleMoiveList(dianying);


                    //电视剧
                    List<Map<String, String>> dianshiju = HTMLParserUtil.retMovies(k, BC_Constant.RET_dianshiju);


                    handleMoiveList(dianshiju);

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

                    LOGGER.error("movies task InterruptedException==============" + e);
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
			
			vpsManager.executeSql(delmovie_sql); 

            LOGGER.info("movies task==============end=" + TimeUtils.getNowTime());
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.error("movies task  Exception==============" + e);
        }
//	


        // ..爬虫电影代码end--


        try {

            /////////////////////推送微信定时星座运势塔罗牌天气、、、、、、、、、、、、、、、、、、、、、、、、

//			LOGGER.info("send wx astro msg task==============start="+TimeUtils.getNowTime());
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
//						LOGGER.info("send wx astro msg info log result==============="+result);
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
//			LOGGER.info("send wx astro msg task==============end="+TimeUtils.getNowTime());
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
                        model.setColor(PinyinUtil.getFanyi1(title.trim()));
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
