package cn.northpark.test.dataclean;

import cn.northpark.utils.MinioUtils;
import cn.northpark.utils.NPQueryRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 批量迁移富文本
 */
@Slf4j
public class transMovieMinio {

    public static void main(String[] args) {
        String countSql = "SELECT COUNT(*) FROM bc_movies where movie_desc2 is null";
        int totalCount = NPQueryRunner.query(countSql, new ScalarHandler<Long>()).intValue();

        int batchSize = 100;
        int totalBatches = (int) Math.ceil((double) totalCount / batchSize);

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int batchNumber = 0; batchNumber < totalBatches; batchNumber++) {
            int offset = batchNumber * batchSize;

            String sql = "SELECT id, movie_desc FROM bc_movies where movie_desc2 is null ORDER BY id ASC LIMIT " + offset + "," + batchSize;
            List<Map<String, Object>> maps = NPQueryRunner.query(sql, new MapListHandler());

            for (Map<String, Object> item : maps) {
                executorService.execute(() -> {
                    log.info("线程" + Thread.currentThread().getId() + "开始处理---" );
                    String id = item.get("id").toString();
                    String movie_desc = item.get("movie_desc").toString();
                    // 富文本上传
                    String minioId = MinioUtils.uploadText(movie_desc);
                    String up_sql = "UPDATE bc_movies SET movie_desc2 = '" + minioId + "' WHERE id = " + id;
                    NPQueryRunner.update(up_sql);
                    log.info("线程" + Thread.currentThread().getId() + "处理完成---" );
                });
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error("线程池等待被中断", e);
            Thread.currentThread().interrupt();
        }
    }

}
