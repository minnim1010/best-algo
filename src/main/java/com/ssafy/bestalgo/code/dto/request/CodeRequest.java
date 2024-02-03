package com.ssafy.bestalgo.code.dto.request;

import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_CODE_CONTENT_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MAX_MEMBER_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_CODE_CONTENT_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.common.constraints.RequestValidation.MIN_MEMBER_PASSWORD_LENGTH;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeRequest(@Size(min = MIN_MEMBER_NAME_LENGTH, max = MAX_MEMBER_NAME_LENGTH)
                          @NotBlank String solver,
                          @Size(min = MIN_CODE_CONTENT_LENGTH, max = MAX_CODE_CONTENT_LENGTH)
                          @NotBlank String content,
                          @Size(min = MIN_MEMBER_PASSWORD_LENGTH, max = MAX_MEMBER_PASSWORD_LENGTH)
                          @NotBlank String password) {
}