package com.ssafy.bestalgo.problem.dto.response;

import java.util.List;

public class ProblemSolverListResponse {
    private final List<ProblemSolverResponse> solvers;

    public ProblemSolverListResponse(List<ProblemSolverResponse> solvers) {
        this.solvers = solvers;
    }

    public List<ProblemSolverResponse> getSolvers() {
        return solvers;
    }
}
