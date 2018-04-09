package cn.northpark.utils;/**
 * Created by Administrator on 2017/5/10.
 */

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author caomin
 * @Date 2017-05-10 16:57
 * @Version 1.0
 */
public class HttpGetUtils {
    /**
     * get 方法
     * @param url
     * @return
     */
    public static String getDataResult(String url){
        String result = "";
        try {
            //获取httpclient实例
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //获取方法实例。GET
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
            httpGet.setHeader("Keep-Alive", "3000");
            httpGet.setHeader("Connection", "Keep-Alive");
            httpGet.setHeader("Cache-Control", "no-cache");
           //httpGet.setHeader("Referer", referer);
            //执行方法得到响应
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                //如果正确执行而且返回值正确，即可解析

                if (response != null
                        && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                    //设置编码
                    Header[] headers = response.getHeaders("Content-Type");
                    String charset="gb2312";
                    String contentType = headers[0].getValue();

                    int i = contentType.indexOf("=");
                    if (i!=-1) {
                        charset = contentType.substring(i + 1, contentType.length());
                    }
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    result = IOUtils.toString(content,charset);
                }
            } finally {
                httpclient.close();
                response.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("内容出错");
            return "";
        }
        return result;
    }

    public static byte[] getImg(String url){

        try {
            //获取httpclient实例
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpGet httpGet=null;
            //获取方法实例。GET
            try {
                 httpGet = new HttpGet(url);
            }catch (Exception e){
                System.out.println("出错啦！跳过这个请求=====出错url为"+url);
                return null;
            }
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
            httpGet.setHeader("Keep-Alive", "300");
            httpGet.setHeader("Connection", "Keep-Alive");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Content-Type: application/json;","charset=utf-8");
            //httpGet.setHeader("Referer", referer);

            //执行方法得到响应
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                //如果正确执行而且返回值正确，即可解析
                if (response != null
                        && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();
                    //如果是图片
                        InputStream content = entity.getContent();
                        byte[] bytes = IOUtils.toByteArray(content);
                        return bytes;
                }
            } finally {
                httpclient.close();
                response.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取图片出错");
        }
        return null;
    }

    /**
     * stream读取内容，可以传入字符格式
     * @param resEntity
     * @param charset
     * @return
     */
    private static String readResponse(HttpEntity resEntity, String charset) {
        StringBuffer res = new StringBuffer();
        BufferedReader reader = null;
        try {
            if (resEntity == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(
                    resEntity.getContent(), charset));
            String line = null;

            while ((line = reader.readLine()) != null) {
                res.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }



}

