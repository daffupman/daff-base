package io.daff.logging.module;

/**
 * 模块接口。用于标记项目下的各个模块，以便统一管理。
 *
 * @author daff
 * @since 2022/5/28
 */
public interface Module {

    /**
     * 模块编号
     */
    Integer no();

    /**
     * 模块code
     */
    String code();
}
