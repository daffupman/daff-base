package io.daff.cache;

/**
 * 耗时任务计算接口
 *
 * @author daff
 * @since 2022/3/20
 */
public interface Computable<P, R> {

    /**
     * 计算逻辑
     * @param params 入参
     * @return 计算结果
     * @throws Exception 异常
     */
    R compute(P params) throws Exception;
}
