/*
 * Copyright (C) 2016 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A logger utility class that adds the ability to log and return values as well as present a var args array to log vs creating an
 * object array to log multiple things. As an example, one typically will do this for a multi output log:
 *
 * <code>
 * public static void doSomething() {
 *     logger.log(Level.FINE, "This is some output {0} that include this {1} and that {2}", new Object[] {someVal0, someVal1, someVal2});
 * }
 * </code>
 *
 * A better (simpler notation) is to use var args like so:
 *
 * <code>
 * public static void doSomething() {
 *     LoggerUtils.log(logger, Level.FINE, "This is some output {0} that include this {1} and that {2}", someVal0, someVal1, someVal2);
 * }
 * </code>
 *
 * Additional functionality contained in this class allows for log and return a value. Useful in places where a computation as a
 * return value and that computation method is solely a one line return statement. As an example:
 *
 * <code>
 * public static Foo computeVal() {
 *     return someVal + someOtherVal;
 * }
 * </code>
 *
 * To log, we may need to do this:
 *
 * <code>
 * public static Foo computeVal() {
 *     Foo retVal = someVal + someOtherVal;
 *
 *     getLogger().log(Level.FINE, "The return value = {0}", retVal);
 *
 *     return retVal;
 * }
 * </code>
 *
 * Using this utility logger class, we can simplify this by doing:
 *
 * <code>
 * public static Foo computeVal() {
 *     return LoggerUtils.logAndReturn(getLogger(), Level.FINE, "The return value = {0}", someVal + someOtherVal);
 * }
 * </code>
 *
 * @author Scot P. Floess
 */
public final class LoggerUtil {
    /**
     * Performs a log using the var args <code>objs</code> as an array that can be presented to the logger.
     *
     * @param logger    the logger to use.
     * @param level     the level of the log.
     * @param throwable the throwable to log with <code>str</code>.
     * @param str       the format string.
     * @param objs      a var arg thats converted to an object array for logging.
     */
    public static void log(final Logger logger, final Level level, final Throwable throwable, final String str, final Object... objs) {
        final StringWriter sw = new StringWriter();
        try (final PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            final String toLog = StringUtil.concat(str, System.getProperty("line.separator"), sw.toString());
            logger.log(level, toLog, objs);
        }
    }

    /**
     * Performs a log using the var args <code>objs</code> as an array that can be presented to the logger.
     *
     * @param logger    the logger to use.
     * @param level     the level of the log.
     * @param throwable the throwable to log with <code>str</code>.
     * @param str       the format string.
     */
    public static void log(final Logger logger, final Level level, final Throwable throwable, final String str) {
        logger.log(level, str, throwable);
    }

    /**
     * Performs a log using the var args <code>objs</code> as an array that can be presented to the logger.
     *
     * @param logger the logger to use.
     * @param level  the level of the log.
     * @param str    the format string.
     * @param objs   a var arg thats converted to an object array for logging.
     */
    public static void log(final Logger logger, final Level level, final String str, final Object... objs) {
        logger.log(level, str, objs);
    }

    /**
     * Log and return the value.
     *
     * @param <V>    the type of data to return.
     *
     * @param logger the logger to use.
     * @param level  the level to log at.
     * @param str    the log string.
     * @param retVal the value to return.
     *
     * @return the object logged.
     */
    public static <V> V logAndReturn(final Logger logger, final Level level, final String str, final V retVal) {
        logger.log(level, str, retVal);

        return retVal;
    }

    /**
     * Log and return the value thats found at <code>index</code> in the var arg <code>objs</code>.
     *
     * @param <V>    the type to return.
     *
     * @param logger the logger to use.
     * @param level  the level of the log.
     * @param str    the format string.
     * @param index  the index into <code>objs</code> that is the return value.
     * @param objs   a var arg thats converted to an object array for logging.
     *
     * @return the value found at index <code>index</code> in the var args <code>objs</code>.
     */
    public static <V> V logAndReturnByIndex(final Logger logger, final Level level, final String str, int index, final Object... objs) {
        log(logger, level, str, objs);

        return (V) objs[index];
    }

    /**
     * Log and return the value at found as the 0th index in the var args <code>objs</code>.
     *
     * @param <V>    the type to return.
     *
     * @param logger the logger to use.
     * @param level  the level of the log.
     * @param str    the format string.
     * @param objs   a var arg thats converted to an object array for logging.
     *
     * @return the value found at 0th index in the var args <code>objs</code>.
     */
    public static <V> V logAndReturn(final Logger logger, final Level level, final String str, final Object... objs) {
        return logAndReturnByIndex(logger, level, str, 0, objs);
    }

    /**
     * Default constructor not allowed.
     */
    private LoggerUtil() {
    }
}
