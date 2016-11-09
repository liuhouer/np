package cn.northpark.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <p>
 * Title: buci.com
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author bruce
 * @version 1.0
 */

public class HTMLParserUtil
{

	/**
	 * 按字节长度截取字符串(支持截取带HTML代码样式的字符串)
	 * 
	 * @param param
	 *            将要截取的字符串参数
	 * @param length
	 *            截取的字节长度
	 * @param end
	 *            字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	@SuppressWarnings("unchecked")
	public String subStringHTML(String param, int length, String end)
	{
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++)
		{
			temp = param.charAt(i);
			if (temp == '<')
			{
				isCode = true;
			} else if (temp == '&')
			{
				isHTML = true;
			} else if (temp == '>' && isCode)
			{
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML)
			{
				isHTML = false;
			}

			if (!isCode && !isHTML)
			{
				n = n + 1;
				// UNICODE码字符占两个字节
				if ((temp + "").getBytes().length > 1)
				{
					n = n + 1;
				}
			}

			result.append(temp);
			if (n >= length)
			{
				break;
			}
		}
		result.append(end);
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);

		List endHTML = new ArrayList();

		while (m.find())
		{
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--)
		{
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}

		return result.toString();
	}
	
	public static String replaceHtml(String html){
		try{
			String content ="";
			String regEx="<.+?>"; //表示标签
		    Pattern p=Pattern.compile(regEx);
		    Matcher m=p.matcher(html);
		    content = m.replaceAll("");
		    return content;
		}catch (Exception e) {
			return "";
		}
	}
	
	public String replaceHtmAndTrim(String html){
		try{
			String content ="";
			String regEx="<.+?>"; //表示标签
			Pattern p=Pattern.compile(regEx);
			Matcher m=p.matcher(html);
			content = m.replaceAll("");
			//处理空格
			content=  content.replaceAll(">\\s*<", "><").replaceAll("\n|\r|\rn|\nr|\t", "");
			return content;
		}catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 取出html中除img，p，br以外标签。
	 */
	public String htmlTextSimple(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		htmlStr = htmlStr.replaceAll("(?is)<!DOCTYPE.*?>", "");
		htmlStr = htmlStr.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
		htmlStr = htmlStr.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		htmlStr = htmlStr.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
		htmlStr = htmlStr.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		htmlStr = htmlStr.replaceAll("(?is)<(?!img|br|p|/p).*?>", "");          // 保留img,br,p标签
		textStr = htmlStr;
		return textStr;
	}
	
	/**
	 * 取出html中除img，p，br以外标签。
	 */
	public String removedHtmlTagAndTrim(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		htmlStr = htmlStr.replaceAll("(?is)<!DOCTYPE.*?>", "");
		htmlStr = htmlStr.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
		htmlStr = htmlStr.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		htmlStr = htmlStr.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
		htmlStr = htmlStr.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		htmlStr = htmlStr.replaceAll("(?is)<.*?>", "");          // 保留img,br,p标签
		textStr = htmlStr;
		return textStr;
	}
	
	/**
	 * 将html按指定tag标签截取
	 */
	public void splitByTag(String tag){
		Pattern pattern = Pattern.compile("<img[^>]*>");
		Matcher m = pattern.matcher(tag);
		while(m.find()){
			for(int i=0;i<m.groupCount();i++){
				 System.out.println(m.group(i));
			}
		}
	}

	
	 public static String GetNoteContent(String html) {  
	        //String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";  
	        String ss = ">[^<]+<";  
	        String temp = null;  
	        Pattern pa = Pattern.compile(ss);  
	        Matcher ma = null;  
	        ma = pa.matcher(html);  
	        String result = null;  
	        while(ma.find()){  
	            temp = ma.group();  
	            if(temp!=null){  
	            	System.out.println(temp);
	                if(temp.startsWith(">")){  
	                    temp = temp.substring(1);  
	                }  
	                if(temp.endsWith("<")){  
	                    temp = temp.substring(0, temp.length()-1);  
	                }  
	                if(!temp.equalsIgnoreCase("")){  
	                    if(result==null){  
	                        result = temp;  
	                    }  
	                    else{  
	                        result+="____"+temp;  
	                    }  
	                }  
	            }  
	        }  
	        return result;  
	    }  
      
