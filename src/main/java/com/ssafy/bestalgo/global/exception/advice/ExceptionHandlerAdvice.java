package com.ssafy.bestalgo.global.exception.advice;

import com.ssafy.bestalgo.global.exception.response.ExceptionResponse;
import com.ssafy.bestalgo.global.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.global.exception.type.DataNotExistsYetException;
import com.ssafy.bestalgo.global.exception.type.DataNotFoundException;
import com.ssafy.bestalgo.global.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.global.exception.type.InvalidRequestException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {
        "com.ssafy.bestalgo.domain.problem.controller",
        "com.ssafy.bestalgo.domain.code.controller"})
public class ExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationFailException(Exception e) {
        logger.error("AuthenticationFailException:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicatedDataException(Exception e) {
        logger.error("DuplicatedDataException:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDataNotFoundException(Exception e) {
        logger.error("DataNotFoundException:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(Exception e) {
        logger.error("InvalidRequestException:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotExistsYetException.class)
    public ResponseEntity<ExceptionResponse> handleDataNotExistsYetException(Exception e) {
        logger.error("DataNotExistsYetException:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.NO_CONTENT.value(), e.getMessage()),
                HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException:", e);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.BAD_REQUEST.value(),
                errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        logger.error("Exception:", e);
        return new ResponseEntity<>(ExceptionResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "에러가 발생했습니다."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
