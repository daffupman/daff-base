package io.daff.utils.id;

import io.daff.utils.common.SnowFlake;

/**
 * @author daff
 * @since 2023/1/11
 */
public class SnowFlakeIdGenerator implements DistributeIdGenerator {

    private final SnowFlake snowFlake = new SnowFlake();

    @Override
    public String gen() {
        return String.valueOf(snowFlake.nextId());
    }
}
