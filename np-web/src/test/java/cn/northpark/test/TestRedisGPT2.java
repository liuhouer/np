package cn.northpark.test;

import cn.northpark.utils.RedisGPTUtil;
import cn.northpark.utils.RedisUtil;

import java.util.Random;
import java.util.UUID;

/**
 * @author bruce
 * @date 2023年02月08日 15:11:39
 */
public class TestRedisGPT2 {


    public static void main(String[] args) throws Exception{

        RedisGPTUtil n2 = new RedisGPTUtil();
        String lockKey = "lockKey";
        String requestId = UUID.randomUUID().toString();
        int k = 0;
        while (true) {
            String lockValue = n2.tryLock(lockKey, requestId, 10000);
            if (lockValue != null) {
                try {
                    // 获取锁成功，执行业务逻辑
                    System.out.println("Get lock success, lockValue: " + lockValue);
                    // 处理业务
                    System.out.println("Process business...");
                    break;
                } finally {
                    // 释放锁
                    n2.unLock(lockKey, lockValue);
                    System.out.println("Release lock success, lockValue: " + lockValue);
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
