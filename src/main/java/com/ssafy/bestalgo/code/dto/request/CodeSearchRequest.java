package com.ssafy.bestalgo.code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CodeSearchRequest(int problem,
                                @Size(min = 1, max = 10) @NotBlank String solver,
                                @Size(min = 1, max = 10) @NotBlank String type) {
}