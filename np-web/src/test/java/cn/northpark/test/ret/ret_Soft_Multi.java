package cn.northpark.test.ret;

import cn.northpark.threadpool.MultiThread;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.HttpGetUtils;
import cn.northpark.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ret_Soft_Multi {


    //线程池执行
    public static void main(String[] args) {


        /*构造页码*/
        List<Integer> todo_list = new ArrayList<>();
        for (int i = 31; i < 40; i++) {
            todo_list.add(i);
        }

        MultiThread<Integer, Integer> multiThread = new MultiThread<Integer, Integer>(todo_list) {
            @Override
            public Integer outExecute(int currentThread, Integer data) {

                System.err.println("currentThread===>"+currentThread);
                System.err.println("page id ===>"+data);
                run(data);
                return currentThread;
            }
        };

        try {
            System.err.println("线程"+multiThread.getResult()+"正在执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    /**
     * 执行一页的爬取
     */
    static void run(final int taskNum){
        System.out.println("正在执行task " + taskNum);

        //====================执行逻辑=====================
        List<Map<String, String>> collect = new ArrayList<>();

        try {

            List<Map<String, String>> list = HTMLParserUtil.retSoft0DayDown(taskNum);
            collect.addAll(list);
        } catch (Exception e) {

            e.printStackTrace();
        }


        //每页请求一次新增数据
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auo-generated catch block
            e.printStackTrace();
        }
        //====================执行逻辑====================================

        System.out.println("task " + taskNum + "执行完毕");
    }

}
