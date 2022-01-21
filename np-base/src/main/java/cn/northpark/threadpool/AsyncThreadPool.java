package cn.northpark.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  <deprecated>（corePoolSize）核心线程10个 （maximumPoolSize）最大线程100 （workQueue）队列200 公平策略</deprecated>
 *  <northpark-async-tool>核心线程5个 最大线程10 队列5 公平策略</northpark-async-tool>
 *  @author zhangyang
 *  @date 2021年1月29日
 */
@Slf4j

public class AsyncThreadPool {

    private static int corePoolSize = 5;

    private static int maximumPoolSize = 10;

    private static long keepAliveTime = 10;


    private static ThreadPoolExecutor threadPoolExecutor;

    private static AsyncThreadPool asyncThreadPool;

    private AsyncThreadPool() {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5, true);
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
    }

    public static synchronized AsyncThreadPool getInstance() {
        if (null == asyncThreadPool) {
            synchronized (AsyncThreadPool.class) {
                if (null == asyncThreadPool) {
                    asyncThreadPool = new AsyncThreadPool();
                }
            }
        }
        log.info("AsyncThreadPool threadPoolExecutor={}", threadPoolExecutor);
        return asyncThreadPool;
    }

    public synchronized ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }


}
