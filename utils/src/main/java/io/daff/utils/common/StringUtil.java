package io.daff.utils.common;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串辅助类
 *
 * @author daffupman
 * @since 2020/7/12
 */
public class StringUtil extends StringUtils {

    /**
     * 判断str是否是一个合法的数字
     */
    public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 生成默认长度的uuid
     */
    public static String uuid() {
        return uuid(32);
    }

    /**
     * 生成指定长度的uuid
     */
    public static String uuid(int length) {
        if (length > 0) {
            return UUID.randomUUID().toString()
                    .replaceAll("-", "")
                    .substring(0, length);
        }
        return null;
    }

    /**
     * 尝试字符转换成Integer类型，转换失败则返回0
     */
    public static int parseInt(String str) {
        str = str.trim();
        if (isEmpty(str)) {
            return 0;
        }
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return parseInt;
    }

    /**
     * 尝试字符转换成Long类型，转换失败则返回0L
     */
    public static long parseLong(String str) {
        str = str.trim();
        if (isEmpty(str)) {
            return 0;
        }
        long parseLong;
        try {
            parseLong = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        }
        return parseLong;
    }

    public static List<Integer> parseIntegerList(String strs) {
        strs = strs.trim();
        if (isEmpty(strs)) {
            return Collections.emptyList();
        }
        List<Integer> ints = new ArrayList<>();
        try {
            ints = Arrays.stream(strs.split(",")).map(each -> {
                each = each.trim();
                return isEmpty(each) ? null : Integer.parseInt(each);
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ints;
    }

    public static List<Long> parseLongList(String strs) {
        strs = strs.trim();
        if (isEmpty(strs)) {
            return Collections.emptyList();
        }
        List<Long> longs = new ArrayList<>();
        try {
            longs = Arrays.stream(strs.split(",")).map(each -> {
                each = each.trim();
                return isEmpty(each) ? null : Long.parseLong(each);
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longs;
    }
}
