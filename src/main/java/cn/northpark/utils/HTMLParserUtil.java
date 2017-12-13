package cn.northpark.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import cn.northpark.manager.MoviesManager;
import cn.northpark.manager.SoftManager;

/**
 * @author bruce
 */


public class HTMLParserUtil{

    private static final Logger LOGGER = Logger
            .getLogger(HTMLParserUtil.class);
    


    
    	
    	/**
         * 爬虫采麦的最爱信息
         * loveUdavid
         * ret_url:http://www.caimai.cc/love/page2
         * http://www.caimai.cc/love/page397
         * @throws IOException
         */
        public static List<Map<String,String>> retCaiMai(int index) throws IOException {
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
                try{
                Document doc = Jsoup.connect("http://www.caimai.cc/love/page"+index)
    			            		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
    								.referrer("http://www.google.com") 
    								.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                					.get();
                
                //   `id`,  `title`, `titlecode`,  `updatedate`, `albumImg`, `zan`, `pl`
                Elements info   = doc.select("div[class=col-xs-6 col-sm-3 margin-b20 ]");
                for(Element p : info){

                    HashMap<String, String> map =new HashMap<String, String>();
                  
                    
                    //标题的主题页面
                    Elements elements = p.select("div[class=thumbnail radius-0 border-0 margin-b0]");
                    
                    Element a = elements.get(0).select("a").get(0);
                    
                    String aurl = a.attr("href");
                    
                    String titlecode = aurl.replace("/love/", "");
                    
                    System.out.println(titlecode);
                    
                    Element img = a.select("img").get(0);
                    
                    String date = TimeUtils.getRandomDate();
                    
                    //下载图片
                    String weburl = img.attr("src");
                    
                    //标题

                    String title = img.attr("alt");
                    System.out.println(title);

                    HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOS("album") ,date);
                        
                    String albumimg = map22.get("trimpath");

                    //赞和评论
                    String zan_pl = p.select("div[class=col-xs-5 text-right]").text();
                    String zan = zan_pl.split("  ")[0];
                    String pl  = zan_pl.split("  ")[1];
                    System.out.println(zan_pl.split("  ")[0]);
                    System.out.println(zan_pl.split("  ")[1]);
                    
                    
                    

                    map.put("title", title);
                    map.put("titlecode", titlecode);
                    map.put("albumImg", albumimg);
                    map.put("zan", zan);
                    map.put("pl", pl);
                    list.add(map);

                    }
                }catch (Exception e) {
                	 LOGGER.error("HTMLPARSERutils------->", e);;
                }
                
                
                
               
            return list;
        }


        /**
         * 爬虫采麦的最爱主题关联信息  ------------根据主题页  获取作者信息  、、粉丝列表、、评论列表
                    
         * http://www.caimai.cc/love/xiang-ni-de-mei-yi-tian-lyz
         * @throws IOException
         */
        public static Map<String, String> retCaiMaiZT(String titlecode) throws IOException {
        	Map<String,String> map = new HashMap<>();
                try{
                Document doc = Jsoup.connect("http://www.caimai.cc/love/"+titlecode)
    			            		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
    								.referrer("http://www.google.com") 
    								.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                					.get();
                
                
                //根据主题页  获取作者信息  、、粉丝列表、、评论列表
                
                Element author   = doc.select("h2[class=margin0]").get(0);
                
                Element author_info = author.select("a").get(0);
                
                String username = author_info.text();

                String author_href = author_info.attr("href");
                
                String tailslug = author_href.replace("/", ""); 
                
                author_href = "http://www.caimai.cc"+author_href;

                
                Document doc2 = Jsoup.connect(author_href)
	            		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
						.referrer("http://www.google.com") 
						.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
    					.get();
                 
                //头像
                Element avatar = doc2.select("div[class=thumbnail bg-no border-0]").get(0).select("img").get(0);  
                
                
                String courseware ="";
                String date = "";
                int size = doc2.select("h3[class=margin0]").size();
                
                if(size>=2){
                	 courseware = doc2.select("h3[class=margin0]").get(0).text();  
                     
                     date = doc2.select("h3[class=margin0]").get(1).text().replace("加入时间：", "");  
                }else{
                	 date = doc2.select("h3[class=margin0]").get(0).text().replace("加入时间：", "");  
                }
                
                
                String meta = "";
                Elements desc_element = doc2.select("p[class=margin0]");
                
                if(desc_element.size()>0){
                	meta = desc_element.get(0).text();
                }
                
                String headpath = ""; 
                //下载图片
                try {
                    String weburl = avatar.attr("src");


                    HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOS("heads")  ,date);
                    
                    headpath = map22.get("trimpath");
                    
                    

                } catch (Exception e) {
                    // TODO: handle exception
                    LOGGER.error("ret pic exception===>"+e.toString());
                }
                
                //作者用户信息
                map.put("username", username); 
                map.put("tailslug", tailslug); 
                map.put("courseware", courseware); 
                map.put("date", date); 
                map.put("meta", meta); 
                map.put("headpath", headpath); 
                
                
                
                //取得点赞的作者
                
                //取得评论的信息
                    
                    

                }catch (Exception e) {
                	 LOGGER.error("HTMLPARSERutils------->", e);;
                }
                
                
                
            return map;
        }
        
        
        
        
    /**
     * 爬虫获取情圣的文章
     * loveUdavid
     * ret_url:http://chuansong.me/account/loveUdavid?start=(i-1)*12
     * @throws IOException
     */
    public static List<Map<String,String>> retEQArticle() throws IOException {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(int i=1;i<=1;i++){
            try{
            Document doc = Jsoup.connect("http://chuansong.me/account/loveudavid?start="+i*12)
			            		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
								.referrer("http://www.google.com") 
								.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
            					.get();
            Elements info   = doc.select("div[class=pagedlist_item]");
            for(Element p : info){

                HashMap<String, String> map =new HashMap<String, String>();
                //标题
                Elements titles = p.select("a[class=question_link]");

                String title = titles.get(0).text();
                title = title.replace("大卫", "");
                String aurl = "http://chuansong.me"+titles.get(0).select("a").get(0).attr("href");

                Elements dates = p.select("span[class=timestamp]");

                String date = dates.get(0).text();

                //文章
                
                
                //休眠
                try {
    			    Thread.sleep(500);
    			} catch (InterruptedException e) {
    			    // TODO Auo-generated catch block
    			    e.printStackTrace();
    			}
                
                Document doc_ = Jsoup.connect(aurl)
					            		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
										.referrer("http://www.google.com") 
										.timeout(1000*5) //it's in milliseconds, so this means 5 seconds. 
										.followRedirects(true)
										.maxBodySize(1024*1024*3)    //3Mb Max
										  //.ignoreContentType(true) //for download xml, json, etc
				                		.get();
                Elements articles = doc_.select("div[class=rich_media_content]");
                String article = articles.get(0).html();
                article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "")
                		.replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "")
                		.replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "")
                		.replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "")
                		.replace("<p><img class=\"\" data-ratio=\"0.736\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"1000\" src=\"http://img.chuansong.me/mmbiz/nMpCNia3hrN4BiaKeiadNc9Cd33C0g7Dt22pn0KOE54PowMzbygnmChH9lpBOLZEK1YvvNJtk1TOgiazP4VhibTAImw/0?wx_fmt=jpeg\" style=\"\"></p>", "")
                		.replace("<p style=\"white-space: normal;text-align: center;\"><span style=\"color: rgb(255, 169, 0);\"><strong>长按图片赞赏，请<情圣>吃金拱门！</strong></span></p>","")
                		.replace("<p style=\"white-space: normal;\"><br></p>","")
                		.replace("<p style=\"white-space: normal;\"><img class=\"\" data-copyright=\"0\" data-ratio=\"1\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"1080\" src=\"http://img.chuansong.me/mmbiz_jpg/nMpCNia3hrN67eXCUbQySMmXpKibVaPsicm1KHjywtIvUXWFDlBqnIrZMhCJ1Z1rKg9qM8ETCGt2ypMClrkB1oNHw/0?wx_fmt=jpeg\"></p>", "")
                		.replace("<p><img class=\"\" data-ratio=\"0.6826196473551638\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"794\" src=\"http://img.chuansong.me/mmbiz/nMpCNia3hrN4BiaKeiadNc9Cd33C0g7Dt22uCIbRO1q9t6uFJSzAmYjvmkkJSAw6ZN78QdqBcGf7hZ2UEFkzLXMGQ/0?wx_fmt=jpeg\" style=\"\"></p>", "")
        				.replace("<img data-ratio=\"1\" data-w=\"20\" src=\"http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=https://res.wx.qq.com/mpres/htmledition/images/icon/common/emotion_panel/emoji_wx/2_06.png\" style=\"display:inline-block;width:20px;vertical-align:text-bottom;\">", "")
        				
                		
                		;



                //简介
                Elements texts = doc_.select("p");
                String brief = texts.get(3).text();
                brief = brief.replace("大卫", "情圣");
                
                
                // 生成码
                String retcode = MD5Utils.encoding(title);

                map.put("title", title);
                map.put("aurl", aurl);
                map.put("brief", brief);
                map.put("date", date);
                map.put("article", article);
                map.put("retcode", retcode);

                
                LOGGER.info(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");
                
                list.add(map);

                }
            }catch (Exception e) {
            	 LOGGER.error("HTMLPARSERutils------->", e);;
                continue;
            }
            
            
            
            //休眠10秒
            try {
			    Thread.sleep(1000*5);
			    LOGGER.info("第"+i+"页================");
			} catch (InterruptedException e) {
			    // TODO Auo-generated catch block
			    e.printStackTrace();
			}
        }
        return list;
    }

   

  

    /**
     * 爬取今日情圣的文章
     */
    public static Map<String, String> retTodayEq() {
        // TODO Auto-generated method stub
                HashMap<String, String> map =new HashMap<String, String>();
        try{
        	
        	Document doc = Jsoup.connect("http://chuansong.me/account/loveudavid")
        			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
					.referrer("http://www.google.com") 
					.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
        			.get();
            Element info   = doc.select("div[class=pagedlist_item]").get(0);

                //标题
                Elements titles = info.select("a[class=question_link]");

                String title = titles.get(0).text();
                title = title.replace("大卫", "");
                String aurl = "http://chuansong.me"+titles.get(0).select("a").get(0).attr("href");

                Elements dates = info.select("span[class=timestamp]");

                String date = dates.get(0).text();

                //文章
                Document doc_ = Jsoup.connect(aurl)
            			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
    					.referrer("http://www.google.com") 
    					.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                		.get();
                Elements articles = doc_.select("div[class=rich_media_content]");
                String article = articles.get(0).html();
                article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "")
                		.replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "")
                		.replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "")
                		.replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "")
                		.replace("<p><img class=\"\" data-ratio=\"0.736\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"1000\" src=\"http://img.chuansong.me/mmbiz/nMpCNia3hrN4BiaKeiadNc9Cd33C0g7Dt22pn0KOE54PowMzbygnmChH9lpBOLZEK1YvvNJtk1TOgiazP4VhibTAImw/0?wx_fmt=jpeg\" style=\"\"></p>", "")
                		.replace("<p style=\"white-space: normal;text-align: center;\"><span style=\"color: rgb(255, 169, 0);\"><strong>长按图片赞赏，请<情圣>吃金拱门！</strong></span></p>","")
                		.replace("<p style=\"white-space: normal;\"><br></p>","")
                		.replace("<p style=\"white-space: normal;\"><img class=\"\" data-copyright=\"0\" data-ratio=\"1\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"1080\" src=\"http://img.chuansong.me/mmbiz_jpg/nMpCNia3hrN67eXCUbQySMmXpKibVaPsicm1KHjywtIvUXWFDlBqnIrZMhCJ1Z1rKg9qM8ETCGt2ypMClrkB1oNHw/0?wx_fmt=jpeg\"></p>", "")
                		.replace("<p><img class=\"\" data-ratio=\"0.6826196473551638\" data-s=\"300,640\" data-type=\"jpeg\" data-w=\"794\" src=\"http://img.chuansong.me/mmbiz/nMpCNia3hrN4BiaKeiadNc9Cd33C0g7Dt22uCIbRO1q9t6uFJSzAmYjvmkkJSAw6ZN78QdqBcGf7hZ2UEFkzLXMGQ/0?wx_fmt=jpeg\" style=\"\"></p>", "")
        				.replace("<img data-ratio=\"1\" data-w=\"20\" src=\"http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=https://res.wx.qq.com/mpres/htmledition/images/icon/common/emotion_panel/emoji_wx/2_06.png\" style=\"display:inline-block;width:20px;vertical-align:text-bottom;\">", "")
        				
                		
                		;


                //简介
                Elements texts = doc_.select("p");
                String brief = texts.get(3).text();
                brief = brief.replace("大卫", "情圣");
                // 生成码
                String retcode = MD5Utils.encoding(title);

                map.put("title", title);
                map.put("aurl", aurl);
                map.put("brief", brief);
                map.put("date", date);
                map.put("article", article);
                map.put("retcode", retcode);

                LOGGER.info(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                LOGGER.error("HTMLPARSERutils------->", e);;
            }

        return map;
    }

   
    
    /**
     * 爬取1页的优惠资源vps、优惠券、图书、影视、建站、网络资源、节日优惠...
     * @param index
     * @param retlink
     * @return
     */
    public static List<Map<String, String>> retCoupon(Integer index,String retlink) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        HashMap<String, String> map =null;
        try{
        	
        		LOGGER.info("page============================="+index+"============================页");
        		
        		String url = retlink+index+"/";
	        	
	        	
                Document doc = Jsoup.connect(url)
			            			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
			    					.referrer("http://www.google.com") 
			    					.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
			            			.get();
                
                Elements  lis   = doc.select("article[class=excerpt excerpt-one]");
                if(!lis.isEmpty()){
                    for (int i = 0; i < lis.size(); i++) {
                    	
                    	map = new HashMap<>();
                        Element li  = lis.get(i);
                       
                        //获取相关信息
                        Element a = li.select("header").get(0).select("a").get(0);
                        
                        String aurl = a.attr("href");

                        String title = a.attr("title");
                        
                        title = title.replace("-VPS推荐网", "");
                        
                        String retcode = MD5Utils.encoding(title);
                        
                        String date = li.select("p[class=text-muted time]").get(0).text();
                        
                        date = date.substring(date.length()-10, date.length());
                        
                        String brief = li.select("p[class=note]").get(0).text();
                        
                        
                        String tags = li.select("span[class=post-tags]").text().replace("标签：", "").replace(" ", "").replace("/", ",");
                        
                        System.out.println(tags);
                        
                        //休眠2秒
                        try {
            			    Thread.sleep(500);
            			} catch (InterruptedException e) {
            			    // TODO Auo-generated catch block
            			    e.printStackTrace();
            			}
                        
                        //正文信息
                        Document doc2 = Jsoup.connect(aurl)
		            			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
		    					.referrer("http://www.google.com") 
		    					.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
		            			.get();
                        
                        
                        Element content = doc2.select("article[class=article-content]").get(0);
                        
                        content.select("p[class=post-copyright]").remove();
                        
                        
                        //上传并且替换图片 开始===============================
                        
                        Elements imgs = content.select("img");
                        for (int j = 0; j < imgs.size(); j++) {
                            try {
                                String weburl = imgs.get(j).attr("src");
                                //web图片上传到七牛

                                //-------------开始--------------------------------

                                HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOS() ,date);

                                String rs = QiniuUtils.getInstance.upload(map22.get("localpath"), "vps/"+date+"/"+map22.get("key"));

                                //-------------结束--------------------------------

                                imgs.get(j).attr("src", rs);
                                
                                //删除原图src设置
                                imgs.get(j).removeAttr("srcset");
                            } catch (Exception e) {
                                // TODO: handle exception
                                LOGGER.error("ret pic exception===>"+e.toString());
                                continue;
                            }

                        }
                        //上传并且替换图片 结束===============================
                        
                        String article = content.html();
                        
                        map.put("aurl", aurl);
                        map.put("title", title);
                        map.put("retcode", retcode);
                        map.put("date", date);
                        map.put("brief", brief);
                        map.put("article", article);
                        map.put("tags", tags);
                        
                        list.add(map);
                        
                        LOGGER.info("\t\r"+"aurl--->"+aurl+"\t\r"+"title--->"+title+"\t\r"+"retcode--->"+retcode+"\t\r"+"date--->"+date+"\t\r"+"brief--->"+brief+"\t\r"+"article---->"+article+"\t\r"+"-----------------");
                    }
                }

            }catch(Exception e){
                LOGGER.error("HTMLPARSERutils------->", e);;
            }

        return list;
    }
    

   
    /**
     * 爬取1页的mac软件内容
     */
    public static List<Map<String, String>> retSoft(Integer index) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        HashMap<String, String> map = null;
        try{
                Document doc =  Jsoup.connect("http://www.sdifen.com/category/black-apple/apple-software/page/"+index+"/")
                		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        				.referrer("http://www.google.com") 
        				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
        				.get();
                
               
                Elements articles   = doc.select("article");
                if(!articles.isEmpty()){
                    for (int i = 0; i < articles.size(); i++) {
                        
                        Element article  = articles.get(i);
                        
                        //code
                        String code = article.attr("id");

                        LOGGER.info("code===================="+code);
                        
                        //判断code在系统不存在再去处理后面的事
                        
                        SoftManager softManager = (SoftManager)SpringContextUtils.getBean("SoftManager");
                        int flag = softManager.countHql( " where o.retcode= '"+code+"' ");
    					
    					if(flag<=0){
    						
    				    map =new HashMap<String, String>();

                        //标题
                        Elements titles = article.select("h1[class=entry-title]");

                        String title = titles.get(0).text();
                        LOGGER.info("title===================="+title);
                        String aurl = titles.get(0).select("a").get(0).attr("href");

                        //标签tags

                        Elements tags = article.select("span[class=cat-links]");

                        String tag = tags.get(0).text();
                        tag = tag.replaceAll("发表在", "").replaceAll(" ", "");

                        LOGGER.info("tag===================="+tag);
                        //计算标签编码、
                        String tagcode = "005";
                        if(tag.contains("应用")){
                            tagcode = "001";
                            tag = "系统、应用软件";
                        }else if(tag.contains("开发")){
                            tagcode = "002";
                            tag = "开发、设计软件";
                        }else if(tag.contains("媒体")){
                            tagcode = "003";
                            tag = "媒体软件";
                        }else if(tag.contains("安全")){
                            tagcode = "004";
                            tag = "网络、安全软件";
                        }else if(tag.contains("其他")){
                            tagcode = "005";
                            tag = "其他软件";
                        }else if(tag.contains("游戏")){
                            tagcode = "006";
                            tag = "游戏一箩筐";
                        }else if(tag.contains("限时免费")){
                            tagcode = "007";
                            tag = "限免软件";
                        }else if(tag.contains("疑难")){
                            tagcode = "008";
                            tag = "疑难杂症";
                        }else{
                            tagcode = "005";
                            tag = "其他软件";
                        }
                        LOGGER.info("tagcode===================="+tagcode);
                      
                        //日期
                         Elements dates = article.select("time[class=entry-date]");
                        String date = dates.get(0).text();
                        date=date.replaceAll(" ", "");
                        date=date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
                        LOGGER.info("date===================="+date);
                        //月
                        String month = date.substring(0, date.lastIndexOf("-"));
                        LOGGER.info("month===================="+month);
                        //年
                        String year = month.substring(0, month.lastIndexOf("-"));
                        LOGGER.info("year===================="+year);
                        //文章
                        StringBuilder sb = new StringBuilder();

                        
                        
                        //休眠
                        try {
            			    Thread.sleep(500);
            			} catch (InterruptedException e) {
            			    // TODO Auo-generated catch block
            			    e.printStackTrace();
            			}
                        
                        Document doc_ = Jsoup.connect(aurl)
                        		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                				.referrer("http://www.google.com") 
                				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                				.get();
                        Elements article_alls = doc_.select("div[class=entry-content] p");
                        if(!article_alls.isEmpty()){
                            for (int i1 = 0; i1 < article_alls.size(); i1++) {

                                Elements imgs = article_alls.get(i1).select("img");
                                for (int j = 0; j < imgs.size(); j++) {
                                    try {
                                        String weburl = imgs.get(j).attr("src");
                                        //web图片上传到七牛

                                        //-------------开始--------------------------------

                                        HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOS() ,date);

                                        String rs = QiniuUtils.getInstance.upload(map22.get("localpath"), "soft/"+date+"/"+map22.get("key"));

                                        //-------------结束--------------------------------

                                        imgs.get(j).attr("src", rs);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        LOGGER.error("ret pic exception===>"+e.toString());
                                        continue;
                                    }

                                }

                                String p1 = article_alls.get(i1).html();
                                if(!p1.contains("本文链接") && !p1.contains("转载声明") ){
                                    sb.append("<p>"+p1+"</p>");
                                }
                            }
                        }

                        String text = sb.toString().replaceAll("小子", "小布");
                        LOGGER.info("article============="+text);
                        //简介
                        Elements briefs = article.select("div[class=entry-content] p");

                        StringBuilder sb2 = new StringBuilder();

                        if(!briefs.isEmpty()){
                            for (int i1 = 0; i1 < briefs.size(); i1++) {

                                Elements imgs = briefs.get(i1).select("img");
                                for (int j = 0; j < imgs.size(); j++) {

                                    try {

                                        String weburl = imgs.get(j).attr("src");
                                        //web图片上传到七牛

                                        //-------------开始--------------------------------

                                        HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOS(),date);

                                        String rs = QiniuUtils.getInstance.upload(map22.get("localpath"), "soft/"+date+"/"+map22.get("key"));

                                        //-------------结束--------------------------------

                                        imgs.get(j).attr("src", rs);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        LOGGER.error("ret pic exception===>"+e.toString());
                                        continue;
                                    }

                                }
                                String p1 = briefs.get(i1).html();
                                if(!p1.contains("继续阅读") ){
                                    sb2.append("<p>"+p1+"</p>");
                                }
                            }
                        }

                        String brief = sb2.toString().replaceAll("小子", "小布");

                        LOGGER.info("brief===================="+brief);
                        map.put("title", title);
                        map.put("aurl", aurl);
                        map.put("brief", brief);
                        map.put("date", date);
                        map.put("article", text);
                        map.put("tag", tag);
                        map.put("code", code);
                        map.put("os", "mac");
                        map.put("month", month);
                        map.put("year", year);
                        map.put("tagcode", tagcode);
                        list.add(map);
    					
    					}
                    }
                }

