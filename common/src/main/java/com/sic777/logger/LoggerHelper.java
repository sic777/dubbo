package com.sic777.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>logger utils
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */

public class LoggerHelper {
    private static final Logger logger = LoggerFactory.getLogger(LoggerHelper.class);

    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void warn(String msg) {
        logger.warn(msg);
    }

    public static void debug(String format, Object... arguments) {
        logger.debug(format, arguments);

    }


    public static void info(String format, Object... arguments) {
        logger.info(format, arguments);

    }


    public static void warn(String format, Object... arguments) {
        logger.warn(format, arguments);

    }


    public static void error(String msg, Throwable t) {
        logger.error(msg, t);

    }

    public static void error(String format, Object... arguments) {
        logger.error(format, arguments);

    }

    public static void error(Throwable t, String format, Object... arguments) {
        logger.error(format, arguments, t);

    }

}
