package com.casestudy.rms.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

/** Error Detail POJO for showing custom error message.
 * 
 * @author Anand Tiwari */
public class ErrorDetail {

    private Date timestamp;
    private String code;
    private HttpStatus error;
    private String message;

    /** Parameterized Constructor.
     * 
     * @param timestamp
     *            time of the exception.
     * @param code
     *            HTTP Code of the exception.
     * @param error
     *            error occurred during exception.
     * @param message
     *            message to show for exception. */
    public ErrorDetail(Date timestamp, String code, HttpStatus error, String message) {
        this.timestamp = timestamp;
        this.code = code;
        this.error = error;
        this.message = message;
    }

    /** Getter for timestamp.
     * 
     * @return time of the exception. */
    public Date getTimestamp() {
        return timestamp;
    }

    /** Getter for code (HTTP Status Code).
     * 
     * @return HTTP Status Code of the exception. */
    public String getCode() {
        return code;
    }

    /** Getter for error.
     * 
     * @return error occurred during exception. */
    public HttpStatus getError() {
        return error;
    }

    /** Getter for message.
     * 
     * @return message to show for exception. */
    public String getMessage() {
        return message;
    }

}
