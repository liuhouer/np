package cn.northpark.test.dataclean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bruce
 * @date 2023年11月23日 16:59:23
 */
@Slf4j
public class MaxThread {
    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        log.info("this PC's MAX thread is ----{}",i);
    }
}
