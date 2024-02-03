package com.ssafy.bestalgo.common.exception.response;

public class ExceptionResponse {
    private int status;
    private String message;

    public ExceptionResponse() {
    }

    private ExceptionResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ExceptionResponse of(int status, String message) {
        return new ExceptionResponse(status, message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
