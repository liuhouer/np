package cn.northpark.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

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

    private static final Logger LOGGER = Logger
            .getLogger(HTMLParserUtil.class);

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
        htmlStr = htmlStr.replaceAll("(?is)<!--.*?-->", "");                // remove html comment
        htmlStr = htmlStr.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
        htmlStr = htmlStr.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
        htmlStr = htmlStr.replaceAll("&.{2,5};|&#.{2,5};", " ");            // remove special char
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
        htmlStr = htmlStr.replaceAll("(?is)<!--.*?-->", "");                // remove html comment
        htmlStr = htmlStr.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
        htmlStr = htmlStr.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
        htmlStr = htmlStr.replaceAll("&.{2,5};|&#.{2,5};", " ");            // remove special char
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
//    public static void retQuan() throws IOException {
//        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//        Document doc = Jsoup.connect("http://quan.liuguofa.com/").get();
//
//        Elements tr   = doc.select("tr");
//
//        for(Element p : tr){
//
//            HashMap<String, String> map =new HashMap<String, String>();
//            Elements td = p.select("td");
//
//            Element url = td.get(0);
//            String from = td.get(1).html();
//            String time = td.get(2).html();
//            String authorIP = td.get(3).html();
//
//            Elements al = url.select("a");
//            String path = al.attr("href");
//            String title = al.attr("title");
//
//
//            map.put("from", from);
//            map.put("publishtime", time);
//            map.put("authorIP", authorIP);
//            map.put("path", path);
//            map.put("title", title);
//            map.put("id", SpiderAction.getInt6()+"");
//
//            System.out.println(path+"\t\r"+from+"\t\r"+time+"\t\r"+authorIP+"\t\r"+title+"\t\r"+"-----------------");
//
//            list.add(map);
//
//
//        }
//
//        //处理List集合
//        for (int i = 0; i < list.size(); i++) {
//            String path = list.get(i).get("path");
//            if(StringUtils.isNotEmpty(path)){
//                String url = "http://quan.liuguofa.com"+path;
//                Document doc_mt = Jsoup.connect(url).get();
//
//                Elements iframe = doc_mt.select("iframe");
//                String path_mt =  iframe.attr("src");
//                list.get(i).put("path_mt", path_mt);
//
//                list.get(i).put("addtime",TimeUtils.nowTime());
//            }
//        }
//
//        list.remove(0);
//
//        //更新redis缓存
//        System.out.println(list);
//        JedisUtil.remove("B_quan");
//        JedisUtil.addList("B_quan",list);
//    }

    /**
     * @throws IOException
     */
    public static List<String> readsong() throws IOException {

        List<String> urilist = new ArrayList<String>();
        for (int i = 1; i <= 6; i++) {
            File in = new File("/Users/zhangyang/Downloads/a"+i+".html");

            Document doc = Jsoup.parse(in, "UTF-8", "");
//            String title = doc.title();
//            System.out.println(title);
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

    //    <div class="info">
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

//                Elements dates = p.select("span[class=text]");
//
//                String date = dates.get(0).text();

                //文章
                Document doc_ = Jsoup.connect(aurl).get();
                Elements articles = doc_.select("div[class=innertext]");
                String article = articles.get(0).html();
                article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "").replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "").replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "").replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "");

                //日期
                Elements dates = doc_.select("span[class=activity-meta no-extra]");
                String date = dates.get(0).text();

//                if(!(date.equals("2016-07-20")) && !(date.equals("2016-07-23"))&& !(date.equals("2016-07-23"))){
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
                    if(file2.exists()){

                    }else{
                        FileOutputStream   out   =   new   FileOutputStream(path+name);
                        System.out.println("写入中..."+path+name);

                        int   i1=0;
                        while   ((i1=is.read())!=-1)   {
                            out.write(i1);
                        }
                    }
//                    if(name.endsWith(".png")||name.endsWith(".jpg")||name.endsWith(".ico")||name.endsWith(".gif")){

