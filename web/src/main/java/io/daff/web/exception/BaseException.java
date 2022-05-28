package io.daff.web.exception;


import io.daff.web.enums.Hint;

/**
 * 系统基础异常
 * @author daffupman
 * @since 2020/7/12
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -4113091967574026465L;

    /**
     * 响应码，参考 {@link Hint}
     */
    private final Integer code;
    private final String msg;

    public BaseException(Hint hint) {
        super(hint.msg());
        this.code = hint.code();
        this.msg = hint.msg();
    }

    public BaseException(Hint hint, String msg) {
        super(msg);
        this.code = hint.code();
        this.msg = msg != null && !msg.trim().equals("") ? msg : hint.msg();
    }

    public Integer getCode() { return code; }

    public String getMsg() { return msg; }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + this.code +
                "message=" + getMessage() +
                '}';
    }
}
