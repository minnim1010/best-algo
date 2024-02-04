package com.ssafy.bestalgo.common.exception.type;

public class DataNotFoundException extends BestAlgoException {

    public DataNotFoundException() {
        super("데이터를 찾지 못했습니다.");
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
