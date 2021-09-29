//package cn.northpark.utils;
//
//import cn.northpark.task.movie_spider.MovieListPage;
//import com.google.common.cache.CacheBuilder;
//import com.google.common.cache.CacheLoader;
//import com.google.common.cache.LoadingCache;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * cache缓存
// */
//@Slf4j
//public class CacheUtil {
//
//
//    private static LoadingCache<String, List<MovieListPage>> cache = null;
//
//    /**
//     * 初始化cache缓存
//     */
//    static {
//        cache = CacheBuilder.newBuilder()
//                .maximumSize(10) // 最多存放10个数据
//                .expireAfterWrite(30, TimeUnit.MINUTES) // 缓存30min
//                .recordStats() // 开启记录状态数据功能
//                .build(new CacheLoader<String, List<MovieListPage>>() {
//                    @Override
//                    public List<MovieListPage> load(String key) throws Exception {
//                        return null;
//                    }
//                });
//
//    }
//
//    /**
//     * 获取Jedis实例
//     *
//     * @return
//     */
//    public synchronized static LoadingCache<String, List<MovieListPage>> getCache() {
//        return cache;
//    }
//
//}
//
//
