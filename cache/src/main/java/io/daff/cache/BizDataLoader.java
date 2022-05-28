package io.daff.cache;

/**
 * 业务数据模型预加载接口
 *
 * @author daff
 * @since 2022/3/20
 */
public interface BizDataLoader {

    /**
     * 加载业务数据模型
     */
    void load();
}
