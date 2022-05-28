package io.daff.web.exception;

import io.daff.web.enums.Hint;

/**
 * 业务逻辑异常
 *
 * @author daffupman
 * @since 2020/8/18
 */
public class BusinessException extends BaseException {

    public BusinessException(String msg) {
        super(Hint.BUSINESS_LOGIC_ERROR, msg);
    }
}
