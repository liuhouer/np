package cn.northpark.test.ret;

import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.HttpGetUtils;
import cn.northpark.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class retRestSoft {


    public static void main(String[] args) {
        List<Map<String, String>> collect = new ArrayList<>();
        for (int k = 4; k <= 8; k++) {

            try {

                List<Map<String, String>> list = HTMLParserUtil.retSoft0DayDown(k);
                collect.addAll(list);
            } catch (Exception e) {

                e.printStackTrace();
            }

            try {
                Thread.sleep(3000);
                log.info("第" + k + "页================");
            } catch (InterruptedException e) {
                // TODO Auo-generated catch block
                e.printStackTrace();
            }
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
