package io.daff.valid.anno;


import io.daff.valid.validator.LimitValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 参数限制注解
 *
 * <p>参数必须是指定的类型，否则报错，目前仅支持String类型和Integer类型的校验</p>
 *
 * @see io.daff.valid.validator.LimitValidator 参数限制注解检验器
 * @author daffupman
 * @since 2020/7/12
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LimitValidator.class)
public @interface Limit {

    String[] value() default {};
    String message() default "输入的值不再范围内";
    Class<?> clazz() default String.class;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
