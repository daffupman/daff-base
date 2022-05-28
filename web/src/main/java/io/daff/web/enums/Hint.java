package io.daff.web.enums;

/**
 * 异常响应提示类
 * @author daffupman
 * @since 2021/4/16
 */
public enum Hint {

    /**
     * 成功
     */
    SUCCESS(1000, "成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(2000, "系统繁开小差了，请稍后重试"),

    /**
     * 参数校验异常
     */
    PARAM_VALIDATE_ERROR(3000, "参数校验异常"),

    /**
     * 参数缺失
     */
    PARAM_MISS_ERROR(3001, "参数缺失"),

    /**
     * 业务错误
     */
    BUSINESS_LOGIC_ERROR(3002, "业务错误"),

    /**
     * 认证失败
     */
    AUTHENTICATION_FAILED(4000, "认证失败"),

    /**
     * 授权失败
     */
    AUTHORITY_FAILED(4001, "授权失败"),

    /**
     * 权限不足
     */
    PERMISSION_NOT_ENOUGH(4002, "权限不足"),

    /**
     * 缺少凭证信息
     */
    CREDENTIALS_MISS(5000, "缺少凭证信息"),

    /**
     * 非法的凭证信息
     */
    CREDENTIALS_INVALID(5001, "非法的凭证信息"),

    /**
     * 重复提交
     */
    REPEAT_SUBMIT(5002, "重复提交"),

    /**
     * 无法访问的数据
     */
    DATA_UNAVAILABLE(6000, "无法访问的数据"),

    /**
     * 数据状态异常
     */
    DATA_STATUS_ERROR(6001, "数据状态异常"),

    /**
     * 网络阻塞
     */
    NETWORK_BLOCK(7000, "网络阻塞"),

    ;

    private final Integer code;
    private final String msg;

    Hint(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return this.code;
    }
    public String msg() {
        return this.msg;
    }
}
