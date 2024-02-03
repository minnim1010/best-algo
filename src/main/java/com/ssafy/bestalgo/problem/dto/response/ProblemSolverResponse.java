package com.ssafy.bestalgo.problem.dto.response;

import java.time.LocalDateTime;

public class ProblemSolverResponse {
    private String name;
    private LocalDateTime submitAt;

    public ProblemSolverResponse(String name, LocalDateTime submitAt) {
        this.name = name;
        this.submitAt = submitAt;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getSubmitAt() {
        return submitAt;
    }

    @Override
    public String toString() {
        return "ProblemSolverResponse{" +
                "name='" + name + '\'' +
                ", submitAt=" + submitAt +
                '}';
    }
}
