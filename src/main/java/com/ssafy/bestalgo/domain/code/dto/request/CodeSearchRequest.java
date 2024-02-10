package com.ssafy.bestalgo.domain.code.dto.request;

import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_CODE_TYPE_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_MEMBER_NAME_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_CODE_TYPE_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_ID;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_MEMBER_NAME_LENGTH;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeSearchRequest(@Min(MIN_ID) int problem,
                                @Size(min = MIN_MEMBER_NAME_LENGTH, max = MAX_MEMBER_NAME_LENGTH)
                                @NotBlank String solver,
                                @Size(min = MIN_CODE_TYPE_LENGTH, max = MAX_CODE_TYPE_LENGTH)
                                @NotBlank String type) {
}