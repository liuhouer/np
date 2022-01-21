package cn.northpark.utils;/**
 * Created by Administrator on 2017/5/10.
 */

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/**
 * @author caomin
 * @Date 2017-05-10 17:02
 * @Version 1.0
 */
public class StartImg {
    public static void main(String[] args) throws IOException {
        String initUrl = "https://www.waitsun.com/page/2";
        final String dataResult = getDataResultByUrl(initUrl);

        String baseUrl = initUrl;

        HashMap<String, String> allUrls = getAllUrls(dataResult, baseUrl);

        SaveImg(dataResult, baseUrl);
        Set<String> keySet = allUrls.keySet();

        for (final String url : keySet) {
            try {
                String dataResult1 = HttpGetUtils.getDataResult(url);
                SaveImg(dataResult1, url);
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        HashMap<String, String> allUrls1 = getAllUrls(dataResult, url);
                        Set<String> keySet1 = allUrls1.keySet();
                        for (String url2 : keySet1) {
                            String dataResultByUrl = getDataResultByUrl(url2);
                            try {
                                SaveImg(dataResultByUrl, url2);
                            } catch (IOException e) {
                                continue;
                            }
                        }


                    }
                });
                thread.start();


            } catch (Exception e) {
                continue;
            }

        }
        System.out.println("获取图片结束！ the end");
    }

    /**
     * 获取所有子链接
     *
     * @param
     * @return
     */
    public static HashMap<String, String> getAllUrls(String dataResult, String baseUrl) {


        Document parse = Jsoup.parse(dataResult, baseUrl);

        HashMap<String, String> urls = new HashMap<String, String>();

        Elements hrefs = parse.select("a[href]");

        for (Element element : hrefs) {

            String href = element.absUrl("href");
            String text = element.text();
            if (href.equals("#") || href.equals("javascript:void(0);") || !href.startsWith("http") || href.contains("topics") || href.contains("/tag/") || href.contains("/aboutus") || href.contains("wp-login.php") || href.contains("/xpay-html") || href.contains("/page/") || !href.contains("waitsun.com/")) {
                continue;
            }
            urls.put(href, text);
        }

        return urls;
    }

    /**
     * 获取一个链接里面的所有图片，并保存图片
     *
     * @param
     * @throws IOException
     */
    public static void SaveImg(String resultData, String baseUrl) throws IOException {


        String path = LocalDate.now().toString();
        Document parse = Jsoup.parse(resultData, baseUrl);
        Elements imgs = parse.getElementsByTag("img");
        HashMap<String, String> imgUrls = new HashMap<String, String>();

        for (Element element : imgs) {
            String imgUrl = element.absUrl("src");
            String alt = element.attr("alt");
            if (!imgUrl.startsWith("http"))
                continue;
            imgUrls.put(imgUrl, alt);
        }
        Set<String> keySet = imgUrls.keySet();
        for (String s1 : keySet) {
            try {
                byte[] img1 = HttpGetUtils.getImg(s1);
                if (img1 != null) {
                    String fileName = imgUrls.get(s1);
                    int length = s1.length();

                    String suffix = s1.subSequence(length - 4, length).toString();
                    if (!suffix.startsWith(".")) {
                        suffix = ".png";
                    }
                    if (suffix.equals(".gif")) {
                        FileUtils.writeByteArrayToFile(new File("e:/imgs/gif/" + path + fileName + suffix), img1);
                    }
                    if (StringUtils.isEmpty(fileName)) {
                        fileName = UUID.randomUUID().toString();
                        FileUtils.writeByteArrayToFile(new File("e:/imgs/noName/" + path + fileName + suffix), img1);
                    } else {
                        System.out.println("保存了图片---------路径为：e:/imgs/" + path + fileName);
                        FileUtils.writeByteArrayToFile(new File("e:/imgs/" + path + "/" + fileName + suffix), img1);
                    }
                }
            } catch (Exception e) {
                System.out.println("保存图片出错！");
                continue;
            }

        }
    }

    public static String getDataResultByUrl(String url) {
        String dataResult = HttpGetUtils.getDataResult(url);
        return dataResult;
    }

}
