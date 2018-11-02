package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception class for catching entity not found exception.
 * 
 * @author Anand Tiwari */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** Default Constructor. */
    public ResourceNotFoundException() {
        super();
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print. */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print.
     * @param throwable
     *            exception object. */
    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}