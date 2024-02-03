package com.ssafy.bestalgo.common.exception.type;

public class AuthenticationFailException extends BestAlgoException {

    public AuthenticationFailException() {
        super("인증에 실패했습니다. 비밀번호를 확인해주세요.");
    }

    public AuthenticationFailException(String message) {
        super(message);
    }
}
