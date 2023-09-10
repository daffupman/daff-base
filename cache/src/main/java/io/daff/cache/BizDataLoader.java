package io.daff.cache;

/**
 * 业务数据模型预加载器，此loader是常驻JVM的缓存，在项目启动的时候启动
 *
 * @author daff
 * @since 2021/11/15
 */
public interface BizDataLoader<K, V> {

    /**
     * 加载业务数据
     */
    void load();

    /**
     * 判断数据加载是否完成
     */
    boolean finish();

    /**
     * 根据K获取V
     */
    V get(K k);
}