    public static String GetContent(String html) {  
        //String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";  
        String ss = ">[^<]+<";  
        String temp = null;  
        Pattern pa = Pattern.compile(ss);  
        Matcher ma = null;  
        ma = pa.matcher(html);  
        String result = null;  
        while(ma.find()){  
            temp = ma.group();  
            if(temp!=null){  
                if(temp.startsWith(">")){  
                    temp = temp.substring(1);  
                }  
                if(temp.endsWith("<")){  
                    temp = temp.substring(0, temp.length()-1);  
                }  
                if(!temp.equalsIgnoreCase("")){  
                    if(result==null){  
                        result = temp;  
                    }  
                    else{  
                        result+="____"+temp;  
                    }  
                }  
            }  
        }  
        return result;  
    }  
      
    public static String GetLabel(String html) {  
        //String html = "<ul><li>1.hehe</li><li>2.hi</li><li>3.hei</li></ul>";  
        String ss = "<[^>]+>";  
        String temp = null;  
        Pattern pa = Pattern.compile(ss);  
        Matcher ma = null;  
        ma = pa.matcher(html);  
        String result = null;  
        while(ma.find()){  
            temp = ma.group();  
            if(temp!=null){  
                if(temp.startsWith(">")){  
                    temp = temp.substring(1);  
                }  
                if(temp.endsWith("<")){  
                    temp = temp.substring(0, temp.length()-1);  
                }  
                if(!temp.equalsIgnoreCase("")){  
                    if(result==null){  
                        result = temp;  
                    }  
                    else{  
                        result+="____"+temp;  
                    }  
                }  
            }  
        }  
        return result;  
    }  
    
    
    
    /**
     * 
     * 获取class元素内容
     * "div[class=clearfix hidden]"
     * http://www.caimai.cc/story/page7/
     * @param url
     * @param classname
     * @return List<String>
     */
    public static List<String> getClassCont(String url,String classname){
    	List<String> list = new ArrayList<String>();
    	try {
    		Document doc = Jsoup.connect(url).get();
    		
    		String title = doc.title();
    		System.out.println("title ||---  "+title);
    		Elements notes   = doc.select(classname);
    		
    		for(Element p : notes){

    				   String note = p.html();
    				   list.add(note);
    				   //String note2 = p.text();
    				   System.out.println(note);
    				   //System.out.println(note2);

    		}
    		
    		
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    	}
		return list;

    }
    
    /**
     * 
     * 获取图片地址的集合
     * "div[class=clearfix hidden]"
     * http://www.caimai.cc/story/page7/
     * @param url
     * @param classname
     * @return List<String>
     */
    public static List<String> getImgUrl(String url){
    	List<String> list = new ArrayList<String>();
    	try {
    		Document doc = Jsoup.connect(url).get();
    		
    		String title = doc.title();
    		System.out.println(title);
    		Elements notes   = doc.select("img");
    		
    		for(Element p : notes){

    			String uri = p.absUrl("src");
    			System.out.println(uri);
    			list.add(uri);

    		}
    		
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    	}
		return list;

    }
    
   

	/**
	 * 爬虫获取红包添加到缓存
	 * @throws IOException
	 */
//	public static void retQuan() throws IOException {
//		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		Document doc = Jsoup.connect("http://quan.liuguofa.com/").get();
//
//		Elements tr   = doc.select("tr");
//
//		for(Element p : tr){
//			
//			HashMap<String, String> map =new HashMap<String, String>();
//			Elements td = p.select("td");
//			
//			Element url = td.get(0);
//			String from = td.get(1).html();
//			String time = td.get(2).html();
//			String authorIP = td.get(3).html();
//			
//			Elements al = url.select("a");
//			String path = al.attr("href");
//			String title = al.attr("title");
//			
//			
//			map.put("from", from);
//			map.put("publishtime", time);
//			map.put("authorIP", authorIP);
//			map.put("path", path);
//			map.put("title", title);
//			map.put("id", SpiderAction.getInt6()+"");
//			
//			System.out.println(path+"\t\r"+from+"\t\r"+time+"\t\r"+authorIP+"\t\r"+title+"\t\r"+"-----------------");
//			
//			list.add(map);
//			
//
//		}
//		
//		//处理List集合
//		for (int i = 0; i < list.size(); i++) {
//			String path = list.get(i).get("path");
//			if(StringUtils.isNotEmpty(path)){
//				String url = "http://quan.liuguofa.com"+path;
//				Document doc_mt = Jsoup.connect(url).get();
//				
//				Elements iframe = doc_mt.select("iframe");
//				String path_mt =  iframe.attr("src");
//				list.get(i).put("path_mt", path_mt);
//				
//				list.get(i).put("addtime",TimeUtils.nowTime());
//			}
//		}
//		
//		list.remove(0);
//		
//		//更新redis缓存
//		System.out.println(list);
//		JedisUtil.remove("B_quan");
//		JedisUtil.addList("B_quan",list);
//	}

