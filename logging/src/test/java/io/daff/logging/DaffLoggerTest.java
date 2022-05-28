package io.daff.logging;

import io.daff.logging.module.InnerModule;

/**
 * DaffLogger测试类
 *
 * @author daff
 * @since 2022/5/28
 */
public class DaffLoggerTest {

    public static void main(String[] args) {
        DaffLogger logger = DaffLogger.getLogger(DaffLoggerTest.class);
        logger.error("hello, {}, {}", InnerModule.TEST, "wzj", 19);
        logger.error("发生异常", InnerModule.TEST, new RuntimeException("fadsfdsa"));
    }
}
