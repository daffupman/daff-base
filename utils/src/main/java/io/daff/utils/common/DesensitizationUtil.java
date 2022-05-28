package io.daff.utils.common;

import org.springframework.util.StringUtils;

/**
 * 脱敏工具类：可用于用户名，手机号，邮箱，地址等
 *
 * @author daffupman
 * @since 2020/8/22
 */
public class DesensitizationUtil {

    /**
     * 最大的脱敏长度
     */
    private static final int SIZE = 6;
    /**
     * 脱敏符号：使用指定符号来替换真实信息
     */
    private static final String REPLACE_SYMBOL = "*";

    /**
     * 通用脱敏方法
     */
    public static String desensitize(String str) {

        if (StringUtils.isEmpty(str)) {
            return str;
        }
        int len = str.length();
        int part_a = len / 2;
        int part_b = part_a - 1;
        int part_c = len % 2;
        StringBuilder ret = new StringBuilder();
        if (len <= 2) {
            if (part_c == 1) {
                return REPLACE_SYMBOL;
            }
            ret.append(REPLACE_SYMBOL);
            ret.append(str.charAt(len - 1));
        } else {
            if (part_b <= 0) {
                ret.append(str, 0, 1);
                ret.append(REPLACE_SYMBOL);
                ret.append(str, len - 1, len);
            } else if (part_b >= SIZE / 2 && SIZE + 1 != len) {
                int part_d = (len - SIZE) / 2;
                ret.append(str, 0, part_d);
                for (int i = 0; i < SIZE; i++) {
                    ret.append(REPLACE_SYMBOL);
                }
                if ((part_c == 0 && SIZE / 2 == 0) || (part_c != 0 && SIZE % 2 != 0)) {
                    ret.append(str, len - part_d, len);
                } else {
                    ret.append(str, len - (part_d + 1), len);
                }
            } else {
                int part_e = len - 2;
                ret.append(str, 0, 1);
                for (int i = 0; i < part_e; i++) {
                    ret.append(REPLACE_SYMBOL);
                }
                ret.append(str, len - 1, len);
            }
        }
        return ret.toString();
    }
}
