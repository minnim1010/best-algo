package com.ssafy.bestalgo.global.exception.type;

public class DuplicatedDataException extends BestAlgoException {

    public DuplicatedDataException() {
        super("데이터가 이미 존재합니다.");
    }

    public DuplicatedDataException(String message) {
        super(message);
    }

    public DuplicatedDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
