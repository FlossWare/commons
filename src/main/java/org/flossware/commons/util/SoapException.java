
package org.flossware.commons.util;

/**
 *
 * @author sfloess
 */
public class SoapException extends RuntimeException {
    /**
     * Default constructor.
     */
    public SoapException() {
    }

    /**
     * Sets the message.
     *
     * @param message detail message.
     */
    public SoapException(final String message) {
        super(message);
    }

    /**
     * Sets the cause of why self is being raised.
     *
     * @param cause the cause of why self is being raised.
     */
    public SoapException(final Throwable cause) {
        super(cause);
    }

    /**
     * Sets the cause of why self is raised and a message about it.
     *
     * @param message detail message.
     * @param cause   the cause of why self is being raised.
     */
    public SoapException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
