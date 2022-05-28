package io.daff.web.exception;

import io.daff.web.enums.Hint;

/**
 * @author daffupman
 * @since 2020/8/9
 */
public class InsufficientPermissionsException extends BaseException {

    private static final long serialVersionUID = 7103360464347579079L;

    public InsufficientPermissionsException(String msg) {
        super(Hint.AUTHORITY_FAILED, msg);
    }
}
