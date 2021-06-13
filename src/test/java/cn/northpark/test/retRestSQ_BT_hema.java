package cn.northpark.test;

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
public class retRestSQ_BT_hema {


    public static void main(String[] args) {

        //1366
        for (int k=271;k<= 1366; k++) {

            List<Map<String, String>> collect = new ArrayList<>();

            try {

                List<Map<String, String>> list = HTMLParserUtil.ret_SQ_hema(k);

                collect.addAll(list);
            }catch (Exception e ) {
                if(e instanceof java.net.SocketException){
                    break;
                }
                // TODO: handle exception
                e.printStackTrace();
            }


            //每页请求一次新增数据
            String jsonData = JsonUtil.object2json(collect);


            log.info("爬取的数据----》,{}", jsonData);
            String url = "https://northpark.cn/ret/movies/data";
            try {
                HttpGetUtils.sendPostJsonData(url, jsonData);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
                log.info("第" + k + "页================");
            } catch (InterruptedException e) {
                // TODO Auo-generated catch block
                e.printStackTrace();
            }




        }

    }


}
