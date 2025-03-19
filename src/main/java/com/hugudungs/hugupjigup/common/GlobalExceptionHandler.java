package com.hugudungs.hugupjigup.common;

import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
     public ResponseEntity<ResponseDto<Void>> handleException(Exception e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationException(HandlerMethodValidationException e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