//                LOGGER.info(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                LOGGER.error("HTMLPARSERutils------->", e);;
            }

        return list;
    }

    /**
     * 爬取1页的电影图书资源
     */
    public static List<Map<String, String>> retMovies(Integer index,String rettype) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        HashMap<String, String> map =null;
        try{
        	
        		LOGGER.info("page============================="+index+"============================页");
	        	//String url = "http://www.vip588660.cm/page/"+index+"/";
//        		String url = "http://www.vip588660.com/category/movie/page/"+index+"/";
//	        	String url = "http://www.vip588660.com/category/dianshiju/page/"+index+"/";
//	        	String url = "http://www.vip588660.com/category/dongman/page/"+index+"/";
        		
        		String url = rettype+index+"/";
	        	
	        	
                Document doc = Jsoup.connect(url)
                		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        				.referrer("http://www.google.com") 
        				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
        				.get();
                Element  ul   = doc.select("ul[class=masonry clearfix]").get(0);
                Elements lis  = ul.select("li[class=post box row ");
                if(!lis.isEmpty()){
                    for (int i = 0; i < lis.size(); i++) {
                        Element li  = lis.get(i);
                       
                        //获取相关信息
                        Elements divs = li.select("div[class=thumbnail]");

                        String title = divs.get(0).select("a").get(0).attr("title");
                        
                        String retcode = MD5Utils.encoding(title);
                        
                        
                        //判断code在系统不存在再去处理后面的事
                        
                        MoviesManager moviesManager = (MoviesManager)SpringContextUtils.getBean("MoviesManager");
                        int flag = moviesManager.countHql(" where o.retcode= '"+retcode+"' ");
    					
    					if(flag<=0){
                        
    					map = new HashMap<String, String>();
    						
                        String aurl =  divs.get(0).select("a").get(0).attr("href");

                        String date =  li.select("span[class=info_date info_ico]").get(0).text();
                        
                        
                        String tag = "";
                        String tagcode = "";
                        Elements tags = li.select("span[class=info_category info_ico]").get(0).select("a");
                        if(!CollectionUtils.isEmpty(tags)){
                        	for (int j = 0; j < tags.size(); j++) {
								Element taga = tags.get(j);
								String hrefa = taga.attr("href");
								if(StringUtils.isNotEmpty(hrefa)){
									tagcode+=hrefa.substring(hrefa.lastIndexOf("/")+1)+",";
								}
								tag+=taga.text()+",";
							}
                        }
                        
                        
                        if(StringUtils.isNotEmpty(tag) && tag.endsWith(",")){
                        	tag  =  tag.substring(0, tag.length()-1);
                        }
                        if(StringUtils.isNotEmpty(tagcode) && tagcode.endsWith(",")){
                        	tagcode  =  tagcode.substring(0, tagcode.length()-1);
                        }
                        

                        LOGGER.info("title==============>"+title);
                        LOGGER.info("aurl==============>"+aurl);
                        LOGGER.info("date==============>"+date);
                        LOGGER.info("tag==============>"+tag);
                        LOGGER.info("tagcode==============>"+tagcode);
                        

                        String desc = "";

                        
                      //休眠
                        try {
            			    Thread.sleep(500);
            			} catch (InterruptedException e) {
            			    // TODO Auo-generated catch block
            			    e.printStackTrace();
            			}
                        
                        Document doc_ = Jsoup.connect(aurl)
                        		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                				.referrer("http://www.google.com") 
                				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                				.get();
                        Elements article_alls = doc_.select("div[id=post_content]");
                        if(!article_alls.isEmpty()){

                                Elements imgs = article_alls.get(0).select("img");
                                for (int j = 0; j < imgs.size(); j++) {
                                    try {
                                        String weburl = imgs.get(j).attr("src");
                                        //web图片上传到七牛

                                        //-------------开始--------------------------------

                                        HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOSMovies() ,date);

                                        String rs = QiniuUtils.getInstance.upload(map22.get("localpath"), "movies/"+date+"/"+map22.get("key"));

                                        //-------------结束--------------------------------

                                        imgs.get(j).attr("src", rs);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        LOGGER.error("ret movies pic exception===>"+e.toString());
                                        continue;
                                    }

                                }

                                desc = "#电影简介\n";

//                              desc +=desc+preText+markText;
                                
                                //删除社交代码\脚本代码、播放代码、播放样式
                                article_alls.get(0).select("#sociables").remove();
                                article_alls.get(0).select("#wp-connect-share-css").remove();
                                article_alls.get(0).select("div[class=MinePlayer]").remove();
                                article_alls.get(0).select("div[class=MineBottomList]").remove();
                                article_alls.get(0).select("#minevideo-css").remove();
                                article_alls.get(0).select("script").remove();
                                
                                desc += article_alls.get(0).html();
                                
                                desc = desc.replaceAll("<!-- 社会化分享按钮 来自 WordPress连接微博 插件 -->", "");
                                LOGGER.info("desc==============>"+desc);
                        }

                        map.put("title", title);
                        map.put("aurl", aurl);
                        map.put("date", date);
                        map.put("article", desc);
                        map.put("retcode", retcode);
                        map.put("tag", tag);
                        map.put("tagcode", tagcode);
                        list.add(map);
    					}
    					
                    }
                }

