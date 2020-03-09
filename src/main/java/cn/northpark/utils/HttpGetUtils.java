package cn.northpark.utils;/**
 * Created by Administrator on 2017/5/10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

/**
 * @author caomin
 * @Date 2017-05-10 16:57
 * @Version 1.0
 */
public class HttpGetUtils {
    /**
     * get 方法
     *
     * @param url
     * @return
     */
    public static String getDataResult(String url) {
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
                    String charset = "utf-8";
                    String contentType = headers[0].getValue();

                    int i = contentType.indexOf("=");
                    if (i != -1) {
                        charset = contentType.substring(i + 1, contentType.length());
                    }
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    result = IOUtils.toString(content, StandardCharsets.UTF_8.toString());
                }
            } finally {
                httpclient.close();
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("内容出错");
            return "";
        }
        return result;
    }

    public static byte[] getImg(String url) {

        try {
            //获取httpclient实例
            CloseableHttpClient httpclient = HttpClients.createDefault();

            HttpGet httpGet = null;
            //获取方法实例。GET
            try {
                httpGet = new HttpGet(url);
            } catch (Exception e) {
                System.out.println("出错啦！跳过这个请求=====出错url为" + url);
                return null;
            }
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36");
            httpGet.setHeader("Keep-Alive", "300");
            httpGet.setHeader("Connection", "Keep-Alive");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Content-Type: application/json;", "charset=utf-8");
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取图片出错");
        }
        return null;
    }

    /**
     * stream读取内容，可以传入字符格式
     *
     * @param resEntity
     * @param charset
     * @return
     */
    public static String readResponse(HttpEntity resEntity, String charset) {
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


    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        //1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2.生成一个post请求
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            //3.执行get请求并返回结果
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.处理结果，这里将结果返回为字符串
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<NameValuePair> formparams = Lists.newArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //给参数赋值
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity1 = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送HttpPost请求，参数为JsonData
     *
     * @param url
     * @param Json
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String sendPostJsonData(String url, String Json) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(Json, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");

        httpPost.setEntity(stringEntity);

        System.out.println("Executing request " + httpPost.getRequestLine());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(final HttpResponse response)
                    throws ClientProtocolException, IOException {//
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {

                    HttpEntity entity = response.getEntity();

                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException(
                            "Unexpected response status: " + status);
                }
            }
        };
        String responseBody = httpclient.execute(httpPost, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);

        return responseBody;
    }


    public static void main(String[] args) {
        System.out.println(StandardCharsets.UTF_8.toString());
        System.out.println(StandardCharsets.UTF_8.name());
    }

    /**
     * 使用htmlunit工具来获取页面数据，可以处理异步数据
     *
     * @param url
     * @return
     */
    public static String getDataByHtmlUnit(String url) {
        final int timeOut = 5000;
        //等待异步JS执行时间,默认20秒
        int waitForBackgroundJavaScript = 5000;

        String pageXml = "";
        try {
            // 屏蔽HtmlUnit等系统 log
            LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
            java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);

            // TODO Auto-generated method stub
            WebClient wc = new WebClient(BrowserVersion.CHROME);

            wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true

            wc.getOptions().setCssEnabled(false);// 禁用css支持

            wc.getOptions().setThrowExceptionOnScriptError(false);// js运行错误时，是否抛出异常

            wc.getOptions().setThrowExceptionOnFailingStatusCode(false);// 状态码错误时，是否抛出异常

            wc.getOptions().setTimeout(timeOut); // 设置连接超时时间 ，这里是5S。如果为0，则无限期等待


            wc.getOptions().setActiveXNative(false); // 是否允许使用ActiveX
//
            wc.setAjaxController(new NicelyResynchronizingAjaxController()); // 设置Ajax异步处理控制器即启用Ajax支持
//
            wc.waitForBackgroundJavaScript(timeOut); // 等待js时间
//
            wc.getOptions().setDoNotTrackEnabled(false); // 不跟踪抓取

            wc.setJavaScriptTimeout(timeOut);//设置JS执行的超时时间


            HtmlPage page;
            try {
                page = wc.getPage(url);
            } catch (Exception e) {
                wc.close();
                throw e;
            }

            //该方法阻塞线程
            wc.waitForBackgroundJavaScript(waitForBackgroundJavaScript);

            // 以xml的形式获取响应文本
            pageXml = page.asXml();
            wc.close();
        } catch (Exception e) {
            // TODO: handle exception
//			e.printStackTrace();
        }

        return pageXml;
    }
}

