package com.ssafy.bestalgo.common.exception.advice;

import com.ssafy.bestalgo.common.exception.response.ExceptionResponse;
import com.ssafy.bestalgo.common.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.common.exception.type.DataNotFoundException;
import com.ssafy.bestalgo.common.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.common.exception.type.InvalidRequestException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDataNotFoundException(Exception e) {
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(Exception e) {
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(),
                errors.toString()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
//        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
