package com.org.entportal.controller;

import com.org.entportal.common.ResponseUtil;
import com.org.entportal.common.ServiceResponse;
import com.org.entportal.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//defining exception handling for all the exceptions
@ControllerAdvice
@RestController
@CrossOrigin(origins = {"http://172.18.62.63:7060"})
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ResponseUtil<Object> responseUtil;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ServiceResponse<Object>> handleAllExceptions(Exception ex) {
        logger.debug("handleAllExceptions");
        ex.printStackTrace();

        if (ex instanceof CustomException) {
            return responseUtil.buildResponseError(HttpStatus.BAD_GATEWAY, ex.getMessage(), false);
        }

        return responseUtil.buildResponseError(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, ex.getMessage(), false);

    }
}