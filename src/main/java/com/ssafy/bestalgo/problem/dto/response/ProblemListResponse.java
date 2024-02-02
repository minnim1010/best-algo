package com.ssafy.bestalgo.problem.dto.response;

import java.util.List;

public class ProblemListResponse {
    private List<ProblemSubmissionListResponse> items;

    public List<ProblemSubmissionListResponse> getItems() {
        return items;
    }

    public void setItems(List<ProblemSubmissionListResponse> items) {
        this.items = items;
    }
}