//                LOGGER.info(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                LOGGER.error("HTMLPARSERutils------->", e);;
            }

        return list;
    }
    
    
    
    
    /**
     * 爬取1页的诗词
     */
    public static List<Map<String, String>> retPoem(Integer index) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try{
        	
        		LOGGER.info("page============================="+index+"============================页");
	        	//String url = "http://www.vip588660.cm/page/"+index+"/";
//        		String url = "http://www.vip588660.com/category/movie/page/"+index+"/";
//	        	String url = "http://www.vip588660.com/category/dianshiju/page/"+index+"/";
//	        	String url = "http://www.haoshici.com/"+index+"_E59490_0_0_0_0.html";
//        		String url = "http://www.haoshici.com/"+index+"_E5AE8B_0_0_0_0.html";
        		String url = "http://www.haoshici.com/"+index+"_E58583_0_0_0_0.html";
	        	
	        	
	        	
                Document doc = Jsoup.connect(url)
                		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        				.referrer("http://www.google.com") 
        				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
        				.get();
                Elements lis  = doc.select("li[class=lst ");
                if(!lis.isEmpty()){
                    for (int i = 0; i < lis.size(); i++) {
                        HashMap<String, String> map =new HashMap<String, String>();
                        Element li  = lis.get(i);

                        /**
                         * 
						  <li class="lst">
								<a href="Lishangyin4.html" target="_top" title="七绝">
									<span class="bigger">
										《初入武夷》
									</span>
								</a>
								<span class="_11px">
									（唐代李商隐作品）
								</span>
								<br>
								<a href="Lishangyin4.html" target="_top" title="">
									未到名山梦已新，千峰拔地玉嶙峋。幔亭一夜风吹雨，似与游人洗俗尘。
								</a>
							</li>
                         * 
                         * 
                         * */
                        //获取相关信息
                        Elements a_s = li.select("a");

                        //诗词类型【五言、七言、绝句】
                        String types = a_s.get(0).attr("title");
                        
                        //诗词名
                        String title = a_s.get(0).select("span").get(0).text();
                      
                        
                        //作者
                        String author = li.select("span[class=_11px]").get(0).text();
                        
                        author = author.replaceAll("唐代", "").replaceAll("作品", "");
                        
                        
                       
                        
                        //内容
                        String content  = a_s.get(1).text();
                        
                        //详情url
                        String detail_url = "http://www.haoshici.com/"+a_s.get(1).attr("href");
                        
                        
                        //生成代码
                        String retcode = MD5Utils.encoding(title+types+author+detail_url);
                        
                       

                        LOGGER.info("title==============>"+title);
                        LOGGER.info("detail_url==============>"+detail_url);
                        LOGGER.info("author==============>"+author);
                        LOGGER.info("content==============>"+content);
                        LOGGER.info("types==============>"+types);
                        

                        //图片赏析
                        String pic_poem = "";
                        
                        //文字赏析
                        String enjoys = "";
                      //诗词内容
                        String content1  = "";


                        //休眠
                        try {
            			    Thread.sleep(500);
            			} catch (InterruptedException e) {
            			    // TODO Auo-generated catch block
            			    e.printStackTrace();
            			}
                        
                        Document doc_ = Jsoup.connect(detail_url)
                        		.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                				.referrer("http://www.google.com") 
                				.timeout(1000*5) //it's in milliseconds, so this means 5 seconds.  
                				.get();
                        Elements article_alls = doc_.select("div[class=middle]");
                        if(!article_alls.isEmpty()){

                                Elements imgs = article_alls.get(0).select("img");
                                String date = TimeUtils.nowdate();
                                for (int j = 0; j < imgs.size(); j++) {
                                    try {
                                        String weburl = imgs.get(j).attr("src");
                                        LOGGER.info(weburl);
                                        //web图片上传到七牛
                                        if(StringUtils.isNotEmpty(weburl)&&!weburl.contains("verify.php")){
                                        	 //-------------开始--------------------------------
                                        	
                                        	if(!weburl.startsWith("http://www.haoshici.com")){
                                        		weburl="http://www.haoshici.com/"+weburl ; 
                                        	}

                                            HashMap<String, String> map22 = HTMLParserUtil.webPic2Disk(weburl, getLocalFolderByOSMovies() ,date);

                                            String rs = QiniuUtils.getInstance.upload(map22.get("localpath"), "movies/"+date+"/"+map22.get("key"));

                                            //-------------结束--------------------------------

                                            imgs.get(j).attr("src", rs);
                                            
                                            pic_poem = rs;
                                        }
                                       
                                        
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        LOGGER.error("ret movies pic exception===>"+e.toString());
                                        continue;
                                    }

                                }

                                //诗词赏析
                                   enjoys = article_alls.get(0).select("p[class=explanation]").html();

                                   LOGGER.info("enjoys==============>"+enjoys);    
                                   
                                   //诗词内容
                                   content1 = article_alls.get(0).select("p[class=poetry]").get(0).html();
                                   LOGGER.info("content1==============>"+content1);    
                                   
                        }

                        
                        
                        map.put("title", title);
                        map.put("detail_url", detail_url);
                        map.put("author", author);
                        map.put("content", content);
                        map.put("types", types);
                        map.put("enjoys", enjoys);
                        map.put("pic_poem", pic_poem);
                        map.put("retcode", retcode);
                        map.put("content1", content1);
                        
                        list.add(map);
    					}
                    }

//                LOGGER.info(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                LOGGER.error("HTMLPARSERutils------->", e);;
            }

        return list;
    }
    
    
    /**
     *	=====================================tools开始================================================================
     */

    
    /**
     * 读取网络图片到硬盘
     */
    public static void readPic2Disk(){
        try {
            //retMeizitu();

            Document doc = Jsoup.connect("http://orenogazoo.tumblr.com/")
            					.get();
            //src
            Elements pics = doc.select("img");
            for(Element p : pics){
                try{
                    URL   url   =   new   URL(p.attr("src"));
                    URLConnection   uc   =   url.openConnection();
                    InputStream   is   =   uc.getInputStream();

                    String name = p.attr("src").substring(p.attr("src").lastIndexOf("/")+1, p.attr("src").length());

                    String path = "/Users/zhangyang/Pictures/";

                    String date = TimeUtils.nowdate()+"/";
                    path = path+date;

                    File filepath = new File(path);
                    if(!filepath.exists() && !filepath.isDirectory()){
                        filepath.mkdirs();
                    }
                    if(name.endsWith(".png")||name.endsWith(".jpg")||name.endsWith(".ico")||name.endsWith(".gif")){
                        FileOutputStream   out   =   new   FileOutputStream(path+name);
                        LOGGER.info("写入中..."+path+name);
                        int   i1=0;
                        while   ((i1=is.read())!=-1)   {
                            out.write(i1);
                        }
                        
                        out.close();
                    }
                    is.close();
                }catch(Exception e){
                    LOGGER.error("HTMLPARSERutils------->", e);;
                    continue;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("HTMLPARSERutils------->", e);;
        }
    }

    /**
     * 读取网络图片到硬盘
     */
    public static HashMap<String,String> webPic2Disk(String weburl,String localpath,String date){
        HashMap<String,String> map  = new HashMap<String, String>();
        String path = "";
        String name = "";
                try{
                    URL   url   =   new   URL(weburl);
                    URLConnection   uc   =   url.openConnection();
                    InputStream   is   =   uc.getInputStream();

                    name = weburl.substring(weburl.lastIndexOf("/")+1, weburl.length());

                    path = localpath;//"/Users/zhangyang/Pictures/";

                    date = date+"/";

                    map.put("key", name);
                    path = path+date;

                    File filepath = new File(path);
                    if(!filepath.exists() && !filepath.isDirectory()){
                        filepath.mkdirs();
                    }

                    File file2 = new File(path+name);
                    map.put("localpath", path+name);
                    map.put("trimpath",(path+name).replace("E:\\bruce\\", ""));
                    
                    
                    if(file2.exists()){

                    }else{
                    	
                    	FileOutputStream   out   =   new   FileOutputStream(path+name);
                    	try{
                             LOGGER.info("写入中..."+path+name);
                             //5秒内图片没有写完  停止
                             int  timeoutMillis = 5;
                             long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;
                         	 while (System.currentTimeMillis() < maxTimeMillis) {
                         		int   i1=0;
                                while   ((i1=is.read())!=-1)   {
                                    out.write(i1);
                                }
                         	 }
                         	 
                    	}catch(Exception e){
                    		LOGGER.error("HTMLPARSERutils------->", e);
                    	}finally{
                    		 out.flush();
                    		 out.close();
                    	}
                       
                        
                       
                        
                    }
                    is.close();
                }catch(Exception e){
                    LOGGER.error("HTMLPARSERutils------->", e);
                }
        return map;
    }

    /**
     * 解析七牛URL为markdown格式
     * @return
     */
    public static List<String> url2markdown(){
        List<String> urilist = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        try {
                File in = new File("C:\\Users\\bruce\\Desktop\\111\\aaaa.html");

                Document doc = Jsoup.parse(in, "UTF-8", "");
                Elements notes   = doc.select("button[class=test_pic]");
                for(Element p : notes){

                String links = p.attr("cval");
                urilist.add(links);

                }

                HashSet<String> v = new HashSet<String>();
                v.addAll(urilist);

                list.addAll(v);
                for (String s:list) {
                    LOGGER.info(s);
                }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.error("HTMLPARSERutils------->", e);;
        }
        return list;
    }

    /**
     * 生成妹子图从N张动漫
     * @return
     */
    public static List<String> gegeMEIZITU(){
        List<String> meizi = new ArrayList<String>();

        for (int i = 1; i <= 18; i++) {
            String img = "/img/eq/tumblr_o"+i+".png";
            meizi.add(img);
        }
        return meizi;
    }

    /**
     * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
     * @return
     */
    public static Integer getRandomOne(List<?> list){

        Random ramdom =  new Random();
        int number = -1;
        int max = list.size();

        //size 为  10 ，取得类似0-9的区间数
        number = Math.abs(ramdom.nextInt() % max  );

        return number;

    }
     /**
      * 根据操作系统获取本地存储文件夹
     * @return
     */
    public static String getLocalFolderByOS(){

          String rs = "D:\\BZ\\soft\\";
          try {
              Properties prop = System.getProperties();
              String os = prop.getProperty("os.name");
              if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统

              }else{  //linux || mac osx
                  rs = "/mnt/apk/soft/";
              }
          } catch (Exception e) {
            // TODO: handle exception
              rs = "D:\\BZ\\soft\\";
          }

        return rs;

      }
    
    /**
     * 根据操作系统获取本地存储文件夹
    * @return
    */
   public static String getLocalFolderByOS(String retType){

         String rs = "E:\\bruce\\"+retType+"\\";
         try {
             Properties prop = System.getProperties();
             String os = prop.getProperty("os.name");
             if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统

             }else{  //linux || mac osx
                 rs = "/mnt/apk/"+retType+"/";
             }
         } catch (Exception e) {
           // TODO: handle exception
        	 LOGGER.error(e);
         }

       return rs;

     }

     /**
      * 根据操作系统获取本地存储文件夹Movies
     * @return
     */
    public static String getLocalFolderByOSMovies(){

          String rs = "D:\\BZ\\movies\\";
          try {
              Properties prop = System.getProperties();
              String os = prop.getProperty("os.name");
              if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统

              }else{  //linux || mac osx
                  rs = "/mnt/apk/movies/";
              }
          } catch (Exception e) {
            // TODO: handle exception
              rs = "D:\\BZ\\movies\\";
          }

        return rs;

      }
    
    
    
    /**
     * //妹子图获取
     * // http://huaban.com/from/meizitu.com/
     * @throws IOException
     */
    public static List<String> retMeizitu() throws IOException {
        List<String> list = new ArrayList<String>();
            try{
                Document doc = Jsoup.connect("http://huaban.com/from/meizitu.com/?inepv8xm&max=695360534&limit=300&wfl=1").get();
                //src
                Elements pics = doc.select("img");
//                int index = 0;
                for(Element p : pics){
//                    index++;
                    list.add(p.attr("src"));
                    //LOGGER.info(index+ p.attr("src"));
                }
                list.remove(list.size()-1);
                //session.setAttribute("meizutu", list);
            }catch (Exception e) {
                LOGGER.error("HTMLPARSERutils------->", e);;
            }
        return list;
    }

    
    public static int geneViewNum(){
    	int max=59000;
    	int min=1000;
    	Random random = new Random();
    	int s = random.nextInt(max)%(max-min+1) + min;
    	LOGGER.info(s);
		return s;
    }
	
    
    /**
     * 字符串超长截取
     * @param s
     * @param length
     * @return
     * @throws Exception
     */
    public static String CutString(String s, int length){
        String rs = "";
        try {

            byte[] bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (; i < bytes.length && n < length; i++)
            {
                // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                if (i % 2 == 1)
                {
                    n++; // 在UCS2第二个字节时n加1
                }
                else
                {
                    // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    if (bytes[i] != 0)
                    {
                        n++;
                    }
                }
            }
            // 如果i为奇数时，处理成偶数
            if (i % 2 == 1)

            {
                // 该UCS2字符是汉字时，去掉这个截一半的汉字
                if (bytes[i - 1] != 0)
                    i = i - 1;
                // 该UCS2字符是字母或数字，则保留该字符
                else
                    i = i + 1;
            }

            rs = new String(bytes, 0, i, "Unicode");
            if(!rs.equals(s)){
                rs = rs +"...";
            }
        } catch (Exception e) {
            // TODO: handle exception
            rs = s;

        }
        return rs;
    }

    
    
    /**
     *	=====================================tools结束================================================================
     */
    
      public static void main(String[] args) {
            try {
                //retMeizitu();
//                retTodayEq();
//                List<Map<String, String>> retEQArticle = retEQArticle();
//                LOGGER.info(retEQArticle.size());
//                readPic2Disk();
//                retV2Romeo();
//                url2markdown();
//                String cutString = CutString("荒烟蔓草的年头，就连分手都很沉默", 12);
//                LOGGER.info(cutString);
//                retSoft(1);
//                webPic2Disk("http://www.sdifenzhou.com/wp-content/uploads/2016/02/Fantastical2.jpg", "D:\\BZ\\soft\\" );

//                retMovies(78);
//                String url = "http://www.vip588660.com/page/"+77+"/";
////                url = "http://northpark.cn/soft/mac/page77";
//                String pickData = pickData(url);
//                LOGGER.info(pickData);
//            	 String tag  =  "1,2,3,4,5,6,";
//            	 if(tag.endsWith(",")){
//                 	tag  =  tag.substring(0, tag.length()-1);
//                 	LOGGER.info(tag);
//                 }
            	
            
            	
//            	retPoem(26);
//            	LOGGER.info(MD5Utils.encoding("速度与激情8"));
//            	retEQArticle();
//            	retCoupon(1, BC_Constant.Coupon_VPS_7);
            	retCaiMai(1);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                LOGGER.error("HTMLPARSERutils------->", e);;
            }
        }

}