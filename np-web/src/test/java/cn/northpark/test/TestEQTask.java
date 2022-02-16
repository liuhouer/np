//package cn.northpark.test;
//
//import cn.northpark.manager.EqManager;
//import cn.northpark.manager.MoviesManager;
//import cn.northpark.manager.SoftManager;
//import cn.northpark.model.Soft;
//import cn.northpark.utils.HTMLParserUtil;
//import cn.northpark.utils.IDUtils;
//import cn.northpark.utils.TimeUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author zhangyang
// * <p>
// * 定时爬取今日情圣文章
// */
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
//        "classpath:application-transaction.xml"})
//public class TestEQTask {
//
//    @Autowired
//    public EqManager EqManager;
//
//    //
//    @Autowired
//    public SoftManager softManager;
//    //
//    @Autowired
//    public MoviesManager moviesManager;
//    //
//    //
//    // @Autowired
//    // public TagsManager tagsManager;
//    //
//    // @Autowired
//    // public LyricsManager lyricsManager;
//    //
//    // @Autowired
//    // public UserManager userManager;
//    //
//    // @Autowired
//    // public UserLyricsManager ulManager;
//    //
//    // @Autowired
//    // public UserprofileManager upManager;
//    //
//    // @Autowired
//    // public LyricsZanManager lzManager;
//    //
//    // @Autowired
//    // public LyricsCommentManager lcManager;
//    // @Autowired
//    // public PoemManager poemManager;
//    //
//
//    public void runTask() {
//
//        // =========================================================新url的sitemap===========================================================================================
//
//        // 添加新url的sitemap
//        // StringBuilder sb = new StringBuilder();
//        // List<Map<String, Object>> list = softManager.querySqlMap(" select ret_code
//        // from bc_soft where id > 517139 order by id desc ");
//        // for(Map<String, Object> map :list){
//        // String ret_code = (String) map.get("ret_code");
//        // sb.append("<url>");
//        // sb.append("<loc>https://northpark.cn/soft/");
//        // sb.append(ret_code+".html</loc>");
//        // sb.append("</url>");
//        // }
//        //
//        // try {
//        // org.apache.commons.io.FileUtils.writeStringToFile(new
//        // File("d:\\newsoft.xml"), sb.toString());
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//
//        // 电影的描述H1处理
//        // List<Movies> list = moviesManager.querySqlEntity(" SELECT * FROM `bc_movies`
//        // WHERE `movie_desc` LIKE '%<h1>%' order by id desc ");
//        // for (Movies m:list) {
//        // String content = m.getDescription();
//        // content = content.replace("<h1>", "<p>").replace("</h1>", "</p>");
//        // System.out.println(content);
//        // m.setDescription(content);
//        // moviesManager.updateMovies(m);
//        // }
//        //
//        // 电影的网站地图
//        // StringBuilder sb = new StringBuilder();
//        // List<Map<String, Object>> list = softManager.querySqlMap(" select id from
//        // bc_movies where 1=1 order by id desc ");
//        // for(Map<String, Object> map :list){
//        // Object ret_code = (Object) map.get("id");
//        // sb.append("<url>");
//        // sb.append("<loc>https://northpark.cn/movies/post-");
//        // sb.append(ret_code+".html</loc>");
//        // sb.append("</url>");
//        // }
//        //
//        // try {
//        // org.apache.commons.io.FileUtils.writeStringToFile(new
//        // File("d:\\movies-sitemap.xml"), sb.toString());
//        // } catch (IOException e) {
//        //
//        // e.printStackTrace();
//        // }
//
//        // 情圣的网站地图
//        // StringBuilder sb = new StringBuilder();
//        // List<Map<String, Object>> list = softManager
//        // .querySqlMap(" select id from bc_eq where id>1337 order by id desc ");
//        // for (Map<String, Object> map : list) {
//        // Object ret_code = map.get("id");
//        // sb.append("<url>");
//        // sb.append("<loc>https://northpark.cn/romeo/");
//        // sb.append(ret_code + ".html</loc>");
//        // sb.append("</url>");
//        // }
//        //
//        // try {
//        // org.apache.commons.io.FileUtils.writeStringToFile(new
//        // File("d:\\eq-sitemap.xml"), sb.toString());
//        // } catch (IOException e) {
//        //
//        // e.printStackTrace();
//        // }
//
//        // 电影==把下载链接放到path字段去==============================start==================================================================
//        // try {
//        ////// int countHql = moviesManager.countHql(" where path is null ");
//        ////// int pageCount = countHql / 1000 + 1;
//        ////// for(int i = 0;i<pageCount;i++) {
//        // List<Movies> lst100 = moviesManager.querySqlEntity(" select * from bc_movies
//        // order by id desc limit 0,20");
//        // //按照分页更新数据
//        // for (Movies m:lst100) {
//        // String content = m.getDescription();
//        // Document parse = Jsoup.parse(content);
//        //
//        // //删除打赏和微信二维码信息
//        // Element praise = parse.getElementById("gave");
//        // if(praise!=null) {
//        // if(praise.html().contains("打赏")) {
//        // praise.remove();
//        // }
//        // }
//        //
//        // Element wechat = parse.getElementById("wechatCode");
//        // if(wechat!=null) wechat.remove();
//        //
//        //
//        // String path = "";
//        //
//        // StringBuilder sb = new StringBuilder();
//        //
//        // //处理h2
//        // Elements h2 = parse.select("h2");
//        //
//        //
//        // if(!CollectionUtils.isEmpty(h2)) {
//        // for (Iterator iterator = h2.iterator(); iterator.hasNext();) {
//        // Element link = (Element) iterator.next();
//        // //把iterater里面的元素连接提取到path中
//        // HTMLParserUtil.handleLink(sb, link, "h2");
//        // }
//        //
//        //
//        // }
//        //
//        // //处理a连接，就去a连接查找下载地址，删除后，设置到path
//        // Elements links = parse.select("a");
//        // if(!CollectionUtils.isEmpty(links)) {
//        // for (Iterator iterator = links.iterator(); iterator.hasNext();) {
//        // Element link = (Element) iterator.next();
//        // //把iterater里面的元素连接提取到path中
//        // HTMLParserUtil.handleLink(sb, link ,"a");
//        //
//        // }
//        // }
//        //
//        //
//        //
//        // //处理p中的连接，就去p磁力链查找下载地址，删除后，设置到path
//        // Elements ps = parse.select("p");
//        // if(!CollectionUtils.isEmpty(ps)) {
//        // for (Iterator iterator = ps.iterator(); iterator.hasNext();) {
//        // Element link = (Element) iterator.next();
//        // //把iterater里面的元素连接提取到path中
//        // HTMLParserUtil.handleLink(sb, link , "p");
//        // }
//        // }
//        //
//        // path = sb.toString();
//        //
//        // //设值下载地址
//        // m.setPath(path);
//        // m.setDescription(parse.toString());
//        // moviesManager.updateMovies(m);
//        //
//        // }
//        ////// }
//        //
//        //
//        // } catch (Exception e) {
//        // e.printStackTrace();
//        // }
//        // 电影==把下载链接放到path字段去=====================================end===========================================================
//
//        // 把下载链接放到path字段去==============================start==================================================================
//        // try {
//        // List<Soft> lst100 = softManager.querySql(" select * from bc_soft where path
//        // is null ");
//        // //按照分页更新数据
//        // for (Soft soft:lst100) {
//        // String content = soft.getContent();
//        // Document parse = Jsoup.parse(content);
//        // Element last = parse.select("a").last();
//        // if(last!=null) {
//        // if(last.toString().contains("下载")||last.toString().contains("www.waitsun.com")||last.toString().contains("ctfile.com"))
//        // {
//        // System.out.println("========================");
//        //
//        // System.out.println("===========是=============");
//        // String download = last.toString();
//        // System.out.println(download);
//        // soft.setPath(download);
//        // //删除最后的路径元素
//        // last.remove();
//        // soft.setContent(parse.html());
//        // softManager.updateSoft(soft);
//        // }else {
//        // System.out.println("========================");
//        //
//        // System.out.println("===========否=============");
//        // }
//        // }
//        // }
//        //
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//        // 把下载链接放到path字段去=====================================end===========================================================
//
//        // =========================================================处理软件的下载样式===========================================================================================
//
//        // =========================================================处理软件的下载样式===========================================================================================
//        // List<Soft> result_list = softManager.findByCondition(" where ret_code like
//        // '%wskso%' ").getResultlist();
//        // for(Soft s:result_list) {
//        // String content = s.getContent();
//        // Document parse = Jsoup.parse(content);
//        // Elements select = parse.select("a");
//        // for(Element e:select) {
//        // if(e.attr("href").contains("http://www.wskso.com/wp-content/themes/begin/inc/go.php"))
//        // {
//        // e.addClass("btn");
//        // }
//        // }
//        // s.setContent(parse.html());
//        // softManager.updateSoft(s);
//        //// System.out.println(parse);
//        // }
//
//        // =========================================================处理软件的下载样式===========================================================================================
//
//        // =========================================================情圣===========================================================================================
//
//        // log.info("情圣定时任务开始"+TimeUtils.getNowTime());
//        // try {
//        //
//        // List<Map<String, String>> lift = HTMLParserUtil.retEQArticle();
//        // for (int i = 0; i < lift.size(); i++) {
//        //
//        // String title = lift.get(i).get("title");
//        // System.out.println(title);
//        // String brief = lift.get(i).get("brief");
//        // String date = lift.get(i).get("date");
//        // String article = lift.get(i).get("article");
//        // String ret_code = lift.get(i).get("ret_code");
//        // //是不存在的文章
//        // int flag = EqManager.countHql(" where o.ret_code= '"+ret_code+"' and o.date =
//        // '"+date+"' ");
//        //
//        // if(flag<=0){
//        // //生成并且设置图片
//        // List<String> meizi = HTMLParserUtil.gegeMEIZITU();
//        //
//        // int index = HTMLParserUtil.getRandomOne(meizi);
//        // String img = meizi.get(index);
//        //
//        // Eq eq = new Eq();
//        // eq.setArticle(article);
//        // eq.setBrief(brief);
//        // eq.setDate(date);
//        // eq.setTitle(title);
//        // eq.setImg(img);
//        // eq.setret_code(ret_code);
//        // EqManager.addEq(eq);
//        // }
//        //
//        // }
//        //
//        //
//        //
//        // //去重
//        // String delsql = "DELETE FROM bc_eq WHERE id IN (SELECT * FROM (SELECT id FROM
//        // bc_eq GROUP BY date HAVING ( COUNT(ret_code) > 1 )) AS p)" ;
//        //
//        // EqManager.executeSql(delsql);
//        //
//        //
//        //
//        //
//        //
//        //
//        // } catch (Exception e) {
//        //
//        // log.error("TestEQTask=======>"+e);
//        // }
//        //
//        // log.info("情圣定时任务结束"+TimeUtils.getNowTime());
//
//        // =========================================================情圣===========================================================================================
//
//
//        // =========================================================软件===========================================================================================
//        try {
//
//            log.info("soft task==============start=" + TimeUtils.getNowTime());
//            Map<String, String> map = null;
//
//            for (int k = 1; k <= 1; k++) {
//
//                try {
//
//                    List<Map<String, String>> list = HTMLParserUtil.retSoftNew(k);
//                    // List<Map<String, String>> list = HTMLParserUtil.retSoft_WSKSO(k);
//
//                    if (!CollectionUtils.isEmpty(list)) {
//                        for (int i = 0; i < list.size(); i++) {
//                            map = list.get(i);
//
//                            String title = map.get("title");
//                            String a_url = map.get("a_url");
//                            String brief = map.get("brief");
//                            String date = map.get("date");
//                            String article = map.get("article");
//                            String tag = map.get("tag");
//                            String code = map.get("code") + "-" + IDUtils.getInstance().generateNumberString(3);
//                            String os = map.get("os");
//                            String month = map.get("month");
//                            String year = map.get("year");
//                            String tag_code = map.get("tag_code");
//                            String path = map.get("path");
//
//                            // 是不存在的文章
//                            int flag = softManager.countHql(" where o.title= '" + title + "' or o.ret_code = '" + code + "' ");
//
//                            if (flag <= 0) {
//
//                                Soft model = Soft.builder().brief(brief).content(article).os(os).post_date(date)
//                                        .ret_code(code).ret_url(a_url).tags(tag).title(title).month(month).year(year)
//                                        .tags_code(tag_code).path(path).build();
//                                softManager.addSoft(model);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//
//                    continue;
//                }
//
//                try {
//                    Thread.sleep(1000);
//                    log.info("第" + k + "页================");
//                } catch (InterruptedException e) {
//                    // TODO Auo-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//
//        } catch (Exception e) {
//
//        }
//
//        log.info("soft task==============end=" + TimeUtils.getNowTime());
//
//        log.info("软件定时任务结束" + TimeUtils.getNowTime());
//
//        // =========================================================软件===========================================================================================
//
//        // =========================================================电影===========================================================================================
//        // TODO ..爬虫电影代码
//
////		try {
////
////			log.info("爬虫电影代码 开始==============start="+TimeUtils.getNowTime());
////			Map<String,String> map = null;
////
////
////
////			for (int k = 2; k <=15; k++) {
////				try {
////
////					List<Map<String, String>> list =
////							HTMLParserUtil.retMovies(k,BC_Constant.RET_dianying);
////
////
////					if(!CollectionUtils.isEmpty(list)){
////						for (int i = 0; i < list.size(); i++) {
////							try {
////								map = list.get(i);
////
////								String title = map.get("title");
////								// String a_url = map.get("a_url");
////								String date = map.get("date");
////								String article = map.get("article");
////								String ret_code = map.get("ret_code");
////								String tag = map.get("tag");
////								String tag_code = map.get("tag_code");
////								String path = map.get("path");
////
////								//是不存在的电影
////								int flag = moviesManager.countHql( " where o.ret_code= '"+ret_code+"' ");
////
////								if(flag<=0){
////
////
////									Movies model = new Movies();
////									model.setmovie_name(title);
////									model.setadd_time(date);
////									model.setDescription(article);
////									model.setPrice(1);
////									model.setret_code(ret_code);
////									model.setTag(tag);
////									model.settag_code(tag_code);
////									model.setview_num(HTMLParserUtil.geneview_num());
////									model.setColor(PinyinUtil.getFirstChar(title));
////									model.setPath(path);
////									moviesManager.addMovies(model);
////								}
////							} catch (Exception e) {
////
////								continue;
////							}
////
////						}
////					}
////				} catch (Exception e) {
////
////					continue;
////				}
////
////
////				try {
////					Thread.sleep(1000*5);
////					log.info("第"+k+"页================");
////				} catch (InterruptedException e) {
////					// TODO Auo-generated catch block
////					e.printStackTrace();
////				}
////			}
////
////
////
////			//重复记录每个只保留一条
////
////			String delmovie_sql = "DELETE FROM bc_movies "
////					+ "WHERE id IN ( SELECT id FROM ( SELECT max(id) AS id, count(movie_name) AS count "
////					+ "FROM bc_movies GROUP BY movie_name HAVING count > 1 ORDER BY count DESC ) AS tab )";
////
////			EqManager.executeSql(delmovie_sql);
////
////		} catch (Exception e) {
////			 //TODO: handle exception
////		}
////
////		 log.info("爬虫电影代码==============结束="+TimeUtils.getNowTime());
//
//        // TODO ..爬虫电影代码
//        // =========================================================电影===========================================================================================
//
//        // =========================================================诗词===========================================================================================
//        // TODO ..爬虫诗词代码
//
//        // try {
//        //
//        // log.info("poem task==============start="+TimeUtils.getNowTime());
//        // Map<String,String> map = null;
//        //
//        //
//        //
//        // for (int k = 1; k <=191; k++) {
//        // try {
//        //
//        // List<Map<String, String>> list = HTMLParserUtil.retPoem(k);
//        //
//        //
//        // if(!CollectionUtils.isEmpty(list)){
//        // for (int i = 0; i < list.size(); i++) {
//        // try {
//        // map = list.get(i);
//        //
//        //
//        // String title = map.get("title" );
//        // String author = map.get("author" );
//        // String content = map.get("content" );
//        // String content1 = map.get("content1" );
//        // String types = map.get("types" );
//        // String enjoys = map.get("enjoys" );
//        // String pic_poem = map.get("pic_poem" );
//        // String ret_code = map.get("ret_code" );
//        // String detail_url = map.get("detail_url" );
//        //
//        // //是不存在的诗词
//        // int flag = poemManager.countHql(new Poem(), " where o.ret_code= '"+ret_code+"'
//        // ");
//        //
//        // if(flag<=0){
//        // Poem p = new Poem();
//        // p.setAuthor(author);
//        // p.setContent(content);
//        // p.setCreatetime(TimeUtils.nowTime());
//        // p.setEnjoys(enjoys);
//        // p.setPic_poem(pic_poem);
//        // p.setret_code(ret_code);
//        // p.setTitle(title);
//        // p.setYears("南北朝");
//        // p.setTypes(types);
//        // p.setContent1(content1);
//        // p.setReturl(detail_url);
//        //
//        // poemManager.addPoem(p);
//        //
//        // PoemEnjoy pe = new PoemEnjoy();
//        // pe.setPoem_id(p.getId());
//        // pe.setTitle(title);
//        // pe.setEnjoying(enjoys);
//        // poemenjoyManager.addPoemEnjoy(pe);
//        //
//        // }
//        // } catch (Exception e) {
//        //
//        // continue;
//        // }
//        //
//        // }
//        // }
//        // } catch (Exception e) {
//        //
//        // continue;
//        // }
//        //
//        //
//        // try {
//        // Thread.sleep(1000*5);
//        // log.info("第"+k+"页================");
//        // } catch (InterruptedException e) {
//        // // TODO Auo-generated catch block
//        // e.printStackTrace();
//        // }
//        // }
//        //
//        //
//        //
//        //
//        // log.info("poem task==============end="+TimeUtils.getNowTime());
//        // } catch (Exception e) {
//        //
//        // }
//        //
//
//        // 批量处理诗词标签
//        // try {
//        // int count = poemManager.countSql("select * from bc_poem ");
//        //
//        // log.info("count______>"+count);
//        //
//        // int pagecount = count / 1000+1;
//        //
//        //
//        //
//        //
//        // //按照页码更新数据
//        // for (int i = 0; i < pagecount; i++) {
//        // List<Poem> lst100 = poemManager.querySql(" select * from bc_poem limit
//        // "+i*1000+",100");
//        // //按照分页更新数据
//        // for (Poem p:lst100) {
//        // String types = p.getTypes();
//        // String types_code = PinyinUtil.paraseStringToPinyin(types).toLowerCase();
//        // log.info("types_code------》"+types_code);
//        // p.setTypes_code(types_code);
//        // poemManager.updatePoem(p);
//        // }
//        // }
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//        //
//
//        // TODO ..爬虫诗词代码
//
//        // =========================================================诗词===========================================================================================
//        //
//
//        // =========================================================微信星座===========================================================================================
//
//        // try {
//        // List<Astro> astrolist = astroManager.findByCondition(" where status =
//        // 1").getResultlist();
//        // if(!CollectionUtils.isEmpty(astrolist)){
//        // for (int i = 0; i < astrolist.size(); i++) {
//        // Astro astro = astrolist.get(i);
//        // String wx_cop_userid = astro.getWx_cop_userid();
//        // String xz_name = astro.getxz_name();
//        //
//        // JSONObject json = WXTokenUtil.getXZYS(xz_name, "today");
//        //
//        // // 调取凭证
//        // String access_token = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId,
//        // ParamesAPI.secret).getToken();
//        //
//        // StringBuffer buffer = new StringBuffer();
//        // buffer.append("\ue423").append(xz_name).append("\ue31f").append("\n\n");
//        // buffer.append("\ue21c").append(TimeUtils.nowdate()).append("\n");
//        // buffer.append("\ue21d 综合运势:").append(json.get("summary")).append("\n");
//        // buffer.append("\ue21e 贵人星座:").append(json.get("QFriend")).append("\n");
//        // buffer.append("\ue21f 爱情运势:").append(json.get("love")).append("\n");
//        // buffer.append("\ue220 幸运颜色:").append(json.get("color")).append("\n");
//        // buffer.append("\ue221 工作运势:").append(json.get("work")).append("\n");
//        // buffer.append("\ue222 幸运数字:").append(json.get("number")).append("\n");
//        // buffer.append("\ue223 财运运势:").append(json.get("money")).append("\n");
//        // buffer.append("\ue224 健康运势:").append(json.get("health")).append("\n");
//        // String content = buffer.toString();
//        //
//        //
//        // //发送消息的jsontext
//        // String jsostr = Message.getSendJsonText(wx_cop_userid, "@all", "@all",
//        // content);
//        // String POST_URL =
//        // "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
//        // int result = WeixinQyhUtil.PostMessage(access_token, "POST", POST_URL,
//        // jsostr);
//        // log.info("jsonstr--"+jsostr);
//        // log.info("result--"+result);
//        // // 打印结果
//        // if (0 == result) {
//        // log.info("操作成功");
//        // } else {
//        // log.info("操作失败");
//        // }
//        // }
//        // }
//        // } catch (Exception e) {
//        //
//        // }
//
//        // =========================================================微信星座===========================================================================================
//
//        // =========================================================采麦图册===========================================================================================
//
//        // 插入采麦
//
//        // for (int k = 1; k < 398; k++) {
//        //
//        //
//        // try {
//        // List<Map<String, String>> lift = HTMLParserUtil.retCaiMai(k);
//        //
//        // for (int i = 0; i < lift.size(); i++) {
//        ////
//        // String title = lift.get(i).get("title");
//        // String title_code = lift.get(i).get("title_code");
//        // String album_img = lift.get(i).get("album_img");
//        // String zan = lift.get(i).get("zan");
//        // String pl = lift.get(i).get("pl");
//        //
//        // Lyrics model = new Lyrics();
//        // model.setTitle(title);
//        // model.setAlbumImg(album_img);
//        // model.setPl(Integer.parseInt(pl.replaceAll(" ", "")));
//        // model.setZan(Integer.parseInt(zan.replaceAll(" ", "")));
//        // model.setTitlecode(title_code);
//        // lyricsManager.addLyrics(model);
//        //
//        // }
//        //
//        //
//        // } catch (IOException e) {
//        //
//        // e.printStackTrace();
//        // }
//        //
//        // //休眠10秒
//        // try {
//        // Thread.sleep(500);
//        // log.info("第"+k+"页================");
//        // } catch (InterruptedException e) {
//        // // TODO Auo-generated catch block
//        // e.printStackTrace();
//        // }
//        // }
//
//        // 插入最爱主题的用户
//
//        // String sql = "select id,title_code from bc_lyrics where update_date is null
//        // order by id desc limit 4593,5000 ";
//        //
//        // List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//        //
//        // for (Map<String, Object> m:list) {
//        // Integer lyricsid = (Integer) m.get("id");
//        // String title_code = (String) m.get("title_code");
//        // try {
//        // Map<String, String> retCaiMaiZT = HTMLParserUtil.retCaiMaiZT(title_code);
//        //
//        // String username = retCaiMaiZT.get("username" );
//        // String tailslug = retCaiMaiZT.get("tailslug" );
//        // String courseware = retCaiMaiZT.get("courseware");
//        // String date =retCaiMaiZT.get("date");
//        // String meta =retCaiMaiZT.get("meta");
//        // String head_path =retCaiMaiZT.get("head_path");
//        //
//        // if(StringUtils.isNotEmpty(username)){
//        //
//        // int num = userManager.countHql(" where username = '"+username+"' ");
//        // int userid = 0;
//        // if(num<=0){
//        // User user = new User();
//        // int num_tail = userManager.countHql(" where tail_slug = '"+tailslug+"' ");
//        // if(num_tail>0){
//        //
//        // user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//        // }else{
//        // user.setTail_slug(tailslug);
//        // }
//        // user.setUsername(username);
//        // user.setDate_joined(date);
//        //
//        //
//        // user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//        // user.setPassword("MTIzNDU2MDAwMDAw");
//        // user.sethead_path(head_path);
//        // if(StringUtils.isNotEmpty(username)){
//        // user.sethead_span(username.substring(0,1).toUpperCase());
//        // user.sethead_span_class("text-"+username.substring(0,1).toLowerCase());
//        // }
//        // userManager.addUser(user);
//        //
//        // userid = user.getId();
//        // }else{
//        //// List<User> ul = userManager.findByCondition(" where username =
//        // '"+username+"' ").getResultlist();
//        //// if(!CollectionUtils.isEmpty(ul)){
//        //// User user = ul.get(0);
//        //// userid = user.getId();
//        //// }
//        //
//        // continue;
//        //
//        // }
//        //
//        // if(userid!=0){
//        // //更新或者添加profile
//        // Userprofile up = upManager.getModelByUserid(String.valueOf(userid));
//        //
//        // up.setMeta(meta);
//        // up.setCourseware(courseware);
//        // up.setUser_id(userid);
//        // if(up.getId()!=null){
//        // upManager.updateUserprofile(up);
//        //
//        // }else{
//        // upManager.addUserprofile(up);
//        // }
//        //
//        //
//        // //添加user lyrics关联
//        // UserLyrics ul = new UserLyrics();
//        // ul.setLyricsid(lyricsid);
//        // ul.setUserid(userid);
//        // ulManager.addUserLyrics(ul);
//        // }
//        //
//        // //
//        // }
//        //
//        //
//        //
//        //
//        //
//        //
//        // } catch (IOException e) {
//        //
//        // continue;
//        // }
//        //
//        //
//        //
//        // }
//
//        // 插入最爱主题的点赞用户
//
//        // String sql = "select id,title_code from bc_lyrics where update_date is null
//        // order by id desc ";
//        //
//        // List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//        //
//        // for (Map<String, Object> m:list) {
//        // Integer lyricsid = (Integer) m.get("id");
//        // String title_code = (String) m.get("title_code");
//        // try {
//        // String retCaiMaiZAN = HTMLParserUtil.retCaiMaiZT_ZAN(title_code);
//        //
//        // //拼接sql 查询userid列表；
//        // String sql2 = "select id from bc_user where tail_slug in ("+retCaiMaiZAN+")";
//        //
//        //
//        // List<Map<String, Object>> useridlist = softManager.querySqlMap(sql2);
//        //
//        // for (int i = 0; i < useridlist.size(); i++) {
//        // LyricsZan lz = new LyricsZan();
//        // lz.setLyricsid(lyricsid);
//        // lz.setUserid((Integer)useridlist.get(i).get("id"));
//        // lzManager.addLyricsZan(lz);
//        // }
//        //
//        // }catch(Exception e){
//        // e.printStackTrace();
//        // }
//        // }
//
//        // 插入醉爱主题评论信息
//
//        // List<Map<String, String>> retCaiMaiZT_PL =
//        // HTMLParserUtil.retCaiMaiZT_PL("shui-jue");
//        //
//        // for (int i = 0; i < retCaiMaiZT_PL.size(); i++) {
//        // String username = retCaiMaiZT_PL.get(i).get("username");
//        // String tailslug = retCaiMaiZT_PL.get(i).get("tailslug");
//        // String comment = retCaiMaiZT_PL.get(i).get("comment");
//        // String shijian = retCaiMaiZT_PL.get(i).get("shijian");
//        //
//        // if(StringUtils.isNotEmpty(username)){
//        //
//        // int num = userManager.countHql(" where username = '"+username+"' ");
//        // if(num<=0){//不存在插入用户
//        // User user = new User();
//        // user.setUsername(username);
//        // user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//        // user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//        // user.setPassword("MTIzNDU2MDAwMDAw");
//        // userManager.addUser(user);
//        //
//        // LyricsComment lc = new LyricsComment();
//        // lc.setComment(comment);
//        // lc.setLyricsid(519527);
//        // lc.setUserid(user.getId());
//        // lc.setCreate_time(shijian);
//        // lcManager.addLyricsComment(lc);
//        //
//        // }else{
//        // User user = userManager.findByCondition(" where username = '"+username+"'
//        // ").getResultlist().get(0);
//        // Integer userid = user.getId();
//        //
//        // LyricsComment lc = new LyricsComment();
//        // lc.setComment(comment);
//        // lc.setLyricsid(519527);
//        // lc.setUserid(userid);
//        // lc.setCreate_time(shijian);
//        // lcManager.addLyricsComment(lc);
//        //
//        // }
//        // }
//        // }
//
//        // 插入醉爱主题评论信息
//        // try {
//        //
//        // String sql = "select id,title_code from bc_lyrics where update_date is null
//        // order by id desc limit 4000,6000";
//        //
//        // List<Map<String, Object>> list = lyricsManager.querySqlMap(sql);
//        //
//        // String start = TimeUtils.nowTime();
//        // System.out.println("开始时间："+start);
//        //
//        //
//        // //把所有需要执行的放进线程池中
//        // for (Map<String, Object> m:list) {
//        // Integer lyricsid = (Integer) m.get("id");
//        // String title_code = (String) m.get("title_code");
//        //
//        // try {
//        //
//        //
//        // System.out.println("title_code------------->"+title_code);
//        //
//        // List<Map<String, String>> retCaiMaiZT_PL =
//        // HTMLParserUtil.retCaiMaiZT_PL(title_code);
//        //
//        // for (int i = 0; i < retCaiMaiZT_PL.size(); i++) {
//        // String username = retCaiMaiZT_PL.get(i).get("username");
//        // String tailslug = retCaiMaiZT_PL.get(i).get("tailslug");
//        // String comment = retCaiMaiZT_PL.get(i).get("comment");
//        // String shijian = retCaiMaiZT_PL.get(i).get("shijian");
//        //
//        // if(StringUtils.isNotEmpty(username)){
//        // System.out.println("username----------"+username);
//        // int num = userManager.countHql(" where username = '"+username+"' ");
//        // if(num<=0){//不存在插入用户
//        // User user = new User();
//        // user.setUsername(username);
//        // user.setTail_slug(tailslug+TimeUtils.getRandomDay());
//        // user.setEmail(tailslug+TimeUtils.getRandomDay()+"@qq.com");
//        // user.setPassword("MTIzNDU2MDAwMDAw");
//        // userManager.addUser(user);
//        //
//        // LyricsComment lc = new LyricsComment();
//        // lc.setComment(comment);
//        // lc.setLyricsid(lyricsid);
//        // lc.setUserid(user.getId());
//        // lc.setCreate_time(shijian);
//        // lcManager.addLyricsComment(lc);
//        //
//        // }else{
//        // User user = userManager.findByCondition(" where username = '"+username+"'
//        // ").getResultlist().get(0);
//        // Integer userid = user.getId();
//        //
//        // LyricsComment lc = new LyricsComment();
//        // lc.setComment(comment);
//        // lc.setLyricsid(lyricsid);
//        // lc.setUserid(userid);
//        // lc.setCreate_time(shijian);
//        // lcManager.addLyricsComment(lc);
//        //
//        // }
//        // }
//        // }
//        //
//        // } catch (Exception e) {
//        //
//        // log.error(e);
//        // continue;
//        // }
//        // }
//        //
//        //
//        //
//        // String end = TimeUtils.nowTime();
//        // System.out.println("结束时间："+end);
//        //
//        // System.out.println(TimeUtils.getPastTime(end, start));
//        //
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//        //
//
//        // 插入点赞数据
//
//        // try {
//        //
//        // String sql = " select * from bc_lyrics_comment order by id desc ";
//        //
//        // List<LyricsComment> list = lcManager.querySql(sql);
//        //
//        // String start = TimeUtils.nowTime();
//        // System.out.println("开始时间："+start);
//        //
//        //
//        // //把所有需要执行的放进线程池中
//        // for (LyricsComment m:list) {
//        // try {
//        // String create_time = m.getCreate_time();
//        // String timeByFanyi = TimeUtils.getTimeByFanyi(create_time);
//        // m.setCreate_time(timeByFanyi);
//        // lcManager.updateLyricsComment(m);
//        //
//        // } catch (Exception e) {
//        //
//        // log.error(e);
//        // continue;
//        // }
//        // }
//        //
//        //
//        //
//        // String end = TimeUtils.nowTime();
//        // System.out.println("结束时间："+end);
//        //
//        // System.out.println(TimeUtils.getPastTime(end, start));
//        //
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//
//        // 更新网络图片 替换主题为空的图片
//        // try {
//        //
//        // String sql = "select * from bc_lyrics where album_img ='' order by update_date
//        // desc";
//        //
//        // List<Lyrics> list = lyricsManager.querySql(sql);
//        //
//        // String start = TimeUtils.nowTime();
//        // System.out.println("开始时间："+start);
//        //
//        //
//        // //把所有需要执行的放进线程池中
//        // for (Lyrics m:list) {
//        // try {
//        // String title = m.getTitle();
//        // String title_code = m.getTitlecode();
//        // System.out.println(title);
//        // String retPicByName = HTMLParserUtil.retPicByName(title, title_code);
//        //
//        // m.setAlbumImg(retPicByName);
//        //
//        // lyricsManager.updateLyrics(m);
//        //
//        // } catch (Exception e) {
//        //
//        // log.error(e);
//        // continue;
//        // }
//        //
//        // }
//        //
//        //
//        //
//        // String end = TimeUtils.nowTime();
//        // System.out.println("结束时间："+end);
//        //
//        // System.out.println(TimeUtils.getPastTime(end, start));
//        //
//        // } catch (Exception e) {
//        //
//        // e.printStackTrace();
//        // }
//        //
//        // =========================================================采麦图册===========================================================================================
//
//    }
//
//    // 测试多页
//
//    @Test
//    public void save() {
//        runTask();
//    }
//
//}
