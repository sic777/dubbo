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
    private static final LoggerHelper singleton = new LoggerHelper();

    private LoggerHelper() {

    }

    public static LoggerHelper instance() {
        return singleton;
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void debug(String format, Object... arguments) {
        logger.debug(format, arguments);

    }


    public void info(String format, Object... arguments) {
        logger.info(format, arguments);

    }


    public void warn(String format, Object... arguments) {
        logger.warn(format, arguments);

    }


    public void error(String msg, Throwable t) {
        logger.error(msg, t);

    }

    public void error(String format, Object... arguments) {
        logger.error(format, arguments);

    }

    public void error(Throwable t, String format, Object... arguments) {
        logger.error(format, arguments, t);

    }

}
