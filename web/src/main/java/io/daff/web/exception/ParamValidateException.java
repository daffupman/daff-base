package io.daff.web.exception;

import io.daff.web.enums.Hint;

/**
 * 参数校验异常
 *
 * @author daffupman
 * @since 2020/7/12
 */
public class ParamValidateException extends BaseException {

    private static final long serialVersionUID = 2253678517658387828L;

    public ParamValidateException(String message) {
        super(Hint.PARAM_VALIDATE_ERROR, message);
    }

}
