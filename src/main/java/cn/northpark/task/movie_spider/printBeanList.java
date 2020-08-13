package cn.northpark.task.movie_spider;

import cn.northpark.utils.JsonCacheUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author lulei
 * @date 2020年08月13日 20:08:41
 */
@Slf4j
public class printBeanList {
    public static void main(String[] args) {
        try {
            List<String> beanList = JsonCacheUtil.getCache().get("beanList");

            for (String s : beanList) {
                log.error(s);
                System.out.println(s);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
