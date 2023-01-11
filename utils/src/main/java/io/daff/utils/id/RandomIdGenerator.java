package io.daff.utils.id;

import io.daff.utils.common.StringUtil;

/**
 * @author daff
 * @since 2023/1/11
 */
public class RandomIdGenerator implements LogTraceIdGenerator {

    @Override
    public String gen() {
        return StringUtil.uuid(8);
    }
}
