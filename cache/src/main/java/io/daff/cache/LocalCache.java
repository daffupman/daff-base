package io.daff.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 本地缓存
 *
 * @param <P> 数据缓存的键
 * @param <R> 缓存的数据
 *
 * @see Computable 对实现该接口的任务进行缓存
 * @author daff
 * @since 2022/3/20
 */
public class LocalCache<P, R> {

    /**
     * 缓存数据
     */
    private final Map<P, Future<R>> cacheData = new ConcurrentHashMap<>();

    /**
     * 计算逻辑
     */
    private final Computable<P, R> c;

    private final ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

    public LocalCache(Computable<P, R> c) {
        this.c = c;
    }

    public R get(P params) throws Exception {
        while (true) {
            Future<R> f = cacheData.get(params);
            if (f == null) {
                FutureTask<R> ft = new FutureTask<>(() -> c.compute(params));
                f = cacheData.putIfAbsent(params, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException | InterruptedException e) {
                // 计算任务被取消或中断，任务结束
                e.printStackTrace();
                cacheData.remove(params);
                throw e;
            } catch (ExecutionException e) {
                // 重试
                e.printStackTrace();
                cacheData.remove(params);
            }
        }
    }

    /**
     * 获取数据
     *
     * @param params 数据的key
     * @param expireMsWhenSetData 数据没有缓存，设置缓存时的过期时间
     * @return 缓存数据
     * @throws Exception 异常
     */
    public R get(P params, Integer expireMsWhenSetData) throws Exception {
        if (expireMsWhenSetData > 0) {
            ses.schedule(() -> expire(params), expireMsWhenSetData, TimeUnit.MILLISECONDS);
        }
        return get(params);
    }

    /**
     * 设置过期清除缓存数据的线程
     * @param params 缓存数据的key
     */
    private synchronized void expire(P params) {
        Future<R> f = cacheData.get(params);
        if (f != null) {
            if (!f.isDone()) {
                // 任务已过期，但是任务正在运行还没有完成，则取消任务
                f.cancel(true);
            }
            cacheData.remove(params);
        }
    }
}