	/**
	 * @throws IOException
	 */
	public static List<String> readsong() throws IOException {
		
		List<String> urilist = new ArrayList<String>();
		for (int i = 1; i <= 6; i++) {
			File in = new File("/Users/zhangyang/Downloads/a"+i+".html");

			Document doc = Jsoup.parse(in, "UTF-8", ""); 
//			String title = doc.title();
//			System.out.println(title);
			Elements notes   = doc.select("a");
			for(Element p : notes){

					   String links = p.attr("href");
					   //System.out.println(links);
					   if(links.startsWith("http://www.xukaiqiang.com/article/")){
						   urilist.add(links);
					   }

			}
		}
		
			HashSet<String> v = new HashSet<String>();
			v.addAll(urilist);
			List<String> list = new ArrayList<String>();
			list.addAll(v);
			for (String s:list) {
				System.out.println(s);
			}
			return list;
			
			

	}
	//情圣文章分析------------------------------------------------------------------------------------------------
	
	//	<div class="info">
	//    <div class="title">
	//        <a title="情圣大卫：怎么判断她是不是喜欢你？" href="/yuedu/18984480.html" target="_blank">情圣大卫：怎么判断她是不是喜欢你？</a>
	//    </div>
	//    <div class="summary">
	//    
	//        <div class="pic">
	//            <div class="picinner">
	//                <a title="情圣大卫：怎么判断她是不是喜欢你？" href="/yuedu/18984480.html" target="_blank">
	//                    <img src="http://awb.img1.xmtbang.com/wechatmsg2015/article201503/20150325/thumb/5b2201bdb5c74368a6f8632c095a5d96.jpg" onerror="this.parentNode.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode.parentNode)" original="http://awb.img1.xmtbang.com/wechatmsg2015/article201503/20150325/thumb/5b2201bdb5c74368a6f8632c095a5d96.jpg" alt="情圣大卫：怎么判断她是不是喜欢你？" style="display: inline;">
	//                </a>
	//            </div>
	//        </div>
	//      
	//        <div class="text">女人的好感信号，你分辨的出来吗？</div>
	//    </div>
	//    <div class="clear h"></div>
	//    <div class="ifooter">
	//        <span class="text">2015-03-24</span>
	//        <span class="fav-operate">
	//            <a class="favarticle " href="javascript:;" data-articleid="18984480">收藏，稍后阅读</a>
	//            <a class="unfavarticle undis" href="http://u.aiweibang.com/fav/article" target="_blank">已收藏</a>
	//        </span>
	//    </div>
	//</div>
	
	
	
	/**
	 * 爬虫获取情圣的文章
	 * @throws IOException
	 */
	public static List<Map<String,String>> retEQArticle() throws IOException {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(int i=1;i<=2;i++){
			try{
			Document doc = Jsoup.connect("http://www.aiweibang.com/u/10608?page="+i).get();
			Elements info   = doc.select("div[class=info]");
			for(Element p : info){
				
				HashMap<String, String> map =new HashMap<String, String>();
				//标题
				Elements titles = p.select("div[class=title]");
				
				String title = titles.get(0).text();
				title = title.replace("大卫", "");
				String aurl = "http://www.aiweibang.com"+titles.get(0).select("a").get(0).attr("href");
				
				
				
//				Elements dates = p.select("span[class=text]");
//				
//				String date = dates.get(0).text();
				
				//文章
				Document doc_ = Jsoup.connect(aurl).get();
				Elements articles = doc_.select("div[class=innertext]");
				String article = articles.get(0).html();
				article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "").replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "").replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "").replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "");
				
				//日期
				Elements dates = doc_.select("span[class=activity-meta no-extra]");
				String date = dates.get(0).text();
				
//				if(!(date.equals("2016-07-20")) && !(date.equals("2016-07-23"))&& !(date.equals("2016-07-23"))){
				if(!(date.equals("2016-08-01")) ){
					continue;
				}
				
				//标题图
				Elements pics = doc_.select("img");
				String img = pics.get(0).attr("src");
				
				//简介
				Elements texts = p.select("div[class=text]");
				String brief = texts.get(0).text();
				brief = brief.replace("大卫", "");
				
				map.put("title", title);
				map.put("aurl", aurl);
				map.put("img", img);
				map.put("brief", brief);
				map.put("date", date);
				map.put("article", article);
				//System.out.println(title+"\t\r"+aurl+"\t\r"+img+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");
				
