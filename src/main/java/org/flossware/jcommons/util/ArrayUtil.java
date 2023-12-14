/*
 * Copyright (C) 2016 flossware
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
package org.flossware.jcommons.util;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Array utility class.
 *
 * @author flossware
 */
public class ArrayUtil {
    /**
     * Default minimum size for an array.
     */
    public static final int DEFAULT_MIN_ARRAY_LENGTH = 1;

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ArrayUtil.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>       the type of array.
     *
     * @param values    represents the array to ensureObject there are value or has at least one element.
     * @param minLength the minimum number of elements in the array.
     * @param errorMsg  is the message of the IllegalArgumentException if raised.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final int minLength, final String errorMsg) throws IllegalArgumentException {
        Objects.requireNonNull(values, errorMsg);

        if (values.length < minLength) {
            throw new IllegalArgumentException(errorMsg);
        }

        for (final V value : values) {
            Objects.requireNonNull(value, errorMsg);
        }

        return values;
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>       the type of array.
     *
     * @param values    represents the array to ensureObject there are value or has at least one element.
     * @param minLength the minimum number of elements in the array.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final int minLength) throws IllegalArgumentException {
        return ensureArray(values, minLength, ObjectUtil.DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>      the type for the elements of the array.
     *
     * @param values   represents the array to ensureObject there are value or has at least one element.
     * @param errorMsg is the message of the IllegalArgumentException if raised.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final String errorMsg) throws IllegalArgumentException {
        return ensureArray(values, DEFAULT_MIN_ARRAY_LENGTH, errorMsg);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>    the type for the elements of the array.
     *
     * @param values represents the array to ensureObject there are value or has at least one element.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values) throws IllegalArgumentException {
        return ensureArray(values, ObjectUtil.DEFAULT_ERROR_MSG);
    }

    /**
     * Default constructor not allowed
     */
    private ArrayUtil() {
    }
}
