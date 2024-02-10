package com.ssafy.bestalgo.domain.problem.dto.persist;

public record ProblemSubmission(int id, String name, String date, Long submissions) {

    public ProblemSubmissionResponse toResponse() {
        return new ProblemSubmissionResponse(id, name, submissions);
    }

    public record ProblemSubmissionResponse(int id, String name, Long submissions) {
    }
}
