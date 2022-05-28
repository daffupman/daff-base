package io.daff.web.exception;


import io.daff.web.enums.Hint;

/**
 * 数据不存在异常
 *
 * @author daffupman
 * @since 2020/7/13
 */
public class NoSuchDataException extends BaseException {

    private static final long serialVersionUID = -1999015592879351947L;

    public NoSuchDataException() {
        super(Hint.DATA_UNAVAILABLE);
    }

    public NoSuchDataException(String message) {
        super(Hint.DATA_UNAVAILABLE, message);
    }
}
