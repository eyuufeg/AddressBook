package com.ericsson.javatraining.exception;

/**
 * The AddressBookException class extends Exception
 * 
 * @author eyuufeg
 * 
 */
public class AddressBookException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4261587148978751734L;

    /**
     * Default constructor
     */
    public AddressBookException() {
        super();
    }

    /**
     * 
     * @param message
     *            the detail message.
     */
    public AddressBookException(String message) {
        super(message);
    }

    /**
     * Constructor with the specified detail message and cause.
     * 
     * @param message
     *            the detail message.
     * @param cause
     *            the cause
     */
    public AddressBookException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with the specified cause
     * 
     * @param cause
     *            the cause
     */
    public AddressBookException(Throwable cause) {
        super(cause);
    }

}
