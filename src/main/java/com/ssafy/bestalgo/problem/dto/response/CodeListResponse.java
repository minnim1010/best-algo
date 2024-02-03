package com.ssafy.bestalgo.problem.dto.response;

import java.time.LocalDateTime;

public record CodeListResponse(Integer id, String name, LocalDateTime submitAt) {

    @Override
    public String toString() {
        return "CodeListResponse{" +
                "name='" + name + '\'' +
                ", submitAt=" + submitAt +
                '}';
    }
}
