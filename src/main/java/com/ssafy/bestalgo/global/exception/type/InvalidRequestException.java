package com.ssafy.bestalgo.global.exception.type;

public class InvalidRequestException extends BestAlgoException {

    public InvalidRequestException() {
        super("유효하지 않은 요청입니다.");
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
