package cn.northpark.threadPool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author bruce
 * @date 2022年06月21日 15:24:43
 * 参考 :https://www.cnblogs.com/xiaoxiong2015/p/12706153.html
 * @param <E> :待处理集合的泛型
 * @param <T> :处理集合后的返回类型
 *
 * 这个类等价于retRR_Movies_DUO的阻塞队列实现
 */
public abstract class MultiThread<E, T> {

    public static int i = 0;

    private final ExecutorService exec; // 线程池

    private final BlockingQueue<Future<T>> queue = new LinkedBlockingQueue<>();

    private final CountDownLatch startLock = new CountDownLatch(1); // 启动门，当所有线程就绪时调用countDown

    private final CountDownLatch endLock; // 结束门

    private final List<E> listData;// 被处理的数据

    /**
     * @param list list.size()为多少个线程处理，list里面的H为被处理的数据
     */
    public MultiThread(List<E> list) {
        if (list != null && list.size() > 0) {
            this.listData = list;
            exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // 创建线程池，线程池共有nThread个线程
            endLock = new CountDownLatch(list.size()); // 设置结束门计数器，当一个线程结束时调用countDown
        } else {
            listData = null;
            exec = null;
            endLock = null;
        }
    }

    /**
     *
     * @return 获取每个线程处理结速的数组
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<T> getResult() throws InterruptedException, ExecutionException {

        List<T> resultList = new ArrayList<>();
        if (listData != null && listData.size() > 0) {
            int nThread = listData.size(); // 线程数量
            for (int i = 0; i < nThread; i++) {
                E data = listData.get(i);
                Future<T> future = exec.submit(new Task(i, data) {

                    @Override
                    public T execute(int currentThread, E data) {

                        return outExecute(currentThread, data);
                    }
                }); // 将任务提交到线程池
                queue.add(future); // 将Future实例添加至队列
            }
            startLock.countDown(); // 所有任务添加完毕，启动门计数器减1，这时计数器为0，所有添加的任务开始执行
            endLock.await(); // 主线程阻塞，直到所有线程执行完成
            for (Future<T> future : queue) {
                resultList.add(future.get());
            }
            exec.shutdown(); // 关闭线程池
        }
        return resultList;
    }

    /**
     * 每一个线程执行的功能，需要调用者来实现
     * @param currentThread 线程号
     * @param data 每个线程被处理的数据
     * @return T返回对象
     */
    public abstract T outExecute(int currentThread, E data);

    /**
     * 线程类
     */
    private abstract class Task implements Callable<T> {

        private int currentThread;// 当前线程号

        private E data;

        public Task(int currentThread, E data) {
            this.currentThread = currentThread;
            this.data = data;
        }

        @Override
        public T call() throws Exception {

            // startLock.await(); // 线程启动后调用await，当前线程阻塞，只有启动门计数器为0时当前线程才会往下执行
            T t = null;
            try {
                t = execute(currentThread, data);
            } finally {
                endLock.countDown(); // 线程执行完毕，结束门计数器减1
            }
            return t;
        }

        /**
         * 每一个线程执行的功能
         * @param currentThread 线程号
         * @param data 每个线程被处理的数据
         * @return T返回对象
         */
        public abstract T execute(int currentThread, E data);
    }
}