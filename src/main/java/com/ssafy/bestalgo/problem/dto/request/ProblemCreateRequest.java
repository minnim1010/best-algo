package com.ssafy.bestalgo.problem.dto.request;

import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_ADMIN_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_PROBLEM_CATEGORY_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_PROBLEM_NAME_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_ADMIN_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_PROBLEM_CATEGORY_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_PROBLEM_NAME_LENGTH;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProblemCreateRequest(@Size(min = MIN_PROBLEM_NAME_LENGTH, max = MAX_PROBLEM_NAME_LENGTH)
                                   @NotBlank String name,
                                   @Size(min = MIN_PROBLEM_CATEGORY_LENGTH, max = MAX_PROBLEM_CATEGORY_LENGTH)
                                   @NotBlank String date,
                                   @Size(min = MIN_ADMIN_PASSWORD_LENGTH, max = MAX_ADMIN_PASSWORD_LENGTH)
                                   @NotBlank String password) {
}
