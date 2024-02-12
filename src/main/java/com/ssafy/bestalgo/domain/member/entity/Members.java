package com.ssafy.bestalgo.domain.member.entity;

import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_MEMBER_NAME_LENGTH;

import java.util.Objects;

final class Members {
    private static final int SHA_256_ENCODED_LENGTH = 64;

    private Members() {
    }

    public static void checkValidName(String name) {
        if (Objects.requireNonNull(name).isBlank()) {
            throw new IllegalArgumentException(name + " is not a valid");
        }

        int len = name.length();
        if (len < MIN_MEMBER_NAME_LENGTH || MAX_MEMBER_NAME_LENGTH < len) {
            throw new IllegalArgumentException(
                    "The length of the name must be between 1 and 10 characters. : " + len);
        }
    }

    public static void checkValidPassword(String password) {
        if (Objects.requireNonNull(password).isBlank()) {
            throw new IllegalArgumentException(password + " is not a valid");
        }

        int len = password.length();
        if (len != SHA_256_ENCODED_LENGTH) {
            throw new IllegalArgumentException(
                    "Password is not sha-256 encoded." + len);
        }
    }
}
