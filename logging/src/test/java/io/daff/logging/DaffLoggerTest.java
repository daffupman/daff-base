package io.daff.logging;

import io.daff.logging.module.InnerModule;

/**
 * DaffLogger测试类
 *
 * @author daff
 * @since 2022/5/28
 */
public class DaffLoggerTest {

    private static final DaffLogger logger = DaffLogger.getLogger(DaffLoggerTest.class);
    private static final DaffLogger logger123 = DaffLogger.getLogger(String.class);

    public static void main(String[] args) {

        logger.error("hello, {}, {}", InnerModule.TEST, "wzj", 19);
        logger123.error("发生异常", InnerModule.TEST, new RuntimeException("fadsfdsa"));
    }
}
