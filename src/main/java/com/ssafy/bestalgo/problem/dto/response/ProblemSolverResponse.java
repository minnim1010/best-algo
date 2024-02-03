package com.ssafy.bestalgo.problem.dto.response;

import java.time.LocalDateTime;

public record ProblemSolverResponse(String name, LocalDateTime submitAt) {

    @Override
    public String toString() {
        return "ProblemSolverResponse{" +
                "name='" + name + '\'' +
                ", submitAt=" + submitAt +
                '}';
    }
}
