package soft;

import cn.northpark.threadPool.MultiThread;
import cn.northpark.utils.NPQueryRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author bruce
 * @date 2024年01月13日 08:48:42
 */
@Slf4j
public class HandleUniquePostCode {
    public static void main(String[] args) {
        //更新软件的请求码为软件名[不含版本号]
        List<Map<String, Object>> mapList = NPQueryRunner.query("select id,ret_code from bc_soft   " , new MapListHandler());

        MultiThread<Map<String, Object>, Integer> multiThread = new MultiThread<Map<String, Object>, Integer>(mapList) {
            @Override
            public Integer outExecute(int currentThread, Map<String, Object> data) {

                System.err.println("currentThread===>"+currentThread);
                System.err.println("map data ===>"+data);
                String ret_code = data.get("ret_code").toString();
                return currentThread;
            }
        };

    }



}
