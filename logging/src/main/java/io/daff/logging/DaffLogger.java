package io.daff.logging;

import io.daff.logging.module.Module;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.apache.logging.log4j.util.Supplier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志
 *
 * @author daff
 * @since 2022/5/28
 */
public class DaffLogger {

    private static final String LOG4j2_FORMAT = "{}: ";
    private static final String LOG4j2_FORMAT_STR = "[%s]: ";
    private static final Map<String, Logger> LOGGER_MAP = new ConcurrentHashMap<>();
    private final Logger logger;

    private DaffLogger(String name) {
        logger = getLogger(name);
    }

    public static DaffLogger getLogger(Class<?> clz) {
        Assert.requireNonEmpty(clz, "clz is empty");
        return new DaffLogger(clz.getName());
    }

    private Logger getLogger(String name) {
        Logger logger = LOGGER_MAP.get(name);
        if (logger == null) {
            logger = LogManager.getLogger(name);
            LOGGER_MAP.put(name, logger);
        }
        return logger;
    }

    /**
     * 打印debug正常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param args      日志内容参数
     */
    public void debug(String msgFormat, Module module, Object... args) {
        msgFormat = LOG4j2_FORMAT + msgFormat;
        logger.debug(msgFormat, module::code, () -> args);
    }

    /**
     * 打印debug异常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param t         异常信息
     */
    public void debug(String msgFormat, Module module, Throwable t) {
        msgFormat = LOG4j2_FORMAT_STR + msgFormat;
        logger.debug(String.format(msgFormat, module.code()), t);
    }

    /**
     * 打印info正常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param args      日志内容参数
     */
    public void info(String msgFormat, Module module, Object... args) {
        msgFormat = LOG4j2_FORMAT + msgFormat;
        logger.info(msgFormat, combineSuppliers(module, args));
    }

    /**
     * 打印info异常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param t         异常信息
     */
    public void info(String msgFormat, Module module, Throwable t) {
        msgFormat = LOG4j2_FORMAT_STR + msgFormat;
        logger.info(String.format(msgFormat, module.code()), t);
    }

    /**
     * 打印warn正常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param args      日志内容参数
     */
    public void warn(String msgFormat, Module module, Object... args) {
        msgFormat = LOG4j2_FORMAT + msgFormat;
        logger.info(msgFormat, combineSuppliers(module, args));
    }

    /**
     * 打印warn异常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param t         异常信息
     */
    public void warn(String msgFormat, Module module, Throwable t) {
        msgFormat = LOG4j2_FORMAT_STR + msgFormat;
        logger.info(String.format(msgFormat, module.code()), t);
    }

    /**
     * 打印error正常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param args      日志内容参数
     */
    public void error(String msgFormat, Module module, Object... args) {
        msgFormat = LOG4j2_FORMAT + msgFormat;
        logger.error(msgFormat, combineSuppliers(module, args));
    }

    /**
     * 打印error异常日志
     * @param msgFormat 日志格式
     * @param module    module
     * @param t         异常信息
     */
    public void error(String msgFormat, Module module, Throwable t) {
        msgFormat = LOG4j2_FORMAT_STR + msgFormat;
        logger.error(String.format(msgFormat, module.code()), t);
    }

    private Supplier<?>[] combineSuppliers(Module module, Object... args) {
        Supplier<?>[] suppliers = new Supplier[args.length + 1];
        suppliers[0] = () -> new Object[]{module.code()};
        for (int i = 0; i < args.length; i++) {
            int finalI = i;
            suppliers[i + 1] = () -> args[finalI];
        }
        return suppliers;
    }
}
