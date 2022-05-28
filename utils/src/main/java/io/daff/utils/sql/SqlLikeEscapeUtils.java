package io.daff.utils.sql;

import org.springframework.util.StringUtils;

/**
 * 过滤mysql的 \、%、_ 特殊字符
 *
 * @author daffupman
 * @since 2020/7/19
 */
public class SqlLikeEscapeUtils {

    public static String escapeSpecialChar(String str) {
        return StringUtils.isEmpty(str) ?
                null :
                str.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("%", "\\\\%")
                .replaceAll("_", "\\\\_");
    }
}
