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
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class retRestSQ_BT_hema_DUO {




    //线程池执行
    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1500));

        for(int i=1235;i<= 1366;i++){
            RetOnePageTask myTask = new RetOnePageTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完成别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();

        //1366

    }


    /**
     *  执行一页的爬取
     */
    static class RetOnePageTask implements Runnable {
        private int taskNum;

        public RetOnePageTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);

            //====================执行逻辑=====================
            List<Map<String, String>> collect = new ArrayList<>();

            try {

                List<Map<String, String>> list = HTMLParserUtil.ret_SQ_hema(taskNum);

                collect.addAll(list);
            } catch (Exception e) {
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
                log.info("第" + taskNum + "页================");
            } catch (InterruptedException e) {
                // TODO Auo-generated catch block
                e.printStackTrace();
            }
            //====================执行逻辑====================================

            System.out.println("task "+taskNum+"执行完毕");
        }
    }

}
