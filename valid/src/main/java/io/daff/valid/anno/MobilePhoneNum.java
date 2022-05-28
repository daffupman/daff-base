package io.daff.valid.anno;


import io.daff.valid.validator.MobilePhoneNumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机号验证注解
 *
 * @author daffupman
 * @since 2020/8/18
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobilePhoneNumValidator.class)
public @interface MobilePhoneNum {

    String message() default "请输入合法的手机号";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
