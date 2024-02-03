package com.ssafy.bestalgo.problem.dto.response;

import java.util.List;
import java.util.Map;

public record ProblemListResponse(List<Map<String, Object>> items) {
}