package com.ssafy.bestalgo.code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeRequest(@Size(min = 1, max = 10) @NotBlank String solver,
                          @Size(min = 1, max = 20000) @NotBlank String code,
                          @Size(min = 1, max = 10) @NotBlank String password) {
}