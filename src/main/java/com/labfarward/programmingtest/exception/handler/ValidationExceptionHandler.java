package com.labfarward.programmingtest.exception.handler;

import com.labfarward.programmingtest.constants.exception.ErrorType;
import com.labfarward.programmingtest.dto.ErrorResponseDto;
import com.labfarward.programmingtest.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler({UserException.class})
    public final ResponseEntity<ErrorResponseDto> handleUserException(UserException exception, HttpServletRequest request) {

        ErrorType errorType = exception.getErrorType();
        if (errorType == null) {
            errorType = ErrorType.UNKNOWN_ERROR;
        }
        ErrorResponseDto errorResponse = new ErrorResponseDto(errorType);
        return new ResponseEntity<>(errorResponse, errorType.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ErrorResponseDto> handleDefaultException(Exception exception, HttpServletRequest request) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(ErrorType.UNKNOWN_ERROR);

        log.error("Default Exception with Request: " + request + " & Error Response: " + errorResponse, exception);
        return new ResponseEntity<>(errorResponse, ErrorType.UNKNOWN_ERROR.getStatus());
    }
}
