package io.daff.cache;

import java.util.concurrent.TimeUnit;

/**
 * 预加载缓存数据执行器
 *
 * @author daff
 * @since 2023/1/12
 */
public class PreCacheDataExecutor {

    private final BizDataLoader bizDataLoader;

    public PreCacheDataExecutor(BizDataLoader bizDataLoader) {
        this.bizDataLoader = bizDataLoader;
    }

    /**
     * 执行加载
     */
    public void exec() throws InterruptedException {
        // 数据加载
        if (bizDataLoader != null) {
            bizDataLoader.load();
            while (!bizDataLoader.finish()) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
