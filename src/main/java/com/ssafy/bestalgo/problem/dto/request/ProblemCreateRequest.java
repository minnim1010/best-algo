package com.ssafy.bestalgo.problem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProblemCreateRequest(@Size(min = 1, max = 50) @NotBlank String name,
                                   @Size(min = 1, max = 30) @NotBlank String date,
                                   @Size(min = 1, max = 30) @NotBlank String password) {
}
