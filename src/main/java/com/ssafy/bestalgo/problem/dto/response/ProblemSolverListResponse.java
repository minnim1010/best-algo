package com.ssafy.bestalgo.problem.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ProblemSolverListResponse {
    private List<ProblemSolverResponse> solvers;

    public static class ProblemSolverResponse {
        private String name;
        private LocalDateTime submitAt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getSubmitAt() {
            return submitAt;
        }

        public void setSubmitAt(LocalDateTime submitAt) {
            this.submitAt = submitAt;
        }
    }

    public List<ProblemSolverResponse> getSolvers() {
        return solvers;
    }

    public void setSolvers(
            List<ProblemSolverResponse> solvers) {
        this.solvers = solvers;
    }
}
