package com.ssafy.bestalgo.code.dto.request;

import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_MEMBER_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_MEMBER_PASSWORD_LENGTH;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeDeleteRequest(@Size(min = MIN_MEMBER_NAME_LENGTH, max = MAX_MEMBER_NAME_LENGTH)
                                @NotBlank String solver,
                                @Size(min = MIN_MEMBER_PASSWORD_LENGTH, max = MAX_MEMBER_PASSWORD_LENGTH)
                                @NotBlank String password) {
}