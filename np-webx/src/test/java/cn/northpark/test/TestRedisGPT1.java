package cn.northpark.test;

import cn.northpark.utils.RedisGPTUtil;
import cn.northpark.utils.RedisUtil;

import java.util.UUID;

/**
 * @author bruce
 * @date 2023年02月08日 15:11:39
 */
public class TestRedisGPT1 {
    public static void main(String[] args) throws Exception{

        RedisGPTUtil n1 = new RedisGPTUtil();
        String requestId = UUID.randomUUID().toString();

        String lockKey = "lockKey";
        String lockValue = n1.tryLock(lockKey, requestId,10000);
        if (lockValue != null) {
            try {
                // 获取锁成功，执行业务逻辑
                System.out.println("Get lock success, lockValue: " + lockValue);

                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            get2(lockKey);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.run();

                Thread.sleep(1000 *60 *5);
            } finally {
                // 释放锁
                n1.unLock(lockKey, lockValue);
                System.out.println("Release lock success, lockValue: " + lockValue);
            }
        } else {
            // 获取锁失败
            System.out.println("Get lock failed");
        }



    }

    private static void get2(String lockKey) throws InterruptedException {
        RedisGPTUtil n2 = new RedisGPTUtil();
        String requestId2 = UUID.randomUUID().toString();
        int k = 0;
        while (true) {
            String lockValue2 = n2.tryLock(lockKey, requestId2, 10000);
            if (lockValue2 != null) {
                try {
                    // 获取锁成功，执行业务逻辑
                    System.out.println("Get lock success, lockValue: " + lockValue2);
                    // 处理业务
                    System.out.println("Process business...");
                    break;
                } finally {
                    // 释放锁
                    n2.unLock(lockKey, lockValue2);
                    System.out.println("Release lock success, lockValue: " + lockValue2);
                }
            } else {
                // 获取锁失败，等待1秒后重试
                System.out.println("Get lock failed, wait 1 second and retry");
                Thread.sleep(1000);
                k++;
                System.err.println("=====已等待时长"+k);
            }
        }
    }

}
