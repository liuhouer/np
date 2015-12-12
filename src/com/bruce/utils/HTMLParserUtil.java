package com.bruce.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: Neeu.com
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
 * @author not attributable
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

	public static void main(String args[])
	{
		HTMLParserUtil parserUtil = new HTMLParserUtil();
		String str = "<span>asfdasdfasdfadsfa" + "sdfasdfasdfadsfasdfasdfasdf</span>";
//		String strResult = subStringHTML(str, str.length(), "....");
		String strResult = parserUtil.replaceHtmAndTrim(str);
		// System.out.println(strResult);
		
		parserUtil.splitByTag("<img src='sfd'/> sdfsdf<img src='sf'/> sdfsf<p>ffff</p>");
		
		
	}
}
