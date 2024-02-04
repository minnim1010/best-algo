package com.ssafy.bestalgo.common.exception.type;

public class DataNotExistsYetException extends BestAlgoException {

    public DataNotExistsYetException() {
        super("아직 데이터가 존재하지 않습니다.");
    }

    public DataNotExistsYetException(String message) {
        super(message);
    }
}