				list.add(map);
				

				}
			}catch (Exception e) {
				continue;
			}
		}
		return list;
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
				int index = 0;
				for(Element p : pics){
					index++;
					list.add(p.attr("src"));
					//System.out.println(index+ p.attr("src"));
				}
				list.remove(list.size()-1);
				//session.setAttribute("meizutu", list);
			}catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}
	
	
	/**
	 * 读取网络图片到硬盘
	 */
	public static void readPic2Disk(){
		try {
    		//retMeizitu();
    		
    		Document doc = Jsoup.connect("http://orenogazoo.tumblr.com/").get();
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
			    		System.out.println("写入中..."+path+name);
			    		int   i1=0; 
			    		while   ((i1=is.read())!=-1)   { 
			    			out.write(i1); 
			    		} 
		    		}
		    		is.close();
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					System.out.println(s);
				}
				
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
		
		/**
		 * 爬取5年来。。。。。。。。。
		 */
		public static Map<Integer, String> retV2Romeo() {
			// TODO Auto-generated method stub
			HashMap<Integer, String> map =new HashMap<Integer, String>();
			try{
				
					Document doc = Jsoup.connect("http://www.yi-see.com/art_18165_584.html").get();
					
					Elements as  = doc.select("a");
					
					//catch the map <"index",article>
					for (int j = 0; j < as.size(); j++) {
						System.out.println(as.get(j).html());
						if(as.get(j).html().contains("节")){
							String href  =  as.get(j).attr("href");
							Document doc2 = Jsoup.connect("http://www.yi-see.com/"+href).get();
							Elements elements =doc2.select("div[class=ART]");
							String article = elements.get(0).html();
							map.put(Integer.parseInt(as.get(j).html().replace("第", "").replace("节", "")), article);
						}
					}
					
					
					
					//sort the map
					Object[]   key   =     map.keySet().toArray();   
					Arrays.sort(key);  

					String filePath = "C:\\Users\\bruce\\Downloads\\Text\\5yearv2romeo.txt";
					FileWriter writer = new FileWriter(filePath, true);
					System.out.println("写入start....");
					try {
						
							for   (int   i   =   0;   i   <   key.length;   i++)   {   
									
										//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
										
										writer.write(map.get(key[i]));
										writer.write("---------------------------------------"+"<br>");
										
							}   
							
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						writer.close();
					}
				
				 

				}catch(Exception e){
					e.printStackTrace();
				}
			
			System.out.println("写入end....");
			return map;	
		}
  
	/**
	 * 爬取今日情圣的文章
	 */
	public static Map<String, String> retTodayEq() {
		// TODO Auto-generated method stub
				HashMap<String, String> map =new HashMap<String, String>();
		try{
				Document doc = Jsoup.connect("http://www.aiweibang.com/u/10608?page=1").get();
				Elements info   = doc.select("div[class=info]");
			    Element p  = info.get(0);
				
				//标题
				Elements titles = p.select("div[class=title]");
				
				String title = titles.get(0).text();
				title = title.replace("大卫", "");
				String aurl = "http://www.aiweibang.com"+titles.get(0).select("a").get(0).attr("href");
				
				
				
//				Elements dates = p.select("span[class=text]");
//				
//				String date = dates.get(0).text();
				
				//文章
				Document doc_ = Jsoup.connect(aurl).get();
				Elements articles = doc_.select("div[class=innertext]");
				String article = articles.get(0).html();
				article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "").replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "").replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "").replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "");
				
				//日期
				Elements dates = doc_.select("span[class=activity-meta no-extra]");
				String date = dates.get(0).text();
				
				
				
				//标题图
	//			Elements pics = doc_.select("img");
	//			String img = pics.get(0).attr("src");
				
				//简介
				Elements texts = p.select("div[class=text]");
				String brief = texts.get(0).text();
				brief = brief.replace("大卫", "");
				
				map.put("title", title);
				map.put("aurl", aurl);
				map.put("brief", brief);
				map.put("date", date);
				map.put("article", article);
				System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");
				
				
				

			}catch(Exception e){
				e.printStackTrace();
			}
		
		return map;	
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
	 * 爬取1页的内容
	 */
	public static List<Map<String, String>> retSoft() {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try{
				Document doc = Jsoup.connect("http://www.sdifenzhou.com/page/1/").get();
				Elements articles   = doc.select("article");
				if(!articles.isEmpty()){
					for (int i = 0; i < articles.size(); i++) {
						HashMap<String, String> map =new HashMap<String, String>();
						Element article  = articles.get(i);
						
						//标题
						Elements titles = article.select("h1[class=entry-title]");
						
						String title = titles.get(0).text();
						System.out.println("title===================="+title);
						
						String aurl = titles.get(0).select("a").get(0).attr("href");
						
						
						//code
						String code = article.attr("id");
						
						System.out.println("code===================="+code);
						
						
						//文章
						StringBuilder sb = new StringBuilder();
						
						Document doc_ = Jsoup.connect(aurl).get();
						Elements article_alls = doc_.select("div[class=entry-content] p");
						if(!article_alls.isEmpty()){
							for (int i1 = 0; i1 < article_alls.size(); i1++) {
								String p1 = article_alls.get(i1).html();
								if(!p1.contains("本文链接") && !p1.contains("转载声明") ){
									sb.append("<p>"+p1+"</p>");
								}
							}
						}
					
						String text = sb.toString().replaceAll("小子", "小布");
						System.out.println("article============="+text);
						
						
						//日期
						 Elements dates = doc.select("time[class=entry-date]");
						String date = dates.get(0).text();
						date=date.replaceAll(" ", "");
						date=date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
						System.out.println("date===================="+date);
						
						
						//简介
						Elements briefs = doc.select("div[class=entry-content]");
						
						briefs = briefs.get(0).select("p");
						StringBuilder sb2 = new StringBuilder();
						String brief = briefs.get(0).html();
						
						if(!briefs.isEmpty()){
							for (int i1 = 0; i1 < briefs.size(); i1++) {
								String p1 = briefs.get(i1).html();
								if(!p1.contains("继续阅读") ){
									sb2.append("<p>"+p1+"</p>");
								}
							}
						}
						brief = sb2.toString().replaceAll("小子", "小布");
						
						System.out.println("brief===================="+brief);
						
						map.put("title", title);
						map.put("aurl", aurl);
						map.put("brief", brief);
						map.put("date", date);
						map.put("article", text);
						list.add(map);
					}
				}
				
			    
//				System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");
				
				
				

			}catch(Exception e){
				e.printStackTrace();
			}
		
		return list;	
	}
	
	
	/**
	 * 爬取一篇软件
	 */
	public static Map<String, String> retSoftList() {
		// TODO Auto-generated method stub
				HashMap<String, String> map =new HashMap<String, String>();
		try{
				Document doc = Jsoup.connect("http://www.sdifenzhou.com/page/2/").get();
				Elements articles   = doc.select("article");
			    Element article  = articles.get(0);
				
				//标题
				Elements titles = article.select("h1[class=entry-title]");
				
				String title = titles.get(0).html();
				System.out.println("title===================="+title);
				
				String aurl = titles.get(0).select("a").get(0).attr("href");
				
				
				//文章
				StringBuilder sb = new StringBuilder();
				
				Document doc_ = Jsoup.connect(aurl).get();
				Elements article_alls = doc_.select("div[class=entry-content] p");
				if(!article_alls.isEmpty()){
					for (int i = 0; i < article_alls.size(); i++) {
						String p1 = article_alls.get(i).html();
						if(!p1.contains("本文链接") && !p1.contains("转载声明") ){
							sb.append("<p>"+p1+"</p>");
						}
					}
				}
			
				String text = sb.toString().replaceAll("小子", "小布");
				System.out.println("article============="+text);
				
				
				//日期
				 Elements dates = doc.select("time[class=entry-date]");
				String date = dates.get(0).text();
				date=date.replaceAll(" ", "");
				date=date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
				System.out.println("date===================="+date);
				
				
				//简介
				Elements briefs = doc.select("div[class=entry-content]");
				
				briefs = briefs.get(0).select("p");
				StringBuilder sb2 = new StringBuilder();
				String brief = briefs.get(0).html();
				
				if(!briefs.isEmpty()){
					for (int i = 0; i < briefs.size(); i++) {
						String p1 = briefs.get(i).html();
						if(!p1.contains("继续阅读") ){
							sb2.append("<p>"+p1+"</p>");
						}
					}
				}
				brief = sb2.toString().replaceAll("小子", "小布");
				
				System.out.println("brief===================="+brief);
				
				map.put("title", title);
				map.put("aurl", aurl);
				map.put("brief", brief);
				map.put("date", date);
				map.put("article", text);
//				System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");
				
				
				

			}catch(Exception e){
				e.printStackTrace();
			}
		
		return map;	
	}
	
	
	  public static void main(String[] args) {
	    	try {
	    		//retMeizitu();
	    		//retTodayEq();
//	    		List<Map<String, String>> retEQArticle = retEQArticle();
//	    		System.out.println(retEQArticle.size());
//	    		readPic2Disk();
//	    		retV2Romeo();
//	    		url2markdown();
//	    		String cutString = CutString("荒烟蔓草的年头，就连分手都很沉默", 12);	    
//	    		System.out.println(cutString);
	    		retSoft();
	    		
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    
}

