package com.ssafy.bestalgo.domain.code.entity;

import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_CODE_CONTENT_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_CODE_CONTENT_LENGTH;

import com.ssafy.bestalgo.domain.member.entity.Member;
import com.ssafy.bestalgo.domain.problem.entity.Problem;
import java.util.Objects;

final class Codes {

    private Codes() {
    }

    public static void checkMemberNotNull(Member member) {
        Objects.requireNonNull(member);
    }

    public static void checkProblemNotNull(Problem problem) {
        Objects.requireNonNull(problem);
    }

    public static void checkValidContent(String content) {
        if (Objects.requireNonNull(content).isBlank()) {
            throw new IllegalArgumentException(content + " is not a valid");
        }

        int len = content.length();
        if (len < MIN_CODE_CONTENT_LENGTH || MAX_CODE_CONTENT_LENGTH < len) {
            throw new IllegalArgumentException(
                    "The length of the content must be between 1 and 20000 characters. : " + content.length());
        }
    }

    public static void checkValidCodeType(CodeType codeType) {
        Objects.requireNonNull(codeType);
    }
}
