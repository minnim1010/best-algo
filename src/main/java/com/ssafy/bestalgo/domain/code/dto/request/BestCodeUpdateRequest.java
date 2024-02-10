package com.ssafy.bestalgo.domain.code.dto.request;

import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_ADMIN_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MAX_CODE_TYPE_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_ADMIN_PASSWORD_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_CODE_TYPE_LENGTH;
import static com.ssafy.bestalgo.global.constraints.RequestValidation.MIN_ID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BestCodeUpdateRequest(@Min(MIN_ID) int problem,
                                    @Min(MIN_ID) int code,
                                    @Size(min = MIN_CODE_TYPE_LENGTH, max = MAX_CODE_TYPE_LENGTH)
                                    @NotBlank String type,
                                    @Size(min = MIN_ADMIN_PASSWORD_LENGTH, max = MAX_ADMIN_PASSWORD_LENGTH)
                                    @NotBlank String password) {
}
