package com.ssafy.bestalgo.code.dto.response;

import com.ssafy.bestalgo.code.entity.CodeType;
import java.time.LocalDateTime;

public class CodeResponse {
    private final int id;
    private final String solver;
    private final LocalDateTime submitAt;
    private final String content;
    private final String type;

    public CodeResponse(int id, String solver, LocalDateTime submitAt, String content, CodeType type) {
        this.id = id;
        this.solver = solver;
        this.submitAt = submitAt;
        this.content = content;
        this.type = type.getName();
    }

    public int getId() {
        return id;
    }

    public String getSolver() {
        return solver;
    }

    public LocalDateTime getSubmitAt() {
        return submitAt;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
