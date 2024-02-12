package com.ssafy.bestalgo.domain.problem.entity;

import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_PROBLEM_CATEGORY_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_PROBLEM_NAME_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_PROBLEM_CATEGORY_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_PROBLEM_NAME_LENGTH;

import java.util.Objects;

final class Problems {

    private Problems() {
    }

    public static void checkValidName(String name) {
        if (Objects.requireNonNull(name).isBlank()) {
            throw new IllegalArgumentException(name + " is not a valid");
        }

        int len = name.length();
        if (len < MIN_PROBLEM_NAME_LENGTH || MAX_PROBLEM_NAME_LENGTH < len) {
            throw new IllegalArgumentException(
                    "The length of the problem name must be between 1 and 30 characters. : " + name.length());
        }
    }

    public static void checkValidCategory(String category) {
        if (Objects.requireNonNull(category).isBlank()) {
            throw new IllegalArgumentException(category + " is not a valid");
        }

        int len = category.length();
        if (len < MIN_PROBLEM_CATEGORY_LENGTH || MAX_PROBLEM_CATEGORY_LENGTH < len) {
            throw new IllegalArgumentException(
                    "The length of the category must be between 1 and 30 characters. : " + category.length());
        }
    }
}
