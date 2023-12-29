//package cn.northpark.ret;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.tomcat.util.security.MD5Encoder;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.CollectionUtils;
//
//import com.google.common.base.Strings;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//
//import cn.northpark.Application;
//import cn.northpark.mapper.KnowledgeMapper;
//import cn.northpark.mapper.KnowledgeMessageBoardMapper;
//import cn.northpark.model.Knowledge;
//import cn.northpark.utils.HttpGetUtils;
//import cn.northpark.utils.TimeUtils;
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Slf4j
//public class RetArticle {
//	
//	@Autowired 
//	KnowledgeMapper knowMapper;
//	
//	@Autowired 
//	KnowledgeMessageBoardMapper mbMapper;
//	
//	//640页
//	
//	@Test
//	public void ret() {
//		
//		
//		try {
//
//			log.info("爬虫文章代码 开始==============start="+TimeUtils.getNowTime());
//			Map<String,String> map = null;
//
//
//
//			for (int k = 1; k <=640; k++) {
//				try {
//
//					List<Map<String, String>> list = retArticle(k);
//
//
//					if(!CollectionUtils.isEmpty(list)){
//						for (int i = 0; i < list.size(); i++) {
//							try {
//								map = list.get(i);
//								
//								String title      = map.get("title"      ) ;
//			                    String aurl       = map.get("aurl"       ) ;
//			                    String brief      = map.get("brief"      ) ;
//			                    String brief_img  = map.get("brief_img"  ) ;
//			                    String date       = map.get("date"       ) ;
//			                    String article    = map.get("article"    ) ;
//			                    String tag        = map.get("tag"        ) ;
//			                    String code       = map.get("code"       ) ;
//			                    String tagcode    = map.get("tagcode"    ) ;
//			                    String path       = map.get("path"       ) ;
//			                    String view_times = map.get("view_times" ) ;
//			                    String retcode    = map.get("retcode"    ) ;
//
//								//是不存在的电影
//								int flag = knowMapper.countByRetcode(retcode);
//
//								if(flag<=0){
//
//									Knowledge know = new Knowledge();
//									know.setBrief(brief);
//									know.setBriefImg(brief_img);
//									know.setContent(article);
//									know.setPath(path);
//									know.setPostDate(date);
//									know.setRetCode(retcode);
//									know.setReturl(aurl);
//									know.setTags(tag);
//									know.setTagsCode(tagcode);
//									know.setTitle(title);
//									if(!Strings.isNullOrEmpty(view_times)) know.setViewTimes(Long.valueOf(view_times));
//									knowMapper.insert(know);
//								}
//							} catch (Exception e) {
//								// TODO: handle exception
//								continue;
//							}
//
//						}
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//					continue;
//				}
//
//
//				try {
//					Thread.sleep(1000*3);
//					log.info("第"+k+"页================");
//				} catch (InterruptedException e) {
//					// TODO Auo-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//
//
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		 log.info("爬虫文章代码==============结束="+TimeUtils.getNowTime());
//		
//	}
//	
//	
//	  /**
//     * 爱情守望者
//     * 爬取1页的mac软件内容
//     */
//    public static List<Map<String, String>> retArticle(Integer index) {
//        // TODO Auto-generated method stub
//        List<Map<String, String>> list = Lists.newArrayList();
//        HashMap<String, String> map = null;
//        try {
//
//
//            String initUrl = "http://share.weimo.info/page/" + index;
//            final String dataResult = HttpGetUtils.getDataResult(initUrl);
//
//            System.out.println(dataResult);
//            Document doc = Jsoup.parse(dataResult, initUrl);
//
//            Elements soft11 = null;
//            //取得一页内容梗概
//            soft11 = doc.select("article.content-card");
//
////             String baseUrl=initUrl;
////
////             //获取所有我需要格式匹配的链接
////             HashMap<String, String> allUrls = StartImg.getAllUrls(dataResult,baseUrl);
////             
////             Set<String> keySet = allUrls.keySet();
//
//            for (Element e : soft11) {
//                try {
//
//                	//URL
//                    String url = e.select("a.card-image").get(0).attr("href");
//
//                    //TITLE
//                    String title = e.select("div.card-title").select("h1").text();
//                    
//                    String retcode = MD5Encoder.encode(title.getBytes());
//
//                    //BRIEF
//                    String brief = e.select("div.card-body-text").html();
//                    
//                    //time
//                    String date = e.select("time.post-date").text();
//                    
//                    //Tag
//                    Elements footer = e.select("footer.card-footer");
//					String tagname = footer.select("a").text();
//                    
//                    //view_time
//                    String view_time = "";
//                    footer.select("time").remove();
//                    footer.select("a").remove();
//                    view_time = footer.text().replace(".","").replace("次", "").replace("浏览", "").replace("·", "").replace(" ", "");
//                    
//                    //获取全文的内容
//                    String dataResult1 = HttpGetUtils.getDataResult(url);
//
//                    /*System.out.println(dataResult1);*/
//                    Document parse = Jsoup.parse(dataResult1, url);
//
//                    //唯一码
//                    String code = url.substring(url.lastIndexOf("archives/") + 8, url.length()).replaceAll("/", "");
//
//
//                    //计算标签编码、
//                    String tagcode = "005";
//                    if (tagname.contains("课程") ) {
//                    	tagcode = "classhare";
//                    	tagname = "课程分享";
//                    } else if (tagname.contains("杂谈") ) {
//                    	tagcode = "treehole";
//                    	tagname = "树洞杂谈";
//                    } else if (tagname.contains("书籍")) {
//                    	tagcode = "bookshare";
//                    	tagname = "书籍分享";
//                    } else if (tagname.contains("软件") || tagname.contains("网络")) {
//                    	tagcode = "softshare";
//                    	tagname = "软件分享";
//                    } else if (tagname.contains("其他")) {
//                    	tagcode = "others";
//                    	tagname = "其他";
//                    } 
//                    log.info("tagcode====================" + tagcode);
//                    
//                    //brief_img
//                    String brief_img = "";
//
//                    //正文
//                    String article = "";
//
//                    //下载地址
//                    String path = "";
//
//
//                    Element articles = parse.select("div.content-post-body").get(0);
//
//                    //处理征文图像
//                    Elements imgs = articles.select("img");
//                    for (int j = 0; j < imgs.size(); j++) {
//                    	try {
//                    		String weburl = imgs.get(j).attr("src");
//                    		//web图片上传到七牛
//
//                    		//-------------开始--------------------------------
//
////                    		HashMap<String, String> map22 = webPic2Disk(weburl, getLocalFolderByOS() ,date);
////
////                    		String rs = QiniuUtils.getInstance().upload(map22.get("localpath"), "soft/"+date+"/"+map22.get("key"));
//
//                    		//-------------结束--------------------------------
////                    		if(!Strings.isNullOrEmpty(rs)){
////                    			imgs.get(j).attr("src", rs);
////                    		}
////                    		imgs.get(j).attr("alt", title);//给图像添加元素标记，便于搜索引擎的记录
//                    		
//                    		if(j==0) brief_img = weburl;
//                    	} catch (Exception e1) {
//                    		// TODO: handle exception
//                    		log.error("ret pic exception===>" + e1.toString());
//                    		continue;
//                    	}
//
//                    }
//
//
//
//                    //处理下载地址
//                    StringBuilder sb_path = new StringBuilder();
//                    //执行2次抓取下载地址
//                    Elements last = articles.select("p");
//                    if(!CollectionUtils.isEmpty(last)) {
//
//                    	for (Element element : last) {
//                    		String outerHtml = element.outerHtml();
//                    		if(outerHtml.contains("链接")||outerHtml.contains("pan.baidu")||outerHtml.contains("密码")||outerHtml.contains("lanzous")) {
//                    			System.out.println("========================");
//
//                    			System.out.println("===========是=============");
//                    			String download = outerHtml;
//                    			System.out.println(download);
//                    			sb_path.append(download);
//                    			//删除最后的路径元素
//                    			element.remove();
//                    		}else {
//                    			System.out.println("========================");
//
//                    			System.out.println("===========否=============");
//                    		}
//                    	}
//
//
//                    }
//
//                    System.out.println("sb_path===================>"+sb_path.toString());
//                    path = sb_path.toString();
//
//                    //设置正文
//                    article = articles.html();
//                    System.out.println("===================================================================================================");
//                    System.out.println("正文内容===================>"+articles.html());
//                    System.out.println("===================================================================================================");
//
//
//                    map = Maps.newHashMap();
//                    map.put("title", title);
//                    map.put("aurl", url);
//                    map.put("brief", brief);
//                    map.put("brief_img", brief_img);
//                    map.put("date", date);
//                    map.put("article", article);
//                    map.put("tag", tagname);
//                    map.put("code", code);
//                    map.put("tagcode", tagcode);
//                    map.put("path", path);
//                    map.put("view_times", view_time);
//                    map.put("retcode", retcode);
//                    
//                    list.add(map);
//
//                    //                    }
//                } catch (Exception e2) {
//                	 log.error("HTMLPARSERutils------->", e2);
//                    continue;
//                }
//
//            }
//            System.out.println(index + "页爬取完毕！ the end===============================================================================================");
//
//        } catch (Exception e) {
//            log.error("HTMLPARSERutils------->", e);
//        }
//
//        return list;
//    }
//    
//    
//    /**
//     * 读取网络图片到硬盘
//     */
//    public static HashMap<String, String> webPic2Disk(String weburl, String localpath, String date) {
//        HashMap<String, String> map = new HashMap<String, String>();
//        String path = "";
//        String name = "";
//        try {
//
//            //拼接名字
//
//            name = weburl.substring(weburl.lastIndexOf("/") + 1, weburl.length());
//
//
//            date = date + "/";
//
//            //拼接路径
//            path = localpath + date;
//            
//            
//
//            //写入文件
//            String local_holl_path = HttpGetUtils.downloadHttpUrl(weburl, path, name);
//
//            map.put("key", name);
//            map.put("localpath", local_holl_path);
//            map.put("trimpath", local_holl_path.replace("E:\\bruce\\", ""));
//
//
//        } catch (Exception e) {
//            log.error("HTMLPARSERutils------->", e);
//        }
//        return map;
//    }
//    
//    /**
//     * 根据操作系统获取本地存储文件夹
//     *
//     * @return
//     */
//    public static String getLocalFolderByOS() {
//
//        String rs = "D:\\BZ\\soft\\";
//        try {
//            Properties prop = System.getProperties();
//            String os = prop.getProperty("os.name");
//            if (os.startsWith("win") || os.startsWith("Win")) {// windows操作系统
//
//            } else {  //linux || mac osx
//                rs = "/mnt/apk/soft/";
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            rs = "D:\\BZ\\soft\\";
//        }
//
//        return rs;
//
//    }
//
//}
//
