package com.ssafy.bestalgo.problem.dto.persist;

import java.util.Map;

public record ProblemSubmission(int id, String name, Long submissions, String date) {

    public Map<String, Object> toMap() {
        return Map.of(
                "id", this.id,
                "name", this.name,
                "submissions", this.submissions
        );
    }
}
