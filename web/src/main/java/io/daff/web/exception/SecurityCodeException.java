package io.daff.web.exception;

import io.daff.web.enums.Hint;

/**
 * 验证码异常
 *
 * @author daffupman
 * @since 2020/7/13
 */
public class SecurityCodeException extends BaseException {

    private static final long serialVersionUID = -1999015592879351947L;

    public SecurityCodeException() {
        super(Hint.AUTHENTICATION_FAILED, "验证码错误");
    }

    public SecurityCodeException(String message) {
        super(Hint.AUTHENTICATION_FAILED, message);
    }
}
