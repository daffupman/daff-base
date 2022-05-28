package io.daff.valid.validator;

import io.daff.utils.common.StringUtil;
import io.daff.valid.anno.Limit;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 输入值限制注解校验器
 *
 * @see io.daff.valid.anno.Limit 输入值限制注解
 * @author daffupman
 * @since 2020/7/12
 */
public class LimitValidator implements ConstraintValidator<Limit, String> {

    /**
     * 允许的值
     */
    private List<String> allowValues;

    /**
     * 验证的对象
     */
    private Class<?> clazz;

    public void initialize(Limit constraint) {
        // 把定义在注解的值赋值给values变量
       final String[] values = constraint.value();
       this.allowValues = values.length == 0 ? new ArrayList<>() : Arrays.asList(values);
       this.clazz = constraint.clazz();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        if(String.class == clazz) {
            return allowValues.stream().anyMatch(each -> each.equals(value));
        } else if(Integer.class == clazz) {
            if (StringUtil.isInteger(value)) {
                List<Integer> digits = allowValues.stream().map(Integer::valueOf).collect(Collectors.toList());
                return digits.contains(Integer.valueOf(value));
            }
        }
        return false;
    }
}
