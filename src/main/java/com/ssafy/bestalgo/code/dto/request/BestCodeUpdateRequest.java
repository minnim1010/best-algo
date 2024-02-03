package com.ssafy.bestalgo.code.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BestCodeUpdateRequest(int problem,
                                    @Size(min = 1, max = 10) @NotBlank String solver,
                                    @NotBlank String type,
                                    @Size(min = 1, max = 30) @NotBlank String password) {
}
