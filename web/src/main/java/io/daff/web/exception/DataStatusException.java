package io.daff.web.exception;


import io.daff.web.enums.Hint;

/**
 * 数据状态异常
 *
 * @author daffupman
 * @since 2020/7/13
 */
public class DataStatusException extends BaseException {

    private static final long serialVersionUID = -1999015592879351947L;

    public DataStatusException() {
        super(Hint.DATA_STATUS_ERROR, "数据状态异常");
    }

    public DataStatusException(String message) {
        super(Hint.DATA_STATUS_ERROR, message);
    }
}
