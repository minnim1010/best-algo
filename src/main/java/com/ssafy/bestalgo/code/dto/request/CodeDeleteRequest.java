package com.ssafy.bestalgo.code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeDeleteRequest(@Size(min = 1, max = 10) @NotBlank String solver,
                                @Size(min = 1, max = 10) @NotBlank String password) {
}