package com.casestudy.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception class for wrapping null pointer exception.
 * 
 * @author Anand Tiwari */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class URIPathException extends NullPointerException {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** Default Constructor. */
    public URIPathException() {
        super();
    }

    /** Parameterized Constructor.
     * 
     * @param message
     *            exception message to print. */
    public URIPathException(String message) {
        super(message);
    }
}