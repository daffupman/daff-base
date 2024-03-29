package io.daff.logging.module;

/**
 * 内部组件模块
 *
 * @author daff
 * @since 2022/5/28
 */
public enum InnerModule implements Module {

    /**
     * 内部
     */
    INNER(10, "DAFF.INNER"),

    /**
     * 测试
     */
    TEST(11, "DAFF.TEST"),

    /**
     * 校验
     */
    VALID(12, "DAFF.VALID"),

    /**
     * 缓存
     */
    CACHE(13,"DAFF.CACHE"),

    /**
     * web通用
     */
    WEB(14,"DAFF.WEB"),
    ;

    final Integer no;
    final String code;

    InnerModule(Integer no, String code) {
        this.no = no;
        this.code = code;
    }

    @Override
    public Integer no() {
        return this.no;
    }

    @Override
    public String code() {
        return code;
    }
}
