package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception class for catching entity already exist exception.
 * 
 * @author Anand Tiwari */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceConflictException extends Exception {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** Default Constructor. */
    public ResourceConflictException() {
        super();
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print. */
    public ResourceConflictException(String message) {
        super(message);
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print.
     * @param throwable
     *            exception object. */
    public ResourceConflictException(String message, Throwable throwable) {
        super(message, throwable);
    }
}