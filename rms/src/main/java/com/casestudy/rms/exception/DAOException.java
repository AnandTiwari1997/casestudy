package com.casestudy.rms.exception;

/** Exception class for wrapping all exception occurring at DAO layer.
 * 
 * @author Anand Tiwari */
public class DAOException extends Exception {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** Default Constructor. */
    public DAOException() {
        super();
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print. */
    public DAOException(String message) {
        super(message);
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print.
     * @param throwable
     *            exception object. */
    public DAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}