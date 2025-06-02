package com.somee.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    public static void info(Object object) {
        logger.info(object);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void warn(String format, Object... arguments) {
        logger.warn(format, arguments);
    }

    public static void warn(Object object) {
        logger.warn(object);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }

    public static void error(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    public static void error(Object object) {
        logger.error(object);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void fatal(String message, Throwable t) {
        logger.fatal(message, t);
    }

    public static void fatal(String format, Object... arguments) {
        logger.fatal(format, arguments);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(String format, Object... arguments) {
        logger.debug(format, arguments);
    }

    public static void debug(Object object) {
        logger.debug(object);
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    public static void trace(Object object) {
        logger.trace(object);
    }
}