//                    }
                    is.close();
                }catch(Exception e){
                    e.printStackTrace();
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
                
                
                String retcode = MD5Utils.encoding(title);
                
                String aurl = "http://www.aiweibang.com"+titles.get(0).select("a").get(0).attr("href");

//                Elements dates = p.select("span[class=text]");
//
//                String date = dates.get(0).text();

                //文章
                Document doc_ = Jsoup.connect(aurl).get();
                Elements articles = doc_.select("div[class=innertext]");
                String article = articles.get(0).html();
                article = article.replace("大卫","<情圣>").replace("<p>请回复：<span style=\"background-color: rgb(255, 251, 0);\">千万别追女神</span></p>", "").replace("<p>请回复：<span style=\"background-color: rgb(255, 255, 0);\">千万别追女神</span></p>", "").replace("<p>你想学习正确的追女孩技巧，早日抱得美人归吗？</p>", "").replace("<p>你想得到<情圣>的帮助，解决棘手的情感问题吗？</p>", "");

                //日期
                Elements dates = doc_.select("span[class=activity-meta no-extra]");
                String date = dates.get(0).text();

                //标题图
    //            Elements pics = doc_.select("img");
    //            String img = pics.get(0).attr("src");

                //简介
                Elements texts = p.select("div[class=text]");
                String brief = texts.get(0).text();
                brief = brief.replace("大卫", "");

                map.put("title", title);
                map.put("aurl", aurl);
                map.put("brief", brief);
                map.put("date", date);
                map.put("article", article);
                map.put("retcode", retcode);
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
    public static List<Map<String, String>> retSoft(Integer index) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try{
                Document doc = Jsoup.connect("http://www.sdifenzhou.com/archives/category/black-apple/apple-software/page/"+index+"/").get();
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

                        //标签tags

                        Elements tags = article.select("span[class=cat-links]");

                        String tag = tags.get(0).text();
                        tag = tag.replaceAll("发表在", "").replaceAll(" ", "");

                        System.out.println("tag===================="+tag);

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
                        System.out.println("tagcode===================="+tagcode);

                        //code
                        String code = article.attr("id");

                        System.out.println("code===================="+code);

                        //日期
                         Elements dates = article.select("time[class=entry-date]");
                        String date = dates.get(0).text();
                        date=date.replaceAll(" ", "");
                        date=date.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
                        System.out.println("date===================="+date);

                        //月
                        String month = date.substring(0, date.lastIndexOf("-"));
                        System.out.println("month===================="+month);

                        //年
                        String year = month.substring(0, month.lastIndexOf("-"));
                        System.out.println("year===================="+year);

                        //文章
                        StringBuilder sb = new StringBuilder();

                        Document doc_ = Jsoup.connect(aurl).get();
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
                        System.out.println("article============="+text);

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

                        System.out.println("brief===================="+brief);

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

//                System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                e.printStackTrace();
            }

        return list;
    }

    /**
     * 爬取1页的电影图书资源
     */
    public static List<Map<String, String>> retMovies(Integer index) {
        // TODO Auto-generated method stub
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        try{
        	
        		System.out.println("page============================="+index+"============================页");
	        	//String url = "http://www.vip588660.cm/page/"+index+"/";
//        		String url = "http://www.vip588660.com/category/movie/page/"+index+"/";
	        	String url = "http://www.vip588660.com/category/dianshiju/page/"+index+"/";
//	        	String url = "http://www.vip588660.com/category/dongman/page/"+index+"/";
	        	
	        	String html = pickData(url);
                Document doc = Jsoup.parse(html);
                Element  ul   = doc.select("ul[class=masonry clearfix]").get(0);
                Elements lis  = ul.select("li[class=post box row ");
                if(!lis.isEmpty()){
                    for (int i = 0; i < lis.size(); i++) {
                        HashMap<String, String> map =new HashMap<String, String>();
                        Element li  = lis.get(i);

                        //获取相关信息
                        Elements divs = li.select("div[class=thumbnail]");

                        String title = divs.get(0).select("a").get(0).attr("title");
                        
                        String retcode = MD5Utils.encoding(title);
                        
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
                        

                        System.out.println("title==============>"+title);
                        System.out.println("aurl==============>"+aurl);
                        System.out.println("date==============>"+date);
                        System.out.println("tag==============>"+tag);
                        System.out.println("tagcode==============>"+tagcode);

                        String desc = "";

                        String html_ = pickData(aurl);
//                        System.out.println("html_==============>"+html_);

                        Document doc_ = Jsoup.parse(html_);
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
                                //基本介绍
//                                String preText = article_alls.get(0).select("p").get(0).html();
//                                preText = "<p>"+preText +"</p>";
//                                preText +=  "<p>"+ article_alls.get(0).select("p").get(1).html()+"</p>";
//
//                                //电影介绍
//                                String markText = article_alls.get(0).select("div[id=link-report]").get(0).select("p").get(0).html();
                                //下载地址
//                                String downText = article_alls.get(0).select("div[id=link-report]").get(0).select("h2").get(0).select("a").get(0).attr("href");

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
                                System.out.println("desc==============>"+desc);

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

//                System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                e.printStackTrace();
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
        	
        		System.out.println("page============================="+index+"============================页");
	        	//String url = "http://www.vip588660.cm/page/"+index+"/";
//        		String url = "http://www.vip588660.com/category/movie/page/"+index+"/";
//	        	String url = "http://www.vip588660.com/category/dianshiju/page/"+index+"/";
//	        	String url = "http://www.haoshici.com/"+index+"_E59490_0_0_0_0.html";
//        		String url = "http://www.haoshici.com/"+index+"_E5AE8B_0_0_0_0.html";
        		String url = "http://www.haoshici.com/"+index+"_E58583_0_0_0_0.html";
	        	
	        	
	        	
	        	String html = pickData(url);
//	        	System.out.println("html_1==============>"+html);
                Document doc = Jsoup.parse(html);
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

                        System.out.println("title==============>"+title);
                        System.out.println("detail_url==============>"+detail_url);
                        System.out.println("author==============>"+author);
                        System.out.println("content==============>"+content);
                        System.out.println("types==============>"+types);

                        //图片赏析
                        String pic_poem = "";
                        
                        //文字赏析
                        String enjoys = "";
                        String desc = "";
                      //诗词内容
                        String content1  = "";


                        String html_ = pickData(detail_url);
//                        System.out.println("html_==============>"+html_);

                        Document doc_ = Jsoup.parse(html_);
                        Elements article_alls = doc_.select("div[class=middle]");
                        if(!article_alls.isEmpty()){

                                Elements imgs = article_alls.get(0).select("img");
                                String date = TimeUtils.nowdate();
                                for (int j = 0; j < imgs.size(); j++) {
                                    try {
                                        String weburl = imgs.get(j).attr("src");
                                        System.out.println(weburl);
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

                                   System.out.println("enjoys==============>"+enjoys);    
                                   
                                   //诗词内容
                                   content1 = article_alls.get(0).select("p[class=poetry]").get(0).html();
                                   System.out.println("content1==============>"+content1);    

                                   
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

//                System.out.println(title+"\t\r"+aurl+"\t\r"+"\t\r"+brief+"\t\r"+article+"\t\r"+date+"-----------------");

            }catch(Exception e){
                e.printStackTrace();
            }

        return list;
    }
    
    
    /**
     * 爬取某页面的电影图书资源
     */

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

    
    public static int geneViewNum(){
    	int max=59000;
    	int min=1000;
    	Random random = new Random();
    	int s = random.nextInt(max)%(max-min+1) + min;
    	System.out.println(s);
		return s;
    }
	
    
    /**
     * httpclient获取网页内容html
     * TODO jsoup解析
     * @throws URISyntaxException 
     */
    private static String pickData(String url) throws URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            
            httpget.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
            String charset = "UTF-8";   
            httpget.setURI( new java.net.URI(url));  
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity,charset);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    
      public static void main(String[] args) {
            try {
                //retMeizitu();
                //retTodayEq();
//                List<Map<String, String>> retEQArticle = retEQArticle();
//                System.out.println(retEQArticle.size());
//                readPic2Disk();
//                retV2Romeo();
//                url2markdown();
//                String cutString = CutString("荒烟蔓草的年头，就连分手都很沉默", 12);
//                System.out.println(cutString);
//                retSoft(1);
//                webPic2Disk("http://www.sdifenzhou.com/wp-content/uploads/2016/02/Fantastical2.jpg", "D:\\BZ\\soft\\" );

//                retMovies(78);
//                String url = "http://www.vip588660.com/page/"+77+"/";
////                url = "http://northpark.cn/soft/mac/page77";
//                String pickData = pickData(url);
//                System.out.println(pickData);
//            	 String tag  =  "1,2,3,4,5,6,";
//            	 if(tag.endsWith(",")){
//                 	tag  =  tag.substring(0, tag.length()-1);
//                 	System.out.println(tag);
//                 }
            	
            
            	
            	retPoem(26);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

}