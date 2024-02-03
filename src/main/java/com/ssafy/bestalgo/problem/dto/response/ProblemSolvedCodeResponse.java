package com.ssafy.bestalgo.problem.dto.response;

import java.time.LocalDateTime;

public record ProblemSolvedCodeResponse(Integer id, String name, LocalDateTime submitAt) {

    @Override
    public String toString() {
        return "ProblemSolvedCodeResponse{" +
                "name='" + name + '\'' +
                ", submitAt=" + submitAt +
                '}';
    }
}
