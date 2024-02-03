package com.ssafy.bestalgo.common.exception.type;

public class DuplicatedDataException extends BestAlgoException {

    public DuplicatedDataException() {
        super("데이터가 이미 존재합니다.");
    }

    public DuplicatedDataException(String message) {
        super(message);
    }
}
