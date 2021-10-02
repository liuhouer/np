/**
 *
 */
package cn.northpark.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import cn.northpark.utils.encrypt.DESUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @author bruce
 * @date 2016年7月29日
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * <p>
 * 根据IP地址获取详细的地域信息
 */
@Slf4j
public class AddressUtils {

    private static final String ACCESS_KEY = DESUtils.decrypt("Fr22Vr5R/lpidOAunpLJRM7xoWyZczM2F6OY2KU/BAcZLJTCcl2pvA==", DESUtils.KEY);

    /**
     * 获取一个处理IP 和 区域的实例
     */
    private volatile static AddressUtils instance = null;

    public static AddressUtils getInstance() {
        if (instance == null) {
            synchronized (AddressUtils.class) {
                if (instance == null) {
                    instance = new AddressUtils();
                }
            }
        }
        return instance;
    }

    /**
     * IP定位 2.0
     * IP定位 2.0 API服务地址：
     *
     * URL
     *
     * https://restapi.amap.com/v5/ip?parameters
     *
     * 请求方式
     *
     * GET
     *                 74a85d61fa92afe35bc66be2f95f0c16
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用pconline的接口
        String urlStr = "https://restapi.amap.com/v5/ip";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        //{
        //	"status": "1",
        //	"info": "OK",
        //	"infocode": "10000",
        //	"country": "中国",
        //	"province": "河北省",
        //	"city": "衡水市",
        //	"district": "深州市",
        //	"isp": "中国移动",
        //	"location": "115.554596,38.00347",
        //	"ip": "183.198.122.85"
        //}
        String returnStr = this.getResult(urlStr, content, encodingString);
        if (StringUtils.isNotEmpty(returnStr)) {
            // 处理返回的省市区信息
            log.info(returnStr);
            Map<String, Object> datamap = JsonUtil.json2map(returnStr);
            if (Objects.nonNull(datamap) && !datamap.isEmpty()) {
                String country = (String) datamap.get("country");
                String province = (String) datamap.get("province");
                String city = (String) datamap.get("city");
                String isp = (String) datamap.get("isp");
                String rs = "【国家：" + country + "】【省份：" + province + "】【城市：" + city + "】【供应商：" + isp + "】";
                log.info(rs);
                return rs;
            }
        }
        return null;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            log.error("AddressUtils------->", e);
            ;
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }


    /**
     * 获取IP
     *
     * @param beat
     * @return
     */
    public String getIpAddr(HttpServletRequest beat) {
        String ip = beat.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = beat.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = beat.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = beat.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = beat.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = beat.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取IP + 地址字符串
     *
     * @param beat
     * @return
     */
    public String getIpAndDetail(HttpServletRequest beat) {
        StringBuilder sb = new StringBuilder();
        instance = AddressUtils.getInstance();
        String ip = instance.getIpAddr(beat);
        sb.append("【ip:").append(ip).append("】").append("【");
        try {
            String addresses = instance.getAddresses("ip=" + ip + "&key=" + ACCESS_KEY + "&type=4", "utf-8");
            sb.append("address:").append(addresses);
            sb.append("】");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sb.toString();
    }

    //    // 测试
    public static void main(String[] args) {
        AddressUtils addressUtils = new AddressUtils();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "183.198.122.85";
        String address = "";
        try {
            address = addressUtils.getAddresses("ip=" + ip + "&key=" + ACCESS_KEY + "&type=4", "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            log.error("AddressUtils------->", e);
            ;
        }
        log.info(address);
        // 输出结果为：广东省,广州市,越秀区
    }
}  
