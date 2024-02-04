package com.ssafy.bestalgo.common.exception.type;

public class BestAlgoException extends RuntimeException {

    public BestAlgoException(String message) {
        super(message);
    }

    public BestAlgoException(String message, Throwable cause) {
        super(message, cause);
    }
}
