package com.ssafy.bestalgo.common.exception.advice;

import com.ssafy.bestalgo.common.exception.response.ExceptionResponse;
import com.ssafy.bestalgo.common.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.common.exception.type.DuplicatedDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {
        "com.ssafy.bestalgo.problem.controller",
        "com.ssafy.bestalgo.code.controller"})
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationFailException(Exception e) {
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedDataException(Exception e) {
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
//        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
