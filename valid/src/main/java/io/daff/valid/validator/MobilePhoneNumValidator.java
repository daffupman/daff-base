package io.daff.valid.validator;

import io.daff.valid.anno.MobilePhoneNum;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author daffupman
 * @since 2020/8/18
 */
public class MobilePhoneNumValidator implements ConstraintValidator<MobilePhoneNum, String> {

   public boolean isValid(String value, ConstraintValidatorContext context) {

      if (StringUtils.isEmpty(value)) {
         return true;
      }

      String mobilePhoneRegExp = "^1[3-9]\\d{9}$";
      Pattern pattern = Pattern.compile(mobilePhoneRegExp);
      return pattern.matcher(value).matches();
   }
}
