package com.bruce.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bruce.action.SpiderAction;

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
    
   
    public static void main(String args[]){
    	try {

    		retQuan();
    		

    	} catch (Exception e) {
    		// TODO: handle exception
    			e.printStackTrace();
    	}


    	
    	        
    }

	/**
	 * 爬虫获取红包添加到缓存
	 * @throws IOException
	 */
	public static void retQuan() throws IOException {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Document doc = Jsoup.connect("http://quan.liuguofa.com/").get();

		Elements tr   = doc.select("tr");

		for(Element p : tr){
			
			HashMap<String, String> map =new HashMap<String, String>();
			Elements td = p.select("td");
			
			Element url = td.get(0);
			String from = td.get(1).html();
			String time = td.get(2).html();
			String authorIP = td.get(3).html();
			
			Elements al = url.select("a");
			String path = al.attr("href");
			String title = al.attr("title");
			
			
			map.put("from", from);
			map.put("publishtime", time);
			map.put("authorIP", authorIP);
			map.put("path", path);
			map.put("title", title);
			map.put("id", SpiderAction.getInt6()+"");
			
			System.out.println(path+"\t\r"+from+"\t\r"+time+"\t\r"+authorIP+"\t\r"+title+"\t\r"+"-----------------");
			
			list.add(map);
			

		}
		
		//处理List集合
		for (int i = 0; i < list.size(); i++) {
			String path = list.get(i).get("path");
			if(StringUtils.isNotEmpty(path)){
				String url = "http://quan.liuguofa.com"+path;
				Document doc_mt = Jsoup.connect(url).get();
				
				Elements iframe = doc_mt.select("iframe");
				String path_mt =  iframe.attr("src");
				list.get(i).put("path_mt", path_mt);
				
				list.get(i).put("addtime",TimeUtils.nowTime());
			}
		}
		
		list.remove(0);
		
		//更新redis缓存
		System.out.println(list);
		JedisUtil.remove("B_quan");
		JedisUtil.addList("B_quan",list);
	}

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
    
    
}

