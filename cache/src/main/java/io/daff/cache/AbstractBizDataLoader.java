package io.daff.cache;

import io.daff.logging.DaffLogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daff
 * @since 2023/5/3
 */
public abstract class AbstractBizDataLoader<K, V> implements BizDataLoader {

    protected static final DaffLogger logger = DaffLogger.getLogger(AbstractBizDataLoader.class);
    private Map<K, V> CACHED_MAP = new ConcurrentHashMap<>();
    private boolean loaded = false;

    private final Long period;
    private final TimeUnit timeUnit;

    public AbstractBizDataLoader(long period, TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
    }

    public AbstractBizDataLoader() {
        this(10L, TimeUnit.MINUTES);
    }

    @Override
    public void load() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
        ses.scheduleAtFixedRate(() -> CACHED_MAP = doLoad(), 0L, period, timeUnit);
    }

    @Override
    public boolean finish() {
        return loaded;
    }

    /**
     * 业务数据加载
     */
    protected abstract Map<K, V> doLoad();

    /**
     * 根据K查询数据
     */
    public V get(K k) {
        if (k == null) {
            return null;
        }
        return CACHED_MAP.get(k);
    }

    protected void loaded() {
        this.loaded = true;
    }

}
