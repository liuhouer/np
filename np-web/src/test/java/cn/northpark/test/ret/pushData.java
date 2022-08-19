package cn.northpark.test.ret;

import cn.northpark.utils.HttpGetUtils;
import cn.northpark.utils.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author bruce
 * @date 2022年08月19日 17:53:06
 */
public class pushData {
    public static void main(String[] args) {
        String path = "C:\\Users\\Bruce\\Documents\\page\\21.txt";
        byte[] bs = new byte[1024];
        try {

            IOUtils.read(new FileInputStream(new File(path)),bs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String jsonData = JsonUtil.object2json(collect);


        log.info("爬取的数据----》,{}", jsonData);
        String url = "https://northpark.cn/ret/soft/data";
        try {
            HttpGetUtils.sendPostJsonData(url, jsonData);
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
