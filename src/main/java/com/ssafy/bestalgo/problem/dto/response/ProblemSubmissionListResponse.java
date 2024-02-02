package com.ssafy.bestalgo.problem.dto.response;

import java.util.List;

public class ProblemSubmissionListResponse {
    private String date;
    private List<ProblemSubmissionResponse> problems;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProblemSubmissionResponse> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemSubmissionResponse> problems) {
        this.problems = problems;
    }
}
