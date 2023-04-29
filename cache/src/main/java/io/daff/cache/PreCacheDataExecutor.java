package io.daff.cache;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 预加载缓存数据执行器
 *
 * @author daff
 * @since 2023/1/12
 */
public class PreCacheDataExecutor {

    private final List<BizDataLoader> bizDataLoaders;

    public PreCacheDataExecutor(List<BizDataLoader> bizDataLoaders) {
        this.bizDataLoaders = bizDataLoaders;
    }

    /**
     * 执行加载
     */
    public void exec() throws InterruptedException {
        // 数据加载
        if (!CollectionUtils.isEmpty(bizDataLoaders)) {
            for (BizDataLoader bizDataLoader : bizDataLoaders) {
                bizDataLoader.load();
                while (!bizDataLoader.finish()) {
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }
}
