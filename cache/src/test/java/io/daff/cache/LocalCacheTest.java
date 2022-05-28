package io.daff.cache;

import io.daff.logging.DaffLogger;
import io.daff.logging.module.InnerModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * LocalCache测试类
 *
 * @author daff
 * @since 2022/3/20
 */
public class LocalCacheTest {

    private static final DaffLogger logger = DaffLogger.getLogger(LocalCacheTest.class);
    private static final LocalCache<String, Integer> cache = new LocalCache<>(new ConvertNum());

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                Integer integer = null;
                try {
                    integer = cache.get("111");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.error(Thread.currentThread().getName() + "获取的结果为：" + integer, InnerModule.TEST);
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {}
        logger.error("耗时：" + (System.currentTimeMillis() - start) + "ms", InnerModule.TEST);

        logger.error("获取222：{}", InnerModule.TEST, cache.get("222", 4000));
        logger.error("获取222：{}", InnerModule.TEST, cache.get("222", 4000));
        TimeUnit.SECONDS.sleep(3);
        logger.error("获取222：{}", InnerModule.TEST, cache.get("222", 4000));

    }

    static class ConvertNum implements Computable<String, Integer> {
        @Override
        public Integer compute(String params) throws Exception {
            TimeUnit.SECONDS.sleep(2);
            logger.error(Thread.currentThread().getName() + "执行计算任务", InnerModule.TEST);
            return Integer.valueOf(params);
        }
    }
}
