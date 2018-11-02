package com.casestudy.rms.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.dto.ErrorDetail;

/**
 * This is a custom exception handler.
 * @author anand.tiwari
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    /**
     * This will handle all exception that are instance of URIPathException.
     * @param ex object of URIPathException.
     * @param wb WebEequest object.
     * @return responseEntity object.
     */
    @ExceptionHandler(value=URIPathException.class)
    public final ResponseEntity<ErrorDetail> handleNullValueException(URIPathException ex, WebRequest wb) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * This will handle all exception that are instance of ResourceNotFoundException.
     * @param ex object of ResourceNotFoundException.
     * @param wb WebEequest object.
     * @return responseEntity object.
     */
    @ExceptionHandler(value=ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest wb) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
    }
    
    /**
     * This will handle all exception that are instance of UsernameNotFoundException.
     * @param ex object of ResourceNotFoundException.
     * @param wb WebEequest object.
     * @return responseEntity object.
     */
    @ExceptionHandler(value=UsernameNotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleUsernameNotFoundEsception(UsernameNotFoundException ex, WebRequest wb) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.UNAUTHORIZED.toString(), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.UNAUTHORIZED);
    }
}
